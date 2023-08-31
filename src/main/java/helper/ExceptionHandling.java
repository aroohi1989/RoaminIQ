package helper;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import wrapper.WebDriverWrapper;

public class ExceptionHandling extends WebDriverWrapper
{
    public ExceptionHandling(WebDriver driver)
    {
        BaseClass.driver =driver;
    }
    public static String handleException(Exception e)
    {
        switch(e.getClass().getSimpleName())
        {
            case "NoSuchElementException" :
                System.out.println("NoSuchElementException occurred: " + e.getMessage());
                break;
            case "StaleElementReferenceException" :
                System.out.println("StaleElementReferenceException occurred: " + e.getMessage());
                break;
            case "NullPointerException" :
                System.out.println("NullPointerException occurred: " + e.getMessage());
                break;
            case "ArithmeticException" :
                System.out.println("ArithmeticException occurred: " + e.getMessage());
                break;
            case "IOException" :
                System.out.println("IOException occurred: " + e.getMessage());
                break;
            case "AWTException" :
                System.out.println("AWTException occurred: " + e.getMessage());
                break;
            case "FileNotFoundException":
                System.out.println("FileNotFoundException occurred: " + e.getMessage());
                break;
            case "TimeoutException" :
                System.out.println("TimeoutException occurred: " +e.getMessage());
                break;
            case "SQLException" :
                System.out.println("SQLException occurred: " +e.getMessage());
                break;
            default:
                System.out.println("Exception occurred "+e.getMessage());
        }
        return e.getMessage();
    }
}
