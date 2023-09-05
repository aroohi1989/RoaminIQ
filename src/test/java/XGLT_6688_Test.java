import base.BaseClass;
import dataProvider.ConfigReader;
import helper.BrowserUtilities;
import helper.ScreenshotUtility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.MCSettingsPage;
import pages.NetworkInstancePage;

import static helper.Utility.waitForAllElementsToBePresent;

public class XGLT_6688_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 2,enabled = true)
    public void NetworkInstanceStatus()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        //mc.addwait(mc.mcsettingbtn);
        //mc.openMcSettings();
        //mc.resetSettings();
        //mc.addwait(mc.mcsettingbtn);
        //BrowserUtilities bu= new BrowserUtilities();
       // bu.refreshbrowser();
        //mc.openMcSettings();
        //mc.setMcsettingfirstvalue(ConfigReader.getPropertyvalue("XGLT6688value"));
        //mc.addwait(mc.mcsettingPopup);
        //mc.saveMcSettings();
        //mc.addwait(mc.mcsettingbtn);
        //Assert.assertTrue(mc.verifymcvalueset(ConfigReader.getPropertyvalue("XGLT6688value")));
        NetworkInstancePage np=new NetworkInstancePage(driver);
        mc.addwait(mc.mcsettingbtn);
        //waitForAllElementsToBePresent(driver,200);
        np.PeriodSelection("Current Week");
        mc.addwait(mc.mcsettingbtn);
        //np.PeriodSelection("Next 4 Weeks");
        //mc.addwait(mc.mcsettingbtn);
        //np.PeriodSelection("Custom Dates");
        //np.selectMonth();
        //np.selectDate();
        System.out.println("pending allocation "+np.getmcpendingallocationcount());
        System.out.println("Pending build "+np.getmcpendingbuildcount());
        System.out.println("Empty "+np.getmcemptycount());
        Assert.assertTrue(np.verifypendingallocationcount());
        Reporter.log("Allocation count is correct");

    }
}
