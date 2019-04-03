package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.android.AndroidNavigationUi;
import lib.ui.ios.iOSNavigationUi;

public class NavigationUiFactory {

    public static NavigationUi get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver);
        } else {
            return new iOSNavigationUi(driver);
        }
    }
}
