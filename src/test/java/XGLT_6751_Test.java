import base.BaseClass;
import helper.ScreenshotUtility;
import org.testng.annotations.Test;
import pages.RetailUnitPage;


public class XGLT_6751_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 1,enabled = true)
    public void CreateNewRetailUnit()
    {
        RetailUnitPage ru=new RetailUnitPage(driver);
        ru.AddretailUnit();
    }
}
