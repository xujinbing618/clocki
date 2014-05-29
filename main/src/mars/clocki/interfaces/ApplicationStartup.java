package mars.clocki.interfaces;

import mars.clocki.R;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

@ReportsCrashes(formKey = "",
                mailTo = "webebook.org@gmail.com",
                mode = ReportingInteractionMode.TOAST,
                resToastText = R.string.crash_toast_text)
public class ApplicationStartup extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    ACRA.init(this);
  }

}