import base.BaseClass;
import helper.ScreenshotUtility;
import org.testng.annotations.Test;
import pages.RevenueTypesPage;

public class XGLT_6150_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 1,enabled = true)
    public void CreateNewrevenuType()
    {
        RevenueTypesPage rt =new RevenueTypesPage(driver);
        rt.Addrevenuetype();
    }

}
