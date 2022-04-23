package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicControlsTest {
    WebDriver driver;

    @BeforeClass
    public void doBeforeTests() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test(priority = 1)
    public void verifyCheckboxDisappearsAfterClickingRemoveButtonTest() {
        driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();
        waiter("//p[contains(text(), \"It\'s gone!\")]");
        Boolean checkboxIsDisplayedActual = elementIsShown("//input[@type='checkbox']");
        Assert.assertEquals((boolean) checkboxIsDisplayedActual, false, "Checkbox is Displayed!");
    }

    @Test(priority = 2)
    public void verifyInputIsDisabledTest() {
        Boolean inputIsEnabledActual = elementIsEnabled("//input[@type='text']");
        Assert.assertEquals((boolean) inputIsEnabledActual, false, "Input is enabled!");
    }

    @Test(priority = 3)
    public void verifyIsEnabledAfterClickingEnableButtonTest() {
        driver.findElement(By.xpath("//button[contains(text(),'Enable')]")).click();
        waiter("//p[contains(text(),\"It's enabled!\")]");
        Boolean inputIsEnabledActual = elementIsEnabled("//input[@type='text']");
        Assert.assertEquals((boolean) inputIsEnabledActual, true, "Input is disabled!");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    private WebDriverWait waiter(String element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        return wait;
    }

    private Boolean elementIsShown(String element) {
        try {
            driver.findElement(By.xpath(element)).isEnabled();
            return true;
        } catch (NoSuchElementException Ex) {
            return false;
        }
    }

    private Boolean elementIsEnabled(String element) {
        try {
            driver.findElement(By.xpath(element)).sendKeys("");
            return true;
        } catch (ElementNotInteractableException Ex) {
            return false;
        }

    }

}
