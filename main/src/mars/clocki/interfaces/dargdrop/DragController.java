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
package mars.clocki.interfaces.dargdrop;

import android.content.ClipData;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

public class DragController
    implements View.OnDragListener {

  private DragDropPresenter mPresenter;

  private boolean mDragging;
  private boolean mDropSuccess;

  private DragSource mDragSource;
  private DropTarget mDropTarget;

  public DragController(DragDropPresenter p) {
    mPresenter = p;
  }

  @Override
  public boolean onDrag(View v, DragEvent event) {

    // Check to see if the presenter object has drag-drop enabled.
    if (mPresenter != null) {
      if (!mPresenter.isDragDropEnabled()) {
        return false;
      }
    }

    // Determine if the view is a DragSource, DropTarget, or both.
    // That information is used below when the event is handled.
    boolean isDragSource = false, isDropTarget = false;
    DragSource source = null;
    DropTarget target = null;
    if (v instanceof DragSource) {
      isDragSource = true;
      source = (DragSource) v;
    }
    if (v instanceof DropTarget) {
      isDropTarget = true;
      target = (DropTarget) v;
    }

    boolean eventResult = false;
    final int action = event.getAction();

    // Handles each of the expected events
    switch(action) {

    case DragEvent.ACTION_DRAG_STARTED:
//      Log.d("DragControler", "DragStarted.");

      // We want a call to mPresenter.onDragStarted once. So check to see if
      // we are already dragging.
      if (!mDragging) {
        mDragging = true;
        mDropSuccess = false;
        if (mPresenter != null) {
          mPresenter.onDragStarted(mDragSource);
        }
        Log.d("DragActivity", "Drag started. mDragSource : " + mDragSource);
      }

      // At the start of a drag, all drop targets must say they are interested
      // in the rest of the drag events of this drag-drop operation.
      // Allow for the case where a view is both a source and a target.
      if (isDragSource) {
        // The view continues to see drag events if it is the source of the
        // current drag or if it is a target itself.
        if (source == mDragSource) {
          if (source.allowDrag()) {
            eventResult = true;
            source.onDragStarted();
          }
        }
        else {
          eventResult = isDropTarget && target.allowDrop(mDragSource);
        }
      }
      else if (isDropTarget) {
        eventResult = target.allowDrop(mDragSource);
      }
      else {
        eventResult = false;
      }
      break;
      case DragEvent.ACTION_DRAG_ENTERED:
        Log.d("DragController", "DragController.onDrag - entered view");
        if (isDropTarget) {
          target.onDragEnter(mDragSource);
          mDropTarget = target;
          eventResult = true;
        }
        else {
          eventResult = false;
        }
        break;
      case DragEvent.ACTION_DRAG_EXITED:
        Log.d("DragController", "DragController.onDrag - exited view");
        if (isDropTarget) {
          mDropTarget = target;
          target.onDragExit(mDragSource);
          eventResult = true;
        }
        else {
          eventResult = false;
        }
        break;
      case DragEvent.ACTION_DROP:
        Log.d("DragController", "DragController.onDrag - dropped");
        if (isDropTarget) {
          if (target.allowDrop(mDragSource)) {
            target.onDrop(mDragSource);
            mDropTarget = target;
            mDropSuccess = true;
          }
          eventResult = true;
        }
        else {
          eventResult = false;
        }
        break;
      case DragEvent.ACTION_DRAG_ENDED:
//        Log.d("DargContrller", "DragController.onDrag - ended");
        if (mDragging) {
          Log.d("DragController", "DragControler.onDrag DargSource: " + mDragSource);
          if (mDragSource != null) {
            mDragSource.onDropCompleted(mDropTarget, mDropSuccess);
          }
          if (mPresenter != null) {
            mPresenter.onDropCompleted(mDropTarget, mDropSuccess);
          }
          eventResult = true;
        }
        mDragging = false;
        mDragSource = null;
        mDropTarget = null;
        break;
      }
    return eventResult;
  }

  public boolean startDrag(View v) {
    boolean isDragSource = false;
    DragSource ds = null;
    if (v instanceof DragSource) {
      ds = (DragSource) v;
      isDragSource = true;
    }
    if (!isDragSource) {
      return false;
    }
    if (!ds.allowDrag()) {
      return false;
    }

    mDragging = false;
    mDropSuccess = false;
    mDragSource = ds;
    mDropTarget = null;

    ClipData dargData = ds.clipDataForDragDrop();
    View.DragShadowBuilder shadowView = new View.DragShadowBuilder(v);
    v.startDrag(dargData, shadowView, v, 0);
    return true;
  }

}