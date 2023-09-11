import base.BaseClass;
import dataProvider.ConfigReader;
import helper.BrowserUtilities;
import helper.DataBaseUtility;
import helper.ExceptionHandling;
import helper.ScreenshotUtility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.MCSettingsPage;
import pages.MCUsersWidgetPage;

import java.sql.SQLException;
import java.util.ArrayList;

import static helper.Utility.waitForElementPresent;
import static helper.Utility.waitforPagetoLoad;
import static helper.WaitUtility.WaitForAPIResponse;

public class XGLT_6992_Test  extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 1,enabled = true)
    public void MCsetUser()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        mc.addwait(mc.mcsettingbtn);
        BrowserUtilities bu= new BrowserUtilities();
        if(!mc.verifymcvalueset(ConfigReader.getPropertyvalue("missioncontrolvalue")))
        {
            mc.openMcSettings();
            mc.resetSettings();
            mc.addwait(mc.mcsettingbtn);
            bu.refreshbrowser();
            mc.openMcSettings();
            mc.setMcsettingfirstvalue(ConfigReader.getPropertyvalue("missioncontrolvalue"));
            mc.addwait(mc.mcsettingPopup);
            mc.saveMcSettings();
            mc.addwait(mc.mcsettingbtn);
            Assert.assertTrue(mc.verifymcvalueset(ConfigReader.getPropertyvalue("missioncontrolvalue")));
        }
        else {
        Assert.assertTrue(mc.verifymcvalueset(ConfigReader.getPropertyvalue("missioncontrolvalue")));
        }
    }

    @Test(priority = 2,enabled = true)
    public void getMCUsercounts()  {
        String arr= "";
         MCUsersWidgetPage mcu= new MCUsersWidgetPage(driver);
        waitforPagetoLoad(driver,1000);
        Reporter.log("winActiveDir is "+mcu.getwinActiveDir());
        Reporter.log(" logged in users is "+mcu.getloggedinUsers());
        Reporter.log(" total xgl users "+mcu.gettotalxglUsers());
        Reporter.log(" total number of users is "+mcu.gettotalnumUsers());
        DataBaseUtility db=new DataBaseUtility();
        db.connectToSqlDB(ConfigReader.getPropertyvalue("jdbcUrl"),ConfigReader.getPropertyvalue("DBUname"),ConfigReader.getPropertyvalue("DBPwd"));
       try {
        arr= db.executeSQLdbQueryCount(ConfigReader.getPropertyvalue("jdbcUrl"), ConfigReader.getPropertyvalue("DBUname"), ConfigReader.getPropertyvalue("DBPwd"), ConfigReader.getPropertyvalue("sqlQuery"));
       }
       catch(SQLException e)
        {
            ExceptionHandling.handleException(e);
        }
        System.out.println("DB count is "+arr);
       if(mcu.getTotalusercount().equalsIgnoreCase(arr))
       {
           System.out.println("Value in DB "+arr +"and value on MC widget is "+mcu.getTotalusercount());
           Reporter.log("Value in DB "+arr +" and value on MC widget is "+mcu.getTotalusercount());
       }
        Assert.assertTrue(mcu.getTotalusercount().equalsIgnoreCase(arr));
    }

}
