package mars.clocki.interfaces.levels;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_EXITED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import mars.clocki.R;
import mars.clocki.application.util.GridHelper;
import mars.clocki.domain.model.CellContainer;
import mars.clocki.domain.model.GridContainer;
import mars.clocki.domain.model.Position;
import mars.clocki.domain.model.Square.SquareType;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LevelActivity extends ActionBarActivity {

  protected int moveCount;
  protected GridContainer grid;
  protected GridLayout gridLayout;
  protected TextView moveView;

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

  private final String r_c_ = "r%sc%s";

  protected void initGridLayout(boolean firstTime) {
    refreshLinearLayouts();
    Point screenSize = new Point();
    getWindowManager().getDefaultDisplay().getSize(screenSize);
    int ripBox = (int) (screenSize.y * 0.125);
    resizeView(0, 0, r0c0Cell, ripBox, firstTime);
    resizeView(0, 1, r0c1Cell, ripBox, firstTime);
    resizeView(0, 2, r0c2Cell, ripBox, firstTime);
    resizeView(0, 3, r0c3Cell, ripBox, firstTime);
    resizeView(1, 0, r1c0Cell, ripBox, firstTime);
    resizeView(1, 1, r1c1Cell, ripBox, firstTime);
    resizeView(1, 2, r1c2Cell, ripBox, firstTime);
    resizeView(1, 3, r1c3Cell, ripBox, firstTime);
    resizeView(2, 0, r2c0Cell, ripBox, firstTime);
    resizeView(2, 1, r2c1Cell, ripBox, firstTime);
    resizeView(2, 2, r2c2Cell, ripBox, firstTime);
    resizeView(2, 3, r2c3Cell, ripBox, firstTime);
    resizeView(3, 0, r3c0Cell, ripBox, firstTime);
    resizeView(3, 1, r3c1Cell, ripBox, firstTime);
    resizeView(3, 2, r3c2Cell, ripBox, firstTime);
    resizeView(3, 3, r3c3Cell, ripBox, firstTime);
    resizeView(4, 0, r4c0Cell, ripBox, firstTime);
    resizeView(4, 1, r4c1Cell, ripBox, firstTime);
    resizeView(4, 2, r4c2Cell, ripBox, firstTime);
    resizeView(4, 3, r4c3Cell, ripBox, firstTime);
    // Bottom of puzzle
    if (firstTime) {
      resizeView(5, 0, r5c0Cell, ripBox, firstTime);
      resizeView(5, 1, r5c1Cell, ripBox, firstTime);
      resizeView(5, 2, r5c2Cell, ripBox, firstTime);
      resizeView(5, 3, r5c3Cell, ripBox, firstTime);
      resizeView(6, 0, r6c0Cell, ripBox, firstTime);
      resizeView(6, 1, r6c1Cell, ripBox, firstTime);
      resizeView(6, 2, r6c2Cell, ripBox, firstTime);
      resizeView(6, 3, r6c3Cell, ripBox, firstTime);
      resizeView(7, 0, r7c0Cell, ripBox, firstTime);
      resizeView(7, 1, r7c1Cell, ripBox, firstTime);
      resizeView(7, 2, r7c2Cell, ripBox, firstTime);
      resizeView(7, 3, r7c3Cell, ripBox, firstTime);
    }
  }

  private void resizeView(final int i, final int j, LinearLayout cellView,
      int ripBox, boolean firstTime) {
    String id = String.format(r_c_, i, j);
    if (cellView != null) {
      if (((ViewGroup) cellView).getChildCount() > 0) {
        View child = ((ViewGroup) cellView).getChildAt(0);
        if (child instanceof Button && firstTime) {
          child.setOnTouchListener(new DragListener());
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
                 child.getId() == R.id.sq1x2_b) {
          cellView.getLayoutParams().height = ripBox * 2;
          GridLayout.LayoutParams params = (LayoutParams)
              cellView.getLayoutParams();
          params.rowSpec = GridLayout.spec(i, 2);
          cellView.setLayoutParams(params);
          // Hide one covered view. Note: i + 1 will change i value to i + 1 (keyword 'final' has no effect)
          viewById(String.format(r_c_, i + 1, j)).setVisibility(View.GONE);
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

  /**
   * Dragging starts here.
   */
  private final class DragListener implements OnTouchListener {
    public boolean onTouch(View view, MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        String id = idString(((ViewGroup)view.getParent()).getId());
        if (grid.isAllowedToMoveFrom(GridHelper.row(id),
                                     GridHelper.column(id))) {
          ClipData data = ClipData.newPlainText("", "");
          DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
          view.startDrag(data, shadowBuilder, view, 0);
          view.setVisibility(View.INVISIBLE);
          return true;
        }
        return false;
      }
      else {
        view.setVisibility(View.VISIBLE);
        return false;
      }
    }
  }

  /**
   * Dragging ends here.
   */
  protected final class DropListener implements OnDragListener {
    Drawable normalShape = getResources().getDrawable(
        R.drawable.abc_list_selector_disabled_holo_light);
    Drawable enterShape = getResources().getDrawable(
        R.drawable.abc_list_selector_background_transition_holo_dark);

    public boolean onDrag(View v, DragEvent event) {
      switch(event.getAction()) {
      case ACTION_DRAG_STARTED:
        break; // do nothing
      case ACTION_DRAG_ENTERED:
        v.setBackground(enterShape);
        break;
      case ACTION_DRAG_EXITED:
        v.setBackground(normalShape);
        break;
      case ACTION_DROP:
        // Dropped, reassign View to ViewGroup
        View view = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) view.getParent();
        LinearLayout container = (LinearLayout) v;
        String homeId = idString(owner.getId());
        String dropId = idString(container.getId());
        if (isLastMove(homeId)) {
          owner.removeView(view);
          findViewById(R.id.sq2x2_b).setBackground(getResources().
              getDrawable(R.drawable.square_green_big_full));
          moveCount++;
          moveView.setText(moveCount + "");
          startActivity(new Intent(LevelActivity.this,
                                   WinningDialogActivity.class));
        }
        else if (isAllowedToMoveTo(homeId, dropId)) {
          grid.move(GridHelper.row(homeId), GridHelper.column(homeId),
                    GridHelper.row(dropId), GridHelper.column(dropId));
          updateViewViewNewMove(view, container);
          moveCount++;
          moveView.setText(moveCount + "");
        }
        break;
      case ACTION_DRAG_ENDED:
        v.setBackground(normalShape);
        break;
      default:
        break;
      }
      return true;
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
            grid.isEmpty(Position.point(4, 1)) &&
            grid.isEmpty(Position.point(4, 2))) {
          return true;
        }
      }
      return false;
    }

    private boolean isAllowedToMoveTo(String homeId, String dropId) {
      return grid.isAllowedToMoveTo(
          GridHelper.row(homeId), GridHelper.column(homeId),
          GridHelper.row(dropId), GridHelper.column(dropId));
    }

    private void updateViewViewNewMove(View view, LinearLayout droppedIn) {
      String homeId = idString(((ViewGroup)view.getParent()).getId());
      String dropId = idString(droppedIn.getId());
      final Position home = Position.point(GridHelper.row(homeId),
                                           GridHelper.column(homeId));
      final Position drop = Position.point(GridHelper.row(dropId),
                                           GridHelper.column(dropId));

      ((ViewGroup) view.getParent()).removeView(view);  // Remove view first.

      if (idString(view.getId()).equalsIgnoreCase("sq2x2_a")) { // Big square type 2x2
        String newHomeId = "";
        switch (grid.moveDirectionForSquareType2x2(home, drop)) {
        case RIGHT:
          newHomeId = String.format(r_c_, home.row(), home.column() + 1);
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        case LEFT:
          newHomeId = String.format(r_c_, home.row(), home.column() - 1);
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        case UP:
          newHomeId = String.format(r_c_, home.row() - 1, home.column());
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        case DOWN:
          newHomeId = String.format(r_c_, home.row() + 1, home.column());
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        default:
          break;
        }
      }
      else if (idString(view.getId()).startsWith("sq1x2")) {    // Big square type 1x2
        String newHomeId = "";
        switch (grid.moveDirectionForSquareType1x2(home, drop)) {
        case RIGHT:
          newHomeId = String.format(r_c_, home.row(), home.column() + 1);
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        case LEFT:
          newHomeId = String.format(r_c_, home.row(), home.column() - 1);
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        case UP:
          if (home.row() - drop.row() > 1) {
            newHomeId = String.format(r_c_, home.row() - 2, home.column());
          } else {
            newHomeId = String.format(r_c_, home.row() - 1, home.column());
          }
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        case DOWN:
          if (drop.row() - home.row() > 2) {
            newHomeId = String.format(r_c_, home.row() + 2, home.column());
          } else {
            newHomeId = String.format(r_c_, home.row() + 1, home.column());
          }
          viewById(newHomeId).addView(view);
          initGridLayout(false);
          break;
        default:
          break;
        }
      }
      else {
        droppedIn.addView(view);
      }
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
      if (Build.VERSION.SDK_INT >= 11) {
        recreate();
      }
      else {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
      }
      break;
    default:
      break;
    }
    return super.onOptionsItemSelected(item);
  }

  protected ViewGroup viewById(String id) {
    return (ViewGroup) findViewById(getResources().
        getIdentifier(id, "id", getPackageName()));
  }

  protected String idString(int id) {
    return getResources().getResourceEntryName(id);
  }

  private void refreshLinearLayouts() {
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

}