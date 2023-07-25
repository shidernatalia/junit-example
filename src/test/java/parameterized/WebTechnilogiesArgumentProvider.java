package parameterized;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class WebTechnilogiesArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("Backbone.js", List.of("clean the house", "clean car", "buy ketchup"), List.of("buy ketchup"), 2),
                Arguments.of("AngularJS", List.of("clean the house", "clean car", "buy ketchup"), List.of("buy ketchup"), 2)
        );
    }
}
