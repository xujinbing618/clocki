package mars.clocki.interfaces;

import mars.clocki.R;
import android.os.Bundle;

public class HelpActivity extends AbstractActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(R.layout.activity_help);
  }

}