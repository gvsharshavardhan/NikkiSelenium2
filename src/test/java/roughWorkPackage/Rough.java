package roughWorkPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Rough {
    public static RemoteWebDriver driver = null;
    static String username = "gvsharsha6316";
    static String accessKey = "RI40IBjnulVHi8i14dkflIdkbkj2hOHW2qvoxfWMPG4T6gN4jL";

    public static void main(String[] args) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "92.0");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("resolution", "1024x768");
        capabilities.setCapability("build", "First Test");
        capabilities.setCapability("name", "Sample Test");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }
        try {
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
            driver.findElement(By.id("sampletodotext")).clear();
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
