package io.learn.interactions;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InteractionsTest extends BaseTest {

    public static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @Test
    public void testSendKeys() {
        driver.get(URL);

        WebElement inputText = driver.findElement(By.name("my-text"));
        String textValue = "Hello World!";
        inputText.sendKeys("Hello World!");
        assertThat(inputText.getDomProperty("value")).isEqualTo(textValue);

        inputText.clear();
        assertThat(inputText.getDomProperty("value")).isEmpty();
    }

    @Test
    public void testClickButton() {
        driver.get(URL);

        WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
        submitButton.click();

        WebElement confirmationHeader = driver.findElement(By.cssSelector(".display-6"));
        String text = confirmationHeader.getText();
        assertThat(text).isEqualTo("Form submitted");
    }

    @Test
    public void testClickLink() {
        driver.get(URL);

        WebElement returnToIndex = driver.findElement(By.linkText("Return to index"));
        returnToIndex.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("index.html");
    }

    @Test
    public void testClickCheckBoxes() {
        driver.get(URL);

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
    }

    @Test
    public void testClickRadioButtons() {
        driver.get(URL);

        WebElement radio1 = driver.findElement(By.xpath("//input[@type='radio' and @checked]"));
        assertThat(radio1.getDomAttribute("id")).isEqualTo("my-radio-1");
        assertThat(radio1.isSelected()).isTrue();

        WebElement radio2 = driver.findElement(By.xpath("//input[@type='radio' and not(@checked)]"));
        assertThat(radio2.getDomAttribute("id")).isEqualTo("my-radio-2");
        assertThat(radio2.isSelected()).isFalse();

        radio2.click();
        assertThat(radio1.isSelected()).isFalse();
        assertThat(radio2.isSelected()).isTrue();
    }

    @Test
    public void testDropDowns() {
        driver.get(URL);
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

    @Test
    public void testDataList() {
        driver.get(URL);

        WebElement dataList = driver.findElement(By.name("my-datalist"));
        dataList.click();

        WebElement option = driver.findElement(By.xpath("//datalist/option[2]"));
        String optionValue = option.getDomAttribute("value");
        dataList.sendKeys(optionValue);

        assertThat(optionValue).isEqualTo("New York");
    }
}
