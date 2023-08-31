package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MCSettings extends BaseClass
{
    WebDriverWait wait;
    WebElement user;
    WebElement passwd;
    WebElement login;
    public WebElement getMissionControl()
    {
        return driver.findElement(parser.getobjectLocator("missioncontrol"));
    }
    public MCSettings(WebDriver driver, RespositoryParser parser)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
         user=driver.findElement(parser.getobjectLocator("user"));
    }

    public void setUserName(String szusername){
        user.sendKeys(szusername);
    }
    public void setPassword(String szpassword){
        passwd.sendKeys(szpassword);
    }

}

