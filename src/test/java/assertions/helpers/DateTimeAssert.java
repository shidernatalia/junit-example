package assertions.helpers;

import org.apache.commons.lang3.NotImplementedException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeAssert {
    public static void assertEquals(LocalDateTime expectedDate, LocalDateTime actualDate, DateTimeDeltaType deltaType, int count) throws Exception {
        if ((expectedDate == null) && (actualDate == null)) {
            return;
        } else if (expectedDate == null) {
            throw new NullPointerException("The expected date was null");
        } else if (actualDate == null) {
            throw new NullPointerException("The actual date was null");
        }
        Duration expectedDelta = DateTimeAssert.getTimeSpanDeltaByType(deltaType, count);
        long totalSecondsDifference = Math.abs(actualDate.until(expectedDate, ChronoUnit.SECONDS));
        if (totalSecondsDifference > expectedDelta.getSeconds()) {
            String exceptionMessage = String.format("Expected date: {0}, " +
                    "Actual Date: {1}" +
                    "Expected delta: {2}, " +
                    "Actual delta in seconds: {3} " +
                    "(Delta Type: {4})", expectedDate, actualDate, expectedDelta, totalSecondsDifference, deltaType);
            throw new Exception(exceptionMessage);
        }
    }
    private static Duration getTimeSpanDeltaByType(DateTimeDeltaType deltaType, int count) {
        return switch (deltaType) {
            case DAYS -> Duration.ofDays(count);
            case MINUTES -> Duration.ofMinutes(count);
            default -> throw new NotImplementedException("the delta type is not implemented");
        };
    }
}
