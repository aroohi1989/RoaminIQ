import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import dataProvider.ConfigReader;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.Network_programbreakformatPage;

import java.lang.reflect.Method;

import static listners.ExtentManager.extent;

public class Network_programbreakformat extends BaseClass
{
    public String testName;
    public String network;
    ExtentTest test = extent.createTest("Test Login to XGL", "This is a test to create network breakformat to XGL");

    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
        testName = method.getName();
    }

    @Test(priority = 1)
    @Parameters({"inventorytype"})
    public void create_Breakformat(String invtype)
    {
        Network_programbreakformatPage bf= new Network_programbreakformatPage(driver);

        break_format=bf.create_break_format(Network, ConfigReader.getPropertyvalue("inventorytype"));

        Reporter.log("Program Break format created "+break_format);

    }

}
