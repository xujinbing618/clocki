package mars.clocki.interfaces;

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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    checkScores();
  }

  @Override
  protected void onResume() {
    super.onResume();
    checkScores();
  }

  public void startLevel1(View view) {
    startActivity(new Intent(HomeActivity.this, Level1Activity.class));
  }

  public void startLevel2(View view) {
    startActivity(new Intent(HomeActivity.this, Level2Activity.class));
  }

  public void startLevel3(View view) {
    startActivity(new Intent(HomeActivity.this, Level3Activity.class));
  }

  public void startLevel4(View view) {
    startActivity(new Intent(HomeActivity.this, Level4Activity.class));
  }

  public void startLevel5(View view) {
    startActivity(new Intent(HomeActivity.this, Level5Activity.class));
  }

  public void startLevel6(View view) {
    startActivity(new Intent(HomeActivity.this, Level6Activity.class));
  }

  public void startLevel7(View view) {
    startActivity(new Intent(HomeActivity.this, Level7Activity.class));
  }

  public void startLevel8(View view) {
    startActivity(new Intent(HomeActivity.this, Level8Activity.class));
  }

  public void exit(View view) {
    HomeActivity.this.finish();
    Process.killProcess(Process.myPid());
  }

  public void checkScores() {
    SharedPreferences sharedPref = getSharedPref();
    if (sharedPref.getBoolean(CS.LEVEL1, false)) {
      setScore(R.id.level1_btn, CS.level1Score(), R.id.level1_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL2, false)) {
      setScore(R.id.level2_btn, CS.level2Score(), R.id.level2_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL3, false)) {
      setScore(R.id.level3_btn, CS.level3Score(), R.id.level3_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL4, false)) {
      setScore(R.id.level4_btn, CS.level4Score(), R.id.level4_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL5, false)) {
      setScore(R.id.level5_btn, CS.level5Score(), R.id.level5_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL6, false)) {
      setScore(R.id.level6_btn, CS.level6Score(), R.id.level6_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL7, false)) {
      setScore(R.id.level7_btn, CS.level7Score(), R.id.level7_score);
    }
    if (sharedPref.getBoolean(CS.LEVEL8, false)) {
      setScore(R.id.level8_btn, CS.level7Score(), R.id.level8_score);
    }
  }

  public void setScore(int buttonId, String scoreKey, int textViewId) {
    setBackgroundGreen(buttonId);
    setScoreNumber(scoreKey, textViewId);
  }

  public void setBackgroundGreen(int buttonId) {
    findViewById(buttonId).setBackground(
        getResources().
        getDrawable(R.drawable.square_green_border)
    );
  }

  public void setScoreNumber(String levelScoreKey,
                             int levelLabelId) {
    ((TextView) findViewById(levelLabelId)).setText(score(levelScoreKey) + "");
  }

  public int score(String scoreKey) {
    return getSharedPref().getInt(scoreKey, 0);
  }

  private SharedPreferences getSharedPref() {
    return getApplicationContext().
             getSharedPreferences(
                 CS.SCORE_FILE_KEY,
                 Context.MODE_PRIVATE);
  }

}