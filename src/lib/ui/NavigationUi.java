package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUi extends MainPageObject{

    protected static String
        MY_LISTS_LINK;

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
