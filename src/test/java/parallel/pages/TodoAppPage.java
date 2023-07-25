package parallel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TodoAppPage {
    private final DriverAdapter driver;

    public TodoAppPage(DriverAdapter driver) {
        this.driver = driver;
    }

    public void open() {
        driver.goToUrl("https://todomvc.com/");
    }

    public void assertLeftItems(int expectedCount) {
        WebElement resultSpan = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
        if (expectedCount == 1) {
            String expectedText = String.format("%d items left", expectedCount);
            driver.validateInnerTextIs(resultSpan, expectedText);
        } else {
            String expectedText = String.format("%d items left", expectedCount);
            driver.validateInnerTextIs(resultSpan, expectedText);
        }
    }

    public WebElement getItemCheckBox(String todoItem) {
        String xpathLocator = String.format("//label[text()='%s']/preceding-sibling::input", todoItem);
        return driver.findElement(By.xpath(xpathLocator));
    }

    public void openTechnologyApp(String technologyName) {
        WebElement technologyLink = driver.findElement(By.linkText(technologyName));
        technologyLink.click();
    }

    public void addNewTodoItem(String todoItem) {
        WebElement todoInput = driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']"));
        todoInput.sendKeys(todoItem);
        driver.getActions().click(todoInput).sendKeys(Keys.ENTER).perform();
    }
}
