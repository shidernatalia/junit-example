package parameterized;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;


public class ParameterizedTestsFactory {
    public static Stream<Arguments> provideWebTechnologiesMultipleParams() {
        return Stream.of(
                Arguments.of("Backbone.js", List.of("clean the house", "clean car", "buy ketchup"), List.of("buy ketchup"), 2),
                Arguments.of("AngularJS", List.of("clean the house", "clean car", "buy ketchup"), List.of("buy ketchup"), 2)
        );
    }
}
