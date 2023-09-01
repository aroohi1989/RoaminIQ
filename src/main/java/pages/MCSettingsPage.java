package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import dataProvider.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MCSettingsPage extends BaseClass
{
    WebDriverWait wait;
    WebElement mcsettingbtn;
    WebElement mcsettingfirstbtn;
    WebElement mcsettingsecondbtn;
    WebElement mcsettingthirdbtn;
    WebElement mcsettingforthbtn;
    WebElement mcsettingfirstvalue;
    WebElement mcsettingsecondvalue;
    WebElement mcsettingthirdvalue;
    WebElement mcsettingfourthvalue;

    WebElement mcsettingsave;
    WebElement mcsettingreset;
    WebElement mcdropdownoptions;
    WebElement mcsettingPopup;
    WebElement mcheadersQuadrants;


    public MCSettingsPage(WebDriver driver, RespositoryParser parser)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        mcsettingbtn=driver.findElement(parser.getobjectLocator("mcsetting"));
        mcsettingPopup=driver.findElement(parser.getobjectLocator("mcsettingPopup"));
        mcsettingfirstbtn=driver.findElement(parser.getobjectLocator("mcsettingfirstbtn"));
        mcsettingsecondbtn=driver.findElement(parser.getobjectLocator("mcsettingsecondbtn"));
        mcsettingthirdbtn=driver.findElement(parser.getobjectLocator("mcsettingthirdbtn"));
        mcsettingforthbtn=driver.findElement(parser.getobjectLocator("mcsettingforthbtn"));

        mcsettingfirstvalue=driver.findElement(parser.getobjectLocator("mcsettingfirstvalue"));
        mcsettingsecondvalue=driver.findElement(parser.getobjectLocator("mcsettingsecondvalue"));
        mcsettingthirdvalue=driver.findElement(parser.getobjectLocator("mcsettingthirdvalue"));
        mcsettingfourthvalue=driver.findElement(parser.getobjectLocator("mcsettingfourthvalue"));

        mcsettingsave=driver.findElement(parser.getobjectLocator("mcsettingsave"));
        mcsettingreset=driver.findElement(parser.getobjectLocator("mcsettingreset"));
        mcdropdownoptions=driver.findElement(parser.getobjectLocator("mcdropdownoptions"));

    }
    public void openMcSettings()
    {
        mcsettingbtn.click();
    }
    public void resetSettings()
    {
        mcsettingbtn.click();
        addwait(mcsettingreset);
        mcsettingreset.click();
    }
    public void saveMcSettings()
    {
        mcsettingsave.click();
    }
    public void setMcsettingfirstvalue(String widgetValue)
    {
        mcsettingfirstbtn.click();
        if(mcdropdownoptions.isDisplayed())
        {
           List<WebElement> option=driver.findElements(parser.getobjectLocator("mcdropdownoptions"));
            for (WebElement ele :option)
            {
                if(ele.getAttribute("InnerHTML").equalsIgnoreCase(widgetValue))
                {
                    ele.click();
                    break;
                }
            }
        }
    }

    public boolean verifymcvalueset()
    {
        Boolean flag=false;
        List<WebElement> ele=driver.findElements(parser.getobjectLocator("mcheadersQuadrants"));
        if(ele.get(0).getText().equalsIgnoreCase(ConfigReader.getPropertyvalue("missioncontrolvalue")))
        {
            flag=true;
        }
        return flag;
    }

    public void addwait(WebElement ele)
    {
        Duration timeout = Duration.ofSeconds(50);
        // Convert the Duration to milliseconds
        long timeoutMilliseconds = timeout.toMillis();
        wait= new WebDriverWait(driver,timeoutMilliseconds);
        wait.until(WebDriver->ExpectedConditions.visibilityOf(ele));
    }
}

