package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
        LOGIN_BTN = "xpath://body/div/a[text()='Log in']",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMITBTN = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickAuthBtn()
    {
        this.waitForElementPresent(
                LOGIN_BTN,
                "Не удалось найти кнопку логина",
                5
        );
        this.waitForElementAndClick(
                LOGIN_BTN,
                "Ошибка при нажатии на кнопку логина",
                5
        );
    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(
                LOGIN_BTN,
                login,
                "Ошибка при вводе логина",
                5
        );
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                password,
                "Ошибка при вводе пароля",
                5
        );
    }

    public void submitForm()
    {
        this.waitForElementAndClick(
                SUBMITBTN,
                "Ошибка при нажатии на кнопку входа в профиль",
                5
        );
    }
}
