package io.learn.iframes;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IframesTest extends BaseTest {

    @Test
    public void testIFrames() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");

        // switched to an iFrame
        driver.switchTo().frame("my-iframe");

        // interacted with elements in iFrame
        String text = driver.findElement(By.xpath("//p[@class='lead'][1]")).getText();
        System.out.println(text);

        // switched back to the main window
        driver.switchTo().defaultContent();

        // interacted with elements on the parent document
        String headerText = driver.findElement(By.xpath("//h5")).getText();
        System.out.println(headerText);
    }

    @Test
    public void testIFrames2() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("my-iframe"));

        By pTagName = By.tagName("p");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pTagName, 0)); // 20 p tags ara available, p tags > 0
        List<WebElement> paragraphs = driver.findElements(pTagName);
        assertThat(paragraphs).hasSize(20); // size is 20

        driver.switchTo().defaultContent();
        assertThat(driver.findElement(By.xpath("//h5")).getText()).isEqualTo("Practice site");
    }
}
