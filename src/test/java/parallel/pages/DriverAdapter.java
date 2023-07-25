package parallel.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverAdapter {
    private final int WAIT_FOR_ELEMENT_TIMEOUT = 30;
    private ThreadLocal<WebDriver> driver;
    private ThreadLocal<WebDriverWait> webDriverWait;
    private ThreadLocal<Actions> actions;

    public void start(Browser browser) {
        driver = new ThreadLocal<>();
        webDriverWait = new ThreadLocal<>();
        actions = new ThreadLocal<>();
        switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            }
            default -> throw new IllegalArgumentException(browser.name() + " could not be instantiated");
        }
        actions.set(new Actions(driver.get()));
        webDriverWait.set((new WebDriverWait(driver.get(), Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT))));
    }

    public void quit() {
        driver.get().quit();
    }

    public void goToUrl(String url) {
        driver.get().navigate().to(url);
    }

    public void validateInnerTextIs(WebElement resultElement, String expectedText) {
        webDriverWait.get().until(ExpectedConditions.textToBePresentInElement(resultElement, expectedText));
    }

    public WebElement findElement(By locator) {
        return webDriverWait.get().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public Actions getActions(){
        return actions.get();
    }
}
