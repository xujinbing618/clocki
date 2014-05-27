package mars.clocki.interfaces.levels;

import mars.clocki.domain.model.GridContainer;
import android.os.Bundle;

public class Level9Activity extends LevelActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    grid = GridContainer.initLevel9();
    super.onCreate(savedInstanceState);
  }

}