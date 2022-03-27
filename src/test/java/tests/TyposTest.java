package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.google.common.collect.Iterables.size;

public class TyposTest {
    WebDriver driver;

    @Test
    public void addTwoElementsTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/typos");
        String orthographyExpected = "Sometimes you'll see a typo, other times you won't.";
        String orthographyActual = driver.findElements(By.tagName("p")).get(1).getText();
        Assert.assertEquals(orthographyActual, orthographyExpected);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
