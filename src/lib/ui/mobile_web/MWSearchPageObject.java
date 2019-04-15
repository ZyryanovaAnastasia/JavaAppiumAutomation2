package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BTN = "css:button.cancel";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-result";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://div[contains(@class, 'wikipedia-description')][contains(text(), '{TITLE}')]";
        SEARCH_RESULT_ALL_TITLE_ELM = "css:p.without-results";

        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/ancestor::" +
                "android.widget.LinearLayout/android.widget.TextView[@text='{DESCRIPTION}']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
