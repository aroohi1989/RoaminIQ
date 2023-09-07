package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import helper.BrowserUtilities;
import helper.ExceptionHandling;
import helper.ScreenshotUtility;
import helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static helper.Utility.waitForAllElementsToBePresent;
import static helper.Utility.waitforPagetoLoad;
import static helper.WaitUtility.*;

public class NetworkInstancePage extends BaseClass
{
    WebDriverWait wait;
    ScreenshotUtility st= new ScreenshotUtility();

    @FindBy(xpath = "//div[contains(@class,'defaultRadioInputs')]")
    By netSumRadio; //Current Week,Next 4 Weeks,Custom Dates

    @FindBy(xpath = "//div[contains(@class,'inputCalendar')]")
    WebElement inputCalender;

    @FindBy(xpath = "//div[@class='icon']/i")
    WebElement showCalender;

    @FindBy(xpath = "//div[contains(@class,'calendarWrapper')]")
    WebElement calender;

    @FindBy(xpath = "//th[@class='next']")
    By next;

    @FindBy(xpath = "//th[@class='switch ng-binding']")
    By monthCalender;

    @FindBy(xpath = "//tr[@ng-repeat='week in weeks']")
    By weeksCalender;

    @FindBy(xpath = "//tr[@ng-repeat='week in weeks'][3]/td[@ng-repeat='day in week']")
    By daysCalender;

    @FindBy(xpath = "//tr[@ng-repeat='week in weeks'][3]/td[@ng-repeat='day in week'][1]")
    WebElement startdateCalender;

    @FindBy(xpath = "//tr[@ng-repeat='week in weeks'][3]/td[@ng-repeat='day in week'][7]")
    WebElement enddateCalender;

    @FindBy(xpath = "//button[2]")
    WebElement save;

    @FindBy(xpath = "//div[contains(@ng-click,'PendingAllocation')]/div[@class='ng-binding']")
    public WebElement pendingallocation;

    @FindBy(xpath = "//div[contains(@ng-click,'PendingBuild')]/div[@class='ng-binding']")
    WebElement pendingbuild;

    @FindBy(xpath = "//div[contains(@ng-click,'Empty')]/div[@class='ng-binding']")
    WebElement empty;

    @FindBy(xpath = "//h1[contains(text(),'Empty')]/following-sibling::h1")
    WebElement actualEmptyCount;

    @FindBy(xpath = "//h1[contains(text(),'Pending Build')]/following-sibling::h1")
    WebElement actualPendingBuildCount;

    @FindBy(xpath = "//h1[contains(text(),'Pending Allocation')]/following-sibling::h1")
    WebElement actualPendingAllocationCount;

    public NetworkInstancePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void PeriodSelection(String radioOption)
    {
        List<WebElement> ele=driver.findElements(By.xpath("//div[contains(@class,'defaultRadioInputs')]"));
        for (WebElement e:ele)
        {
            if(e.getText().equalsIgnoreCase(radioOption))
            {
                e.click();
                break;
            }
        }
    }

    public void selectMonth()
    {
        try
        {
            if(!calender.isDisplayed())
            {
                showCalender.click();
            }
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
        }
        List<WebElement> ele= driver.findElements(By.xpath("//th[@class='next']"));
        for (WebElement e:ele)
        {
            e.click();
        }
    }
    public void selectDate()
    {
        List<WebElement> ele =driver.findElements(By.xpath("//tr[@ng-repeat='week in weeks'][3]/td[@ng-repeat='day in week'][1]"));
        ele.get(0).click(); // Start date selected
        List<WebElement> ele1 =driver.findElements(By.xpath("//tr[@ng-repeat='week in weeks'][3]/td[@ng-repeat='day in week'][7]"));
        ele1.get(1).click();// End date selected
        save.click();
    }

    public String getmcpendingallocationcount()
    {
        //waittillElementNotEmpty(driver,60,"//div[contains(@ng-click,'PendingAllocation')]/div[@class='ng-binding']");
        waittillElementProperty(driver,60,"//div[contains(@ng-click,'PendingAllocation')]/div[1]");
        return pendingallocation.getText();
    }
    public String getactualpendingallocationcount()
    {
        pendingallocation.click();
        BrowserUtilities bu= new BrowserUtilities();
        waittillElementNotZero(driver,50,"//h1[contains(text(),'Pending Allocation')]/following-sibling::h1");
        return actualPendingAllocationCount.getText();
    }
    public Boolean verifypendingallocationcount()
    {
        Boolean flag=false;
        String str1="";
        str1=getmcpendingallocationcount();
        System.out.println("Mc count "+str1);
        st.onDemandScreenshotReport();
        String str2=getactualpendingallocationcount();
        System.out.println("Actual page count "+str2);
        st.onDemandScreenshotReport();
        if(str1.equalsIgnoreCase(str2))
        {
            flag=true;
        }
        return flag;
    }
    public String getmcpendingbuildcount()
    {
        waittillElementNotEmpty(driver,60,"//div[contains(@ng-click,'PendingBuild')]/div[@class='ng-binding']");
        waittillElementProperty(driver,60,"//div[contains(@ng-click,'PendingBuild')]/div[1]");
        return pendingbuild.getText();
    }
    public String getactualpendingbuildcount()
    {
        pendingbuild.click();
        BrowserUtilities bu= new BrowserUtilities();
        waittillElementNotZero(driver,50,"//h1[contains(text(),'PendingBuild')]/following-sibling::h1");
        return actualPendingBuildCount.getText();
    }
    public Boolean verifypendingbuildcount()
    {
        Boolean flag=false;
        String str1="";
        str1=getmcpendingbuildcount();
        System.out.println("Mc count pending build "+str1);
        st.onDemandScreenshotReport();
        String str2=getactualpendingbuildcount();
        System.out.println("Actual page count pending build "+str2);
        st.onDemandScreenshotReport();
        if(str1.equalsIgnoreCase(str2))
        {
            flag=true;
        }
        return flag;
    }
    public String getmcemptycount()
    {
        return empty.getText();
    }




}
