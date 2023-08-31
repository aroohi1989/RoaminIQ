import base.BaseClass;
import dataProvider.ConfigReader;
import helper.ScreenshotUtility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AboutPage;

public class XGLT_15177_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

   /* @Test(priority = 1,enabled = false,dataProvider = "loginDetails",dataProviderClass = CustomDataProvider.class)
    public void LoginXGL(String uname, String pwrd)
    {
        logger.info("Test XGLT_15177_Test");
        BaseClass b= new BaseClass();
        b.setupBrowser();
        LoginPage lp= new LoginPage(driver);
        lp.Login_To_Application(uname,pwrd);
        lp.addwait();
        Assert.assertTrue(driver.getCurrentUrl().contains("MissionControl"),"Login failed");
    }*/

    @Test(priority = 2,enabled = true)
    public void About()
    {
        AboutPage ap= new AboutPage(driver);
        ap.clickhelp();
        Assert.assertTrue(ap.getProfile().isDisplayed(),"About is present");
        ap.clickAbout();
        Assert.assertEquals(ap.getVersion(),ConfigReader.getPropertyvalue("Version"));
        ap.printAboutDetails();
        st.onDemandScreenshotReport();
        ap.click_about_Ok();
        Reporter.log("Pass: About information read");
    }

}
