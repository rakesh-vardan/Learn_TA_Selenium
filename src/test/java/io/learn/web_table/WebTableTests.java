package io.learn.web_table;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTableTests extends BaseTest {

    @Test
    public void verifyTable() {
        driver.get(new File("ExampleFiles\\employeeTable.html").getAbsolutePath());

        // Locate the table
        WebElement table = driver.findElement(By.id("employeeTable"));

        // Get all the rows in the table body
        List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));

        // Iterate through all the rows and print the cell data
        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell : cells) {
                System.out.print(cell.getText() + " ");
            }
            System.out.println();
        }

        // Count rows in the table body
        System.out.println("Total Rows: " + rows.size()); // 3

        // Count the columns in the header
        int columnCount = table.findElements(By.xpath("//thead//tr/th")).size();
        System.out.println("Total Columns: " + columnCount); // 4

        // Locate the specific cell (second row, third column)
        WebElement cell = driver.findElement(By.xpath("//tbody//tr[2]/td[3]"));
        System.out.println("Employee Department: " + cell.getText()); // Finance

        // Locate the row containing the specific employee ID
        WebElement row1 = driver.findElement(By.xpath("//tbody//tr[td[text()='102']]"));
        String userName = row1.findElement(By.xpath("td[2]")).getText();
        System.out.println("User name: " + userName); // Bob

        // Locate the row containing the specific name
        WebElement row2 = driver.findElement(By.xpath("//tbody//tr[td[text()='Charlie']]"));
        // retrieve the department name
        String departmentName = row2.findElement(By.xpath("td[3]")).getText();
        System.out.println("Charlie's Department: " +departmentName); // IT

        System.out.println(this.getCellValue(table, 1, 3)); // HR
        System.out.println(this.getCellValue(table, 2, 3)); // Finance
        System.out.println(this.getCellValue(table, 3, 3)); // IT
    }

    private String getCellValue(WebElement table, int row, int column) {
        return table.findElement(By.xpath("//tbody//tr[ "+row+"]/td["+column+"]")).getText();
    }

    @Test
    public void testDatePicker() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        // get the month & year details
        LocalDate today = LocalDate.now(); // Jan 02, 2025
        int currentYear = today.getYear();
        int currentDay = today.getDayOfMonth();

        // Click on the date picker to open the calendar
        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.click();

        // Click on the current month text
        String yearLocator = String.format("//th[contains(text(), '%d')]", currentYear);
        WebElement monthElement = driver.findElement(By.xpath(yearLocator));
        monthElement.click();

        // Click on the left arrow to navigate to previous calendar year
        WebElement leftArrow = driver.findElement(By.cssSelector("div[class='datepicker-months'] th[class='prev']"));
        leftArrow.click();

        // Click on the current month from previous year
        WebElement monthPastYear = driver.findElement(By.xpath("//span[@class='month focused']"));
        monthPastYear.click();

        // Click the current date from the selected month
        String dayElementLocator = String.format("//td[@class='day' and text()='%d']", currentDay);
        WebElement dayElement = driver.findElement(By.xpath(dayElementLocator));
        dayElement.click();

        String oneYearBackDate = datePicker.getDomProperty("value");
        System.out.println("Actual date selected from calendar: " + oneYearBackDate);

        // Assert the expected date is equal to the one selected in the calendar
        LocalDate previousYear = today.minusYears(1); // Jan 02, 2024
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        System.out.println("Expected date: " + expectedDate);

        assertThat(oneYearBackDate).isEqualTo(expectedDate);

    }
}
