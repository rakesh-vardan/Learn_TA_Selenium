package io.learn.old_approach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class SampleTest {

    // Approach-1 used before Selenium WD v4.x - set the system property
    @Test
    public void testWithOldApproach() {
        // For running tests in Chrome
        System.setProperty("webdriver.chrome.driver", "/driver_Files/chromedriver.exe"); // this is mandatory
        WebDriver driver = new ChromeDriver();

        // For running tests in Edge
        System.setProperty("webdriver.edge.driver", "/driver_Files/edgedriver.exe"); // this is mandatory
        WebDriver driver1 = new EdgeDriver();
    }


    // Approach-2 used before Selenium WD v4.x - use Webdrivermanager library
    @Test
    public void testWithOldApproach2() {
        // For running tests in Chrome
        /*WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();*/

        // For running tests in Edge
        /*WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();*/
    }

    // Approach-3 used after Selenium WD v4.x - no manual work
    @Test
    public void testWithNewApproach() {
        // For running tests in Chrome
        WebDriver driver = new ChromeDriver();

        // For running tests in Edge
        WebDriver driver1 = new EdgeDriver();
    }

}
