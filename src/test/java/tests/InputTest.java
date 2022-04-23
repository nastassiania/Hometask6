package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InputTest {
    WebDriver driver;

    @BeforeTest
    public void doBeforeTests(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/inputs");
    }

    @Test (priority = 1)
    public void checkLettersTest() {
        driver.findElement(By.tagName("input")).sendKeys("ASsd");
        String lettersExpected = "";
        String lettersActual = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(lettersActual, lettersExpected);
    }

    @Test (priority = 2)
    public void checkCharsTest() {
        driver.findElement(By.tagName("input")).sendKeys("!@#$%^&*()_");
        String charsExpected = "";
        String charsActual = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(charsActual, charsExpected);
    }

    @Test (priority = 3)
    public void checkNumbersTest() {
        driver.findElement(By.tagName("input")).sendKeys("951");
        String numbersExpected = "951";
        String numbersActual = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(numbersActual, numbersExpected);
    }

    @Test (priority = 4)
    public void checkNumbersArrowsUpTest() {
        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        String numbersArrowsUpExpected = "2";
        String numbersArrowsUpActual = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(numbersArrowsUpActual, numbersArrowsUpExpected);
    }

    @Test (priority = 5)
    public void checkNumbersArrowsDownTest() {
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        String numbersArrowsDownExpected = "-3";
        String numbersArrowsDownActual = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(numbersArrowsDownActual, numbersArrowsDownExpected);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
