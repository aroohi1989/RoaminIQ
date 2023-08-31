package helper;

import base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrapper.WebDriverWrapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Utility extends WebDriverWrapper {
    public static String captureScreenshotInBase64(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        return ts.getScreenshotAs(OutputType.BASE64);

    }

    public  static synchronized WebDriver getDriver() {
        return BaseClass.driver;
    }

    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);

        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getCurrentTime() {
        String date;
        date = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.S").format(new Date());
        date = date.replace(":", "_");
        return date;
    }

    public void captureScreenshot(WebDriver driver, String testName) {
        log.log(Level.INFO,"Taking screenshot");
        try {
            FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./screenshots/" + testName + "_" + getCurrentTime() + ".png"));
        } catch (IOException e) {
            log.log(Level.ERROR,"Something went wrong "+ e.getMessage());
        }

    }

    public String randomAlphaNumeric(int len) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            // generate a random index in the range of the characters string
            int index = rand.nextInt(characters.length());
            // extract the character at the generated index and append it to the StringBuilder
            char c = characters.charAt(index);
            builder.append(c);
        }
        String code = builder.toString();
        return code;
    }

    public static void waitForElementPresent(WebDriver driver, WebElement Locator, long maxSecondsToWait) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
            wait.until(ExpectedConditions.visibilityOf(Locator));
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }

    }

    public static void selectbyVisibleText(WebDriver driver, WebElement element, String value) {
        waitForElementPresent(driver, element, 5);
        try {
            Select select = new Select(element);
            select.selectByVisibleText(value);
        } catch (Exception e) {
            ExceptionHandling.handleException(e);

        }
    }

    public static void selectRadiobutton(WebDriver driver, WebElement Locator) {
        waitForElementPresent(driver, Locator, 5);
        try {
            if (!(Locator.isSelected())) {
                Locator.click();
            }
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }
    }

    public static void inputText(WebDriver driver, WebElement element, String value) {
        waitForElementPresent(driver, element, 5);
        try {
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }
    }

    public static void clickonWebElement(WebDriver driver, WebElement element, long waitTimeinseconds) {
        waitForElementPresent(driver, element, waitTimeinseconds);
        try {
            element.click();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }
    }

    public static void clearTextbox(WebDriver driver, WebElement element) {

        waitForElementPresent(driver, element, 5);
        try {
            if (!element.getAttribute("value").isEmpty()) {
                element.clear();
            }
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }
    }
    public static void getElementText(WebDriver driver,WebElement element)
    {
        waitForElementPresent(driver, element, 5);
        try
        {
            if(element.isDisplayed())
            {
                String elementText = element.getAttribute("value");
                log.info(elementText);
            }

        }
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error(e.getMessage());
        }
    }

    public static void selectCheckbox(WebDriver driver,WebElement element)
    {

        waitForElementPresent(driver, element, 5);
        try
        {
            if(!element.isSelected())
            {
                element.click();
                log.info("element is selected successfully");
            }
        }
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error(e.getMessage());
        }
    }
    public static void waitforPagetoLoad(WebDriver driver, long maxSecondsToWait)
    {
        try {

            WebDriverWait wait = new WebDriverWait(driver,maxSecondsToWait);
            wait.until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        }
        catch(WebDriverException e)
        {
            ExceptionHandling.handleException(e);
        }
    }

    public static void waitForAllElementsToBePresent(WebDriver driver, long maxSecondsToWait)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*")));
            log.info("Element is present");
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error("Element did not become visible within the specified time: " +e.getMessage());
        }
    }

    public static void clickLinkbyText(WebDriver driver, String linkText)
    {
        waitForAllElementsToBePresent(driver,10);
        try
        {
            WebElement link = driver.findElement(By.linkText(linkText));
            link.click();
        }
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
        }
    }
    public static void clickLinkbyAttribute(WebDriver driver, String attributeName, String attributeValue)
    {
        waitForAllElementsToBePresent(driver,10);
        try
        {
            String xpathExpression = String.format("//a[@%s='%s']",attributeName, attributeValue);
            WebElement link = driver.findElement(By.xpath(xpathExpression));
            link.click();
            logger.info("Successfully clicked on link present on webpage");
        }
        catch(WebDriverException e)
        {
            ExceptionHandling.handleException(e);
        }
    }
    public static void acceptAlertBox(WebDriver driver)
    {
        waitForAllElementsToBePresent(driver,10);
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
        }
    }
    public static void cancelAlertBox(WebDriver driver)
    {
        waitForAllElementsToBePresent(driver,10);
        try
        {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }
        catch(WebDriverException e) {
            ExceptionHandling.handleException(e);
        }
    }
    public static void getTextfromAlertBox(WebDriver driver)
    {
        waitForAllElementsToBePresent(driver,10);
        try
        {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            log.info(alert.getText());
        }
        catch(WebDriverException e)
        {
            ExceptionHandling.handleException(e);
        }

    }
    public static void switchToFramefromWebElement(WebDriver driver, WebElement Framelocator)
    {
        waitForElementPresent(driver, Framelocator, 5);
        try
        {
            driver.switchTo().frame(Framelocator);
        }
        catch(WebDriverException e)
        {
            ExceptionHandling.handleException(e);
            log.error(e.getMessage());
        }

    }
}
