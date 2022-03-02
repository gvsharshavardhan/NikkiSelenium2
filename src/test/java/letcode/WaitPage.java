package letcode;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class WaitPage extends BasePage{

    @Test
    void waitAlertTest(){
        By waitButtonLocator = By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Timeout"));
        jsScrollAlongYAxisBy(2300);
        jsClick(waitButtonLocator);
        click(By.id(letCodeProp.getProperty("letcode.windows.simplealert.id")));
        waitUntilAlertIsPresent();
        System.out.println(getTextFromAlert());
    }
}
