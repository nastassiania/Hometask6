package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Frames {
    WebDriver driver;

    @BeforeClass
    public void doBeforeTests() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/frames");
    }

    @Test
    public void verifyTheNameOfUploadedFileIsCorrectTest() {
        driver.findElement(By.xpath("//a[contains(text(),'iFrame')]")).click();
        driver.switchTo().frame("mce_0_ifr");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@id='tinymce']")));
        String iFrameTextActual = driver.findElement(By.xpath("//p[contains(text(),'Your content goes here.')]")).getText();
        String iFrameTextExpected = "Your content goes here.";
        Assert.assertEquals(iFrameTextActual, iFrameTextExpected, "Actual text doesn't match the expected one!");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
