package basic.tests;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.util.concurrent.TimeUnit;

@Tag("nightlyRun")
public class SecondBasicTest {
    @Test
    @Timeout(5) /* @Timeout can be globally set, enabled, or disabled in junit-platform.properties file */
    @Order(2)
    public void test_4() {
        System.out.println("     (5) Test Method 4. Execute with timeout 5 seconds");
    }

    @Test
    @Disabled("JIRA BUG ID = 123456")
    @Order(1)
    public void test_5() {
        System.out.println("     (5) Test Method 5. Disabled because of Jira Bug ID = 123456");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    @Order(3)
    public void test_6() {
        System.out.println("     (5) Test Method 6. Disabled test when run on Windows");
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    public void test_7() {
        System.out.println("     (5) Test Method 7. Enabled test only for Linux, and Mac");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "STAGE")
    public void test_8() {
        System.out.println("     (5) Test Method 8. Test enabled only if run on staging environment");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*DEV.*")
    public void test_9() {
        System.out.println("     (5) Test Method 9. Test disabled if run on any dev environment");
    }

    @Test
    @DisabledIfSystemProperty(named = "CI", matches = "true")
    public void test_10() {
        System.out.println("     (5) Test Method 10. Test disabled if CI system property " +
                "(set either in junit-platform.properties or passed through the run configurations) is true");
    }

    @BeforeEach
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void beforeMethod() {
        System.out.println("    (4) Execute before each method");
    }

    @AfterEach
    public void afterMethod() {
        System.out.println("    (4) Execute after each method");
    }

    @BeforeAll
    public void beforeClass() {
        System.out.println("   (3) Execute before class");
    }

    @AfterAll
    public void afterClass() {
        System.out.println("   (3) Execute after class");
    }
}
