package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject  extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        TITLE_ARTICLE_TPL = "xpath://*[@text='{ARTICLE_NAME}']" ;
        OPTIONS_BTN = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTION_LISTS = "xpath://android.widget.ListView";
        OPTIONS_ADD_TO_MY_LIST_BTN = "xpath://*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_NEW_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        SPECIAL_LIST_NAME_TPL = "xpath://android.widget.TextView[@text='{SPECIAL_NAME_LIST}']";
        MY_LIST_OK_BTN = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BTN = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
