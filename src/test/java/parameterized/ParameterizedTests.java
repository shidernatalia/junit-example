package parameterized;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class ParameterizedTests {
    private final int WAIT_FOR_ELEMENT_TIMEOUT = 30;
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Actions actions;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT));
        actions = new Actions(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void test_noParams() {
        performTestSteps();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backbone.js", "AngularJS"})
    public void test_withParams(String technology) {
        performTestSteps(technology);
    }

    @ParameterizedTest
    @MethodSource("provideWebTechnologies")
    public void test_withMethod(String technology) {
        performTestSteps(technology);
    }

    @ParameterizedTest
    @MethodSource("provideWebTechnologiesMultipleParams")
    public void test_withMethod1(
            String technology, List<String> itemsToAdd, List<String> itemsToCheck, int expectedLeftItems) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(technology);

        itemsToAdd.forEach(this::addNewTodoItem);
        itemsToCheck.forEach(itemToCheck -> getItemCheckBox(itemToCheck).click());
        assertLeftItems(expectedLeftItems);
    }

    @ParameterizedTest
    @MethodSource("JUnitDemo.ParameterizedTestsFactory#provideWebTechnologiesMultipleParams")
    public void test_withMethodFactory(
            String technology, List<String> itemsToAdd, List<String> itemsToCheck, int expectedLeftItems) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(technology);

        itemsToAdd.forEach(this::addNewTodoItem);
        itemsToCheck.forEach(itemToCheck -> getItemCheckBox(itemToCheck).click());
        assertLeftItems(expectedLeftItems);
    }

    @ParameterizedTest
    @ArgumentsSource(WebTechnilogiesArgumentProvider.class)
    public void test_withMethodArgumentSource(
            String technology, List<String> itemsToAdd, List<String> itemsToCheck, int expectedLeftItems) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(technology);

        itemsToAdd.forEach(this::addNewTodoItem);
        itemsToCheck.forEach(itemToCheck -> getItemCheckBox(itemToCheck).click());
        assertLeftItems(expectedLeftItems);
    }

    @ParameterizedTest
    @ArgumentsSource(WebTechnilogiesArgumentProvider.class)
    public void test_withMethodArgumentSourceAndSingleArgument(@AggregateWith(TodoListAggregator.class) TodoList todoList) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(todoList.getTechnology());

        todoList.getItemsToAdd().forEach(this::addNewTodoItem);
        todoList.getItemsToCheck().forEach(itemToCheck -> getItemCheckBox(itemToCheck).click());
        assertLeftItems(todoList.getExpectedItemsLeft());
    }

    @ParameterizedTest
    @EnumSource(WebTechnology.class)
    public void test_withEnum(WebTechnology technology) {
        performTestSteps(technology);
    }

    @ParameterizedTest
    @EnumSource(value = WebTechnology.class, names = {"DOJO", "EMBERJS"})
    public void test_withEnumFiler(WebTechnology technology) {
        performTestSteps(technology);
    }

    @ParameterizedTest
    @EnumSource(value = WebTechnology.class, names = {"DOJO", "EMBERJS"}, mode = EnumSource.Mode.EXCLUDE)
    public void test_withEnumFilerExclude(WebTechnology technology) {
        performTestSteps(technology);
    }

    @ParameterizedTest
    @EnumSource(value = WebTechnology.class, names = ".+JS", mode = EnumSource.Mode.MATCH_ANY)
    //parameters ending with JS
    public void test_withEnumFilerRegex(WebTechnology technology) {
        performTestSteps(technology);
    }

    @ParameterizedTest
    @CsvSource({"Backbone.js,clean the house,clean car,buy ketchup,buy ketchup,2",
            "AngularJS,clean the house,clean car,buy ketchup,buy ketchup,2"})
    public void test_withParamsCsvSourceWithoutFile(
            String technology, String item1, String item2, String item3, String itemToCheck, int expectedLeftItems) {
        performTestSteps(technology, item1, item2, item3, itemToCheck, expectedLeftItems);
    }

    @ParameterizedTest(name = "{index}. Change test case name like this. Use Case: verify todo list successfully created using technology {0}")
    @CsvSource(value = {"Backbone.js\tclean the house\tclean car\tbuy ketchup\tbuy ketchup\t2",
            "AngularJS\tclean the house\tclean car\tbuy ketchup\tbuy ketchup\t2"}, delimiter = '\t')
    public void test_withParamsCsvSourceWithoutFileChangedDelimiter(
            String technology, String item1, String item2, String item3, String itemToCheck, int expectedLeftItems) {
        performTestSteps(technology, item1, item2, item3, itemToCheck, expectedLeftItems);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/parameterized-test.csv", numLinesToSkip = 1)
    public void test_withParamsCsvSourceFile(
            String technology, String item1, String item2, String item3, String itemToCheck, int expectedLeftItems) {
        performTestSteps(technology, item1, item2, item3, itemToCheck, expectedLeftItems);
    }

    /* ------------------------- HELPER METHODS ------------------------- */
    private void performTestSteps() {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp("Backbone.js");

        addNewTodoItem("clean the house");
        addNewTodoItem("clean car");
        addNewTodoItem("buy ketchup");

        getItemCheckBox("buy ketchup").click();
        assertLeftItems(2);
    }

    private void performTestSteps(String technology) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(technology);

        addNewTodoItem("clean the house");
        addNewTodoItem("clean car");
        addNewTodoItem("buy ketchup");

        getItemCheckBox("buy ketchup").click();
        assertLeftItems(2);
    }

    private void performTestSteps(WebTechnology technology) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(technology.getTechnologyName());

        addNewTodoItem("clean the house");
        addNewTodoItem("clean car");
        addNewTodoItem("buy ketchup");

        getItemCheckBox("buy ketchup").click();
        assertLeftItems(2);
    }

    private void performTestSteps(String technology, String item1, String item2, String item3, String itemToCheck, int expectedLeftItems) {
        driver.navigate().to("https://todomvc.com/");
        openTechnologyApp(technology);

        addNewTodoItem(item1);
        addNewTodoItem(item2);
        addNewTodoItem(item3);

        getItemCheckBox(itemToCheck).click();
        assertLeftItems(expectedLeftItems);
    }

    private void assertLeftItems(int expectedCount) {
        WebElement resultSpan = waitAndFindElement(By.xpath("//footer/*/span | //footer/span"));
        if (expectedCount == 1) {
            String expectedText = String.format("%d items left", expectedCount);
            validateInnerTextIs(resultSpan, expectedText);
        } else {
            String expectedText = String.format("%d items left", expectedCount);
            validateInnerTextIs(resultSpan, expectedText);
        }
    }

    private void validateInnerTextIs(WebElement resultElement, String expectedText) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(resultElement, expectedText));
    }

    private WebElement getItemCheckBox(String todoItem) {
        String xpathLocator = String.format("//label[text()='%s']/preceding-sibling::input", todoItem);
        return waitAndFindElement(By.xpath(xpathLocator));
    }

    private void addNewTodoItem(String todoItem) {
        WebElement todoInput = waitAndFindElement(By.xpath("//input[@placeholder='What needs to be done?']"));
        todoInput.sendKeys(todoItem);
        actions.click(todoInput).sendKeys(Keys.ENTER).perform();
    }

    private void openTechnologyApp(String technologyName) {
        WebElement technologyLink = waitAndFindElement(By.linkText(technologyName));
        technologyLink.click();
    }

    private WebElement waitAndFindElement(By locator) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static Stream<String> provideWebTechnologies() {
        return Stream.of("Backbone.js", "AngularJS");
    }

    public static Stream<Arguments> provideWebTechnologiesMultipleParams() {
        return Stream.of(
                Arguments.of("Backbone.js", List.of("clean the house", "clean car", "buy ketchup"), List.of("buy ketchup"), 2),
                Arguments.of("AngularJS", List.of("clean the house", "clean car", "buy ketchup"), List.of("buy ketchup"), 2)
        );
    }
}