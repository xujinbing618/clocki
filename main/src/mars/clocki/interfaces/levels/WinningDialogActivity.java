package mars.clocki.interfaces.levels;

import mars.clocki.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
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
    ((TextView) findViewById(R.id.winning_text_id)).
                setText(
                  getResources().
                  getString(R.string.the_puzzle_has_been_solved) + ": " +
                  lastRecord()
                );
    reSizeLayoutAndOkayButton();
  }

  public void closeDialog(View v) {
    if (cameFrom(LevelActivity.LEVEL1)) {
      Level1Activity.instance.finish();
    }
    else if (cameFrom(LevelActivity.LEVEL2)) {
      Level2Activity.instance.finish();
    }
    else if (cameFrom(LevelActivity.LEVEL3)) {
      Level3Activity.instance.finish();
    }
    else if (cameFrom(LevelActivity.LEVEL4)) {
      Level4Activity.instance.finish();
    }
    WinningDialogActivity.this.finish();
  }

  private int lastRecord() {
    if (cameFrom(LevelActivity.LEVEL1)) {
      return getSharedPref().getInt(LevelActivity.LEVEL1_LAST, 0);
    }
    else if (cameFrom(LevelActivity.LEVEL2)) {
      return getSharedPref().getInt(LevelActivity.LEVEL2_LAST, 0);
    }
    else if (cameFrom(LevelActivity.LEVEL3)) {
      return getSharedPref().getInt(LevelActivity.LEVEL3_LAST, 0);
    }
    else if (cameFrom(LevelActivity.LEVEL4)) {
      return getSharedPref().getInt(LevelActivity.LEVEL4_LAST, 0);
    }
    return -1;
  }

  private boolean cameFrom(String level) {
    return getIntent().
           getStringExtra(LevelActivity.LEVEL).
           equalsIgnoreCase(level);
  }

  private SharedPreferences getSharedPref() {
    return getApplicationContext().
           getSharedPreferences(LevelActivity.SCORE_FILE_KEY,
                                Context.MODE_PRIVATE);
  }

  private void reSizeLayoutAndOkayButton() {
    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);
    int dialogWidth = (int) (point.x * 0.50);
    int dialogHeight = (int) (point.y * 0.50);
    View winningLayout = findViewById(R.id.winning_dialog_layout_id);
    winningLayout.getLayoutParams().width = dialogWidth;
    winningLayout.getLayoutParams().height = dialogHeight;

    View okayButton = findViewById(R.id.winning_dialog_btn_id);
    okayButton.getLayoutParams().width = (int) (dialogWidth * 0.5);
    okayButton.getLayoutParams().height = (int) (dialogWidth * 0.5);
  }

}