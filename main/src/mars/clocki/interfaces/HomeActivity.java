package mars.clocki.interfaces;

import mars.clocki.R;
import mars.clocki.interfaces.levels.Level1Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.nav, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
//    int id = item.getItemId();
//    if (id == R.id.action_settings) {
//      return true;
//    }
    return super.onOptionsItemSelected(item);
  }

  public void startLevel1(View view) {
    Intent intent = new Intent(HomeActivity.this, Level1Activity.class);
    startActivity(intent);
  }

}