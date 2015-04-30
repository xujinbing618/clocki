package mars.clocki.interfaces.dragdrop;

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

  public void setDragController(DragController dragger) {
    // Do nothing. We do not need to know the controller object.
  }

  @Override
  public void onDropCompleted(View target, boolean success) {

    // If the drop succeeds, the SquareBox has moved elsewhere
    // So clear the SquareCell.
    if (success) {
      Log.d ("DD", "SquareBox.onDropCompleted - target: " + target);
    }
//    setBackgroundForParent(R.color.cell_filled);
  }

  public void setBackgroundForParent(int colorId) {
    ((ViewGroup) getParent()).setBackgroundColor(colorId);
  }

  public String getUid() {
    return uid;
  }

}