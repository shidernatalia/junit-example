package parallel.tests;

import parallel.pages.Browser;
import parallel.pages.MainPage;
import parallel.pages.TodoAppPage;
import parallel.pages.TodoFacade;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class CompileTimeJsTodoTestsFirefoxTest extends BaseTest {
    private TodoFacade todoFacade;

    @Override
    protected void initializeDriver() {
        getDriver().start(Browser.FIREFOX);
        todoFacade = new TodoFacade(new MainPage(getDriver()), new TodoAppPage(getDriver()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Angular 2.0", "Dart", "Elm", "Closure", "Spine", "Mithril" , "Kotlin + React", "Firebase + AngularJS", "Vanilla ES6"})
    public void test_withParams(String technology) {
        List<String> itemsToAdd = List.of("clean the house", "clean car", "buy ketchup");
        List<String> itemsToCheck = List.of("buy ketchup");
        todoFacade.verifyTodoListCreatedSuccessfully(technology, itemsToAdd, itemsToCheck, 2);
    }
}
