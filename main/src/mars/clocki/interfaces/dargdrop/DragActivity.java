package mars.clocki.interfaces.dargdrop;

/*
import mars.clocki.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;
*/
/**
 * This activity presents a screen with a grid on which images can be added and
 * moved around. It also defines areas on the screen where the dragged views
 * can be dropped. Feedback is provided to the user as the objects are dragged
 * over these drop zones.
 *
 * <p> Like the DragActivity in the previous version of the DragViw example
 * application, the code here is derived from the Android Launcher code.
 *
 * <p> The original Launcher code required a long click (press) to initiate a
 * drag-drop sequence. If you want to see that behavior, set the variable
 * longClickStartsDrag to true. It is set to false below, which means that
 * any touch event starts a drag-drop.
 *
 */
/*
public class DragActivity extends Activity
    implements View.OnLongClickListener, View.OnClickListener,
               View.OnTouchListener, DragDropPresenter {

  private DragController mDragController;
  public int imageCount = 0;
  private ImageCell lastNewCell = null;
  private boolean longClickStartsDrag = false;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActionBar().setDisplayHomeAsUpEnabled(true);

    setContentView(R.layout.drop_demo);

    mDragController = new DragController(this);

    GridView gridView = (GridView) findViewById(R.id.image_grid_view);

    if (gridView == null) {
      Toast.makeText(this, "Unable to find GridView", Toast.LENGTH_SHORT);
    }
    else {
      gridView.setAdapter(new ImageCellAdapter(this, mDragController));
    }


    Toast.makeText(getApplicationContext(), "Move squares next to the empty cell til big square", Toast.LENGTH_LONG).show();
  }

  public void addNewImageToScreen(int resourceId) {
    if (lastNewCell != null) {
      lastNewCell.setVisibility(View.GONE);
    }
    FrameLayout imageHolder = (FrameLayout) findViewById(R.id.image_source_frame);
    if (imageHolder != null) {
      FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                                                    LayoutParams.MATCH_PARENT,
                                                    LayoutParams.MATCH_PARENT,
                                                    Gravity.CENTER);
      ImageCell newView = new ImageCell(this);
      newView.setImageResource(resourceId);
      imageHolder.addView(newView, lp);
      newView.mEmpty = false;
      newView.mCellNumber = -1;
      lastNewCell = newView;
      imageCount++;

      newView.setOnClickListener(this);
      newView.setOnLongClickListener(this);
      newView.setOnTouchListener(this);
    }
  }

  @Override
  public void onClick(View v) {
    if (longClickStartsDrag) {
      Toast.makeText(this, "Press and hold to drag an square", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public boolean onLongClick(View v) {
    if (longClickStartsDrag) {
      if (!v.isInTouchMode()) {
        Toast.makeText(this, "isInTouchMode returned false. Try touching the view agian", Toast.LENGTH_SHORT).show();
        return false;
      }
    }
    return startDrag(v);
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    if (longClickStartsDrag) {
      return false;
    }

    boolean handledHere = false;

    final int action = event.getAction();

    // In the situation where a long click is not needed to initiate a drag, simply start on the down event.
    if (action == MotionEvent.ACTION_DOWN) {
      handledHere = startDrag(v);
    }

    return handledHere;
  }

  public boolean startDrag(View v) {
    // We are starting a drag-drop opeation.
    // Set up the view and let our controller handle it.
    v.setOnDragListener(mDragController);
    mDragController.startDrag(v);
    return true;
  }

  public void onClickAddImage(View v) {
    addNewImageToScreen(R.drawable.ic_launcher);
  }

  @Override
  public boolean isDragDropEnabled() {
    return true;
  }

  @Override
  public void onDragStarted(DragSource source) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onDropCompleted(DropTarget target, boolean success) {
    // TODO Auto-generated method stub
  }

}
*/