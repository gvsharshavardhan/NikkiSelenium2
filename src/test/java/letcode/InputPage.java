package letcode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

public class InputPage {
    FileInputStream fileInputStream;
    Properties configProp;
    Properties letCodeProp;
    WebDriver driver;

    @BeforeSuite
    void setupProperties() throws IOException {
        fileInputStream = new FileInputStream("./ORs/config.properties");
        configProp = new Properties();
        configProp.load(fileInputStream);
        fileInputStream = new FileInputStream("./ORs/letcode.properties");
        letCodeProp = new Properties();
        letCodeProp.load(fileInputStream);
    }

    @BeforeMethod
    void setupDriver() {
        String browser = configProp.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.navigate().to(configProp.getProperty("letcode.inputpage.url"));
    }


    @Test
    void inputPageTest() throws IOException {
        String expectedFullName = "nikki";
        WebElement fullNameElement = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.fullname.id")));
        fullNameElement.sendKeys(expectedFullName);
        String actualFullName = fullNameElement.getAttribute("value");
        Assert.assertEquals(actualFullName, expectedFullName);

        WebElement joinElement = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.append.id")));
        joinElement.sendKeys(" " + expectedFullName);
        String actualJoinName = joinElement.getAttribute("value");
        Assert.assertEquals(actualJoinName, "I am good " + expectedFullName);
        joinElement.sendKeys(Keys.TAB);
        LocalDateTime ld = LocalDateTime.now();
        takeScreenShot("tab" + ld.getNano());

        WebElement getMeElement = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.textbox.id")));
        System.out.println(getMeElement.getAttribute("value"));
        WebElement clearMeElement = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.cleartext.id")));
        clearMeElement.clear();
        String actualText = clearMeElement.getAttribute("value");
        Assert.assertEquals(actualText, "");

        boolean isFieldEnabled = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.disabled.id"))).isEnabled();
        Assert.assertFalse(isFieldEnabled, "Field is not disabled!!");

        String op1 = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.readonly.id"))).getAttribute("readonly");
        String op2 = driver.findElement(By.id(letCodeProp.getProperty("letcode.input.readonly.id"))).getAttribute("harsha");
        System.out.println("op1:" + op1);
        System.out.println("op2:" + op2);
    }


    @AfterMethod
    void shutDown() {
        driver.quit();
    }

    @AfterSuite
    void closeProperties() throws IOException {
        fileInputStream.close();
    }

    void takeScreenShot(String screenShotName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("./screenshots/" + screenShotName + ".png"));
    }
}