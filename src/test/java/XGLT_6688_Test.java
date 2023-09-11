import base.BaseClass;
import dataProvider.ConfigReader;
import helper.BrowserUtilities;
import helper.ScreenshotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MCSettingsPage;
import pages.NetworkInstancePage;

import java.util.concurrent.TimeUnit;

import static helper.Utility.waitForAllElementsToBePresent;
import static helper.WaitUtility.*;

public class XGLT_6688_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 2,enabled = true)
    public void NetworkInstanceCurrentWeekPA()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        mc.addwait(mc.mcsettingbtn);
        BrowserUtilities bu= new BrowserUtilities();
        if(!mc.verifymcvalueset(ConfigReader.getPropertyvalue("XGLT6688value")))
        {
            mc.openMcSettings();
            mc.resetSettings();
            mc.addwait(mc.mcsettingbtn);
            bu.refreshbrowser();
            mc.openMcSettings();
            mc.setMcsettingfirstvalue(ConfigReader.getPropertyvalue("XGLT6688value"));
            mc.addwait(mc.mcsettingPopup);
            mc.saveMcSettings();
            mc.addwait(mc.mcsettingbtn);
            Assert.assertTrue(mc.verifymcvalueset(ConfigReader.getPropertyvalue("XGLT6688value")));
        }
        NetworkInstancePage np=new NetworkInstancePage(driver);
        waitStatic(50);
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Current Week");
        Assert.assertTrue(np.verifypendingallocationcount());
        mc.missioncontrolHeader.click();

    }
    @Test(priority = 3,enabled = true)
    public void NetworkInstanceCurrentWeekPB()
    {
        NetworkInstancePage np=new NetworkInstancePage(driver);
        np.PeriodSelection("Current Week");
        //waitStatic(60);
        Assert.assertTrue(np.verifypendingbuildcount());
    }
    @Test(priority = 4,enabled = true)
    public void NextfourDaysPA()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.missioncontrolHeader.click();
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Next 4 Weeks");
        np.PeriodSelection("Next 4 Weeks");
        //waitStatic(60);
        waittillElementNotEmpty(driver,40,"//div[contains(@ng-click,'PendingAllocation')]/div[@class='ng-binding']");
        Assert.assertTrue(np.verifypendingallocationcount());

        mc.missioncontrolHeader.click();

       }
    @Test(priority = 5,enabled = true)
    public void NextfourDaysPB()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        NetworkInstancePage np=new NetworkInstancePage(driver);
        np.PeriodSelection("Next 4 Weeks");
        np.PeriodSelection("Next 4 Weeks");
        waittillElementNotEmpty(driver,40,"//div[contains(@ng-click,'PendingBuild')]/div[@class='ng-binding']");
        Assert.assertTrue(np.verifypendingbuildcount());
        mc.missioncontrolHeader.click();
    }

    @Test(priority = 6,enabled = true)
    public void CustomDatePA()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Custom Dates");
        //waitStatic(120);
        np.selectMonth();
        np.selectDate();
        Assert.assertTrue(np.verifypendingallocationcount());
        mc.missioncontrolHeader.click();
    }
    @Test(priority = 6,enabled = true)
    public void CustomDatePB()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.missioncontrolHeader.click();
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Custom Dates");
        np.selectMonth();
        np.selectDate();
        Assert.assertTrue(np.verifypendingbuildcount());
    }
}
