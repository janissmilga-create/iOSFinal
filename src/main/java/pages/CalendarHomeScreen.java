package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;
import util.Helpers;

public class CalendarHomeScreen extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Calendar\"")
    private RemoteWebElement calendarHomeScreenContainer;


    public CalendarHomeScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar home screen is loaded")
    public boolean calendarHomeScreenLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarHomeScreenContainer)).isDisplayed();
    }

    @Step("Event is created on timeslot: {timeSlot}")
    public void createEventOnTimeslot(String timeSlot) {
        WebElement element = driver.findElement(AppiumBy.accessibilityId(timeSlot));
        scrollTo(driver, Directions.DOWN, element, 5);
        longPress(driver, element);
    }
    @Step("Create new event")
    public void createNewEvent(){
        WebElement element = driver.findElement(AppiumBy.accessibilityId("add-plus-button"));
        element.click();
    }

}
