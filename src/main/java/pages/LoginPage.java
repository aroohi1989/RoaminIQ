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

public class LoginPage extends BaseClass
{
    WebDriverWait wait;
    //@FindBy(xpath = "//input[@id='login.login']")
    WebElement user;
    //@FindBy(xpath = "//input[@id='login.password']")
    WebElement passwd;
    //@FindBy(xpath = "//button[@id='login.loginButton']")
    WebElement login;
    public By getMissionControl()
    {
        return By.xpath("//div[@id='header.missionControl']");
    }
    public LoginPage(WebDriver driver, RespositoryParser parser)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
         user=driver.findElement(parser.getobjectLocator("user"));
         passwd=driver.findElement(parser.getobjectLocator("passwd"));
         login=driver.findElement(parser.getobjectLocator("login"));
    }

    public void setUserName(String szusername){
        user.sendKeys(szusername);
    }
    public void setPassword(String szpassword){
        passwd.sendKeys(szpassword);
    }
    public void clickLogin(){
        login.click();
    }

    public void Login_To_Application(String username, String password)
    {
     this.setUserName(username);
     this.setPassword(password);
     this.clickLogin();
    }

    public void addwait()
    {
        Duration timeout = Duration.ofSeconds(50);
        // Convert the Duration to milliseconds
        long timeoutMilliseconds = timeout.toMillis();
        wait= new WebDriverWait(driver,timeoutMilliseconds);
       wait.until(ExpectedConditions.visibilityOfElementLocated(getMissionControl()));
    }
}

