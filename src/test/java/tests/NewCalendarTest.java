package tests;

import data.CalendarTestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Calendar app")
@Feature("New event")
public class NewCalendarTest extends DriverSetup {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new event test functionality")
    @Test(testName = "Calendar test", dataProviderClass = CalendarTestData.class, dataProvider = "calendarData")
    public void calendarTest(String timeSlot, String title) throws InterruptedException {

        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen is not loaded");

        newCalendar.openCalendars();

        newCalendar.addNewCalendar();

        newCalendar.adjustCalendar();

        Assert.assertTrue(newCalendar.newCalendarAdded(), "New calendar has not been added");
        Assert.assertTrue(newCalendar.firstCalendarSelected(), "First calendar is not selected");
        Assert.assertTrue(newCalendar.secondCalendarSelected(), "Second calendar is not selected");

        newCalendar.deleteNewCalendar();

        Assert.assertTrue(newCalendar.calendarIsDeleted(), "Calendar is not deleted");

        newCalendar.closeCalendar();

        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen is not loaded");



    }
}
