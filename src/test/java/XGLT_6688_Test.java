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

    @Test(priority = 2,enabled = false)
    public void NetworkInstanceCurrentWeek()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        mc.addwait(mc.mcsettingbtn);
        BrowserUtilities bu= new BrowserUtilities();
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
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Current Week");
        SoftAssert sa= new SoftAssert();
        sa.assertTrue(np.verifypendingallocationcount());

        Reporter.log("Allocation count is correct");
        mc.missioncontrolHeader.click();
        //Pending build
        np.PeriodSelection("Current Week");
        waitStatic(60);
        sa.assertTrue(np.verifypendingbuildcount());

    }

    @Test(priority = 3,enabled = true)
    public void NextfourDays()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.missioncontrolHeader.click();
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Next 4 Weeks");
        np.PeriodSelection("Next 4 Weeks");
        waitStatic(60);
        waittillElementNotEmpty(driver,40,"//div[contains(@ng-click,'PendingAllocation')]/div[@class='ng-binding']");
        SoftAssert sa= new SoftAssert();
        sa.assertTrue(np.verifypendingallocationcount());

        mc.missioncontrolHeader.click();
        np.PeriodSelection("Next 4 Weeks");
        np.PeriodSelection("Next 4 Weeks");
        waitStatic(60);
        waittillElementNotEmpty(driver,40,"//div[contains(@ng-click,'PendingBuild')]/div[@class='ng-binding']");
        sa.assertTrue(np.verifypendingbuildcount());
    }

    @Test(priority = 4,enabled = false)
    public void CustomDate()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.addwait(mc.mcsettingbtn);
        np.PeriodSelection("Custom Dates");
        waitStatic(120);
        np.selectMonth();
        np.selectDate();
        Assert.assertTrue(np.verifypendingallocationcount());
    }
}
