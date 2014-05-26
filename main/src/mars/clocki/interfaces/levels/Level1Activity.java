package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level1Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    grid = GridContainer.initLevel1();
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