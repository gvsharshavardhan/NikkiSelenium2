package letcode;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertPage extends BasePage {

    @Test
    void alertTest() {
        driver.findElement(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath").replace("$$", "Dialog"))).click();

        //simple alert
        driver.findElement(By.id(letCodeProp.getProperty("letcode.alert.simple.id"))).click();
        waitUntilAlertIsPresent();
        acceptAlert();

        //confirm alert
        driver.findElement(By.id(letCodeProp.getProperty("letcode.alert.confirm.id"))).click();
        waitUntilAlertIsPresent();
        String expectedAlertText = "Are you happy with LetCode?";
        String actualAlertText = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText, expectedAlertText, "Alert text is not as expected!!");
        dismissAlert();

        //prompt alert
        click(By.id(letCodeProp.getProperty("letcode.alert.prompt.id")));
//        waitUntilAlertIsPresent();
        String expectedName = "Nikki";
        sendTextIntoAlert(expectedName);
        acceptAlert();
        String actualName = getTextFromElement(By.id(letCodeProp.getProperty("letcode.alert.notification.id")));
        Assert.assertTrue(actualName.contains(expectedName), "Notification doesn't have the name that is sent to alert!!");

        //sweet alert
        click(By.id(letCodeProp.getProperty("letcode.alert.modern.id")));
        takeScreenShot("withSweetAlert");
        click(By.xpath(letCodeProp.getProperty("letcode.alert.sweetalert.close.xpath")));
        takeScreenShot("afterClosingSweetAlert");
        takeRest(5);
    }
}