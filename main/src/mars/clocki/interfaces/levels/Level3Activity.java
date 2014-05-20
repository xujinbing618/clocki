package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level3Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level3);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initViewFields();
    grid = GridContainer.initLevel3();
    initGridLayout(true);

    moveCount = 0;
    moveView.setText(moveCount + "");

    instance = this;
  }

  @Override
  protected String level() {
    return LEVEL3;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedPref().edit();
    editor.putBoolean(LEVEL3, true);
    editor.putInt(LEVEL3_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL3_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL3_SCORE, moveCount);
    }
    editor.commit();
  }

}