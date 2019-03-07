import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class testWithArticle {

    MyHelp help = new MyHelp();

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/var/hosting/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testSwipeArticleToElement()
    {
        help.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        help.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search...')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container'][@text='Appium']"),
                "Cannot find 'Appium' input",
                5
        );

        help.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        help.swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void SaveArticleToMyListAndDelete()
    {
        help.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        help.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search...')]"),
                "Java",
                "Cannot find search input",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming lauguage']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        help.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                15
        );

        help.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content_desc='More options']"),
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.id("org.wikipedia:id/onboading_button"),
                "Error message",
                5
        );

        help.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Error message",
                5
        );

        String name_of_folder = "Learning programming";

        help.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content_desc='Navigate up']"),
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content_desc='My lists']"),
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Error message",
                5
        );

        help.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Error_message"
        );

        help.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Error_message",
                5
        );
    }

}
