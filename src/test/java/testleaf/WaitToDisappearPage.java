package testleaf;

import letcode.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitToDisappearPage extends BasePage {

    @Test
    public void disappearTest(){
        scrollToBottomOfPage();
        click(By.xpath(testleafProp.getProperty("testleaf.disappearLink.xpath")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement strongtextele = wait.until(ExpectedConditions
                .visibilityOf(getElement(By.tagName(testleafProp.getProperty("testleaf.disappearLink.strongText.tagName")))));
        Assert.assertEquals(strongtextele.getText(),"I know you can do it! Button is disappeared!","text is not as expected!");
    }
}