package assertions.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringsTests {
    @ParameterizedTest
    @ValueSource(strings = {"null"})
    @NullSource
    @EmptySource
    void isStringNullOrEmpty_TwoSeparateAnnotations(String str) {
        Assertions.assertTrue(str == null || str.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"null"})
    @NullAndEmptySource
    void isStringNullOrEmpty_OneAnnotation(String str) {
        Assertions.assertTrue(str == null || str.isEmpty());
    }
}
