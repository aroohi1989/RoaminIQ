import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import dataProvider.ConfigReader;
import helper.ExceptionHandling;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ImportSchedulePage;

import java.awt.*;
import java.lang.reflect.Method;

import static listners.ExtentManager.extent;

public class Import_Schedules extends BaseClass
{
    public String testName;
    ExtentTest test = extent.createTest("Test Login to XGL", "This is a test to create network breakformat to XGL");

    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
        testName = method.getName();
    }

    @Test(description = "upload import schedules")
    public void Import_Schedules()
    {
        String filepath= ConfigReader.getPropertyvalue("importSchedulePath");
        ImportSchedulePage is = new ImportSchedulePage(driver);
        try {
            is.setImport_Schedule(filepath);
        }
        catch (Exception e)
        {
            String msg=ExceptionHandling.handleException(e);
            System.out.println("Some error occurred during schedule import ");
            logger.info(msg);
        }

    }
}
