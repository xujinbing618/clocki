package mars.clocki.interfaces.dargdrop;

import mars.clocki.R;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * This subclass of ImageView is used to display an image on an Gridview.
 * An ImageCell knows which cell on the grid it is showing and which grid it is
 * attached to Cell numbers are from 0 to NumCells-1.
 * It also knows if it is empty.
 *
 * <p> Image cells are places where images can be dragged from an dropped onto.
 * Therefore, this class implements both the DragSource and DropTarget interfaces.
 */
public class ImageCell extends ImageView
    implements DragSource, DropTarget {

  public boolean mEmpty = true;
  public int mCellNumber = -1;
  public GridView mGridView;

  public ImageCell(Context context) {
    super(context);
  }

  public ImageCell(Context context, AttributeSet attrs) {
    super (context, attrs);
  }

  public ImageCell(Context context, AttributeSet attrs, int style) {
    super (context, attrs, style);
  }

  // DragSource interfaces implementations
  /**
   * This method is called to determine if the DragSource has something to
   * drag.
   *
   * @return True if there is something to drag.
   */
  public boolean allowDrag() {
    // There is something to drag if the cell is not empty.
    return !mEmpty;
  }

  /**
   * Return the ClipData associated with the drag operation.
   * @return ClipData
   */
  public ClipData clipDataForDragDrop() {
    return null;
    // eventually, create something with the id of the image and the cell number
  }

  /**
   * Return the view that is the actual source of the information being dragged.
   * Since ImageCell implements the DragSource interface, it is the view itself.
   *
   */
  @Override
  public View dragDropView() {
    return this;
  }

  /**
   * This method is called at the start of a drag-drop operation so the object
   * being dragged knows that it is being dragged.
   */
  @Override
  public void onDragStarted() {
    if (mCellNumber >= 0) {
      setColorFilter(R.color.cell_nearly_empty);
      invalidate();
    }
  }

  /**
   * This method is called on the completion of the drag operation so the
   * DragSource knows whether is succeeded or failed.
   */
  public void onDropCompleted(DropTarget target, boolean success) {
    // Undo what we did in onDragStarted
    if (mCellNumber >= 0) {
      clearColorFilter();
      invalidate();
    }

    // If the drop succeeds, the image has moved elsewhere.
    // So clear the image cell.

    if (success) {
      Log.d("DragActivity", "ImageCell.onDropCompleted - clearing source: " + mCellNumber);
      mEmpty = true;
      if (mCellNumber >= 0) {
        int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
        setBackgroundResource(bg);
        setImageDrawable(null);
      }
      else {
        // If the cell number is negative, it means we are interacting with a free-standing
        // image cell. There is one of those. It is the palce where an image is added when
        // the use clicks "add image".
        // At the conclusion of a drop, clear it.
        setImageResource(0);
      }
    }
    else {
      // On the failure case, reset the background color in cast it is still set to hover color.
      if (mCellNumber >= 0) {
        int bg2 = mEmpty ? R.color.cell_empty : R.color.cell_filled;
        setBackgroundColor(bg2);
      }
    }
  }

  // DropTarget interface implementations
  /**
   * Return true if the DropTarget allows objects to be dropped on it.
   * Disallow drop if the source object is the same ImageCell.
   * Allow a drop of the ImageCell is empty
   *
   * @param source DragSource where the drag started
   * @return boolean True if the drop will be accepted, false otherwise.
   */
  @Override
  public boolean allowDrop(DragSource source) {
    // Do not allow a drop if the DragSource is the same cell.
    if (source == this) {
      return false;
    }

    // An ImageCell accepts a drop if it is empty and if it is part of a grid.
    // A free-standing ImageCell does not accept drops.
    return mEmpty && (mCellNumber >= 0);
  }

  /**
   * Handle an object being dropped on the DropTarget
   *
   * @param source DragSource where the drag started
   */
  @Override
  public void onDrop(DragSource source) {
    Log.d("DragActivity", "ImageCell.onDrop: " + mCellNumber + " source: " + source);

    // Mark the cell so it is no longer empty.
    mEmpty = false;
    int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundColor(bg);

    // The view being dragged does not actually change its parent and switch
    // over to the ImageCell. What we do is copy the drawable from the soruce view.
    ImageView sourceView = (ImageView) source.dragDropView();
    Drawable d = sourceView.getDrawable();
    if (d != null) {
      this.setImageDrawable(d);
      this.invalidate();
    }
    else {
      Log.e("DragActivity", "ImageCell.onDrop. Null Drawable");
    }
  }

  @Override
  public void onDragEnter(DragSource source) {
    if (mCellNumber < 0) {
      return;
    }

    int bg = mEmpty ? R.color.cell_empty_hover : R.color.cell_filled_hover;
    setBackgroundColor(bg);
  }

  @Override
  public void onDragExit(DragSource source) {
    if (mCellNumber < 0) {
      return;
    }
    int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundColor(bg);
  }

  /**
   * Return true if this cell is empty.
   * If it is, it means that it will accept dropped views.
   * It also means that there is nothing to drag.
   */
  public boolean isEmpty() {
    return mEmpty;
  }

  public boolean performClick() {
    if (!mEmpty) {
      return super.performClick();
    }
    return false;
  }

  public boolean performLongClick() {
    if (!mEmpty) {
      return super.performLongClick();
    }
    return false;
  }

}