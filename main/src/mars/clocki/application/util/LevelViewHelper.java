package mars.clocki.application.util;

import mars.clocki.R;
import mars.clocki.application.CS;
import mars.clocki.interfaces.levels.Level1Activity;
import mars.clocki.interfaces.levels.Level2Activity;
import mars.clocki.interfaces.levels.Level3Activity;
import mars.clocki.interfaces.levels.Level4Activity;
import mars.clocki.interfaces.levels.Level5Activity;
import mars.clocki.interfaces.levels.Level6Activity;
import mars.clocki.interfaces.levels.Level7Activity;
import mars.clocki.interfaces.levels.Level8Activity;
import mars.clocki.interfaces.levels.Level9Activity;

public class LevelViewHelper {

  public static int activityViewId(String level) {
    if (level.equalsIgnoreCase(CS.LEVEL1)) {
      return R.layout.activity_level1;
    }
    if (level.equalsIgnoreCase(CS.LEVEL2)) {
      return R.layout.activity_level2;
    }
    if (level.equalsIgnoreCase(CS.LEVEL3)) {
      return R.layout.activity_level3;
    }
    if (level.equalsIgnoreCase(CS.LEVEL4)) {
      return R.layout.activity_level4;
    }
    if (level.equalsIgnoreCase(CS.LEVEL5)) {
      return R.layout.activity_level5;
    }
    if (level.equalsIgnoreCase(CS.LEVEL6)) {
      return R.layout.activity_level6;
    }
    if (level.equalsIgnoreCase(CS.LEVEL7)) {
      return R.layout.activity_level7;
    }
    if (level.equalsIgnoreCase(CS.LEVEL8)) {
      return R.layout.activity_level8;
    }
    if (level.equalsIgnoreCase(CS.LEVEL9)) {
      return R.layout.activity_level9;
    }
    throw new RuntimeException("View id not found for current level: " + level);
  }

  public static void finishActivity(String level) {
    if (level.equalsIgnoreCase(CS.LEVEL1)) {
      Level1Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL2)) {
      Level2Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL3)) {
      Level3Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL4)) {
      Level4Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL5)) {
      Level5Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL6)) {
      Level6Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL7)) {
      Level7Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL8)) {
      Level8Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(CS.LEVEL9)) {
      Level9Activity.instance.finish();
    }
    else {
      throw new RuntimeException("Activity not found for level: " + level);
    }
  }

}