package tests;

import data.CalendarTestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Calendar app")
@Feature("New event")
public class NewEventTest extends DriverSetup {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new event test functionality")
    @Test(testName = "Calendar test", dataProviderClass = CalendarTestData.class, dataProvider = "calendarData")
    public void calendarTest(String timeSlot, String title) {

        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen is not loaded");

        calendarHomeScreen.createNewEvent();
        Assert.assertTrue(newEventScreen.newEventScreenLoaded(), "New event screen is not loaded");

        newEventScreen.enterEventTitle(restAssuredUtility.getActivityValue("activity"));
        newEventScreen.changeDateAndTime();
        newEventScreen.setTravelTime();
        newEventScreen.setToAllDay();

        Assert.assertTrue(newEventScreen.timeNotShown(), "Timer is still shown");
        newEventScreen.confirmChoices();

        String after = newEventScreen.getDateCellValue();

        Assert.assertTrue(after.contains("event"),
                "Expected event to be added");    }
}
