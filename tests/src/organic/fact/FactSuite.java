package organic.fact;

import mars.clocki.application.util.GridHelperTest;
import mars.clocki.domain.model.GridContainerTest;

import junit.framework.TestSuite;

public class FactSuite extends TestSuite {

  public FactSuite() {
    addTestSuite(GridHelperTest.class);
    addTestSuite(GridContainerTest.class);
  }

}