package mars.clocki.interfaces;

import java.util.Locale;

import mars.clocki.application.CS;
import mars.clocki.application.util.BuildHelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public abstract class AbstractActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setLocale(currentLocale());
    super.onCreate(savedInstanceState);
  }

  protected void resetActivity() {
    if (BuildHelper.api11orHigher()) {
      recreate();
    }
    else {
      Intent intent = getIntent();
      finish();
      startActivity(intent);
    }
  }

  protected Locale currentLocale() {
    Locale defaultLocale = Locale.getDefault();
    if (defaultLocale.equals(Locale.ENGLISH)) {
    }
    else if (defaultLocale.equals(new Locale("fa"))) {
    }
    else {
      defaultLocale = Locale.ENGLISH;
    }
    return new Locale(
        getSharedPref().
        getString(CS.LOCALE_KEY, defaultLocale.getLanguage())
      );
  }

  protected void setLocale(Locale locale) {

    SharedPreferences.Editor editor = getSharedEditor();
    editor.putString(CS.LOCALE_KEY, locale.getLanguage());
    editor.commit();

    Configuration conf = getBaseContext().getResources().getConfiguration();
    if (!conf.locale.equals(currentLocale())) {
      Locale.setDefault(currentLocale());
      conf.locale = currentLocale();
      getBaseContext().getResources().updateConfiguration(conf,
          getBaseContext().getResources().getDisplayMetrics());
      resetActivity();
    }
  }

  protected SharedPreferences getSharedPref() {
    return getApplicationContext().
        getSharedPreferences(CS.SCORE_FILE_KEY, Context.MODE_PRIVATE);
  }

  protected SharedPreferences.Editor getSharedEditor() {
    return getSharedPref().edit();
  }

}