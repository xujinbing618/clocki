package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.domain.model.GridContainer;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Level2Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level2);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    r0c0Cell.setOnDragListener(new DropListener());
    r0c1Cell.setOnDragListener(new DropListener());
    r0c2Cell.setOnDragListener(new DropListener());
    r0c3Cell.setOnDragListener(new DropListener());
    r1c0Cell.setOnDragListener(new DropListener());
    r1c1Cell.setOnDragListener(new DropListener());
    r1c2Cell.setOnDragListener(new DropListener());
    r1c3Cell.setOnDragListener(new DropListener());
    r2c0Cell.setOnDragListener(new DropListener());
    r2c1Cell.setOnDragListener(new DropListener());
    r2c2Cell.setOnDragListener(new DropListener());
    r2c3Cell.setOnDragListener(new DropListener());
    r3c0Cell.setOnDragListener(new DropListener());
    r3c1Cell.setOnDragListener(new DropListener());
    r3c2Cell.setOnDragListener(new DropListener());
    r3c3Cell.setOnDragListener(new DropListener());
    r4c0Cell.setOnDragListener(new DropListener());
    r4c1Cell.setOnDragListener(new DropListener());
    r4c2Cell.setOnDragListener(new DropListener());
    r4c3Cell.setOnDragListener(new DropListener());

    r6c1Cell.setOnDragListener(new DropListener());

    gridLayout = (GridLayout) findViewById(R.id.gridLevel2);
    moveCount = 0;
    moveView = (TextView) findViewById(R.id.moves);
    grid = GridContainer.initLevel2();
    initGridLayout(true);
  }

  @Override
  protected String level() {
    return LEVEL2;
  }

  @Override
  protected void writeScore() {
    SharedPreferences sharedPref = getApplicationContext().
        getSharedPreferences(SCORE_FILE_KEY, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putBoolean(LEVEL2, true);
    editor.putInt(LEVEL2_LAST, moveCount);
    int score = sharedPref.getInt(LEVEL2_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL2_SCORE, moveCount);
    }
    editor.commit();
  }

}