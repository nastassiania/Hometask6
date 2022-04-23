package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.google.common.collect.Iterables.size;

public class AddRemoveElementsTest {
    WebDriver driver;

    @Test
    public void addTwoElementsTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        int numberOfElementsExpected = 1;
        int numberOfElementsActual = size(driver.findElements(By.xpath("//button[text()='Delete']")));
        Assert.assertEquals(numberOfElementsActual, numberOfElementsExpected);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
