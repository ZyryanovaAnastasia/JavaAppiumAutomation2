package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUi extends MainPageObject{

    private static final String
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";

    public NavigationUi(AppiumDriver driver)
    {
        super(driver);
    }

    //Нажатие на иконку My lists на панели навигации
    public void clickMyLists()
    {
        this.waitForElementAndClick(
                (MY_LISTS_LINK),
                "Ошибка при переходе в My lists с главного экрана",
                5
        );
    }
}
