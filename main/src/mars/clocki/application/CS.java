package mars.clocki.application;

public class CS {

  public final static String SCORE_FILE_KEY = "SCORE_FILE";

  // Level constants
  public final static String LEVEL = "LEVEL";
  public final static String LEVEL1 = "LEVEL1";
  public final static String LEVEL2 = "LEVEL2";
  public final static String LEVEL3 = "LEVEL3";
  public final static String LEVEL4 = "LEVEL4";
  public final static String LEVEL5 = "LEVEL5";
  public final static String LEVEL6 = "LEVEL6";
  public final static String LEVEL7 = "LEVEL7";
  public final static String LEVEL8 = "LEVEL8";

  public static String levelLastKey(String level) {
    return String.format("%s_LAST", level);
  }

  public static String levelScoreKey(String level) {
    return String.format("%s_SCORE", level);
  }

  public static String level1Score() { return levelScoreKey(LEVEL1); }
  public static String level2Score() { return levelScoreKey(LEVEL2); }
  public static String level3Score() { return levelScoreKey(LEVEL3); }
  public static String level4Score() { return levelScoreKey(LEVEL4); }
  public static String level5Score() { return levelScoreKey(LEVEL5); }
  public static String level6Score() { return levelScoreKey(LEVEL6); }
  public static String level7Score() { return levelScoreKey(LEVEL7); }
  public static String level8Score() { return levelScoreKey(LEVEL8); }

  public static String level1Last() { return levelLastKey(LEVEL1); }
  public static String level2Last() { return levelLastKey(LEVEL2); }
  public static String level3Last() { return levelLastKey(LEVEL3); }
  public static String level4Last() { return levelLastKey(LEVEL4); }
  public static String level5Last() { return levelLastKey(LEVEL5); }
  public static String level6Last() { return levelLastKey(LEVEL6); }
  public static String level7Last() { return levelLastKey(LEVEL7); }
  public static String level8Last() { return levelLastKey(LEVEL8); }

}