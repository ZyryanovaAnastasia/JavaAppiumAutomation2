package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUi extends MainPageObject{

    protected static String
        MY_LISTS_LINK,
        OPEN_NAVIGATION,
        TITLE_MY_LISTS;

    public NavigationUi(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick (
                    OPEN_NAVIGATION,
                    "Ошибка не удалось найти кнопку навигации",
                    5
            );
        } else {
                System.out.println("Метод openMavigation() не работает платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    //Нажатие на иконку My lists на панели навигации
    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElmWithFewAttempts(
                    MY_LISTS_LINK,
                    "Не удалось кликнуть на кнопку My list",
                    5
            );
        }

        this.waitForElementAndClick(
                (MY_LISTS_LINK),
                "Ошибка при переходе в My lists с главного экрана",
                5
        );
        this.waitForElementPresent(
                TITLE_MY_LISTS,
                "Не удалось найти заголовок сцены My lists",
                5
        );
    }
}
