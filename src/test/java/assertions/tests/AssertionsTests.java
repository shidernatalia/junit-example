package assertions.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AssertionsTests {
    private WebDriver driver;
    @BeforeAll
    public static void setUpClass(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterEach
    public void tearDown(){
        if(driver != null) driver.quit();
    }

    @Test
    public void properCheckBoxSelected() throws InterruptedException {
        String url = "https://lambdatest.github.io/sample-todo-app/";
        driver.navigate().to(url);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateTimeFormatter.format(LocalDate.of(1990, 10, 20));
        driver.findElement(By.id("sampletodotext")).sendKeys(formattedDate);
        driver.findElement(By.id("addbutton")).click();
        driver.findElements(By.xpath("//li[@ng-repeat]/input")).get(2).click();
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//li[@ng-repeat]/span"));

        String[] expectedDisplayedCheckBoxes = {"First Item", "Second Item", "Third Item", "Fourth Item", "Fifth Item", "20-10-1990"};
        Object[] actualDisplayedCheckBoxes = allCheckboxes.stream().map(e -> e.getText()).toArray();
        Assertions.assertArrayEquals(expectedDisplayedCheckBoxes, actualDisplayedCheckBoxes);

        Assertions.assertTimeout(Duration.ofSeconds(8), () -> {
            System.out.println("Sleeping for demonstration purposes at max for 8 seconds");
            Thread.sleep(7000);
        });
    }
}
