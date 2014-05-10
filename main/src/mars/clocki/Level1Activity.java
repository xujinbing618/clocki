package mars.clocki;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_EXITED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class Level1Activity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level1);
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

    decorateViewLayout();
  }


  private final class DragTouchListener implements OnTouchListener {
    public boolean onTouch(View view, MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        ClipData data = ClipData.newPlainText("", "");
        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        return true;
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
        owner.removeView(view);
        LinearLayout container = (LinearLayout) v;
        container.addView(view);
//        view.setVisibility(View.VISIBLE);
        decorateViewLayout();
        break;
      case ACTION_DRAG_ENDED:
        v.setBackground(normalShape);
        break;
      default:
        break;
      }
      return true;
    }
  }

  private void decorateViewLayout() {
    makeAllCellsVisiable();
    Point screenSize = new Point();
    getWindowManager().getDefaultDisplay().getSize(screenSize);
    int quarterScreenWidth = (int) (screenSize.x * 0.25);
    int oneEightScreenHeight = (int) (screenSize.y * 0.125);
    if (oneEightScreenHeight * 4 < screenSize.x) { // High likely to make Square
      quarterScreenWidth = oneEightScreenHeight;
    }
    resizeView("r0c0", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r0c1", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r0c2", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r0c3", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r1c0", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r1c1", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r1c2", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r1c3", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r2c0", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r2c1", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r2c2", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r2c3", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r3c0", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r3c1", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r3c2", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r3c3", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r4c0", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r4c1", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r4c2", quarterScreenWidth, oneEightScreenHeight, true);
    resizeView("r4c3", quarterScreenWidth, oneEightScreenHeight, true);


    resizeView("r5c0", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r5c1", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r5c2", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r5c3", quarterScreenWidth, oneEightScreenHeight, false);

    resizeView("r6c0", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r6c1", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r6c2", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r6c3", quarterScreenWidth, oneEightScreenHeight, false);

    resizeView("r7c0", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r7c1", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r7c2", quarterScreenWidth, oneEightScreenHeight, false);
    resizeView("r7c3", quarterScreenWidth, oneEightScreenHeight, false);
  }

  private void resizeView(String id, int width, int height, boolean dropable) {
    View cell = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
    if (cell != null) {
      if (dropable) {
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
          // Let's remove other three cell
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

}