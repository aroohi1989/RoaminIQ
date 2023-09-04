import base.BaseClass;
import dataProvider.ConfigReader;
import helper.BrowserUtilities;
import helper.JavaScriptExecutor;
import helper.ScreenshotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.MCSettingsPage;

public class XGLT_6693_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 2,enabled = true)
    public void MissonControlReset()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        mc.addwait(mc.mcsettingbtn);
        mc.openMcSettings();
        mc.resetSettings();
        mc.addwait(mc.mcsettingbtn);
        BrowserUtilities bu= new BrowserUtilities();
        bu.refreshbrowser();
        mc.openMcSettings();
        mc.setMcsettingfirstvalue(ConfigReader.getPropertyvalue("missioncontrolvalue"));
        mc.addwait(mc.mcsettingPopup);
        mc.saveMcSettings();
        mc.addwait(mc.mcsettingbtn);
        Assert.assertTrue(mc.verifymcvalueset(ConfigReader.getPropertyvalue("missioncontrolvalue")));
        Reporter.log("Pass: Test XGLT-6693");
    }

}
