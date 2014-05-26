package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level4Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    grid = GridContainer.initLevel4();
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