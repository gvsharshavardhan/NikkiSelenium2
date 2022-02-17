package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstSeleniumCode {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();
        Thread.sleep(3000);
        String actualTitle = driver.findElement(By.className("title")).getText();
        if(actualTitle.equals("PRODUCT")){
            System.out.println("PASSED!!");
        }
        else{
            System.out.println("FAILED!!");
        }
        driver.quit();

    }
}