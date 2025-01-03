package io.learn.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTests {

    public static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {

/*        // Grid available on my local machine
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), // Selenium Grid URL - Standalone
                new FirefoxOptions());*/

/*        // Grid available on my AWS virtual machine - remote
        driver = new RemoteWebDriver(new URL("http://13.233.193.203:4444/"), // Selenium Grid URL - Remote
                new FirefoxOptions());*/

        driver = new RemoteWebDriver(new URL("http://13.233.193.203:4444/"), // Selenium Grid URL - Remote
                new ChromeOptions());
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
