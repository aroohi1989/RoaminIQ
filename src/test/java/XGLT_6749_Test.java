import base.BaseClass;
import helper.ScreenshotUtility;
import org.testng.annotations.Test;
import pages.SalesOfficePage;

public class XGLT_6749_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 1,enabled = true)
    public void CreateNewSalesoffice()
    {
        SalesOfficePage sp=new SalesOfficePage(driver);
        sp.CreateSalesOffice();
    }
}
