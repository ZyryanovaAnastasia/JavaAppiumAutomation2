package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    //Ожидание и поиск элемента
    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                //Ждет элемент по локатору by
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //Ожидание, что элемент by отсутствует на странице
    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    //Проверка, что элемент виден на странице
    public boolean ElementIsDisplayed(By by)
    {
        WebElement element = driver.findElement(by);
        return element.isDisplayed();
    }

    //Ожидание и нажатие на элемент
    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    //Ожидание элемента и очистка поля
    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Ожидание и отправка текста в элемент
    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    //swipe статьи
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize(); //Размер экрана

        //Координаты в отношении размера устроства. Ось x неизменяется, т.к. движение снизу вверх
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);//перевод числа в int
        int end_y = (int) (size.height * 0.2);

        //Нажать на экран, подождать, переместить палец в нажатом состоянии вверх. Чем больше timeOfSwipe, тем дольше swipe.
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    //Swipe статьи до определенного элемента
    public void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes){
                waitForElementPresent(
                        by,
                        "Cannot find element by swiping up. \n" + error_message,
                        0
                );
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    //Swipe элемента влево
    public void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX(); // самая левая точка элемента по оси Х
        int right_x = left_x + element.getSize().getWidth(); // самая права точка элемента по оси Х
        int upper_y = element.getLocation().getY(); // самая верхная точка по оси Y
        int lower_y = upper_y + element.getSize().getHeight(); // самая нижняя точка по оси Y
        int middle_y = (upper_y + lower_y) / 2; // середина элемента по оси Y

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release().perform();

    }

    //Получение количества элементов
    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return ((List) elements).size();
    }
    //Ожидание, что элемента нет на странице и вывод ошибки
    public void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present"; // by.toString() - перевод в строковое значение
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    //Получение опредленного атрибута элемента
    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
