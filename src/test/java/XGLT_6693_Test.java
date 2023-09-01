import base.BaseClass;
import dataProvider.ConfigReader;
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

        MCSettingsPage mc= new MCSettingsPage(driver,parser);
        mc.addwait(mc.mcsettingbtn);

        Dimension dimensions = mc.mcsettingbtn.getSize();
        int width = dimensions.getWidth();
        int height = dimensions.getHeight();

        System.out.println("width "+width);
        System.out.println("Height "+height);

       /* mc.openMcSettings();
        System.out.println("Clcked by webdriver");
        mc.addwait(mc.mcsettingbtn);*/

        JavaScriptExecutor js= new JavaScriptExecutor();
        js.clickElementByJS(mc.mcsettingbtn);
        System.out.println("clicked by js");
        js.highlightElement(mc.mcsettingbtn, ConfigReader.getPropertyvalue("Style"));
        js.clickElementByJS(mc.mcsettingbtn);
        mc.resetSettings();
        mc.openMcSettings();
        mc.setMcsettingfirstvalue(ConfigReader.getPropertyvalue("missioncontrolvalue"));
        mc.addwait(mc.mcsettingPopup);
        mc.saveMcSettings();
        Assert.assertTrue(mc.verifymcvalueset());
        Reporter.log("Pass: Test XGLT-6693");
    }

}
