package mars.clocki.interfaces;

import mars.clocki.R;
import mars.clocki.interfaces.FirstActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FirstActivityTest extends ActivityInstrumentationTestCase2<FirstActivity> {

  private FirstActivity firstActivity;
  private TextView firstText;

  public FirstActivityTest() {
    super(FirstActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    firstActivity = getActivity();
    firstText = (TextView) firstActivity.findViewById(R.id.my_first_text_view);
  }

  public void testPreconditions() {
    assertNotNull("firstActivity is null", firstActivity);
    assertNotNull("firstText is null", firstText);
  }

  public void testFirstTextView_labelText() {
    final String expected = firstActivity.getString(R.string.first_text);
    final String actual = firstText.getText().toString();
    assertEquals(expected, actual);
  }

}