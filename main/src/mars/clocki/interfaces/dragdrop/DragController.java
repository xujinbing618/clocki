/*
 * This is a modified version of a class from the Android
 * Open Source Project. The original copyright and license information follows.
 *
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class handles all touch events associated with a user dragging a view
 * around inside a ViewGroup.
 * When a drag starts, it creates a special view (a DragView) that moves around
 * the screen until the user ends the drag. It interacts with other objects
 * through methods defined in the DropTarget and DragSource interfaces.
 *
 * <p> It also supports the DragListener interface for objects that want to
 * be notified when objects are dragged and dropped.
 */

package mars.clocki.interfaces.dragdrop;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * This class handles all touch events associated with a user
 * dragging a view around inside a ViewGroup.
 * When a drag starts, it creates a special view (a DragView) that moves around the screen
 * until the user ends the drag. As feedback to the user, this object causes the device to
 * vibrate as the drag begins. It interacts with other objects through methods defined
 * in the DropTarget and DargSource interfaces.
 *
 * <p> It also supports the DragListener interface for objects that want to be
 * notified when objects are dragged and dropped.
 */
public class DragController {

  /** Indicates the drag is a move. */
  public static int DRAG_ACTION_MOVE = 0;

  /** Indicates the drag is a copy. */
  public static     int DRAG_ACTION_COPY = 1;

  public static final int VIBRATE_DURATION = 35;

  private static final boolean PROFILE_DRAWING_DURING_DRAG = false;

  private Context context;
  private Vibrator vibrator;

  // temporaries to avoid gc thrash
  private Rect rectTemp = new Rect();
  private final int[] coordinatesTemp = new int[2];

  /** Whether or not we're dragging. */
  private boolean dragging;

  /** X coordinate of the down event. */
  private float motionDownX;

  /** Y coordinate of the down event.*/
  private float motionDownY;

  /** Info about the screen for clamping. */
  private DisplayMetrics displayMetrics = new DisplayMetrics();

  /** Original view that is being dragged. */
  private View originator;

  /** X offset from the upper-left corner of the cell the where we touched. */
  private float touchOffsetX;

  /** Y offset from the upper-left corner of the cell the where we touchec. */
  private float touchOffsetY;

  /** Where the drag originated */
  private DragSource dragSource;

  /** The data associated with the object being dragged */
  private Object dragInfo;

  /** The view that moves around while you drag. */
  private DragView dragView;

  /** Who can receive drop events */
  private ArrayList<DropTarget> dropTargets = new ArrayList<DropTarget>();

  private DragListener dragListener;

  /** The window token used as the parent for the DragView. */
  private IBinder windowToken;

  private View moveTarget;

  private DropTarget lastDropTarget;

  private InputMethodManager inputMethodManager;

  private DragDropPresenter presenter;

  /**
   * Interface to receive notifications when a drag starts or stops
   */
  interface DragListener {

    /**
     * A drag has begun
     *
     * @param source An object representing where the drag originated
     * @param info The data associated with the object that is being dragged
     * @param dragAction The drag action: either {@link DragController#DRAG_ACTION_MOVE}
     */
    public void onDragStart(DragSource source, Object info, int dragAction);

    /**
     * The drag has ended
     */
    public void onDragEnd();
  }

  public DragController(Context context, DragDropPresenter dragDropPresenter) {
    this.context = context;
    this.presenter = dragDropPresenter;
    vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
  }

  /**
   * Starts a drag.
   * It creates a bitmap of the view being dragged. That bitmap is what you see moving.
   * The actual view can be repositioned if that is what the onDrop handle choose to do.
   *
   * @param v The view that is being dragged
   * @param source An object representing where the drag originated
   * @param dragInfo The data associated with the object that is being dragged
   * @param dragAction The drag action: either {@link #DRAG_ACTION_MOVE} or
   *                                           {@link #DRAG_ACTION_COPY}
   */
  public void startDrag(View v, DragSource source, Object dragInfo, int dragAction, boolean vibrateIt) {
    // Start dragging, but only if the source has something to drag.
    if (!source.allowDrag()) {
      return;
    }
    originator = v;

    Bitmap b = getViewBitmap(v);
    if (b == null) {
      // out of memory?
      return;
    }

    int[] loc = coordinatesTemp;
    v.getLocationOnScreen(loc);
    int screenX = loc[0];
    int screenY = loc[1];

    startDrag(b, screenX, screenY, 0, 0, v.getWidth(), v.getHeight(),
              source, dragInfo, dragAction, vibrateIt);
    b.recycle();
    if (dragAction == DRAG_ACTION_MOVE) {
      v.setVisibility(View.GONE);
    }
  }

