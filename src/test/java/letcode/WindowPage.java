package letcode;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowPage extends BasePage {

    @Test
    void windowTest() {
        By windowButtonLocator = By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Tabs"));
        jsScrollAlongYAxisBy(650);
        click(windowButtonLocator);
        String parentWindow = driver.getWindowHandle();
        click(By.id(letCodeProp.getProperty("letcode.windows.homepagebutton.id")));
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }
        String actualtitle = driver.getTitle();
        String expectedHomePageTitle = "LetCode - Testing Hub";
        Assert.assertEquals(actualtitle, expectedHomePageTitle, "did not switch to new tab!!");
        driver.switchTo().window(parentWindow);
        driver.close();
    }
    
    @Test
    void windowTest2(){
        By windowButtonLocator = By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Tabs"));
        jsScrollAlongYAxisBy(650);
        click(windowButtonLocator);
        String parentWindow = driver.getWindowHandle();
        click(By.id(letCodeProp.getProperty("letcode.windows.multiplewindowsbutton.id")));
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println(getTextFromElement(By.tagName("h1")));
            }
        }
    }
}