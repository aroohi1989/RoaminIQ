package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.WebDriverWrapper;

import static helper.Utility.waitForElementPresent;

public class DateUtility extends WebDriverWrapper
{
    public static void selectDate(WebDriver driver, WebElement datePicker, String Day, String Month, String Year)
    {

        waitForElementPresent(driver, datePicker, 10);
        try
        {

            String monthYearVal = driver.findElement(By.className("ui-datepicker-title")).getText();

            while(!(getMonthYear(monthYearVal)[0].equals(Month) &&
                    getMonthYear(monthYearVal)[1].equals(Year)))
            {
                driver.findElement(By.xpath("//a[@title=\"Next\"]")).click();
                monthYearVal = driver.findElement(By.className("ui-datepicker-title")).getText();
            }

            driver.findElement(By.xpath("//a[text()='"+Day+"']")).click();

            //Print selected date from calendar
            String selectedDate = datePicker.getAttribute("value");
            //System.out.println("Selected Date: " + selectedDate);
            logger.info("Selected Date: "+selectedDate);
        }

        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error("Thrown Exception: "+e.getMessage());
        }


    }

    //method to get month year separtly
    public static String[] getMonthYear(String monthYearVal)
    {
        return monthYearVal.split(" ");
    }
}
