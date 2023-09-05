package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import helper.ExceptionHandling;
import helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NetworkInstancePage extends BaseClass
{
    WebDriverWait wait;

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
    WebElement pendingallocation;

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
        return pendingallocation.getText();
    }
    public String getactualpendingallocationcount()
    {
        //waitForElementtoload(driver,100,"//h1[contains(text(),'Pending Allocation')]/following-sibling::h1");
        pendingallocation.click();
        Utility ut=new Utility();
        ut.waitforPagetoLoad(driver,50);
        return actualPendingAllocationCount.getText();
    }
    public Boolean verifypendingallocationcount()
    {
        Boolean flag=false;
        String str1=getmcpendingallocationcount();
        String str2=getactualpendingallocationcount();
        if(str1.equalsIgnoreCase(str2))
        {
            flag=true;
        }
        return flag;
    }
    public String getmcpendingbuildcount()
    {
        waitForElementtoload(driver,100,"//div[contains(@ng-click,'PendingBuild')]/div[@class='ng-binding']");
        return pendingbuild.getText();
    }
    public String getmcemptycount()
    {
        waitForElementtoload(driver,100,"//div[contains(@ng-click,'Empty')]/div[@class='ng-binding']");
        return empty.getText();
    }
    public static void waitForElementtoload(WebDriver driver, long maxSecondsToWait,String xpathlocator)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, maxSecondsToWait);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathlocator)));
            log.info("Element is present");
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            log.error("Element did not become visible within the specified time: " +e.getMessage());
        }
    }



}
