package helper;

import base.BaseClass;
import dataProvider.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutor extends BaseClass
{
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void clickElementByJS(By ele)
    {
        js.executeScript("arguments[0].click();",ele);

    }
    public void setElementPropertyByJS(By ele, String height)
    {
        js.executeScript("arguments[0].style.height = '"+height+"';", ele);
    }
    public void sendKeysByJS(By ele,String str)
    {
        js.executeScript("arguments[0].setAttribute('value', '" + str +"')", ele);
    }
    public void highlightElement(WebElement ele,String style)
    {
        try {
            js.executeScript("arguments[0].style.border='"+style+"'", ele);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", ele);
        }
        catch (Exception e)
        {
            ExceptionHandling h=new ExceptionHandling(driver);
            h.handleException(e);
            Thread.currentThread().interrupt();
        }

    }
}
