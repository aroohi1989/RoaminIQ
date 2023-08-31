import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import dataProvider.ConfigReader;
import helper.Utility;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.NetworkPage;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static listners.ExtentManager.extent;

public class Create_Network extends BaseClass
{

    @Test(priority = 1)
    public void search_Network()
    {
        Boolean b=true;
        Utility ut= new Utility();
        Network=ut.randomAlphaNumeric(2);
        NetworkPage np= new NetworkPage(driver);
        b=np.search_network(Network);
        Assert.assertTrue(b);
        if(b)
        {
            System.out.println("Can proceed to create a new network "+Network);
            this.Network=Network;
        }
        else
        {
            System.out.println("Need a new network");
        }
    }

    @Test(priority = 2,dependsOnMethods = "search_Network")
    public void create_Network()
    {
        NetworkPage np= new NetworkPage(driver);
        Boolean result=np.create_Network(Network);
        Assert.assertTrue(result);
        System.out.println("Network "+Network+" is created");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown()
    {
        closeBrowser();
    }
}
