package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import dataProvider.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MCSettingsPage extends BaseClass
{
    WebDriverWait wait;
    @FindBy(xpath = "//button[@class='btnSmall dark']")
    public WebElement mcsettingbtn;

    @FindBy(xpath = "//div[@class='modal-content'")
    public WebElement mcsettingPopup;

    @FindBy(xpath = "//div[@id='missionLayout.firstQuadrant']/div[2]/i")
    WebElement mcsettingfirstbtn;

    @FindBy(xpath = "//div[@id='missionLayout.secondQuadrant']/div[2]/i")
    WebElement  mcsettingsecondbtn;

    @FindBy(xpath = "//div[@id='missionLayout.thirdQuadrant']/div[2]/i")
    WebElement mcsettingthirdbtn;

    @FindBy(xpath = "//div[@id='missionLayout.fourthQuadrant']/div[2]/i")
    WebElement mcsettingforthbtn;

    @FindBy(xpath = "//div[@id='missionLayout.firstQuadrant']/div[1]")
    WebElement mcsettingfirstvalue;

    @FindBy(xpath = "//div[@id='missionLayout.secondQuadrant']/div[1]")
    WebElement mcsettingsecondvalue;

    @FindBy(xpath = "//div[@id='missionLayout.thirdQuadrant']/div[1]")
    WebElement mcsettingthirdvalue;

    @FindBy(xpath = "//div[@id='missionLayout.fourthQuadrant']/div[1]")
    WebElement mcsettingfourthvalue;

    @FindBy(id="cancelSaveOk.Save")
    WebElement mcsettingsave;

    @FindBy(id="cancelSaveOk.Reset")
    WebElement mcsettingreset;

    @FindBy(xpath = "//div[@class='dropDown']/div")
    WebElement mcdropdownoptions;

    @FindBy(xpath = "//h2[contains(@class,'ng-binding')]")
    WebElement mcheadersQuadrants;

    public MCSettingsPage(WebDriver driver, RespositoryParser parser)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);


       /* mcsettingPopup=driver.findElement(parser.getobjectLocator("mcsettingPopup"));
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
        mcdropdownoptions=driver.findElement(parser.getobjectLocator("mcdropdownoptions"));*/
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

