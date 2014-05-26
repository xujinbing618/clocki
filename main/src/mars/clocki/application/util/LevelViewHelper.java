package mars.clocki.application.util;

import mars.clocki.R;
import mars.clocki.interfaces.levels.Level1Activity;
import mars.clocki.interfaces.levels.Level2Activity;
import mars.clocki.interfaces.levels.Level3Activity;
import mars.clocki.interfaces.levels.Level4Activity;
import mars.clocki.interfaces.levels.Level5Activity;
import mars.clocki.interfaces.levels.LevelActivity;

public class LevelViewHelper {

  public static int activityViewId(String level) {
    if (level.equalsIgnoreCase(LevelActivity.LEVEL1)) {
      return R.layout.activity_level1;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL2)) {
      return R.layout.activity_level2;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL3)) {
      return R.layout.activity_level3;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL4)) {
      return R.layout.activity_level4;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL5)) {
      return R.layout.activity_level5;
    }
    throw new RuntimeException("View id not found for current level: " + level);
  }

  public static void finishActivity(String level) {
    if (level.equalsIgnoreCase(LevelActivity.LEVEL1)) {
      Level1Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(LevelActivity.LEVEL2)) {
      Level2Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(LevelActivity.LEVEL3)) {
      Level3Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(LevelActivity.LEVEL4)) {
      Level4Activity.instance.finish();
    }
    else if (level.equalsIgnoreCase(LevelActivity.LEVEL5)) {
      Level5Activity.instance.finish();
    }
    else {
      throw new RuntimeException("Activity not found for level: " + level);
    }
  }

  public static String levelKeyForLastScore(String level) {
    if (level.equalsIgnoreCase(LevelActivity.LEVEL1)) {
      return LevelActivity.LEVEL1_LAST;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL2)) {
      return LevelActivity.LEVEL2_LAST;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL3)) {
      return LevelActivity.LEVEL3_LAST;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL4)) {
      return LevelActivity.LEVEL4_LAST;
    }
    if (level.equalsIgnoreCase(LevelActivity.LEVEL5)) {
      return LevelActivity.LEVEL5_LAST;
    }
    throw new RuntimeException("Level key(last score) not found for level: " + level);
  }

}