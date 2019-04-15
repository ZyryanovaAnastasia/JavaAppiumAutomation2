package lib.ui.mobile_web;

import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUi extends NavigationUi {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchList']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }

    public MWNavigationUi(RemoteWebDriver driver)
    {
        super(driver);
    }
}

