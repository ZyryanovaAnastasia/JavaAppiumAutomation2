package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
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
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
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
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize(); //Размер экрана

            //Координаты в отношении размера устроства. Ось x неизменяется, т.к. движение снизу вверх
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);//перевод числа в int
            int end_y = (int) (size.height * 0.2);


            PointOption pointToPress = new PointOption();
            pointToPress.withCoordinates(x, start_y);

            PointOption pointToMove = new PointOption();
            pointToMove.withCoordinates(x, end_y);

            WaitOptions swipeWaitOptions = new WaitOptions();
            swipeWaitOptions.withDuration(Duration.ofSeconds(timeOfSwipe));

            //Нажать на экран, подождать, переместить палец в нажатом состоянии вверх. Чем больше timeOfSwipe, тем дольше swipe.
//            action
//                    .press(pointToPress)
//                    .waitAction(swipeWaitOptions)
//                    .moveTo(pointToMove)
//                    .release()
//                   .perform();
        } else {
            System.out.println("Метод rotateScreenLandscape() не работает платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            ((JavascriptExecutor) JSExecutor).executeScript("");
        } else {
            System.out.println("Метод rotateScreenPortrait() не работает платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTitleElementNotVisible(String locator, String error_message,long timeoutInSeconds, int max_swipes)
    {
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message, timeoutInSeconds);

        while (!this.isElmLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }

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

    public boolean isElmLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(
                locator,
                "Не удалось найти элемент по локатору",
                1
            ).getLocation().getY();

        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("returon window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y; //Возвращается true, когда элемент находится в зоне видимости на экране
    }

    public void swipeUpTitleElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (!this.isElmLocatedOnTheScreen(locator))
        {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElmLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

//    public void clickElementToTheRightUpperCorner(String locator, String error_message)
//    {
//        if (driver instanceof AppiumDriver) {
//            WebElement element = this.waitForElementPresent(
//                    locator + "/..", //Переход на элемент выше - в родительский
//                    error_message,
//                    5
//            );
//            int right_x = element.getLocation().getX();
//            int upper_y = element.getLocation().getY(); // самая верхная точка по оси Y
//            int lower_y = upper_y + element.getSize().getHeight(); // самая нижняя точка по оси Y
//            int middle_y = (upper_y + lower_y) / 2; // середина элемента по оси Y
//            int width = element.getSize().getWidth();
//
//            int point_to_click_x = (right_x + width) - 3;
//            int point_to_click_y = middle_y;
//
//            TouchAction action = new TouchAction((AppiumDriver) driver);
//            action.tap(point_to_click_x, point_to_click_y).perform();
//        } else {
//            System.out.println("Метод rotateScreenLandscape() не работает платформы " + Platform.getInstance().getPlatformVar());
//        }
//    }
//
//    //Swipe элемента влево
//    public void swipeElementToLeft(String locator, String error_message)
//    {
//        if (driver instanceof AppiumDriver) {
//            WebElement element = waitForElementPresent(
//                    locator,
//                    error_message,
//                    10);
//            int left_x = element.getLocation().getX(); // самая левая точка элемента по оси Х
//            int right_x = left_x + element.getSize().getWidth(); // самая права точка элемента по оси Х
//            int upper_y = element.getLocation().getY(); // самая верхная точка по оси Y
//            int lower_y = upper_y + element.getSize().getHeight(); // самая нижняя точка по оси Y
//            int middle_y = (upper_y + lower_y) / 2; // середина элемента по оси Y
//
//            TouchAction action = new TouchAction((AppiumDriver) driver);
//            action.press(right_x, middle_y);
//
//            if (Platform.getInstance().isAndroid()) {
//                action.waitAction(300);
//            } else {
//                int offset_x = (-1 * element.getSize().getWidth()); //Точка левее на всю шиирну элемента
//                action.moveTo(offset_x, 0);
//            }
//
//            action.moveTo(left_x, middle_y);
//            action.release().perform();
//        } else {
//            System.out.println("Метод rotateScreenLandscape() не работает платформы " + Platform.getInstance().getPlatformVar());
//        }
//    }

    //Получение количества элементов
    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return ((List) elements).size();
    }

    public boolean isElmPresent(String locator)
    {
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElmWithFewAttempts(String locator, String error_messages, int amount_of_attempts)
    {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_messages,1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_messages, 1);
                }
            }
            ++current_attempts;
        }
    }

    public List getAllElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements;
    }


}
