package mars.clocki.interfaces;

import java.util.Locale;

import mars.clocki.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AbstractActivity
    implements OnItemSelectedListener {

  private ArrayAdapter<CharSequence> languageAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(R.layout.activity_settings);

    Spinner languageSpinner = (Spinner) findViewById(R.id.language_spinner);
    languageAdapter = ArrayAdapter.createFromResource(
        this,
        R.array.langauges_array,
        android.R.layout.simple_spinner_dropdown_item);
    languageSpinner.setAdapter(languageAdapter);
    languageSpinner.setOnItemSelectedListener(this);
    languageSpinner.setSelection(currentSelectedPosition());
  }

  private int currentSelectedPosition() {
    if (currentLocale().equals(Locale.ENGLISH)) {
      return languageAdapter.getPosition(
          getResources().getString(R.string.english));
    }
    else if (currentLocale().equals(new Locale("fa"))) {
      return languageAdapter.getPosition(
          getResources().getString(R.string.farsi));
    }
    return 0;
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position,
      long id) {
    String languageLabel = (String) parent.getItemAtPosition(position);
    if (languageLabel.equalsIgnoreCase(
        getResources().getString(R.string.english))) {
      if (!currentLocale().equals(Locale.ENGLISH)) {
        setLocale(Locale.ENGLISH);
      }
    }
    else if (languageLabel.equalsIgnoreCase(
        getResources().getString(R.string.farsi))) {
      if (!currentLocale().equals(new Locale("fa"))) {
        setLocale(new Locale("fa"));
      }
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
  }

}