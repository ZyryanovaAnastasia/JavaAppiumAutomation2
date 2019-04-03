package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject  extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        OPTIONS_ADD_TO_MY_LIST_BTN = "id:Save for later";
        CLOSE_ARTICLE_BTN = "id:Back";
        FOOTER_ELEMENT = "id:'View article in browser";

        TITLE_ARTICLE_TPL = "xpath://*[@text='{ARTICLE_NAME}']" ;
        SPECIAL_LIST_NAME_TPL = "xpath://android.widget.TextView[@text='{SPECIAL_NAME_LIST}']";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
