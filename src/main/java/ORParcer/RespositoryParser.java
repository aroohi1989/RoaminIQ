package ORParcer;

import base.BaseClass;
import helper.ExceptionHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;

import java.util.List;
import java.util.Properties;

public class RespositoryParser extends BaseClass
{
    private FileInputStream stream;
    private String RepositoryFile;
    private Properties propertyFile = new Properties();

    public RespositoryParser(String fileName)
    {
        try {
            this.RepositoryFile = fileName;
            stream = new FileInputStream(RepositoryFile);
            propertyFile.load(stream);
        }
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
        }
    }

    public By getobjectLocator(String locatorName)
    {
        String locatorProperty = propertyFile.getProperty(locatorName);
        System.out.println(locatorProperty);
        String locatorType = locatorProperty.split(":")[0];
        String locatorValue = locatorProperty.split(":")[1];

        By locator = null;
        switch(locatorType)
        {
            case "Id":
                locator = By.id(locatorValue);
                break;
            case "Name":
                locator = By.name(locatorValue);
                break;
            case "CssSelector":
                locator = By.cssSelector(locatorValue);
                break;
            case "LinkText":
                locator = By.linkText(locatorValue);
                break;
            case "PartialLinkText":
                locator = By.partialLinkText(locatorValue);
                break;
            case "TagName":
                locator = By.tagName(locatorValue);
                break;
            case "Xpath":
                locator = By.xpath(locatorValue);
                break;
            default:
                //log.error("Object locator not found in the case");
        }
        return locator;
    }

    public List<WebElement> getobjectLocatorlist(String locatorName)
    {
        String locatorProperty = propertyFile.getProperty(locatorName);
        System.out.println(locatorProperty);
        String locatorType = locatorProperty.split(":")[0];
        String locatorValue = locatorProperty.split(":")[1];

        List<WebElement> locator = null;
        switch(locatorType)
        {
            case "Id":
                locator = driver.findElements(By.id(locatorValue));
                break;
            case "Name":
                locator = driver.findElements(By.name(locatorValue));
                break;
            case "CssSelector":
                locator = driver.findElements(By.cssSelector(locatorValue));
                break;
            case "LinkText":
                locator = driver.findElements(By.linkText(locatorValue));
                break;
            case "PartialLinkText":
                locator = driver.findElements(By.partialLinkText(locatorValue));
                break;
            case "TagName":
                locator = driver.findElements(By.tagName(locatorValue));
                break;
            case "Xpath":
                locator = driver.findElements(By.xpath(locatorValue));
                break;
            default:
                logger.error("Object locator not found in the case");
        }
        return locator;
    }
}
