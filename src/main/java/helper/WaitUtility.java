package helper;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;

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

    public static void waittillElementProperty(WebDriver driver, long maxSecondsToWait,String xpathlocator)
    {
        try
        {
        WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
        ExpectedCondition<Boolean> attributeProperty = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(By.xpath(xpathlocator));
                String attributeValue = element.getAttribute("class");
                return !attributeValue.contains("ng-hide");
            }
        };
        wait.until(attributeProperty);
    }
        catch(Exception e)
    {
        ExceptionHandling.handleException(e);
        log.error("Element did not become visible within the specified time: " +e.getMessage());
    }
    }
    public static void waittillElementInteractable(WebDriver driver, long maxSecondsToWait,String xpathlocator)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
            ExpectedCondition<Boolean> attributeProperty = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.xpath(xpathlocator));
                    Boolean attributeValue = element.isDisplayed();
                    return !attributeValue;
                }
            };
            wait.until(attributeProperty);
        }
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error("Element did not become visible within the specified time: " +e.getMessage());
        }
    }
    public static void WaitForAPIResponse()
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // URL of the API endpoint you want to check
        String apiUrl = "http://10.96.200.236/xglweb/Setup/GetMarkets?$top=2000";

        try {
            // Use HttpURLConnection to send an HTTP GET request to the API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Wait until the API returns a response with status code 200 (OK)
            wait.until(webDriver -> {
                try {
                    int responseCode = connection.getResponseCode();
                    System.out.println("response is "+responseCode);
                    return responseCode == 200;
                } catch (Exception e) {
                    return false;
                }
            });

            // Once the API returns a 200 OK response, you can proceed with your Selenium actions
            // For example, navigate to a web page or interact with elements on the page

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