  /**
   * Starts a drag.
   *
   * @param b The bitmap to display as the drag image. It will be re-scaled to the
   *          enlarged size.
   * @param screenX The x position on screen of the left-top of the bitmap.
   * @param screenY The y position on screen of the left-top of the bitmap.
   * @param textureLeft The left edge of the region inside b to use.
   * @param textureTop The top edge of the region inside b to use.
   * @param textureWidth The width of the region inside b to use.
   * @param textureHeight The height of the region inside b to use.
   * @param source An object representing where the drag originated
   * @param dInfo The data associated with the object that is being dragged
   * @param dragAction The drag action: either {@link #DRAG_ACTION_MOVE} or
   *                                           {@link #DRAG_ACTION_COPY}
   */
  private void startDrag(Bitmap b, int screenX, int screenY, int textureLeft, int textureTop,
      int textureWidth, int textureHeight, DragSource source, Object dInfo, int dragAction, boolean vibrateIt) {
    if (PROFILE_DRAWING_DURING_DRAG) {
      android.os.Debug.startMethodTracing("Launcher");
    }

    // Hide soft keyboard, if visible
    if (inputMethodManager == null) {
      inputMethodManager = (InputMethodManager)
                           context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0);

    if (dragListener != null) {
      dragListener.onDragStart(source, dInfo, dragAction);
    }

    int registerationX = ((int) motionDownX) - screenX;
    int registerationY = ((int) motionDownY) - screenY;

    touchOffsetX = motionDownX - screenX;
    touchOffsetY = motionDownY - screenY;

    dragging = true;
    dragSource = source;
    dragInfo = dInfo;

    if (vibrateIt) {
      vibrator.vibrate(VIBRATE_DURATION);
    }
    DragView dv = dragView = new DragView(context, b, registerationX, registerationY,
                                          textureLeft, textureTop, textureWidth, textureHeight);
    dv.show(windowToken, (int) motionDownX, (int) motionDownY);
  }

  private Bitmap getViewBitmap(View v) {
    v.clearFocus();
    v.setPressed(false);

    boolean willNotCache = v.willNotCacheDrawing();
    v.setWillNotCacheDrawing(false);

    // Reset the drawing cache background color to fully transparent
    // for the durationn of this operation
    int color = v.getDrawingCacheBackgroundColor();
    v.setDrawingCacheBackgroundColor(0);

    if (color != 0) {
      v.destroyDrawingCache();
    }
    v.buildDrawingCache();
    Bitmap cacheBitmap = v.getDrawingCache();
    if (cacheBitmap == null) {
      Log.d("DD", "failed getViewBitmap(" + v + ")", new RuntimeException());
      return null;
    }

    Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

    // Restore the view
    v.destroyDrawingCache();
    v.setWillNotCacheDrawing(willNotCache);
    v.setDrawingCacheBackgroundColor(color);

    return bitmap;
  }

  /**
   * Call this from a drag source view like this:
   *
   * <pre>
   * @Override
   * public boolean dispatchKeyEvent(KeyEvent event) {
   *   return dragController.dispatchKeyEvent(this, event) ||
   *          super.dispatchKeyEvent(event);
   * }
   * </pre>

   */
  public boolean dispatchKeyEvent(KeyEvent event) {
    return dragging;
  }

  /**
   * Stop dragging without dropping.
   */
  public void cancelDrag() {
    endDrag();
  }

  private void endDrag() {
    if (dragging) {
      dragging = false;
      Log.d("DD", "Drag ended for " + originator + " and " + lastDropTarget);
      if (originator != null) {
        originator.setVisibility(View.VISIBLE);
      }
      if (dragListener != null) {
        dragListener.onDragEnd();
      }
      if (dragView != null) {
        dragView.remove();
        dragView = null;
      }
    }
  }

