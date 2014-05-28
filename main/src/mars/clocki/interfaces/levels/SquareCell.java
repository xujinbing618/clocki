package mars.clocki.interfaces.levels;

import mars.clocki.interfaces.dargdrop.DragSource;
import mars.clocki.interfaces.dargdrop.DropTarget;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class SquareCell extends LinearLayout
    implements DropTarget {

  private final String uid;
  private int row;
  private int column;

  public SquareCell(Context context) {
    super(context);
    this.uid = "";
  }

  public SquareCell(Context context, String uid) {
    super(context);
    this.uid = uid;
  }

  public SquareCell(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.uid = "";
  }

  public SquareCell(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.uid = "";
  }

  @Override
  public boolean allowDrop(DragSource source) {
    return isEmpty();
  }

  @Override
  public View dragDropView() {
    return this;
  }

  @Override
  public void onDrop(DragSource source) {
    Log.d("SquareCell", "SquareCell.onDrop: " + row + ", " + column + " source: " + source);

//    setBackgroundColor(R.color.cell_filled);

    SquareBox sourceView = (SquareBox) source.dragDropView();
    ViewGroup sourceParentView = (ViewGroup) sourceView.getParent();
    copyLayoutParams(sourceParentView);

    sourceParentView.removeView(sourceView);
    this.addView(sourceView);

    sourceParentView.invalidate();
    this.invalidate();
  }

  private void copyLayoutParams(View anotherLayout) {
    GridLayout.LayoutParams params = (GridLayout.LayoutParams) anotherLayout.getLayoutParams();
    setLayoutParams(params);
  }

  @Override
  public void onDragEnter(DragSource source) {
//    setBackgroundColor(isEmpty() ? R.color.cell_empty_hover :
//                               R.color.cell_filled_hover);
  }

  @Override
  public void onDragExit(DragSource source) {
//    setBackgroundColor(isEmpty() ? R.color.cell_empty : R.color.cell_filled);
  }

  public boolean isEmpty() {
    return getChildCount() == 0;
  }

  public String getUid() {
    return uid;
  }

}