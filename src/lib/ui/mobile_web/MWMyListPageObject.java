package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";

        ARTICLE_BY_TITLE_TPL = "xpath://ul[contain(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BTN = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../div[contains(@class, 'watched')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
