package helper;

import base.BaseClass;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static helper.Utility.waitForAllElementsToBePresent;

public class BrowserUtilities extends BaseClass
{
    public void refreshbrowser()
    {
        driver.navigate().refresh();
        Utility ut=new Utility();
        ut.waitforPagetoLoad(driver,50);
    }
    //5. method for browser back
    public static void backfromBrowser()
    {
        try
        {
            driver.navigate().back();
            System.out.println("Navigated back from browser");
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            logger.error("An exception occurred while navigating back from browser: " + e.getMessage());
        }
    }

//6. method for move browser forward

    public static void moveForwardBrowser()
    {
        try
        {
            driver.navigate().forward();
            System.out.println("Navigated forward to browser");
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            logger.error("An exception occurred while navigating forward to browser: " + e.getMessage());
        }
    }

//7. method for switch browser tab 1 to tab 2

    public static void switchBrowserTab(WebDriver driver, int tabIndex)
    {
        waitForAllElementsToBePresent(driver,10);
        try
        {
            List<String> newTab = new ArrayList<String>(driver.getWindowHandles());
            if(tabIndex >=0 && tabIndex < newTab.size())
            {
                driver.switchTo().window(newTab.get(tabIndex));
                System.out.println("Switched successfully on new tab");
            }
            else
            {
                System.out.println("Found invalid tab index..."+tabIndex);
                logger.error("invalid tab index");
            }
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            logger.error("An exception occurred while navigating new browser tab:");
        }
    }

}
