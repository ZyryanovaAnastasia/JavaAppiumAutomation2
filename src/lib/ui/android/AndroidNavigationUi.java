package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class AndroidNavigationUi extends NavigationUi {

    static {
        MY_LISTS_LINK = "id:org.wikipedia:id/view_page_title_text";
    }

    public AndroidNavigationUi(AppiumDriver driver)
    {
        super(driver);
    }
}
