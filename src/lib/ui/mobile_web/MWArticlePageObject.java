package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        OPTIONS_ADD_TO_MY_LIST_BTN = "css:#page-actions li#ca-watch button";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_REMOVE_FROM_MY_LIST_BTN = "css:#page-actions li#ca-watch.mw-ui-icon-watched watched button";

        TITLE_ARTICLE_TPL = "xpath://*[@text='{ARTICLE_NAME}']" ;
        SPECIAL_LIST_NAME_TPL = "xpath://android.widget.TextView[@text='{SPECIAL_NAME_LIST}']";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
{
    super(driver);
}
}
