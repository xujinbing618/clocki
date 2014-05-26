package mars.clocki.interfaces.dargdrop;

public interface DragDropPresenter {

  public boolean isDragDropEnabled();

  public void onDragStarted(DragSource source);

  public void onDropCompleted(DropTarget target, boolean success);

}