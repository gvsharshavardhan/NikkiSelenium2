package letcode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class InputPage extends BasePage {


    @Test
    void inputPageTest() throws IOException {
        driver.findElement(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath").replace("$$", "Edit"))).click();
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


}