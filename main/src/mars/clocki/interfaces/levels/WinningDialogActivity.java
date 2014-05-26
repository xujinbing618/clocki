package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.application.util.LevelViewHelper;
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
    LevelViewHelper.finishActivity(level());
    WinningDialogActivity.this.finish();
  }

  private int lastRecord() {
    return getSharedPref().getInt(LevelViewHelper.levelKeyForLastScore(level()), 0);
  }

  private String level() {
    return getIntent().
           getStringExtra(LevelActivity.LEVEL);
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