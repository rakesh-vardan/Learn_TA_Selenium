package io.learn.alerts;

import io.learn.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertsTest extends BaseTest {

    private static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html";

    @Test
    public void testSimpleAlert() {
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement simpleAlertButton = driver.findElement(By.id("my-alert"));
        simpleAlertButton.click();

        Alert alert = driver.switchTo().alert(); // mandatory step
        assertThat(alert.getText()).isEqualTo("Hello world!");
        alert.accept(); // clicks on OK button
    }

    @Test
    public void testConfirmationAlert() {
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement confirmationAlertButton = driver.findElement(By.id("my-confirm"));
        confirmationAlertButton.click();

        Alert alert = driver.switchTo().alert(); // mandatory step
        assertThat(alert.getText()).isEqualTo("Is this correct?");
        alert.dismiss(); // clicks on CANCEL button
        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo("You chose: false");

        confirmationAlertButton.click();
        alert.accept(); // clicks on OK button
        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo("You chose: true");
    }

    @Test
    public void testPromptAlert() {
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement promptAlertButton = driver.findElement(By.id("my-prompt"));
        promptAlertButton.click();

        Alert alert = driver.switchTo().alert(); // mandatory step
        assertThat(alert.getText()).isEqualTo("Please enter your name");
        String text = "Hello Tester!";
        alert.sendKeys(text);
        alert.accept(); // clicks on OK button

        assertThat(driver.findElement(By.id("prompt-text")).getText()).isEqualTo("You typed: " + text);
    }

    @Test
    public void testModal() {
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement promptAlertButton = driver.findElement(By.id("my-modal"));
        promptAlertButton.click();

//        WebElement modalText = driver.findElement(By.cssSelector(".modal-body"));
//        assertThat(modalText.getText()).isEqualTo("This is the modal body");

        // explicit wait - TBD
        WebElement closeButton = driver.findElement(By.xpath("//button[text()='Close']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();

        assertThat(driver.findElement(By.id("modal-text")).getText()).isEqualTo("You chose: Close");
    }
}
