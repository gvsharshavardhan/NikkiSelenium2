package letcode;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ButtonPage extends BasePage {


    @Test
    void buttonTest() {
        driver.findElement(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath").replace("$$", "Click"))).click();


        //button-1 navigate back
        driver.findElement(By.id(letCodeProp.getProperty("letcode.button.home.id"))).click();
        String actualHomepageTitle = driver.getTitle();
        String expectedHomePageTitle = "LetCode with Koushik";
        Assert.assertEquals(actualHomepageTitle, expectedHomePageTitle, "home page button did not work properly!!");
        driver.navigate().back();
        String expectedButtonPageTitle = "Interact with Button fields";
        String actualButtonPageTitle = driver.getTitle();
        Assert.assertEquals(actualButtonPageTitle, expectedButtonPageTitle, "navigate back did not bring us back to button page!! ");

        //button-2 coordinates
        Point location = driver.findElement(By.id(letCodeProp.getProperty("letcode.button.position.id"))).getLocation();
        System.out.println("X co-ordinate: " + location.getX() + "\nY co-ordinate: " + location.getY());

        //button-3 color
        System.out.println("bgc : " + driver.findElement(By.id(letCodeProp.getProperty("letcode.button.color.id"))).getCssValue("background-color"));

        //button-4
        Dimension size = driver.findElement(By.id(letCodeProp.getProperty("letcode.button.property.id"))).getSize();
        System.out.println("Height : " + size.getHeight() + "\nWidth : " + size.getWidth());

        //button-5
        boolean actualStatus = driver.findElement(By.xpath(letCodeProp.getProperty("letcode.button.isDisabled.xpath"))).isEnabled();
        Assert.assertFalse(actualStatus,"Button should be disabled right???");

        //button-6
        Actions action = new Actions(driver);
        WebElement holdButton = driver.findElement(By.xpath(letCodeProp.getProperty("letcode.button.hold.xpath")));
        action.clickAndHold(holdButton).pause(Duration.ofSeconds(3)).perform();
        String expectedButtonTitle = "Button has been long pressed";
        String actualButtonTitle = holdButton.getText();
        Assert.assertEquals(actualButtonTitle,expectedButtonTitle,"After long press button title is not as expected!!");
    }
}