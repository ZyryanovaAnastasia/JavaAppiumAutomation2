package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class AndroidNavigationUi extends NavigationUi {

    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
        TITLE_MY_LISTS = "xpath://*[@text='My lists']";
    }

    public AndroidNavigationUi(AppiumDriver driver)
    {
        super(driver);
    }
}
