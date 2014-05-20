package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level1Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level1);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initViewFields();
    grid = GridContainer.initLevel1();
    initGridLayout(true);

    moveCount = 0;
    moveView.setText(moveCount + "");

    instance = this;
  }

  @Override
  protected String level() {
    return LEVEL1;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedEditor();
    editor.putBoolean(LEVEL1, true);
    editor.putInt(LEVEL1_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL1_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL1_SCORE, moveCount);
    }
    editor.commit();
  }

}