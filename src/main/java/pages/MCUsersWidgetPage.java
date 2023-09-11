package pages;

import base.BaseClass;
import helper.ScreenshotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helper.WaitUtility.waittillElementNotEmpty;
import static helper.WaitUtility.waittillElementProperty;

public class MCUsersWidgetPage extends BaseClass
{
    WebDriverWait wait;
    ScreenshotUtility st= new ScreenshotUtility();

    @FindBy(xpath = "//div[contains(@ng-click,'totalNtLoggedInUsers')]/div[@class='ng-binding']")
    WebElement winActiveDir;

    @FindBy(xpath = "//div[contains(@ng-click,'totalNtUsers')]/div[@class='ng-binding']")
    WebElement totalnumUsers;

    @FindBy(xpath = "//div[contains(@ng-click,'totalXglLoggedInUsers')]/div[@class='ng-binding']")
    WebElement loggedinUsers;

    @FindBy(xpath = "//div[contains(@ng-click,'totalXglUsers')]/div[@class='ng-binding']")
    WebElement totalxglUsers;

    @FindBy(xpath = "//div[last()]/h4[contains(text(),'Users')]")
    WebElement totalUserCount;

    public MCUsersWidgetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getwinActiveDir()
    {
        waittillElementNotEmpty(driver,500,"//div[contains(@ng-click,'totalNtLoggedInUsers')]/div[@class='ng-binding']");
        return winActiveDir.getText();
    }
    public String gettotalnumUsers()
    {
        waittillElementNotEmpty(driver,500,"//div[contains(@ng-click,'totalNtUsers')]/div[@class='ng-binding']");
        return totalnumUsers.getText();
    }
    public String getloggedinUsers()
    {
        waittillElementNotEmpty(driver,500,"//div[contains(@ng-click,'totalXglLoggedInUsers')]/div[@class='ng-binding']");
        return loggedinUsers.getText();
    }
    public String gettotalxglUsers()
    {
        waittillElementNotEmpty(driver,500,"//div[contains(@ng-click,'totalXglUsers')]/div[@class='ng-binding']");
        return totalxglUsers.getText();
    }
    public String getTotalusercount()
    {
        waittillElementNotEmpty(driver,500,"//div[last()]/h4[contains(text(),'Users')]");
        String arr[]=totalUserCount.getText().split(" ");
        return arr[0];
    }

}
