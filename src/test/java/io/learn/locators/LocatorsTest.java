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
    public void testByHtmlAttributes() {
        driver.get(URL);

        // By id
        WebElement textById = driver.findElement(By.id("my-text-id"));
        assertThat(textById.getDomAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomAttribute("myprop")).isEqualTo("myvalue");

        // By name
        WebElement textByName = driver.findElement(By.name("my-text"));
        assertThat(textByName.getDomAttribute("type")).isEqualTo("text");
        assertThat(textByName.getDomAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textByName.isEnabled()).isTrue();
    }

    @Test
    public void testByTagName() {
        driver.get(URL);

        // By Tag name
        WebElement textarea = driver.findElement(By.tagName("textarea"));
        assertThat(textarea.getDomAttribute("rows")).isEqualTo("3");
    }

    @Test
    public void testByClassName() {
        driver.get(URL);

        // By Class name
        List<WebElement> elements = driver.findElements(By.className("form-control")); // returns multiple webelements in an array
        assertThat(elements).isNotEmpty(); // total 9 elements are returned
        assertThat(elements.get(0).getDomAttribute("name")).isEqualTo("my-text");
    }

    @Test
    public void testByLinkText() {
        driver.get(URL);

        // By Link text
        WebElement linkByText = driver.findElement(By.linkText("Return to index"));
        assertThat(linkByText.getTagName()).isEqualTo("a");
        assertThat(linkByText.getDomAttribute("href")).contains("index.html");

        // By Partial link text
        WebElement linkByPartialText = driver.findElement(By.partialLinkText("index"));
        assertThat(linkByPartialText.getTagName()).isEqualTo("a");
        assertThat(linkByPartialText.getDomAttribute("href")).contains("index.html");
    }

    @Test
    public void testByCssSelector() {
        driver.get(URL);

        // By CSS Selector
        WebElement hiddenElement = driver.findElement(By.cssSelector("input[type='hidden']"));
        assertThat(hiddenElement.isDisplayed()).isFalse();
    }

    @Test
    public void testByXpath() {
        driver.get(URL);

        // By XPath Selector
        WebElement hiddenElement = driver.findElement(By.xpath("//input[@type='hidden']"));
        assertThat(hiddenElement.isDisplayed()).isFalse();
    }
}
