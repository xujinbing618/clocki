package mars.clocki.interfaces;

import mars.clocki.R;
import mars.clocki.interfaces.help.HelpPageFragment;
import mars.clocki.interfaces.help.IntroducationFragment;
import mars.clocki.interfaces.help.MovingBlocksFragment;
import mars.clocki.interfaces.help.ObjectiveFragment;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AbstractActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(R.layout.activity_help);

    if (savedInstanceState != null) {
      return;
    }

    HelpPageFragment fragment = new HelpPageFragment();
    fragment.setArguments(getIntent().getExtras());

    getSupportFragmentManager().
      beginTransaction().
      add(R.id.help_container, fragment, helpHomePage).
      commit();
  }

  public void goToIntroduction(View view) {
    getSupportFragmentManager().
      beginTransaction().
      replace(R.id.help_container, new IntroducationFragment()).
      addToBackStack(null).
      commit();
  }

  public void goToObjective(View view) {
    getSupportFragmentManager().
      beginTransaction().
      replace(R.id.help_container, new ObjectiveFragment()).
      addToBackStack(null).
      commit();
  }

  public void goToMovingBlocks(View view) {
    getSupportFragmentManager().
      beginTransaction().
      replace(R.id.help_container, new MovingBlocksFragment()).
      addToBackStack(null).
      commit();
  }

  public void backTo(View view) {
    getSupportFragmentManager().
    beginTransaction().
      replace(R.id.help_container,
              getSupportFragmentManager().
                findFragmentByTag(helpHomePage)).
      commit();
  }

  private final String helpHomePage = "helpHomePage";

}