package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.os.Bundle;

public class Level4Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    grid = GridContainer.initLevel4();
    super.onCreate(savedInstanceState);
  }

}