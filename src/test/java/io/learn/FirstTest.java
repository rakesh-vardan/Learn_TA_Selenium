package io.learn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        String title = driver.getTitle();
        System.out.println("Title of the page is: " +title);
        driver.quit();
    }
}
