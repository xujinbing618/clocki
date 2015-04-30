/*
 * This is a modified version of a class from the Android Open Source Project.
 * The original copyright and license information follows.
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

package mars.clocki.interfaces.dragdrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * A DragView is a special view used by a DragController. During a drag operation,
 * what is actually moving on the screen is a DragView. A DragView is constructed
 * using a bitmap of the view the use really wants to move.
 */
public class DragView extends View {

  // Number of pixels to add to the dragged item for scaling. Should be even for pixel alignment.
  private static final int DRAG_SCALE = 0;  // In Launcher, value is 40

  private Bitmap bitmap;
//  private Paint paint;
  private int registerationX;
  private int registerationY;

//  private float scale;
  private float animationScale = 0.9f;

  private WindowManager.LayoutParams layoutParams;
  private WindowManager windowManager;

  public DragView(Context context) {
    super(context);     // Just for suppressing Lint warning
  }

  public DragView(Context context, Bitmap bitmap, int registerationX, int registerationY,
                  int left, int top, int width, int height) {
    super(context);

    windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

    Matrix matrixScale = new Matrix();
    float scaleFactor = width;
    scaleFactor = /* scale = */ (scaleFactor + DRAG_SCALE) / scaleFactor;
    matrixScale.setScale(scaleFactor, scaleFactor);
    Log.d("DD","left " + left + " top " + top + " width " + width + " height " + height + " scale " + matrixScale);
    this.bitmap = Bitmap.createBitmap(bitmap, left, top, width, height, matrixScale, true);

    this.registerationX = registerationX + (DRAG_SCALE / 2);
    this.registerationY = registerationY + (DRAG_SCALE / 2);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(bitmap.getWidth(), bitmap.getHeight());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (true) {
      // Puts a little border around the view so you can see that you selected something.
      Paint p = new Paint();
      p.setStyle(Paint.Style.FILL);
      p.setColor(0x8800dd11);
      p.setAlpha(80);
      canvas.drawRect(0, 0, getWidth(), getHeight(), p);
      float scale_ = animationScale;
      if (scale_ < 0.999f) { // allow for some float error
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        float offset1 = (width-(width*scale_))/2;
        float offset2 = (height - (height*scale_))/2;
        canvas.translate(offset1, offset2);
        canvas.scale(scale_, scale_);
      }
      Paint p2 = new Paint();
      p2.setAlpha(100);
      canvas.drawBitmap(bitmap, 0.0f, 0.0f, p2);
    }
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    bitmap.recycle();
  }

  public void setPaint(Paint paint) {
//    this.paint = paint;
    invalidate();
  }

  public void setScale(float scale) {
    if (scale > 1.0f) {
      animationScale = 1.0f;
    }
    else {
      animationScale = scale;
    }
    invalidate();
  }

  /**
   * Create a window containing this view and show it.
   *
   * @param windowToken obtained from v.getWindowToken() from one of your views
   * @param touchX the x coordinate the user touched in screen coordinates
   * @param touchY the y coordinate the user touched in screen coordinates
   */
  public void show(IBinder windowToken, int touchX, int touchY) {
    WindowManager.LayoutParams lp;
    int pixelFormat;

    pixelFormat = PixelFormat.TRANSLUCENT;

    lp = new WindowManager.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        touchX - registerationX, touchY - registerationY,
        WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL,
        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
     /* WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM |*/
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        pixelFormat);
//   lp.token = statusBarView.getWindowToken();
    lp.token = windowToken;
    lp.gravity = Gravity.LEFT | Gravity.TOP;
    lp.setTitle("DragView");
    layoutParams = lp;

    windowManager.addView(this, lp);
  }

  /**
   * Move the window containing this view.
   *
   * @param touchX the x coordinate the user touched in screen coordinates
   * @param touchY the y coordinate the user touched in screen coordinates
   */
  void move(int touchX, int touchY) {
    // This is what was done in the Launcher code.
    WindowManager.LayoutParams lp = layoutParams;
    lp.x = touchX - registerationX;
    lp.y = touchY - registerationY;
    windowManager.updateViewLayout(this, lp);
  }

  void remove() {
    windowManager.removeView(this);
  }

}