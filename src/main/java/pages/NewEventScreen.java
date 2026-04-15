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

public class NewEventScreen {

    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar[`name == \"New\"`]")
    private RemoteWebElement newEventScreenTitle;
    @iOSXCUITFindBy(accessibility = "title-field")
    private RemoteWebElement titleTextField;
    @iOSXCUITFindBy(accessibility = "Starts")
    private RemoteWebElement startDateSelector;
    @iOSXCUITFindBy(accessibility = "Ends")
    private RemoteWebElement endDateSelector;
    @iOSXCUITFindBy(accessibility = "add-button")
    private RemoteWebElement addButton;
    @iOSXCUITFindBy(accessibility = "BackButton")
    private RemoteWebElement backButton;
    @iOSXCUITFindBy(accessibility = "Saturday, April 11")
    private RemoteWebElement eventDate;


    public NewEventScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("New event screen is loaded")
    public boolean newEventScreenLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(newEventScreenTitle)).isDisplayed();
    }

    @Step("Entering event title: {0}")
    public void enterEventTitle(String eventTitle) {
        titleTextField.clear();
        titleTextField.sendKeys(eventTitle);
    }
    @Step("Change date and time")
    public void changeDateAndTime(){
        startDateSelector.click();
        WebElement start = driver.findElement(AppiumBy.accessibilityId("11"));
        start.click();

        WebElement startCell = driver.findElement(
                AppiumBy.iOSNsPredicateString("label CONTAINS 'Starts'"));
        startCell.findElement(AppiumBy.iOSNsPredicateString("name MATCHES '\\\\d{2}:\\\\d{2}'")).click();

        WebElement hourWheel = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[1]"));
        hourWheel.sendKeys("10");

        WebElement minuteWheel = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[2]"));
        minuteWheel.sendKeys("30");

        endDateSelector.click();
        WebElement end = driver.findElement(AppiumBy.accessibilityId("12"));
        end.click();

        WebElement endCell = driver.findElement(
                AppiumBy.iOSNsPredicateString("label CONTAINS 'Ends'"));
        endCell.findElement(AppiumBy.iOSNsPredicateString("name MATCHES '\\\\d{2}:\\\\d{2}'")).click();

        WebElement endHourWheel = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[1]"));
        endHourWheel.sendKeys("10");

        WebElement endMinuteWheel = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[2]"));
        endMinuteWheel.sendKeys("30");
    }
    @Step("Set travel time")
    public void setTravelTime(){
        WebElement travelTimeButton = driver.findElement(AppiumBy.accessibilityId("Travel Time"));
        travelTimeButton.click();

        WebElement travelTime30 = driver.findElement(AppiumBy.accessibilityId("tavel-time-menu-option:30 minutes"));
        travelTime30.click();
    }
    @Step("Set to all day")
    public void setToAllDay(){
        WebElement allDayButton = driver.findElement(AppiumBy.accessibilityId("all-day-switch"));
        allDayButton.click();
    }

    @Step("Time not shown")
    public boolean timeNotShown() {
        return driver.findElements(
                AppiumBy.iOSNsPredicateString(
                        "label CONTAINS 'Ends' AND name MATCHES '\\d{2}:\\d{2}'")).isEmpty();
    }
    @Step("Confirm choices")
    public void confirmChoices(){
        addButton.click();
        backButton.click();
    }
    @Step("Event is added")
    public String getDateCellValue() {
        WebElement dateCell = new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(eventDate));

        return dateCell.getAttribute("value");
    }
}
