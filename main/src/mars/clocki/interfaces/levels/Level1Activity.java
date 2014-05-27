package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.os.Bundle;

public class Level1Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    grid = GridContainer.initLevel1();
    super.onCreate(savedInstanceState);
  }

}