package letcode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BasePage {
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
        driver.navigate().to(configProp.getProperty("letcode.url"));
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