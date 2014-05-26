package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level6Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    grid = GridContainer.initLevel6();
  }

  @Override
  protected String level() {
    return LEVEL6;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedEditor();
    editor.putBoolean(LEVEL6, true);
    editor.putInt(LEVEL6_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL6_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL6_SCORE, moveCount);
    }
  }

}