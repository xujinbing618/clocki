package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.domain.model.GridContainer;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level2Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level2);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initViewFields();
    grid = GridContainer.initLevel2();
    initGridLayout(true);

    moveCount = 0;
    moveView.setText(moveCount + "");

    instance = this;
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