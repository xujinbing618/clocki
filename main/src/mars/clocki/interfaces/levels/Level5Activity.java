package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.os.Bundle;

public class Level5Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    grid = GridContainer.initLevel5();
    super.onCreate(savedInstanceState);
  }

}