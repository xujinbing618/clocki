package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Level7Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    grid = GridContainer.initLevel7();
  }

  @Override
  protected String level() {
    return LEVEL7;
  }

  @Override
  protected void writeScore() {
    SharedPreferences.Editor editor = getSharedEditor();
    editor.putBoolean(LEVEL7, true);
    editor.putInt(LEVEL7_LAST, moveCount);
    int score = getSharedPref().getInt(LEVEL7_SCORE, 0);
    if (score == 0 || score > moveCount) {
      editor.putInt(LEVEL7_SCORE, moveCount);
    }
    editor.commit();
  }

}