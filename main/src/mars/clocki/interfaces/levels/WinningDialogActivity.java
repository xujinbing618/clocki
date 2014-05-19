package mars.clocki.interfaces.levels;

import mars.clocki.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class WinningDialogActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_winning_dialog);
    ((TextView) findViewById(R.id.winning_text_id)).setText(
        getResources().getString(R.string.the_puzzle_has_been_solved) + " ... " + lastRecord());
  }

  public void closeDialog(View v) {
    WinningDialogActivity.this.finish();
  }

  private int lastRecord() {
    if (getIntent().getStringExtra(LevelActivity.LEVEL).
        equalsIgnoreCase(LevelActivity.LEVEL1)) {
      SharedPreferences sharedPref = getApplicationContext().
          getSharedPreferences(LevelActivity.SCORE_FILE_KEY, Context.MODE_PRIVATE);
      return sharedPref.getInt(LevelActivity.LEVEL1_LAST, 0);
    }
    else if (getIntent().getStringExtra(LevelActivity.LEVEL).
        equalsIgnoreCase(LevelActivity.LEVEL2)) {
      SharedPreferences sharedPref = getApplicationContext().
          getSharedPreferences(LevelActivity.SCORE_FILE_KEY, Context.MODE_PRIVATE);
      return sharedPref.getInt(LevelActivity.LEVEL2_LAST, 0);
    }
    return -1;
  }

}