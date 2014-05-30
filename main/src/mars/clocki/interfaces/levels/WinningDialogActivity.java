package mars.clocki.interfaces.levels;

import mars.clocki.R;
import mars.clocki.application.CS;
import mars.clocki.application.util.LevelViewHelper;
import mars.clocki.interfaces.AbstractActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
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
    this.setFinishOnTouchOutside(false);
  }

  public void closeDialog(View v) {
    LevelViewHelper.finishActivity(level());
    WinningDialogActivity.this.finish();
  }

  private int lastRecord() {
    return getSharedPref().getInt(CS.levelLastKey(level()), 0);
  }

  /**
   * Duplicate of {@link AbstractActivity#getSharedPref()}
   * Unfortunately this class could not extends AbstractActivity.
   * TODO: find a way to share SharedPreferences in one place.
   */
  protected SharedPreferences getSharedPref() {
    return getApplicationContext().
        getSharedPreferences(CS.SCORE_FILE_KEY, Context.MODE_PRIVATE);
  }

  private String level() {
    return getIntent().
           getStringExtra(CS.LEVEL);
  }

  @SuppressLint("NewApi")
  private void reSizeLayoutAndOkayButton() {
    int dialogWidth = 0;
    int dialogHeight = 0;
    if (Build.VERSION.SDK_INT >= 13) {
      Point point = new Point();
      getWindowManager().getDefaultDisplay().getSize(point);
      dialogWidth = (int) (point.x * 0.50);
      dialogHeight = (int) (point.y * 0.50);
    }
    else {
      @SuppressWarnings("deprecation")
      int width = getWindowManager().getDefaultDisplay().getHeight();
      dialogWidth = (int) (width * 0.50);
      @SuppressWarnings("deprecation")
      int height = getWindowManager().getDefaultDisplay().getWidth();
      dialogHeight = (int) (height * 0.50);
    }
    View winningLayout = findViewById(R.id.winning_dialog_layout_id);
    winningLayout.getLayoutParams().width = dialogWidth;
    winningLayout.getLayoutParams().height = dialogHeight;

    View okayButton = findViewById(R.id.winning_dialog_btn_id);
    okayButton.getLayoutParams().width = (int) (dialogWidth * 0.5);
    okayButton.getLayoutParams().height = (int) (dialogWidth * 0.5);
  }

}