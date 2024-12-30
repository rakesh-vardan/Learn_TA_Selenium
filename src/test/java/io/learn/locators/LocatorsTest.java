package io.learn.locators;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorsTest extends BaseTest {

    public static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @Test
    void testByHtmlAttributes() {
        driver.get(URL);

        // By id
        WebElement textById = driver.findElement(By.id("my-text-id"));
        assertThat(textById.getDomAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomAttribute("myprop")).isEqualTo("myvalue");

        // By name
        WebElement textByName = driver.findElement(By.name("my-text"));
        assertThat(textByName.isEnabled()).isTrue();
    }

    @Test
    public void testByTagName() {
        driver.get(URL);

        // By tag name
        WebElement textarea = driver.findElement(By.tagName("textarea"));
        assertThat(textarea.getDomAttribute("rows")).isEqualTo("3");
    }

    @Test
    public void testByClassName() {
        driver.get(URL);

        // By class name
        List<WebElement> byClassName = driver.findElements(By.className("form-control"));
        assertThat(byClassName).isNotEmpty();
        assertThat(byClassName.get(0).getDomAttribute("name")).isEqualTo("my-text");
    }

    @Test
    void testByLinkText() {
        driver.get(URL);

        // by link text
        WebElement linkByText = driver.findElement(By.linkText("Return to index"));
        assertThat(linkByText.getTagName()).isEqualTo("a");
        assertThat(linkByText.getDomAttribute("href")).contains("index.html");

        // by partial link text
        WebElement linkByPartialText = driver.findElement(By.partialLinkText("index"));
        assertThat(linkByPartialText.getLocation()).isEqualTo(linkByText.getLocation());
        assertThat(linkByPartialText.getRect()).isEqualTo(linkByText.getRect());
    }

    @Test
    void testByCssSelector() {
        driver.get(URL);

        // by CSS selector
        WebElement hidden = driver.findElement(By.cssSelector("input[type=hidden]"));
        assertThat(hidden.isDisplayed()).isFalse();
    }

    @Test
    void testByXPath() {
        driver.get(URL);

        // by xpath
        WebElement hidden = driver.findElement(By.xpath("//input[@type='hidden']"));
        assertThat(hidden.isDisplayed()).isFalse();
    }

}
