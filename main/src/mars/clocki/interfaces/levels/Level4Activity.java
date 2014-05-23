package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level4Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level4);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initViewFields();
    grid = GridContainer.initLevel4();
    initGridLayout(true);

    moveCount = 0;
    moveView.setText(moveCount + "");

    instance = this;
  }

  @Override
  protected String level() {
    return LEVEL4;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedEditor();
    editor.putBoolean(LEVEL4, true);
    editor.putInt(LEVEL4_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL4_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL4_SCORE, moveCount);
    }
    editor.commit();
  }

}