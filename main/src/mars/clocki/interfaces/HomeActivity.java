package mars.clocki.interfaces;

import mars.clocki.R;
import mars.clocki.interfaces.levels.Level1Activity;
import mars.clocki.interfaces.levels.Level2Activity;
import mars.clocki.interfaces.levels.Level3Activity;
import mars.clocki.interfaces.levels.LevelActivity;
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

  public void exit(View view) {
    HomeActivity.this.finish();
    Process.killProcess(Process.myPid());
  }

  public void checkScores() {
    SharedPreferences sharedPref = getSharedPref();
    if (sharedPref.getBoolean(LevelActivity.LEVEL1, false)) {
      setBackgroundGreen(R.id.level1_btn);
      setScoreNumber(LevelActivity.LEVEL1_SCORE, R.id.level1_score);
    }
    if (sharedPref.getBoolean(LevelActivity.LEVEL2, false)) {
      setBackgroundGreen(R.id.level2_btn);
      setScoreNumber(LevelActivity.LEVEL2_SCORE, R.id.level2_score);
    }
    if (sharedPref.getBoolean(LevelActivity.LEVEL3, false)) {
      setBackgroundGreen(R.id.level3_btn);
      setScoreNumber(LevelActivity.LEVEL3_SCORE, R.id.level3_score);
    }
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

  public int score(String levelKey) {
    return getSharedPref().getInt(levelKey, 0);
  }

  private SharedPreferences getSharedPref() {
    return getApplicationContext().
             getSharedPreferences(
                 LevelActivity.SCORE_FILE_KEY,
                 Context.MODE_PRIVATE);
  }

}