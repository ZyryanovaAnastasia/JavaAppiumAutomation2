package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","emulator-5554");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","/Users/Nastia/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName","IOS");
            capabilities.setCapability("deviceName","iPhone 5E");
            capabilities.setCapability("platformVersion","11.3");
            capabilities.setCapability("app","/Users/Nastia/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        } else {
            throw new Exception("Некоректное имя платформы. Значение " + platform);
        }

        return capabilities;
    }

    //Поворот экрана
    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    //Отправка приложение в фоновый режим
    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }
}
