package letcode;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FramePage extends BasePage {

    @Test
    public void frameTest() {

        By frameButtonLocator = By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Inner HTML"));
        jsScrollAlongYAxisBy(650);
        takeRest(2);
        jsClick(frameButtonLocator);
        takeRest(5);

        driver.switchTo().frame("firstFr");
        enterText(By.xpath(letCodeProp.getProperty("letcode.frames.firstname.xpath")), "Nikki");
        enterText(By.xpath(letCodeProp.getProperty("letcode.frames.lastname.xpath")), "kunapareddy");

        driver.switchTo().frame(driver.findElement(By.cssSelector(".has-background-white[src='innerFrame']")));

        enterText(By.xpath(letCodeProp.getProperty("letcode.frames.email.xpath")), "nikkik@gmail.com");
        takeRest(2);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("firstFr");
        driver.findElement(By.xpath(letCodeProp.getProperty("letcode.frames.lastname.xpath"))).clear();
        enterText(By.xpath(letCodeProp.getProperty("letcode.frames.lastname.xpath")), "canada");
//        driver.switchTo().parentFrame();
//        driver.findElement(By.xpath(letCodeProp.getProperty("letcode.frames.lastname.xpath"))).clear();
//        enterText(By.xpath(letCodeProp.getProperty("letcode.frames.lastname.xpath")), "canada");
//        takeRest(5);
    }
}
