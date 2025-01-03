package io.learn.browser_specific.insecure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InsecureChromeTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // same for edge & firefox
        driver = new ChromeDriver(options);
    }

    @Test
    public void testInsecure() {
        driver.get("https://self-signed.badssl.com/");
        String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
        Color red = new Color(255, 0, 0, 1);
        assertThat(Color.fromString(bgColor)).isEqualTo(red);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
