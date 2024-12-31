package io.learn.windows;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WindowHandlingTest extends BaseTest {

    @Test
    public void testNewTab() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initialHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles()).hasSize(2);

        driver.switchTo().window(initialHandle);
        driver.close();
        assertThat(driver.getWindowHandles()).hasSize(1);
    }

    @Test
    public void testNewWindow() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initialHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles()).hasSize(2);

        driver.switchTo().window(initialHandle);
        driver.close();
        assertThat(driver.getWindowHandles()).hasSize(1);
    }

    @Test
    public void testContentInNewTab() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String initialHandle = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[normalize-space()='Click Here']")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        assertThat(windowHandles).hasSize(2);

        for(String handle : windowHandles) {
            if(!handle.equals(initialHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        assertThat(driver.findElement(By.xpath("//h3")).getText()).isEqualTo("New Window");
        driver.switchTo().window(initialHandle);
        driver.close(); // not working at the moment
        assertThat(windowHandles).hasSize(1);
    }
}
