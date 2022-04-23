package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeTest
    public void doBeforeTests(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
    }

    @Test(priority = 1)
    public void checkTheFirstCheckBoxUncheckedTest() {
        WebElement firstElement = driver.findElement(By.cssSelector("[type=checkbox]"));
        boolean firstElementStatusActual = firstElement.isSelected();
        Assert.assertEquals(firstElementStatusActual, false);
    }

    @Test (priority = 2)
    public void checkTheFirstCheckBoxCheckedTest() {
        driver.findElement(By.cssSelector("[type=checkbox]")).click();
        WebElement firstElementChecked = driver.findElement(By.cssSelector("[type=checkbox]"));
        boolean firstElementCheckedStatusActual = firstElementChecked.isSelected();
        Assert.assertEquals(firstElementCheckedStatusActual, true);
    }

    @Test (priority = 3)
    public void checkTheSecondCheckBoxCheckedTest() {
        WebElement secondElement = driver.findElements(By.cssSelector("[type=checkbox]")).get(1);
        boolean secondElementStatusActual = secondElement.isSelected();
        Assert.assertEquals(secondElementStatusActual, true);
    }

    @Test (priority = 4)
    public void checkTheSecondCheckBoxUncheckedTest() {
        driver.findElements(By.cssSelector("[type=checkbox]")).get(1).click();
        WebElement secondElementUnchecked = driver.findElements(By.cssSelector("[type=checkbox]")).get(1);
        boolean secondElementUncheckedStatusActual = secondElementUnchecked.isSelected();
        Assert.assertEquals(secondElementUncheckedStatusActual, false);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
