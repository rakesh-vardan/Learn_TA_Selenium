package io.learn.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTests2 {

    public static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        SafariOptions browserOptions = new SafariOptions();
        browserOptions.setPlatformName("macOS 13");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "XXXXXXXXXXX"); // enter your username here
        sauceOptions.put("accessKey", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); // enter your accessKey here
        sauceOptions.put("build", "selenium-build-ZFTV6");
        sauceOptions.put("name", "Sample Test Demo");
        browserOptions.setCapability("sauce:options", sauceOptions);

        // start the session
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);
    }

    @Test
    public void testSendKeys() throws InterruptedException {
        driver.get(URL);

        Thread.sleep(3000);
        WebElement inputText = driver.findElement(By.name("my-text"));
        String textValue = "Hello World!";
        inputText.sendKeys("Hello World!");
        assertThat(inputText.getDomProperty("value")).isEqualTo(textValue);

        Thread.sleep(3000);
        WebElement checkbox1 = driver.findElement(By.cssSelector("[type=checkbox]:checked"));
        assertThat(checkbox1.getDomAttribute("id")).isEqualTo("my-check-1");
        assertThat(checkbox1.isSelected()).isTrue();

        WebElement checkbox2 = driver.findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
        assertThat(checkbox2.getDomAttribute("id")).isEqualTo("my-check-2");
        assertThat(checkbox2.isSelected()).isFalse();

        checkbox1.click();
        assertThat(checkbox1.isSelected()).isFalse();

        checkbox2.click();
        assertThat(checkbox2.isSelected()).isTrue();

        Thread.sleep(3000);
        WebElement radio1 = driver.findElement(By.xpath("//input[@type='radio' and @checked]"));
        assertThat(radio1.getDomAttribute("id")).isEqualTo("my-radio-1");
        assertThat(radio1.isSelected()).isTrue();

        WebElement radio2 = driver.findElement(By.xpath("//input[@type='radio' and not(@checked)]"));
        assertThat(radio2.getDomAttribute("id")).isEqualTo("my-radio-2");
        assertThat(radio2.isSelected()).isFalse();

        radio2.click();
        assertThat(radio1.isSelected()).isFalse();
        assertThat(radio2.isSelected()).isTrue();

        Thread.sleep(3000);
        WebElement listBox = driver.findElement(By.name("my-select"));
        Select select = new Select(listBox);

        // print all option text values
        List<WebElement> options = select.getOptions();
        for(WebElement option: options) {
            System.out.println(option.getText());
        }

        // select one option based on visible text
        String optionLabel = "Three";
        select.selectByVisibleText(optionLabel);

        assertThat(select.getFirstSelectedOption().getText()).isEqualTo(optionLabel);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}
