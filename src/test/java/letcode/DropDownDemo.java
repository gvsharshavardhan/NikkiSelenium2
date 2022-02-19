package letcode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class DropDownDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        //property file configuration code
        FileInputStream fis = new FileInputStream("./ORs/letcode.properties");
        Properties prop = new Properties();
        prop.load(fis);

        //selenium code
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://letcode.in/dropdowns");
        Thread.sleep(2000);
        WebElement fruitDropDown = driver.findElement(By.id((String) prop.get("letcode.select.fruitDropDown.id")));
        Select select = new Select(fruitDropDown);
        select.selectByVisibleText("Banana");
        System.out.println(select.isMultiple());

        String subtitle = driver.findElement(By.cssSelector("p[class='subtitle']")).getText();
//        if (subtitle.equals("You have selected Apple")) {
//            System.out.println("PASSED!");
//        }
//        else{
//            System.out.println("FAILED!");
//        }

        Assert.assertEquals(subtitle, "You have selected Banana");

        //multiple dropdown

        WebElement superheroDropdown = driver.findElement(By.id("superheros"));
        Select superHeroSelect = new Select(superheroDropdown);
        List<WebElement> superheroElements = superHeroSelect.getOptions();
        List<String> superheronames = new ArrayList<>();
        for (WebElement ele : superheroElements) {
            superheronames.add(ele.getText());
        }
        System.out.println(superheronames);

        Random rand = new Random();
        int i = rand.nextInt(superheronames.size()-1);

        superHeroSelect.selectByIndex(i);
        superHeroSelect.selectByIndex(rand.nextInt(superheronames.size()-1));
        Thread.sleep(2000);
        List<WebElement> subtitles = superHeroSelect.getAllSelectedOptions();
        for(WebElement sub : subtitles){
            System.out.println(sub.getText());
        }

        driver.quit();
    }
}