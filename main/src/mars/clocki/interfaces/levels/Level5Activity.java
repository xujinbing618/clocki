package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level5Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    grid = GridContainer.initLevel5();
  }

  @Override
  protected String level() {
    return LEVEL5;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedEditor();
    editor.putBoolean(LEVEL5, true);
    editor.putInt(LEVEL5_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL5_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL5_SCORE, moveCount);
    }
    editor.commit();
  }

}