package helper;

import com.aventstack.extentreports.MediaEntityBuilder;
import listners.ExtentReportListener;

public class ScreenshotUtility
{
    public void onDemandScreenshotReport()
    {
        ExtentReportListener.test.get().pass(MediaEntityBuilder.createScreenCaptureFromPath(Utility.getScreenshot()).build());
    }
}
