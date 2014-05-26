package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.interfaces.dargdrop.DragSource;
import mars.clocki.interfaces.dargdrop.DropTarget;
import android.content.ClipData;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SquareBox extends Button
    implements DragSource {

  private final String uid;

  public SquareBox(Context context) {
    super(context);
    this.uid = "";
  }

  public SquareBox(Context context, String uid) {
    super(context);
    this.uid = uid;
  }

  public SquareBox(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.uid = "";
  }

  public SquareBox(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.uid = "";
  }

  @Override
  public boolean allowDrag() {
    return true;
  }

  @Override
  public ClipData clipDataForDragDrop() {
    return null;
  }

  @Override
  public View dragDropView() {
    return this;
  }

  @Override
  public void onDragStarted() {
    ((ViewGroup) getParent()).setBackgroundColor(R.color.cell_nearly_empty);
    invalidate();
  }

  @Override
  public void onDropCompleted(DropTarget target, boolean success) {
    invalidate();

    if (success) {
      Log.d ("SquareBox", "SquareBox.onDropCompleted - target: " + target);
    }
    setBackgroundForParent(R.color.cell_filled);
  }

  public void setBackgroundForParent(int colorId) {
    ((ViewGroup) getParent()).setBackgroundColor(colorId);
  }

  public String getUid() {
    return uid;
  }

}