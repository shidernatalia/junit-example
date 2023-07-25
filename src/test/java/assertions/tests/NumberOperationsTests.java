package assertions.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static assertions.helpers.Operation.*;
import static org.junit.jupiter.api.Assertions.*;

public class NumberOperationsTests {
    @ParameterizedTest
    @ValueSource(ints = {-1, 3, 0, 6, -2, Integer.MAX_VALUE})
    @NullSource
    void testIsOddNumber(int number) {
        assertTrue(number % 2 == 0);
    }

    @Test
    public void testOperationsWithNumbers() {
        var actualArithmeticException = assertThrows(ArithmeticException.class, () -> DIVIDED_BY.eval(2, 0));
        Assertions.assertAll(
                () -> assertEquals(4, PLUS.eval(2, 2)), // 2+2= 4 and 2*2=4
                () -> assertEquals(4, TIMES.eval(2,2)),
                () -> assertEquals(5, PLUS.eval(3, 2)),
                () -> assertEquals(6, TIMES.eval(3,2)),
                () -> assertEquals(0.001, MINUS.eval(3.000, 2.999), 1e-12),
                () -> assertTrue(IS_MULTIPLE.eval(4, 2) - 2 < 1e-12),
                () -> assertFalse(IS_MULTIPLE.eval(5, 2) < 1e-12),
                () -> assertEquals("division by zero is not allowed!", actualArithmeticException.getMessage())
        );
    }
}
