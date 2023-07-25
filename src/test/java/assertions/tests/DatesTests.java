package assertions.tests;

import assertions.helpers.DashDateConverter;
import assertions.helpers.DateTimeAssert;
import assertions.helpers.DateTimeDeltaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DatesTests {
    @Test
    public void testTwoDates() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTimeInPast = LocalDateTime.now().minusMinutes(3);
        DateTimeAssert.assertEquals(now, localDateTimeInPast, DateTimeDeltaType.MINUTES, 2);
    }

    @ParameterizedTest
    @CsvSource({"2021-11-21,2021", "2022--01-12,2022"})
    public void verifyYear_whenCustomConverter(@ConvertWith(DashDateConverter.class) LocalDate date, int expected) {
        Assertions.assertEquals(expected, date.getYear());
    }
}
