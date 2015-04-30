package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.application.CS;
import mars.clocki.application.util.BuildHelper;
import mars.clocki.application.util.GridHelper;
import mars.clocki.application.util.LevelViewHelper;
import mars.clocki.domain.model.CellContainer;
import mars.clocki.domain.model.GridContainer;
import mars.clocki.domain.model.Position;
import mars.clocki.domain.model.Square.SquareType;
import mars.clocki.interfaces.AbstractActivity;
import mars.clocki.interfaces.dragdrop.DragController;
import mars.clocki.interfaces.dragdrop.DragDropPresenter;
import mars.clocki.interfaces.dragdrop.DragLayer;
import mars.clocki.interfaces.dragdrop.DragSource;
import mars.clocki.interfaces.dragdrop.DropTarget;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayout.LayoutParams;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public abstract class LevelActivity extends AbstractActivity
                                    implements View.OnLongClickListener,
                                               View.OnClickListener,
                                               View.OnTouchListener,
                                               DragDropPresenter {

  private DragController dragController;    // Object that handles a drag-drop sequence. It interacts with DragSource and DropTarget objects.
  private DragLayer dragLayer;              // The ViewGroup within which an object can be dragged.
  private boolean longClickStartsDrag = false;  // If true, it takes a long click to start the drag operation.
                                                // Otherwise, any touch event starts a drag.
  private boolean vibrationOn = false;          // Vibration is off by default, could be enabled by user from Menu.

  protected GridContainer grid;
  protected int moveCount;
  protected TextView moveView;
  private int mostRecentSquareId;
  private String mostRecentDraggedId = "";
  private boolean moveDecreasedOnce;
  private static int ripBoxSize = 0;

  protected LinearLayout r0c0Cell;
  protected LinearLayout r0c1Cell;
  protected LinearLayout r0c2Cell;
  protected LinearLayout r0c3Cell;
  protected LinearLayout r1c0Cell;
  protected LinearLayout r1c1Cell;
  protected LinearLayout r1c2Cell;
  protected LinearLayout r1c3Cell;
  protected LinearLayout r2c0Cell;
  protected LinearLayout r2c1Cell;
  protected LinearLayout r2c2Cell;
  protected LinearLayout r2c3Cell;
  protected LinearLayout r3c0Cell;
  protected LinearLayout r3c1Cell;
  protected LinearLayout r3c2Cell;
  protected LinearLayout r3c3Cell;
  protected LinearLayout r4c0Cell;
  protected LinearLayout r4c1Cell;
  protected LinearLayout r4c2Cell;
  protected LinearLayout r4c3Cell;

  protected LinearLayout r5c0Cell;
  protected LinearLayout r5c1Cell;
  protected LinearLayout r5c2Cell;
  protected LinearLayout r5c3Cell;
  protected LinearLayout r6c0Cell;
  protected LinearLayout r6c1Cell;
  protected LinearLayout r6c2Cell;
  protected LinearLayout r6c3Cell;
  protected LinearLayout r7c0Cell;
  protected LinearLayout r7c1Cell;
  protected LinearLayout r7c2Cell;
  protected LinearLayout r7c3Cell;

  public static LevelActivity instance;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (BuildHelper.api11orHigher()) {
      supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
    }
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(LevelViewHelper.activityViewId(grid.level()));

    dragController = new DragController(this, this);
    dragLayer = (DragLayer) findViewById(R.id.drag_layer);
    dragLayer.setDragController(dragController);

    dragController.setDragListener(dragLayer);

    moveCount = 0;
    initViewFields();
    initGridLayout(true);
    instance = this;

    if (BuildHelper.api13orHigher()) {
      Point screenSize = new Point();
      getWindowManager().getDefaultDisplay().getSize(screenSize);
      ripBoxSize = (int) (screenSize.y * 0.110);
    }
    else {
      @SuppressWarnings("deprecation")
      long height = getWindowManager().getDefaultDisplay().getHeight();
      ripBoxSize= (int) (height * 0.110);
    }
  }

  @Override
  public void onClick(View v) {
    if (longClickStartsDrag) {
      toast("Press and hold to drag a box.");
    }
  }

  /**
   * Handle a long click.
   * If longClickStartsDrag only is true, this will be the only way to start a drag operation.
   *
   * @param v View
   * @return boolean - true indicates that the event was handled
   */
  @Override
  public boolean onLongClick(View v) {
    if (longClickStartsDrag) {
      Log.d("DD", "onLongClick in view: " + v + " touchMode: " + v.isInTouchMode());

      // Make sure the drag was started by a long press as opposed to a long click.
      // (Note: I got this from the Workspace object in the Android Launcher code.
      // I think it is here to ensure that the device is still in touch mode as we start the drag operation.)
      if (!v.isInTouchMode()) {
        Log.d("DD", "isInTouchMode returned false. Try touching the view again.");
        return false;
      }
      return startDrag(v);
    }
    // If we get here, return false to indicate that we have not taken care of the event.
    return false;
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    // If we are configured to start only on a long click, we are not going to handle any events here.
    if (longClickStartsDrag) {
      return false;
    }
    boolean handledHere = false;

    final int action = event.getAction();
    final String id = idString((ViewGroup)view.getParent());
    if (action == MotionEvent.ACTION_DOWN) {
      if (grid.isAllowedToMoveFrom(GridHelper.row(id),
                                   GridHelper.column(id))) {

        handledHere = startDrag(view);
      }
    }
    return handledHere;
  }

  private boolean startDrag(View view) {
    DragSource dragSource = (DragSource) view;

    // We are starting a drag. Let the DragController handle it.
    dragController.startDrag(view, dragSource, dragSource, DragController.DRAG_ACTION_MOVE, vibrationOn);
    return true;
  }

  @Override
  public boolean isDragDropEnabled() {
    return true;
  }

  @Override
  public void onDragStarted(View source) {
    Log.d("DD", "onDragStarted in activity with source " + source);
  }

  @Override
  public void onDropCompleted(View source, View target, boolean success) {
    if (success) {
      View view = source;
      ViewGroup owner = (ViewGroup) view.getParent();
      LinearLayout container = (LinearLayout) target;
      String homeId = idString(owner);
      String dropId = idString(container);

      if (isAllowedToMoveTo(homeId, dropId)) {
        if (isLastMove(homeId)) {
          owner.removeView(view);
          findViewById(R.id.sq2x2_b).
            setBackgroundResource(R.drawable.square_green_big_full);
          moveCount++;
          moveView.setText(moveCount + "");
          writeScore();
          startActivity(new Intent(LevelActivity.this,
                                   WinningDialogActivity.class).
                                   putExtra(CS.LEVEL, grid.level()));
        }
        else {
          grid.move(GridHelper.row(homeId), GridHelper.column(homeId),
                    GridHelper.row(dropId), GridHelper.column(dropId));
          updateViewWithNewMove(view, container);
          if (mostRecentSquareId == view.getId() &&
              mostRecentDraggedId.equalsIgnoreCase(dropId) &&
              !moveDecreasedOnce) {
            moveCount--;
            moveDecreasedOnce = true;
          } else {
            moveCount++;
            moveDecreasedOnce = false;
          }
          moveView.setText(moveCount + "");
          mostRecentSquareId = view.getId();
          mostRecentDraggedId = homeId;
        }
      }
    }
  }


  private void writeScore() {
    SharedPreferences.Editor editor = getSharedEditor();
    editor.putBoolean(grid.level(), true);
    editor.putInt(CS.levelLastKey(grid.level()), moveCount);
    int score = getSharedPref().getInt(CS.levelScoreKey(grid.level()), 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(CS.levelScoreKey(grid.level()), moveCount);
    }
    editor.commit();
  }

  private boolean isLastMove(String homeId) {
    final int lastRow = GridHelper.row(homeId);
    final int lastColumn = GridHelper.column(homeId);
    CellContainer lastCell = grid.cell(lastRow, lastColumn);
    if (lastCell.square() != null &&
        lastCell.square().type().equals(SquareType.s2x2)) {
      if (lastRow == 3 && lastColumn == 1) {
        return true;
      }
      if (lastRow == 2 && lastColumn == 1 &&
          grid.isEmpty(4, 1) &&
          grid.isEmpty(4, 2)) {
        return true;
      }
      if (lastRow == 3 && lastColumn == 0 &&
          grid.isEmpty(3, 2) &&
          grid.isEmpty(4, 2)) {
        return true;
      }
      if (lastRow == 3 && lastColumn == 2 &&
          grid.isEmpty(3, 1) &&
          grid.isEmpty(4, 1)) {
        return true;
      }
    }
    return false;
  }

  private boolean isAllowedToMoveTo(String homeId, String dropId) {
    if (dropId.equalsIgnoreCase("r6c1")) {  // Special case for last move jump
      if (isLastMove(homeId)) {
        return true;
      }
    }
    return grid.isAllowedToMoveTo(
        GridHelper.row(homeId), GridHelper.column(homeId),
        GridHelper.row(dropId), GridHelper.column(dropId));
  }

  private void updateViewWithNewMove(View view, LinearLayout droppedIn) {
    String homeId = idString((ViewGroup)view.getParent());
    String dropId = idString(droppedIn);
    final Position home = Position.point(GridHelper.row(homeId),
                                         GridHelper.column(homeId));
    final Position drop = Position.point(GridHelper.row(dropId),
                                         GridHelper.column(dropId));

    ((ViewGroup) view.getParent()).removeView(view);  // Detach view first.

    if (idString(view).equalsIgnoreCase("sq2x2_a")) { // Square type 2x2
      switch (grid.moveDirectionForSquareType2x2(home, drop)) {
      case RIGHT:
        viewById(home.row(), home.column() + 1).addView(view);
        break;
      case LEFT:
        viewById(home.row(), home.column() - 1).addView(view);
        break;
      case UP:
        viewById(home.row() - 1, home.column()).addView(view);
        break;
      case DOWN:
        viewById(home.row() + 1, home.column()).addView(view);
        break;
      default:
        break;
      }
      initGridLayout(false);
    }
    else if (idString(view).startsWith("sq1x2")) {    // Square type 1x2
      switch (grid.moveDirectionForSquareType1x2(home, drop)) {
      case RIGHT:
        viewById(home.row(), home.column() + 1).addView(view);
        break;
      case LEFT:
        viewById(home.row(), home.column() - 1).addView(view);
        break;
      case UP:
        if (home.row() - drop.row() > 1) {
          viewById(home.row() - 2, home.column()).addView(view);
        } else {
          viewById(home.row() - 1, home.column()).addView(view);
        }
        break;
      case DOWN:
        if (drop.row() - home.row() > 2) {
          viewById(home.row() + 2, home.column()).addView(view);
        } else {
          viewById(home.row() + 1, home.column()).addView(view);
        }
        break;
      default:
        break;
      }
      initGridLayout(false);
    } else if (idString(view).startsWith("sq2x1")) {  // Square type 2x1
      switch(grid.moveDirectionForSquareType2x1(home, drop)) {
      case RIGHT:
        if (drop.column() - home.column() > 2) {
          viewById(home.row(), home.column() + 2).addView(view);
        }
        else {
          viewById(home.row(), home.column() + 1).addView(view);
        }
        break;
      case LEFT:
        if (home.column() - drop.column() > 1) {
          viewById(home.row(), home.column() - 2).addView(view);
        }
        else {
          viewById(home.row(), home.column() - 1).addView(view);
        }
        break;
      case UP:
        viewById(home.row() - 1, home.column()).addView(view);
        break;
      case DOWN:
        viewById(home.row() + 1, home.column()).addView(view);
        break;
      default:
        break;
      }
      initGridLayout(false);
    }
    else {
      droppedIn.addView(view);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.reset, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.reset_level:
      resetActivity();
      break;
    case R.id.change_touch_mode:
      longClickStartsDrag = !longClickStartsDrag;
      break;
    case R.id.vibration_on_off:
      vibrationOn = !vibrationOn;
      break;
    default:
      break;
    }
    return super.onOptionsItemSelected(item);
  }

  protected String idString(View view) {
    return getResources().getResourceEntryName(view.getId());
  }

  protected ViewGroup viewById(String id) {
    return (ViewGroup) findViewById(getResources().
        getIdentifier(id, "id", getPackageName()));
  }

  protected ViewGroup viewById(int row, int column) {
    return viewById(formattedId(row, column));
  }

  protected String formattedId(int row, int column) {
    return String.format("r%sc%s", row, column);
  }

  @SuppressLint("NewApi")
  private void initGridLayout(boolean firstTime) {
    visiableAllLinearLayouts();
    resizeView(0, 0, r0c0Cell, ripBoxSize, firstTime);
    resizeView(0, 1, r0c1Cell, ripBoxSize, firstTime);
    resizeView(0, 2, r0c2Cell, ripBoxSize, firstTime);
    resizeView(0, 3, r0c3Cell, ripBoxSize, firstTime);
    resizeView(1, 0, r1c0Cell, ripBoxSize, firstTime);
    resizeView(1, 1, r1c1Cell, ripBoxSize, firstTime);
    resizeView(1, 2, r1c2Cell, ripBoxSize, firstTime);
    resizeView(1, 3, r1c3Cell, ripBoxSize, firstTime);
    resizeView(2, 0, r2c0Cell, ripBoxSize, firstTime);
    resizeView(2, 1, r2c1Cell, ripBoxSize, firstTime);
    resizeView(2, 2, r2c2Cell, ripBoxSize, firstTime);
    resizeView(2, 3, r2c3Cell, ripBoxSize, firstTime);
    resizeView(3, 0, r3c0Cell, ripBoxSize, firstTime);
    resizeView(3, 1, r3c1Cell, ripBoxSize, firstTime);
    resizeView(3, 2, r3c2Cell, ripBoxSize, firstTime);
    resizeView(3, 3, r3c3Cell, ripBoxSize, firstTime);
    resizeView(4, 0, r4c0Cell, ripBoxSize, firstTime);
    resizeView(4, 1, r4c1Cell, ripBoxSize, firstTime);
    resizeView(4, 2, r4c2Cell, ripBoxSize, firstTime);
    resizeView(4, 3, r4c3Cell, ripBoxSize, firstTime);
    // Bottom of puzzle
    if (firstTime) {
      resizeView(5, 0, r5c0Cell, ripBoxSize, firstTime);
      resizeView(5, 1, r5c1Cell, ripBoxSize, firstTime);
      resizeView(5, 2, r5c2Cell, ripBoxSize, firstTime);
      resizeView(5, 3, r5c3Cell, ripBoxSize, firstTime);
      resizeView(6, 0, r6c0Cell, ripBoxSize, firstTime);
      resizeView(6, 1, r6c1Cell, ripBoxSize, firstTime);
      resizeView(6, 2, r6c2Cell, ripBoxSize, firstTime);
      resizeView(6, 3, r6c3Cell, ripBoxSize, firstTime);
      resizeView(7, 0, r7c0Cell, ripBoxSize, firstTime);
      resizeView(7, 1, r7c1Cell, ripBoxSize, firstTime);
      resizeView(7, 2, r7c2Cell, ripBoxSize, firstTime);
      resizeView(7, 3, r7c3Cell, ripBoxSize, firstTime);
    }
  }

  private void resizeView(final int i, final int j, LinearLayout cellView,
      int ripBox, boolean firstTime) {
    String id = formattedId(i, j);
    if (cellView != null) {
      if (((ViewGroup) cellView).getChildCount() > 0) {
        View child = ((ViewGroup) cellView).getChildAt(0);
        if (child instanceof Button && firstTime) {
          child.setOnTouchListener(this);
          child.setOnClickListener(this);
          child.setOnLongClickListener(this);
        }
        if (child.getId() == R.id.sq2x2_a ||
            child.getId() == R.id.sq2x2_b) {
          cellView.getLayoutParams().width = ripBox * 2;
          cellView.getLayoutParams().height = ripBox * 2;
          // Let's invisible other three covered cells
          String threeCoveredCells = GridHelper.findOtherThreeCell(id);
          if (threeCoveredCells != null) {
            for (String c : threeCoveredCells.split(",")) {
              viewById(c).setVisibility(View.GONE);
            }
          }
          GridLayout.LayoutParams params = (LayoutParams)
              cellView.getLayoutParams();
          params.rowSpec = GridLayout.spec(i, 2);
          params.columnSpec = GridLayout.spec(j, 2);
          cellView.setLayoutParams(params);
        }
        else if (child.getId() == R.id.sq1x2_a ||
                 child.getId() == R.id.sq1x2_b ||
                 child.getId() == R.id.sq1x2_c ||
                 child.getId() == R.id.sq1x2_d) {
          cellView.getLayoutParams().width = ripBox;
          cellView.getLayoutParams().height = ripBox * 2;
          GridLayout.LayoutParams params = (LayoutParams)
              cellView.getLayoutParams();
          params.rowSpec = GridLayout.spec(i, 2);
          cellView.setLayoutParams(params);
          // Hide one covered view. Note: i + 1 will change i value to i + 1 (keyword 'final' has no effect)
          viewById(i + 1, j).setVisibility(View.GONE);
        }
        else if (child.getId() == R.id.sq2x1_a ||
                 child.getId() == R.id.sq2x1_b ||
                 child.getId() == R.id.sq2x1_c) {
          cellView.getLayoutParams().width = ripBox * 2;
          cellView.getLayoutParams().height = ripBox;
          ((GridLayout.LayoutParams)cellView.getLayoutParams()).
            columnSpec = GridLayout.spec(j, 2);
          // Hide covered view.
          viewById(i, j + 1).setVisibility(View.GONE);
        }
        else {
          cellView.getLayoutParams().width = ripBox;
          cellView.getLayoutParams().height = ripBox;
          GridLayout.LayoutParams params = (LayoutParams)
              cellView.getLayoutParams();
          params.rowSpec = GridLayout.spec(i, 1);
          params.columnSpec = GridLayout.spec(j, 1);
          cellView.setLayoutParams(params);
        }
      }
      else {
        cellView.getLayoutParams().width = ripBox;
        cellView.getLayoutParams().height = ripBox;
        GridLayout.LayoutParams params = (LayoutParams)
            cellView.getLayoutParams();
        params.rowSpec = GridLayout.spec(i, 1);
        params.columnSpec = GridLayout.spec(j, 1);
        cellView.setLayoutParams(params);
      }
    }
  }

  private void visiableAllLinearLayouts() {
    r0c0Cell.setVisibility(View.VISIBLE);
    r0c1Cell.setVisibility(View.VISIBLE);
    r0c2Cell.setVisibility(View.VISIBLE);
    r0c3Cell.setVisibility(View.VISIBLE);
    r1c0Cell.setVisibility(View.VISIBLE);
    r1c1Cell.setVisibility(View.VISIBLE);
    r1c2Cell.setVisibility(View.VISIBLE);
    r1c3Cell.setVisibility(View.VISIBLE);
    r2c0Cell.setVisibility(View.VISIBLE);
    r2c1Cell.setVisibility(View.VISIBLE);
    r2c2Cell.setVisibility(View.VISIBLE);
    r2c3Cell.setVisibility(View.VISIBLE);
    r3c0Cell.setVisibility(View.VISIBLE);
    r3c1Cell.setVisibility(View.VISIBLE);
    r3c2Cell.setVisibility(View.VISIBLE);
    r3c3Cell.setVisibility(View.VISIBLE);
    r4c0Cell.setVisibility(View.VISIBLE);
    r4c1Cell.setVisibility(View.VISIBLE);
    r4c2Cell.setVisibility(View.VISIBLE);
    r4c3Cell.setVisibility(View.VISIBLE);
  }

  protected void initViewFields() {
    r0c0Cell = (LinearLayout) findViewById(R.id.r0c0);
    r0c1Cell = (LinearLayout) findViewById(R.id.r0c1);
    r0c2Cell = (LinearLayout) findViewById(R.id.r0c2);
    r0c3Cell = (LinearLayout) findViewById(R.id.r0c3);
    r1c0Cell = (LinearLayout) findViewById(R.id.r1c0);
    r1c1Cell = (LinearLayout) findViewById(R.id.r1c1);
    r1c2Cell = (LinearLayout) findViewById(R.id.r1c2);
    r1c3Cell = (LinearLayout) findViewById(R.id.r1c3);
    r2c0Cell = (LinearLayout) findViewById(R.id.r2c0);
    r2c1Cell = (LinearLayout) findViewById(R.id.r2c1);
    r2c2Cell = (LinearLayout) findViewById(R.id.r2c2);
    r2c3Cell = (LinearLayout) findViewById(R.id.r2c3);
    r3c0Cell = (LinearLayout) findViewById(R.id.r3c0);
    r3c1Cell = (LinearLayout) findViewById(R.id.r3c1);
    r3c2Cell = (LinearLayout) findViewById(R.id.r3c2);
    r3c3Cell = (LinearLayout) findViewById(R.id.r3c3);
    r4c0Cell = (LinearLayout) findViewById(R.id.r4c0);
    r4c1Cell = (LinearLayout) findViewById(R.id.r4c1);
    r4c2Cell = (LinearLayout) findViewById(R.id.r4c2);
    r4c3Cell = (LinearLayout) findViewById(R.id.r4c3);

    r5c0Cell = (LinearLayout) findViewById(R.id.r5c0);
    r5c1Cell = (LinearLayout) findViewById(R.id.r5c1);
    r5c2Cell = (LinearLayout) findViewById(R.id.r5c2);
    r5c3Cell = (LinearLayout) findViewById(R.id.r5c3);
    r6c0Cell = (LinearLayout) findViewById(R.id.r6c0);
    r6c1Cell = (LinearLayout) findViewById(R.id.r6c1);
    r6c2Cell = (LinearLayout) findViewById(R.id.r6c2);
    r6c3Cell = (LinearLayout) findViewById(R.id.r6c3);
    r7c0Cell = (LinearLayout) findViewById(R.id.r7c0);
    r7c1Cell = (LinearLayout) findViewById(R.id.r7c1);
    r7c2Cell = (LinearLayout) findViewById(R.id.r7c2);
    r7c3Cell = (LinearLayout) findViewById(R.id.r7c3);

    dragController.addDropTarget((DropTarget) r0c0Cell);
    dragController.addDropTarget((DropTarget) r0c1Cell);
    dragController.addDropTarget((DropTarget) r0c2Cell);
    dragController.addDropTarget((DropTarget) r0c3Cell);
    dragController.addDropTarget((DropTarget) r1c0Cell);
    dragController.addDropTarget((DropTarget) r1c1Cell);
    dragController.addDropTarget((DropTarget) r1c2Cell);
    dragController.addDropTarget((DropTarget) r1c3Cell);
    dragController.addDropTarget((DropTarget) r2c0Cell);
    dragController.addDropTarget((DropTarget) r2c1Cell);
    dragController.addDropTarget((DropTarget) r2c2Cell);
    dragController.addDropTarget((DropTarget) r2c3Cell);
    dragController.addDropTarget((DropTarget) r3c0Cell);
    dragController.addDropTarget((DropTarget) r3c1Cell);
    dragController.addDropTarget((DropTarget) r3c2Cell);
    dragController.addDropTarget((DropTarget) r3c3Cell);
    dragController.addDropTarget((DropTarget) r4c0Cell);
    dragController.addDropTarget((DropTarget) r4c1Cell);
    dragController.addDropTarget((DropTarget) r4c2Cell);
    dragController.addDropTarget((DropTarget) r4c3Cell);

    dragController.addDropTarget((DropTarget) r6c1Cell);

    moveView = (TextView) findViewById(R.id.moves);
    moveView.setText(moveCount + "");
  }

  /**
   * Show a string on the screen via Toast.
   *
   * @param msg String
   */
  public void toast(String msg) {
    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
  }

}