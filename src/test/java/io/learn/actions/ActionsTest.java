package io.learn.actions;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionsTest extends BaseTest {

    @Test
    public void testContextAndDoubleClick() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);

        WebElement dropDown2 = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(dropDown2).build().perform();
        WebElement contextMenu2 = driver.findElement(By.id("context-menu-2"));
        assertThat(contextMenu2.isDisplayed()).isTrue();

        WebElement dropDown3 = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(dropDown3).build().perform();
        WebElement contextMenu3 = driver.findElement(By.id("context-menu-3"));
        assertThat(contextMenu3.isDisplayed()).isTrue();
    }

    @Test
    public void testMouseHover() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);

        WebElement compassImage = driver.findElement(By.xpath("//img[@src='img/compass.png']"));
        actions.moveToElement(compassImage).build().perform();

        WebElement caption = driver.findElement(RelativeLocator.with(By.tagName("p")).near(compassImage));
        assertThat(caption.getText()).isEqualTo("Compass");
    }

    @Test
    public void testMouseHover1() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        List<String> imageList = Arrays.asList("Compass", "Calendar", "Award", "Landscape");

        for(String imageName: imageList) {
            String locator = String.format("//img[@src='img/%s.png']", imageName.toLowerCase()); // //img[@src='img/landscape.png']
            WebElement image = driver.findElement(By.xpath(locator));
            actions.moveToElement(image).build().perform();

            WebElement caption = driver.findElement(RelativeLocator.with(By.tagName("p")).near(image));
            assertThat(caption.getText()).isEqualTo(imageName);
        }
    }

    @Test
    public void testDragAndDrop() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        WebElement draggable = driver.findElement(By.id("draggable"));
        int offSet = 100;
        Point initialLocation = draggable.getLocation();

        actions.dragAndDropBy(draggable, offSet, 0)
                .dragAndDropBy(draggable, 0, offSet)
                .dragAndDropBy(draggable, -offSet, 0)
                .dragAndDropBy(draggable, 0, -offSet)
                .build().perform();

        Point finalLocation = draggable.getLocation();
        assertThat(initialLocation).isEqualTo(finalLocation);
    }

    @Test
    public void testDragAndDrop2() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("target"));

        actions.dragAndDrop(source, target).build().perform();
        Point finalLocation = source.getLocation();

        assertThat(finalLocation).isEqualTo(target.getLocation());
    }

    @Test
    public void testClickAndHold() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        WebElement canvas = driver.findElement(By.tagName("canvas"));
        actions.moveToElement(canvas).clickAndHold();

        int numPoints = 10;
        int radius = 8;
        for (int i = 0; i <= numPoints; i++) {
            double angle = Math.toRadians((double) (360 * i) / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;
            actions.moveByOffset((int) x, (int) y);
        }

        actions.release(canvas).build().perform();
    }

    @Test
    public void testCopyAndPaste() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        WebElement textInput = driver.findElement(By.id("my-text-id"));
        WebElement textArea = driver.findElement(By.name("my-textarea"));

        actions.sendKeys(textInput, "Hello Tester").keyDown(Keys.CONTROL)
                .sendKeys(textInput, "a").sendKeys(textInput, "c")
                .sendKeys(textArea, "v").keyUp(Keys.CONTROL).build().perform();

        assertThat(textInput.getDomAttribute("value")).isEqualTo(textArea.getDomAttribute("value"));
    }
}














