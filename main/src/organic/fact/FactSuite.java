package organic.fact;

import organic.fact.tests.GridHelperTest;

import junit.framework.TestSuite;

public class FactSuite extends TestSuite {

  public FactSuite() {
    addTestSuite(GridHelperTest.class);
  }

}