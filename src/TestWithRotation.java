import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class TestWithRotation {

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
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        help.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Learning programming";

        help.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                search_line,
                "Error message",
                5
        );

        help.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming lauguage']"),
                "Cannot find 'Search Wikipedia' input",
                15
        );

        String title_before_rotation = help.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Errof",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = help.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Errof",
                5
        );

        Assert.assertEquals(
                "error",
                title_before_rotation,
                title_after_rotation
                );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = help.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Errof",
                5
        );

        Assert.assertEquals(
                "error",
                title_before_rotation,
                title_after_second_rotation
        );

    }
}
