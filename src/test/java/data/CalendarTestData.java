package data;

import org.testng.annotations.DataProvider;

public class CalendarTestData {

    @DataProvider(name = "calendarData")
    public Object[][] calendarData() {
        return new Object[][]{
//                {"01:00", "Test"},
//                {"14:00", "Test2"},
                {"05:00", "Test3"}
        };
    }
}