package mars.clocki.interfaces.dragdrop;

import android.view.View;

public interface DragDropPresenter {

  public boolean isDragDropEnabled();

  public void onDragStarted(View source);

  public void onDropCompleted(View dragSource, View target, boolean success);

}