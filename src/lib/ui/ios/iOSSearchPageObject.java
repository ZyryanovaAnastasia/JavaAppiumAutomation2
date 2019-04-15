package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.SearchTests;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField(@name,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField(@value,'Search Wikipedia')]";
        SEARCH_CANCEL_BTN = "id:close";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://XCUIElementTypeSearchField[contains(@name='{TITLE}')]";
        SEARCH_RESULT_ALL_TITLE_ELM = "xpath://XCUIElementTypeLink";

        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/ancestor::" +
                "android.widget.LinearLayout/android.widget.TextView[@text='{DESCRIPTION}']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
