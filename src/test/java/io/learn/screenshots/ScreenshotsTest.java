package io.learn.screenshots;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.assertj.core.api.Assertions.assertThat;

public class ScreenshotsTest extends BaseTest {

    @Test
    public void testScreenshotPNG() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().window().maximize();

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

        Path destination = Paths.get("screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);

        assertThat(destination).exists();
    }

    @Test
    public void testScreenshotBase64() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().window().maximize();

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        String screenshot = takesScreenshot.getScreenshotAs(OutputType.BASE64);

        System.out.println("Screenshot in BASE64 format: data:image/png;base64," + screenshot);
        // data:image/png;base64,
        assertThat(screenshot).isNotEmpty();
    }

    @Test
    public void testWebElementScreenshot() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement element = driver.findElement(By.tagName("form"));
        File screenshot = element.getScreenshotAs(OutputType.FILE);

        Path destination = Paths.get("element-screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);

        assertThat(destination).exists();
    }


}
