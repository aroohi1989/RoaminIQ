package listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import dataProvider.ConfigReader;
import helper.Utility;

public class ExtentManager
{    public static ExtentReports extent;
    public static ExtentReports getInstance()
    {  if(extent==null)
        {
            extent=createInstance();
            return extent;
        }
        else
        {
            return extent;
        }

    }
    public static ExtentReports createInstance()
    {

        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(ConfigReader.getPropertyvalue("ExtentReportPath")+" /Automation_"+Utility.getCurrentTime()+".html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName(ConfigReader.getPropertyvalue("ExtentReportName"));
        sparkReporter.config().setDocumentTitle(ConfigReader.getPropertyvalue("ExtentDocumentTitle"));

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(sparkReporter);

        return extent;
    }


}