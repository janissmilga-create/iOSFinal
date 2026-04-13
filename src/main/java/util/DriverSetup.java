package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import pages.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Listeners({ITestListenerUtility.class})
public class DriverSetup {

    protected IOSDriver driver;
    protected final AppiumServerManager appiumServerManager = new AppiumServerManager();

    protected RestAssuredUtility restAssuredUtility;

    protected CalendarHomeScreen calendarHomeScreen;
    protected NewEventScreen newEventScreen;
    protected NewCalendar newCalendar;

    @BeforeSuite
    public void startAppiumServer() {
        appiumServerManager.startAppiumServerWithCustomFlags();
    }

    @Step("Driver is started")
    @BeforeMethod
    public void setUp() {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(ConfigReader.getProperty("deviceName"))
                .setPlatformVersion(ConfigReader.getProperty("platformVersion"))
                .setBundleId(ConfigReader.getProperty("bundleId"))
                .autoAcceptAlerts()
                .setNoReset(false)
                .setFullReset(false);

        try {
            driver = new IOSDriver(new URI(GlobalVariables.appiumLocalUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        restAssuredUtility = new RestAssuredUtility();

        newCalendar = new NewCalendar(driver);
        calendarHomeScreen = new CalendarHomeScreen(driver);
        newEventScreen = new NewEventScreen(driver);
    }

    @Step("Driver is closed")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        appiumServerManager.stopAppiumServer();
    }
}
