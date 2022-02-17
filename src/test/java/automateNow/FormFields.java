package automateNow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class FormFields {

    static String homepageButtonsXpath = "//a[text()='$$']";
    static Properties prop = new Properties();

    public static void main(String[] args) throws InterruptedException, IOException {

        FileInputStream fis = new FileInputStream("./ORs/automatenow.properties");
        prop.load(fis);
        int[] options = {1, 3};

        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://automatenow.io/sandbox-automation-testing-practice-website/");
        String formFieldsXpath = ((String) prop.get("automatenow.sandbox.fields.xpath")).replace("$$", "Form Fields");
        driver.findElement(By.xpath(formFieldsXpath)).click();
        System.out.println(driver.getTitle());
        driver.findElement(By.id((String) prop.get("automatenow.sandbox.formfields.inputfield.id"))).sendKeys("nikki");

        for (int i : options) {
            String option = ((String) prop.get("automatenow.sandbox.formfields.checkboxes.xpath")).replace("$$", String.valueOf(i));
            driver.findElement(By.xpath(option)).click();
        }

        List<WebElement> radios = driver.findElements(By.xpath((String) prop.get("automatenow.sandbox.formfields.radiobutton.xpath")));
        for(WebElement radio:radios){
            radio.click();
            Thread.sleep(2000);
        }


//        driver.quit();


    }
}
