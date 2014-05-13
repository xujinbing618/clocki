package mars.clocki.interfaces;

import mars.clocki.R;
import mars.clocki.interfaces.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {

  private HomeActivity homeActivity;
  private TextView welcomeText;

  public HomeActivityTest() {
    super(HomeActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    homeActivity = getActivity();
    welcomeText = (TextView) homeActivity.findViewById(R.id.welcome_label_id);
  }

  public void testPreconditions() {
    assertNotNull("homeActivity is null", homeActivity);
    assertNotNull("welcomeText is null", welcomeText);
  }

  public void testFirstTextView_labelText() {
    final String expected = homeActivity.getString(R.string.welcome_to_clocki_game);
    final String actual = welcomeText.getText().toString();
    assertEquals(expected, actual);
  }

}