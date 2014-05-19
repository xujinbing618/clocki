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
import android.os.Bundle;
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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Level1Activity extends ActionBarActivity {

  private GridContainer grid;
  private int moveCount;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level1);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    findViewById(R.id.sq1x1_a).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_b).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_c).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_d).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_e).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_f).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_g).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_h).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_i).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_j).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_k).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_l).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_m).setOnTouchListener(new DragTouchListener());
    findViewById(R.id.sq1x1_n).setOnTouchListener(new DragTouchListener());

    findViewById(R.id.sq2x2_a).setOnTouchListener(new DragTouchListener());

    moveCount = 0;
    ((TextView)findViewById(R.id.moves)).setText(moveCount + "");
    grid = GridContainer.initLevel1();
    reSizeSquareViews(true);

  }


  private final class DragTouchListener implements OnTouchListener {
    public boolean onTouch(View view, MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        String id = getResources().getResourceEntryName(((ViewGroup)view.getParent()).getId());
        if (grid.isAllowedToMoveFrom(GridHelper.row(id), GridHelper.column(id))) {
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


  class DragListener implements OnDragListener {
    Drawable normalShape = getResources().getDrawable(R.drawable.abc_list_selector_disabled_holo_light);
    Drawable enterShape = getResources().getDrawable(R.drawable.abc_list_selector_background_transition_holo_dark);

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
        String homeId = getResources().getResourceEntryName(owner.getId());
        String dropId = getResources().getResourceEntryName(container.getId());
        if (isLastMove(homeId)) {
          owner.removeView(view);
          findViewById(R.id.sq2x2_b).setBackground(getResources().getDrawable(R.drawable.square_green_big_full));
          moveCount++;
          ((TextView)findViewById(R.id.moves)).setText(moveCount + "");
          startActivity(new Intent(Level1Activity.this, WinningDialogActivity.class));
        }
        else if (isAllowedToMoveTo(homeId, dropId)) {
          updateViewWithMove(view, container);
          grid.move(GridHelper.row(homeId), GridHelper.column(homeId),
                    GridHelper.row(dropId), GridHelper.column(dropId));
          moveCount++;
          ((TextView)findViewById(R.id.moves)).setText(moveCount + "");
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

    private boolean isAllowedToMoveTo(String homeId, String dropId) {
      return grid.isAllowedToMoveTo(
          GridHelper.row(homeId), GridHelper.column(homeId),
          GridHelper.row(dropId), GridHelper.column(dropId));
    }

    private boolean isLastMove(String homeId) {
      final int lastRow = GridHelper.row(homeId);
      final int lastColumn = GridHelper.column(homeId);
      CellContainer lastCell = grid.cell(lastRow, lastColumn);
      if (lastCell.square() != null &&
          lastCell.square().type().equals(SquareType.s2x2)) {
        if (lastRow == 2 && lastColumn == 1 &&
            grid.isEmpty(Position.point(4, 1)) &&
            grid.isEmpty(Position.point(4, 2))) {
          return true;
        }
        if (lastRow == 3 && lastColumn == 1) {
          return true;
        }
      }
      return false;
    }

    private void updateViewWithMove(View view, LinearLayout droppedIn) {
      String homeId = getResources().getResourceEntryName(((ViewGroup)view.getParent()).getId());
      String dropId = getResources().getResourceEntryName(droppedIn.getId());

      ((ViewGroup) view.getParent()).removeView(view);  // Remove view from it's current parent

      if (getResources().getResourceEntryName(view.getId()).
          equalsIgnoreCase("sq2x2_a")) {                // Big square type need special considerations
        String newHomeId = "";
        switch (grid.moveDirectionForSquareType2x2(
                  Position.point(GridHelper.row(homeId), GridHelper.column(homeId)),
                  Position.point(GridHelper.row(dropId), GridHelper.column(dropId)))) {
        case RIGHT:
          newHomeId = String.format("r%sc%s", GridHelper.row(homeId), GridHelper.column(homeId) + 1);
          ((ViewGroup) findViewById(getResources().getIdentifier(newHomeId, "id", getPackageName()))).addView(view);
          reSizeSquareViews(false);
          break;
        case LEFT:
          newHomeId = String.format("r%sc%s", GridHelper.row(homeId), GridHelper.column(homeId) - 1);
          ((ViewGroup) findViewById(getResources().getIdentifier(newHomeId, "id", getPackageName()))).addView(view);
          reSizeSquareViews(false);
          break;
        case UP:
          newHomeId = String.format("r%sc%s", GridHelper.row(homeId) - 1, GridHelper.column(homeId));
          ((ViewGroup) findViewById(getResources().getIdentifier(newHomeId, "id", getPackageName()))).addView(view);
          reSizeSquareViews(false);
          break;
        case DOWN:
          newHomeId = String.format("r%sc%s", GridHelper.row(homeId) + 1, GridHelper.column(homeId));
          ((ViewGroup) findViewById(getResources().getIdentifier(newHomeId, "id", getPackageName()))).addView(view);
          reSizeSquareViews(false);
          break;
        default:
          droppedIn.addView(view);  // This should not happen, but in case, we will see some incorrect visual effect
          break;
        }
      }
      else {
        droppedIn.addView(view);
      }
    }
  }

  private void reSizeSquareViews(boolean addDropListener) {
    makeAllCellsVisiable();
    Point screenSize = new Point();
    getWindowManager().getDefaultDisplay().getSize(screenSize);
    int oneEightScreenHeight = (int) (screenSize.y * 0.125);
    int quarterScreenWidth = oneEightScreenHeight; // Square shape needed
    resizeView("r0c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r0c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r0c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r0c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r1c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r1c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r1c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r1c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r2c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r2c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r2c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r2c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r3c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r3c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r3c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r3c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r4c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r4c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r4c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r4c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);

    resizeView("r5c0", quarterScreenWidth, oneEightScreenHeight, false, addDropListener);
    resizeView("r5c1", quarterScreenWidth, oneEightScreenHeight, false, addDropListener);
    resizeView("r5c2", quarterScreenWidth, oneEightScreenHeight, false, addDropListener);
    resizeView("r5c3", quarterScreenWidth, oneEightScreenHeight, false, addDropListener);

    resizeView("r6c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r6c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r6c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r6c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);

    resizeView("r7c0", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r7c1", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r7c2", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
    resizeView("r7c3", quarterScreenWidth, oneEightScreenHeight, true, addDropListener);
  }

  private void resizeView(String id, int width, int height, boolean dropable, boolean addDropListener) {
    View cell = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
    if (cell != null) {
      if (addDropListener && dropable) {
        cell.setOnDragListener(new DragListener());
      }
      cell.getLayoutParams().width = width;
      cell.getLayoutParams().height = height;
      if (((ViewGroup) cell).getChildCount() > 0) {
        View child = ((ViewGroup) cell).getChildAt(0);
        if (child.getId() == R.id.sq2x2_a ||
            child.getId() == R.id.sq2x2_b) {
          cell.getLayoutParams().width = width * 2;
          cell.getLayoutParams().height = height * 2;
          // Let's invisible other three covered cells
          String other3cell = GridHelper.findOtherThreeCell(id+"");
          if (other3cell != null) {
            String[] cells = other3cell.split(",");
            for (String c : cells) {
              View view = findViewById(getResources().getIdentifier(c, "id", getPackageName()));
              if (view != null) {
                view.setVisibility(View.INVISIBLE);
              }
            }
          }
          GridLayout.LayoutParams params = new GridLayout.LayoutParams(cell.getLayoutParams());
          params.rowSpec = GridLayout.spec(GridHelper.row(id), 2);
          params.columnSpec = GridLayout.spec(GridHelper.column(id), 2);
          cell.setLayoutParams(params);
        }
      }
    }
  }

  private void makeAllCellsVisiable() {
    findViewById(R.id.r0c0).setVisibility(View.VISIBLE);
    findViewById(R.id.r1c0).setVisibility(View.VISIBLE);
    findViewById(R.id.r2c0).setVisibility(View.VISIBLE);
    findViewById(R.id.r3c0).setVisibility(View.VISIBLE);
    findViewById(R.id.r4c0).setVisibility(View.VISIBLE);
    findViewById(R.id.r0c1).setVisibility(View.VISIBLE);
    findViewById(R.id.r1c1).setVisibility(View.VISIBLE);
    findViewById(R.id.r2c1).setVisibility(View.VISIBLE);
    findViewById(R.id.r3c1).setVisibility(View.VISIBLE);
    findViewById(R.id.r4c1).setVisibility(View.VISIBLE);
    findViewById(R.id.r0c2).setVisibility(View.VISIBLE);
    findViewById(R.id.r1c2).setVisibility(View.VISIBLE);
    findViewById(R.id.r2c2).setVisibility(View.VISIBLE);
    findViewById(R.id.r3c2).setVisibility(View.VISIBLE);
    findViewById(R.id.r4c2).setVisibility(View.VISIBLE);
    findViewById(R.id.r0c3).setVisibility(View.VISIBLE);
    findViewById(R.id.r1c3).setVisibility(View.VISIBLE);
    findViewById(R.id.r2c3).setVisibility(View.VISIBLE);
    findViewById(R.id.r3c3).setVisibility(View.VISIBLE);
    findViewById(R.id.r4c3).setVisibility(View.VISIBLE);
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
      resetLevel(item);
      break;
    default:
      break;
    }
    return super.onOptionsItemSelected(item);
  }

  public void resetLevel(MenuItem item) {
    if (Build.VERSION.SDK_INT >= 11) {
      recreate();
    }
    else {
      Intent intent = getIntent();
      finish();
      startActivity(intent);
    }
  }

}