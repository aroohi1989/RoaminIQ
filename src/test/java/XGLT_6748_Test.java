import base.BaseClass;
import helper.ScreenshotUtility;
import org.testng.annotations.Test;
import pages.SalesPersonPage;

public class XGLT_6748_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 1,enabled = true)
    public void CreateNewSalesPerson()
    {
        SalesPersonPage sp=new SalesPersonPage(driver);
        sp.setAddSalesPeople();
    }
}
