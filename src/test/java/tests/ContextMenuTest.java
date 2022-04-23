package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContextMenuTest {
    WebDriver driver;

    @BeforeClass
    public void doBeforeTests() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/context_menu");
    }

    @Test(priority = 1)
    public void verifyAlertContainsProperTextTest() {
        Actions actionRightClick = new Actions(driver);
        actionRightClick.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        String alertTextExpected = "You selected a context menu";
        String alertTextActual = driver.switchTo().alert().getText();
        Assert.assertEquals(alertTextActual, alertTextExpected, "Actual alert text doesn't match the expected one!");
    }

    @Test(priority = 2)
    public void checkAlertIsClosedTest() {
        driver.switchTo().alert().accept();
        Boolean alertIsClosedActual = alertIsShown();
        Assert.assertEquals((boolean) alertIsClosedActual, false, "Alert is not closed!");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    private Boolean alertIsShown() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }

    }
}
