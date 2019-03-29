package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    //Преобразование строки в корректный локатор с типом
    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2); //разделяет локатор на тип и локатор
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Не верный тип локтаора. Локатор: " + locator_with_type);
        }
    }

    //Ожидание и поиск элемента
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                //Ждет элемент по локатору by
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //Ожидание, что элемент by отсутствует на странице
    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    //Проверка, что элемент виден на странице
    public boolean ElementIsDisplayed(String locator)
    {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        return element.isDisplayed();
    }

    //Ожидание, что элемента нет на странице и вывод ошибки
    public void assertElementNotPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "' supposed to be not present"; // by.toString() - перевод в строковое значение
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    //Получение опредленного атрибута элемента
    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    //Ожидание и нажатие на элемент
    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    //Ожидание элемента и очистка поля
    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Ожидание и отправка текста в элемент
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
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
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(
                        locator,
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
    public void swipeElementToLeft(String locator, String error_message)
    {
        WebElement element = waitForElementPresent(
                locator,
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
    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return ((List) elements).size();
    }

    public List getAllElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements;
    }
}
