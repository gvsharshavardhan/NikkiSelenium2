package letcode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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

    void takeScreenShot(String screenShotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./screenshots/" + screenShotName + ".png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    void takeRest(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void waitUntilAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    void acceptAlert() {
        switchToAlert().accept();
    }

    void dismissAlert() {
        switchToAlert().dismiss();
    }

    String getTextFromAlert() {
        return switchToAlert().getText();
    }

    void sendTextIntoAlert(String text) {
        switchToAlert().sendKeys(text);
    }

    WebElement findElement(By by) {
        return driver.findElement(by);
    }

    void click(By by) {
        driver.findElement(by).click();
    }

    void click(WebElement element) {
        element.click();
    }

    String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    void jsScrollIntoView(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    void jsScrollAlongYAxisBy(int Ypixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + Ypixels + ")");
    }

    void jsClick(By by){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",driver.findElement(by));
    }
}