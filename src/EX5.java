import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class EX5 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/Nastia/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void saveAndDeleteArticle()
    {
        String search_text = "Java";
        String article_name_one = "JavaScript";
        String article_name_two = "Java";
        String name_of_list = "Learning programming";

       searchAndOpenArticle(search_text, article_name_one);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Не удалось найти и нажать на иконку дополнительных опций",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Не удалось нажать на пункт в выпадающем меню для сохранения статью в список",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Не удалось нажать на кнопку добавления списка",
                10
        );

// Закомментировала из-за пробллемы автонажатия на кнопку paste и вставки строки из буфера.

//        waitForElementAndClear(
//                By.id("org.wikipedia:id/text_input"),
//                "Не удалось очистить поле ввода названия списка",
//                5
//        );

//        waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                "Не удалось ввести текст в поле ввода названия списка",
//                5,
//                name_of_list
//        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Не удалось нажать на кнопку ОК в диалоговом окне",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Ошибка при нажатии на Х закрытия статьи",
                5
        );

//Добавление второй статьи
        searchAndOpenArticle(search_text, article_name_two);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Не удалось найти и нажать на иконку дополнительных опций",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Не удалось нажать на пункт в выпадающем меню для сохранения статью в список",
                5
        );

        waitForElementAndClick(
                //Если бы корректно работало создание списка
                //By.xpath("//android.widget.TextView[@text='" + name_of_list + "']"),
                By.xpath("//android.widget.TextView[@text='My reading list']"),
                "Не удалось нажать на кнопку добавления в список",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Ошибка при нажатии на Х закрытия статьи",
                5
        );

//Открытие списка My lists и удаление статьи
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"),
                "Ошибка при переходе в My lists с главного экрана",
                10
        );

        waitForElementAndClick(
                //Если бы корректно работало создание списка
                //By.xpath("//*[@text='" + name_of_list + "']"),
                By.xpath("//*[@text='My reading list']"),
                "Не удалось перейти в список",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + article_name_one + "']"),
                "В списке не удалось найти статью с заголовком " + article_name_one + " для удаления"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + article_name_one + "']"),
                "В списке присутствует удаленная статья с заголовком " + article_name_one,
                5
        );

//Проверка, что вторая статья осталась и сравнение заголовков
        //Бессмысленная проверка наличия статьи, т.к. ниже идет нажатия на статью. Если статьи нет, то нажать на нее не возможно.
        waitForElementPresent(
                By.xpath("//*[@text='" + article_name_two + "']"),
                "В списке не найдена статья с заголовком " + article_name_two,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + article_name_two + "']"),
                "В списке не удалось нажать на статью с заголовком " + article_name_two,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "У статьи не найден заголовок",
                5
        );

        String title_article = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Ошибка при получении атриббута text из заголовка статьи",
                5
        );

        Assert.assertEquals(
                "Не соответствие заголовка статьи " + title_article + " с ожидаемым заголовоком " + article_name_two,
                title_article,
                article_name_two
        );

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    
    private WebElement waitForElementAndSendKeys(By by, String error_message, long timeoutInSeconds, String value)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release().perform();

    }
    public void searchAndOpenArticle(String search_text, String article_name )
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "На главной нет строки поиска",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Не удалось ввести текст в поле поиска",
                5,
                search_text
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + article_name + "']"),
                "Не удалось нажать на статью с заголовком '" + article_name + "'",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "У статьи не найден заголовок",
                10
        );
    }
}
