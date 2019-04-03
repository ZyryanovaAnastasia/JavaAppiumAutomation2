package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

     static {
         SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
         SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
         SEARCH_CANCEL_BTN = "id:org.wikipedia:id/search_close_btn";
         SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                         "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
         SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
         SEARCH_RESULT_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description']" +
                         "[@text='{TITLE}']";
         SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/ancestor::" +
                         "android.widget.LinearLayout/android.widget.TextView[@text='{DESCRIPTION}']";
         SEARCH_RESULT_ALL_TITLE_ELM = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
     }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
