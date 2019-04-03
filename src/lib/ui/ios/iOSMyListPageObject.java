package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";

        ARTICLE_BY_TITLE_TPL = "xpath:XCUIElementTypeLink[contains(@name='{TITLE}')]";
    }

    public iOSMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
