package mars.clocki.interfaces;

import mars.clocki.R;
import mars.clocki.interfaces.levels.Level1Activity;
import mars.clocki.interfaces.levels.Level2Activity;
import mars.clocki.interfaces.levels.LevelActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

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

  public void exit(View view) {
    HomeActivity.this.finish();
  }

  public void checkScores() {
    SharedPreferences sharedPref = getApplicationContext().
        getSharedPreferences(LevelActivity.SCORE_FILE_KEY, Context.MODE_PRIVATE);
    if (sharedPref.getBoolean(LevelActivity.LEVEL1, false)) {
      Button level1Btn = (Button) findViewById(R.id.level1_btn);
      level1Btn.setBackground(getResources().
                              getDrawable(R.drawable.square_green_border));
      level1Btn.setText(getResources().getString(R.string.i_only_18_steps)
          + " ... " + score(LevelActivity.LEVEL1_SCORE));
    }
    if (sharedPref.getBoolean(LevelActivity.LEVEL2, false)) {
      Button level1Btn = (Button) findViewById(R.id.level2_btn);
      level1Btn.setBackground(getResources().
          getDrawable(R.drawable.square_green_border));
      level1Btn.setText(getResources().getString(R.string.ii_daisy)
          + " .......... " + score(LevelActivity.LEVEL2_SCORE));
    }
  }

  public int score(String level_key) {
    SharedPreferences sharedPref = getApplicationContext().
        getSharedPreferences(LevelActivity.SCORE_FILE_KEY, Context.MODE_PRIVATE);
    return sharedPref.getInt(level_key, 0);
  }

}