package helper;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility extends BaseClass
{
    //Do not use unless critically necessary
    public static void waitStatic(long maxSecondsToWait)
    {
        try
        {
          Thread.sleep(maxSecondsToWait);
        }
        catch (InterruptedException e) {
            ExceptionHandling.handleException(e);
        }
    }
    public static void waittillElementNotEmpty(WebDriver driver, long maxSecondsToWait, String xpathlocator)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
            ExpectedCondition<Boolean> attributeNotEmpty = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.xpath(xpathlocator));
                    String attributeValue = element.getText();
                    return !attributeValue.isEmpty();
                }
            };
            wait.until(attributeNotEmpty);
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error("Element did not become visible within the specified time: " +e.getMessage());
        }
    }
    public static void waittillElementNotZero(WebDriver driver, long maxSecondsToWait,String xpathlocator)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
            ExpectedCondition<Boolean> attributeNotZero = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.xpath(xpathlocator));
                    String attributeValue = element.getText();
                    return !attributeValue.equalsIgnoreCase("0");
                }
            };
            wait.until(attributeNotZero);
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error("Element did not become visible within the specified time: " +e.getMessage());
        }
    }

    public static void waittillElementNotloaded(WebDriver driver, long maxSecondsToWait,String xpathlocator)
    {
        try
        {
        WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
        ExpectedCondition<Boolean> attributeNotZero = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(By.xpath(xpathlocator));
                String attributeValue = element.getText();
                return !attributeValue.equalsIgnoreCase("0");
            }
        };
        wait.until(attributeNotZero);
    }
        catch(Exception e)
    {
        ExceptionHandling.handleException(e);
        log.error("Element did not become visible within the specified time: " +e.getMessage());
    }
    }
}
