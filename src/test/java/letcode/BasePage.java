package letcode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

public class BasePage {
    protected FileInputStream fileInputStream;
    protected Properties configProp;
    protected Properties letCodeProp;
    protected Properties testleafProp;
    protected WebDriver driver;

    @BeforeSuite
    protected void setupProperties() throws IOException {
        fileInputStream = new FileInputStream("./ORs/config.properties");
        configProp = new Properties();
        configProp.load(fileInputStream);
        fileInputStream = new FileInputStream("./ORs/letcode.properties");
        letCodeProp = new Properties();
        letCodeProp.load(fileInputStream);
        fileInputStream = new FileInputStream("./ORs/testleaf.properties");
        testleafProp = new Properties();
        testleafProp.load(fileInputStream);
    }

    @BeforeMethod
    protected void setupDriver(Method method) {
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
        String packageName = method.getDeclaringClass().getPackage().getName();
        if (packageName.equals("letcode")) {
            driver.navigate().to(configProp.getProperty("letcode.url"));
        } else if (packageName.equals("testleaf")) {
            driver.navigate().to(configProp.getProperty("testleaf.url"));
        } else {
            driver.get("https://www.google.com");
        }
    }

    @AfterMethod
    protected void shutDown(ITestResult result) {
        String name = result.getName();
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenShot(name);
        }
        driver.quit();
    }

    @AfterSuite
    protected void closeProperties() throws IOException {
        fileInputStream.close();
    }

    protected void takeScreenShot(String screenShotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./screenshots/" + screenShotName + LocalDateTime.now().getNano() + ".png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    protected void takeRest(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void takeRestForHalfSecond() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void waitUntilAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    protected Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    protected void acceptAlert() {
        switchToAlert().accept();
    }

    protected void dismissAlert() {
        switchToAlert().dismiss();
    }

    protected String getTextFromAlert() {
        return switchToAlert().getText();
    }

    protected void sendTextIntoAlert(String text) {
        switchToAlert().sendKeys(text);
    }

    protected WebElement getElement(By by) {
        return driver.findElement(by);
    }

    protected void click(By by) {
        driver.findElement(by).click();
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    protected void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    protected void jsScrollIntoView(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    protected void jsScrollAlongYAxisBy(int Ypixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + Ypixels + ")");
    }

    protected void scrollToBottomOfPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void jsClick(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(by));
    }
}