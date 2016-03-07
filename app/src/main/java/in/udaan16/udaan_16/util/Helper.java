package in.udaan16.udaan_16.util;

import android.content.Context;
import android.content.SharedPreferences;

import in.udaan16.udaan_16.BuildConfig;
import in.udaan16.udaan_16.R;

/**
 * Creator: vbarad
 * Date: 2016-03-07
 * Project: udaan16-android-app
 */
public class Helper {

  /**
   * Indicates that this is a fresh install of the application
   */
  public static int LAUNCH_FIRST = -1;

  /**
   * Indicates that this is a normal starting of the application
   */
  public static int LAUNCH_NORMAL = 0;

  /**
   * Indicates that this is a first start after last upgrade
   */
  public static int LAUNCH_UPGRADE = 1;

  /**
   * This function checks whether this is the first launch of application
   * after either first install or any upgrade or any normal launch
   *
   * @param context Context which the method uses to find current version and the saved version
   * @return LAUNCH_FIRST if the app is a fresh install
   * <br /> LAUNCH_UPGRADE if it is launched first time after an upgrade
   * <br /> LAUNCH_NORMAL if it just another normal launch
   */
  public static int isFirstInstall(Context context) {
    final String preferencesFileName = context.getString(R.string.prefs_file);
    final String preferenceVersionCodeKey = context.getString(R.string.prefs_key_version_code);

    final int DOES_NOT_EXIST = -1;

    // Get current version code
    int currentVersionCode = BuildConfig.VERSION_CODE;

    // Get saved version code
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE);
    int savedVersionCode = sharedPreferences.getInt(preferenceVersionCodeKey, DOES_NOT_EXIST);

    // Update the current version code in SharedPreference
    sharedPreferences.edit().putInt(preferenceVersionCodeKey, currentVersionCode).apply();

    // Check for first run, upgrade or normal run
    if (savedVersionCode == DOES_NOT_EXIST) {
      return Helper.LAUNCH_FIRST;
    } else if (savedVersionCode < currentVersionCode) {
      return Helper.LAUNCH_UPGRADE;
    } else {
      return Helper.LAUNCH_NORMAL;
    }
  }
}
