package mars.clocki.interfaces.dragdrop;

import mars.clocki.R;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class SquareCell extends LinearLayout
    implements DropTarget {

  public SquareCell(Context context) {
    super(context);
  }

  public SquareCell(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SquareCell(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  /**
   * This method is called to determine if the DragSource has something to drag.
   *
   * @return True if there is something to drag
   */
  public boolean allowDrag() {
    // There is something to drag if the cell is not empty.
    return true;
  }

  public void setDragController(DragController dragger) {
    // Do nothing. We do not need to know the controller object.
  }

  public boolean empty() {
//    return (getChildCount() == 0);
    return true;
  }

  /**
   * onDropCompleted
   * {@inheritDoc DropTarget#onDrop(DragSource, int, int, int, int, DragView, Object)}
   */
  @Override
  public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset,
      DragView dragView, Object dragInfo) {
    // Mark the cell so it is no longer empty.
//    empty = false;
    int bg = empty() ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundResource(bg);

    Log.d("DD", "onDrop occured with source " + source + " and dragView " + dragView + " and " + x + " " + y + " " + xOffset + " " + yOffset);
  }

  /**
   * React to dragged object entering the are of this DropSpot.
   * Provide the use with some visual feedback.
   * {@inheritDoc DropTarget#onDragEnter(DragSource, int, int, int, int, DragView, Object)}
   */
  @Override
  public void onDragEnter(DragSource source, int x, int y, int xOffset,
      int yOffset, DragView dragView, Object DragInfo) {
    setBackgroundResource(empty() ? R.color.cell_empty_hover : R.color.cell_filled_hover);
  }

  /**
   * React to something being dragged over the drop target.
   * {@inheritDoc DropTarget#onDragOver(DragSource, int, int, int, int, DragView, Object)}
   */
  @Override
  public void onDragOver(DragSource source, int x, int y, int xOffset,
      int yOffset, DragView dragView, Object dragInfo) {
  }

  /**
   * {@inheritDoc DropTarget#onDragExit(DragSource, int, int, int, int, DragView, Object)}
   */
  @Override
  public void onDragExit(DragSource source, int x, int y, int xOffset,
      int yOffset, DragView dragView, Object dragInfo) {
    setBackgroundResource(empty() ? R.color.cell_empty: R.color.cell_filled);
  }

  /**
   * {@inheritDoc DropTarget#acceptDrop(DragSource, int, int, int, int, DragView, Object)}
   */
  @Override
  public boolean acceptDrop(DragSource source, int x, int y, int xOffset,
      int yOffset, DragView dragView, Object dragInfo) {
    return empty();
  }

  /**
   * {@inheritDoc DropTarget#estimateDropLocation(DragSource, int, int, int, int, DragView, Object, Rect)}
   */
  @Override
  public Rect estimateDropLocation(DragSource source, int x, int y,
      int xOffset, int yOffset, DragView dragView, Object dragInfo, Rect recycle) {
    return null;
  }

  /**
   * Call this view's onClick listener. Return true if it was called.
   * Click are ignored if the cell is empty.
   *
   * @return boolean
   */
  @Override
  public boolean performClick() {
    if (! empty()) {
      return super.performClick();
    }
    return false;
  }

  @Override
  public boolean performLongClick() {
    if (!empty()) {
      return super.performLongClick();
    }
    return false;
  }
}