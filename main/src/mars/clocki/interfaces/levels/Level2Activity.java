package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level2Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    grid = GridContainer.initLevel2();
  }

  @Override
  protected String level() {
    return LEVEL2;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedPref().edit();
    editor.putBoolean(LEVEL2, true);
    editor.putInt(LEVEL2_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL2_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL2_SCORE, moveCount);
    }
    editor.commit();
  }

}