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

public class FileUpload {
    WebDriver driver;

    @BeforeClass
    public void doBeforeTests() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @Test
    public void verifyTheNameOfUploadedFileIsCorrectTest() {
        String fileNameExpected = "Lesson2.doc";
        driver.findElement(By.xpath("//input[@id='file-upload']")).sendKeys("C:/Users/Admin/Desktop/autotest/"+fileNameExpected);
        driver.findElement(By.xpath("//input[@id='file-submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'File Uploaded!')]")));
        String fileNameActual = driver.findElement(By.xpath("//div[@id='uploaded-files']")).getText();
        Assert.assertEquals(fileNameActual, fileNameExpected, "Actual file name doesn't match the expected one!");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}

