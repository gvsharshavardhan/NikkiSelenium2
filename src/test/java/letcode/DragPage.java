package letcode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragPage extends BasePage {

    @Test
    public void dragTest() {
        jsScrollAlongYAxisBy(1000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "AUI - 1")));
        Actions actions = new Actions(driver);
        WebElement sampleBox = getElement(By.id(letCodeProp.getProperty("letcode.drag.sampleBox.id")));
        actions.dragAndDropBy(sampleBox, 70, 70).perform();
    }

    @Test
    public void dragAndDropTest() {
        jsScrollAlongYAxisBy(1000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "AUI - 2")));
        Actions actions = new Actions(driver);
        WebElement draggable = getElement(By.id(letCodeProp.getProperty("letcode.drop.draggable.id")));
        WebElement droppable = getElement(By.id(letCodeProp.getProperty("letcode.drop.droppable.id")));
        actions.dragAndDrop(draggable, droppable).perform();
    }

    @Test
    public void sortTest() {
        String dropableXpath = "//div[@id='sample-box1' and normalize-space(text())='$$']";
        jsScrollAlongYAxisBy(1000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "AUI - 3")));
        Actions actions = new Actions(driver);
        int todoCount = driver.findElements(By.xpath("//div[@id='cdk-drop-list-0']//div[@id='sample-box1']")).size();

        WebElement doneElement;
        for (int i = 1; i <= todoCount; i++) {
            WebElement todoElement = driver.findElement(By.xpath("//div[@id='cdk-drop-list-0']//div[@id='sample-box1']"));
            String todoText = todoElement.getText().trim();
            switch (todoText) {
                case "Get to work":
                    doneElement = driver.findElement(By.xpath(dropableXpath.replace("$$", "Check e-mail")));
                    break;
                default:
                    doneElement = driver.findElement(By.xpath(dropableXpath.replace("$$", "Walk dog")));
            }

            if (!todoText.equals("Fall asleep")) {
                actions.dragAndDrop(todoElement, doneElement).perform();
            } else {
//                int y = driver.findElement(By.xpath(dropableXpath.replace("$$", "Walk dog"))).getLocation().getY();
//                int x = driver.findElement(By.xpath(dropableXpath.replace("$$", "Walk dog"))).getLocation().getX();
//                System.out.println(String.format("x: %d y: %d",x,y));
                actions.clickAndHold(todoElement).moveToElement(doneElement).moveByOffset(0, 150).release().perform();
            }
            takeRest(3);
        }
    }

    @Test
    public void multiSelectTest() {
        jsScrollAlongYAxisBy(1000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "AUI - 4")));
        Actions actions = new Actions(driver);
        WebElement sel = getElement(By.xpath("//h3[normalize-space(text())='Selenium']/parent::div"));
        WebElement let = getElement(By.xpath("//h3[normalize-space(text())='LetCode']/parent::div"));
        actions.click(sel).keyDown(Keys.SHIFT).moveToElement(let).click().keyUp(Keys.SHIFT).perform();
    }

    @Test
    public void sliderTest() {
        int mycount = 15;
        jsScrollAlongYAxisBy(2000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "AUI - 5")));
        Actions actions = new Actions(driver);
        WebElement slider = getElement(By.id("generate"));
        int count = Integer.parseInt(getElement(By.xpath("//h1[normalize-space(@class)='subtitle has-text-info']")).getText().split(":")[1].trim());
        while (count < mycount) {
            actions.dragAndDropBy(slider, 5, 0).perform();
            count = Integer.parseInt(getElement(By.xpath("//h1[normalize-space(@class)='subtitle has-text-info']")).getText().split(":")[1].trim());
            takeRestForHalfSecond();
        }
        click(By.xpath("//button[normalize-space(text())='Get Countries']"));
        int countrycount = getTextFromElement(By.xpath("//div[@class='card-content']//p")).split("-").length;
        Assert.assertEquals(countrycount, mycount, "no of countries as not as expected!!");
    }
}