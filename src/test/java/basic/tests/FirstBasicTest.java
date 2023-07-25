package basic.tests;

import basic.helpers.DailyRun;
import basic.helpers.DailyRunTest;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)/*Default:PER_METHOD. Set via @TestInstance or junit-platform.properties*/
@TestMethodOrder(value = MethodOrderer.Random.class)/*Global method ordering can be set in junit-platform.properties*/
public class FirstBasicTest {
    private int counter = 0;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("   (3) Execute before class.");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("   (3) Execute after class.");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("    (4) Execute before each method. @TestInstance features: counter = " + counter);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("    (4) Execute after each method. @TestInstance features: counter = " + counter);
    }

    @Test
    @RepeatedTest(value = 2, name = RepeatedTest.LONG_DISPLAY_NAME)
    @Tag("nightlyRun")
    public void test_1() {
        System.out.println("     (5) Test Method 1. @TestInstance features: counter = " + (++counter));
    }

    @Test
    @DailyRun
    @RepeatedTest(value = 2, name = "{displayName}_{currentRepetition}/{totalRepetitions}")
    public void test_2() {
        System.out.println("     (5) Test Method 2. @TestInstance features: counter = " + (++counter));
    }

    @DailyRunTest
    @DisplayName("Test_Method_3")
    public void test_3() {
        System.out.println("     (5) Test Method 3. @TestInstance features: counter = " + (++counter));
    }
}
