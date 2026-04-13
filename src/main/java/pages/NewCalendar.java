package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class NewCalendar {
    protected IOSDriver driver;
    public NewCalendar(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @iOSXCUITFindBy(accessibility = "calendars-button")
    private RemoteWebElement calendarsButton;
    @iOSXCUITFindBy(accessibility = "Add Calendar")
    private RemoteWebElement addCalendar;
    @iOSXCUITFindBy(accessibility = "add-calendar-menubutton")
    private RemoteWebElement addCalendarMenuButton;
    @iOSXCUITFindBy(accessibility = "calendar-title-field")
    private RemoteWebElement calendarTitleField;
    @iOSXCUITFindBy(accessibility = "calendar-current-selected-color")
    private RemoteWebElement calendarColor;
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"calendar-current-selected-color\" AND label == \"Blue\"")
    private RemoteWebElement blueColor;
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"BackButton\" AND label == \"Add Calendar\"")
    private RemoteWebElement backButton;
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"done-button\" AND label == \"Done\"")
    private RemoteWebElement doneButton;
    @iOSXCUITFindBy(accessibility = "My new Calendar")
    private RemoteWebElement newCalendar;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeImage[`name == \"info.circle\"`][2]")
    private RemoteWebElement infoButton;
    @iOSXCUITFindBy(accessibility = "delete-calendar-button")
    private RemoteWebElement deleteCalendar;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeImage[`name == \"checkmark.circle.fill\"`][1]")
    private RemoteWebElement checkMarkOne;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeImage[`name == \"checkmark.circle.fill\"`][2]")
    private RemoteWebElement checkMarkTwo;
    @iOSXCUITFindBy(accessibility = "done-button")
    private RemoteWebElement closeButton;

    @Step("Open calendars")
    public void openCalendars(){
        calendarsButton.click();
    }
    @Step("Add new calendar")
    public void addNewCalendar(){
        addCalendar.click();
        addCalendarMenuButton.click();
    }
    @Step("Adjust calendar")
    public void adjustCalendar(){
        calendarTitleField.sendKeys("My new Calendar");
        calendarColor.click();
        blueColor.click();
        backButton.click();
        doneButton.click();
    }
    @Step("New calendar is added")
    public boolean newCalendarAdded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(newCalendar)).isDisplayed();
    }
    @Step("First calendar is selected")
    public boolean firstCalendarSelected() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(checkMarkOne)).isDisplayed();
    }
    @Step("Second calendar is selected")
    public boolean secondCalendarSelected() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(checkMarkTwo)).isDisplayed();
    }
    @Step("Delete calendar")
    public void deleteNewCalendar() throws InterruptedException {
        infoButton.click();
        deleteCalendar.click();
        Thread.sleep(5000);
    }
    @Step("Calendar is deleted")
    public boolean calendarIsDeleted() {
        return driver.findElements(
                AppiumBy.accessibilityId(
                        "My new Calendar")).isEmpty();
    }
    @Step("Close calendar")
    public void closeCalendar(){
        closeButton.click();
    }

}
