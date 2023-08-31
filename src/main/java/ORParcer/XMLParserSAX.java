package ORParcer;


import helper.ExceptionHandling;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import java.io.File;


public class XMLParserSAX
{
    public Document LoadXMLParser()
    {
        Document document = null;
        try {
            File inputFile = new File(System.getProperty("user.dir") +"\\configs/OR.xml");
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(inputFile);
        } catch ( Exception e) {
            ExceptionHandling.handleException(e);
        }
        return document;
    }
    public By getXMLORProperty(String WebElementName)
    {
        Document document;
        document=LoadXMLParser();
        String node="//objectrepository"+"/"+WebElementName;
        String ele=document.selectSingleNode(node).getText();

        String locatorType = ele.split(":")[0];
        String locatorValue = ele.split(":")[1];
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
                System.out.println("locator type unidentified");
        }
        return locator;
    }
}