  /**
   * Call this from a drag source view.
   */
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    final int action = ev.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      recordScreenSize();
    }

    final int screenX = clamp((int) ev.getRawX(), 0, displayMetrics.widthPixels);
    final int screenY = clamp((int) ev.getRawY(), 0, displayMetrics.heightPixels);

    switch(action) {
    case MotionEvent.ACTION_MOVE:
      break;
    case MotionEvent.ACTION_DOWN:
      // Remember location of down touch
      motionDownX = screenX;
      motionDownY = screenY;
      lastDropTarget = null;
      break;
    case MotionEvent.ACTION_CANCEL:
    case MotionEvent.ACTION_UP:
      if (dragging) {
        drop(screenX, screenY);
      }
      endDrag();
      break;
    }

    return dragging;
  }

  /**
   * Sets the view that should handle move events.
   */
  void setMoveTarget(View view) {
    moveTarget = view;
  }

  public boolean dispatchUnhandledMove(View focused, int direction) {
    return moveTarget != null && moveTarget.dispatchUnhandledMove(focused, direction);
  }

  public boolean onTouchEvent(MotionEvent ev) {
    if (!dragging) {
      return false;
    }

    final int action = ev.getAction();
    final int screenX = clamp((int) ev.getRawX(), 0, displayMetrics.widthPixels);
    final int screenY = clamp((int) ev.getRawY(), 0, displayMetrics.heightPixels);

    switch(action) {
    case MotionEvent.ACTION_DOWN:
      // Remember where the motion event started
      motionDownX = screenX;
      motionDownY = screenY;
      break;
    case MotionEvent.ACTION_MOVE:
      // Update the drag view. Don't use the clamped pos here so the dragging looks
      // like it goes off screen a little, instead of bumping up against the edge.
      dragView.move((int) ev.getRawX(), (int) ev.getRawY());
      // Drop on someone?
      final int[] coordinates = coordinatesTemp;
      DropTarget dropTarget = findDropTarget(screenX, screenY, coordinates);
      if (dropTarget != null) {
        if (lastDropTarget == dropTarget) {
          dropTarget.onDragOver(dragSource, coordinates[0], coordinates[1],
                                (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
        }
        else {
          if (lastDropTarget != null) {
            lastDropTarget.onDragExit(dragSource, coordinates[0], coordinates[1],
                                      (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
          }
          dropTarget.onDragEnter(dragSource, coordinates[0], coordinates[1],
                                 (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
        }
      }
      else {
        if (lastDropTarget != null) {
          lastDropTarget.onDragExit(dragSource, coordinates[0], coordinates[1],
                                    (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
        }
      }
      lastDropTarget = dropTarget;
      /* The original Launcher activity supports a delete region and scrolling.
         It is not needed in this example */
      break;
    case MotionEvent.ACTION_UP:
      if (dragging) {
        drop(screenX, screenY);
      }
      endDrag();
      break;
    case MotionEvent.ACTION_CANCEL:
      cancelDrag();
    }
    return true;
  }

  private boolean drop(float x, float y) {
    final int[] coordinates = coordinatesTemp;
    DropTarget dropTarget = findDropTarget((int) x, (int) y, coordinates);

    if (dropTarget != null) {
      dropTarget.onDragExit(dragSource, coordinates[0], coordinates[1],
                           (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
      if (dropTarget.acceptDrop(dragSource, coordinates[0], coordinates[1],
                                (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo)) {
        dropTarget.onDrop(dragSource, coordinates[0], coordinates[1],
                          (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
        dragSource.onDropCompleted((View) dropTarget, true);
        presenter.onDropCompleted(originator, (View)dropTarget, true);
        return true;
      }
      else {
        dragSource.onDropCompleted((View) dropTarget, false);
        return true;
      }
    }
    return false;
  }

  private DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
    final Rect r = rectTemp;

    final ArrayList<DropTarget> dropTargets_ = dropTargets;
    final int count = dropTargets_.size();
    for (int i=count-1; i>=0; i--) {
      final DropTarget target = dropTargets_.get(i);
      target.getHitRect(r);
      target.getLocationOnScreen(dropCoordinates);
      r.offset(dropCoordinates[0] - target.getLeft(), dropCoordinates[1] - target.getTop());
      if (r.contains(x, y)) {
        dropCoordinates[0] = x - dropCoordinates[0];
        dropCoordinates[1] = y - dropCoordinates[1];
        return target;
      }
    }
    return null;
  }

  /**
   * Get the screen size so we can clamp events to the screen size so even if
   * you drag off the edge of the screen, we find something.
   */
  private void recordScreenSize() {
    ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).
                             getDefaultDisplay().getMetrics(displayMetrics);
  }

  /**
   * Clamp val to be &gt;= min and &lt; max.
   */
  private static int clamp(int val, int min, int max) {
    if (val < min) {
      return min;
    }
    else if (val >= max) {
      return max - 1;
    }
    else {
      return val;
    }
  }

  public void setWindowToken(IBinder token) {
    windowToken = token;
  }

  /**
   * Sets the drag listener which will be notified when a drag starts or ends.
   */
  public void setDragListener(DragListener l) {
    dragListener = l;
  }

  /**
   * Remove a previously installed drag listener.
   */
  public void removeDragListener(DragListener l) {
    dragListener = null;
  }

  /**
   * Add a DropTarget to the list of potential places to receive drop events.
   */
  public void addDropTarget(DropTarget target) {
    dropTargets.add(target);
  }

  /**
   * Don't send drop events to <em>target</em> any more.
   */
//  public void removeDropTarget(DropTarget target) {
//    dropTargets.remove(target);
//  }

  /**
   * Don't send drop events to <em>target</em> any more.
   */
//  public void removeAllDropTargets() {
//    dropTargets = new ArrayList<DropTarget>();
//  }

}