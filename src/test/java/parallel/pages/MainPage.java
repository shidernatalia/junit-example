package parallel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final DriverAdapter driver;

    public MainPage(DriverAdapter driver) {
        this.driver = driver;
    }
    public void open(){driver.goToUrl("https://todomvc.com/");}
    public void openTechnologyApp(String technologyName) {
        WebElement technologyLink = driver.findElement(By.linkText(technologyName));
        technologyLink.click();
    }
}
