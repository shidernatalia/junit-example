package parallel.tests;

import parallel.pages.DriverAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    public DriverAdapter driver;

    @BeforeEach
    public void setUp() {
        initializeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
    public DriverAdapter getDriver(){
        if(driver == null) driver = new DriverAdapter();
        return driver;
    }

    protected abstract void initializeDriver();
}
