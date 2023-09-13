import base.BaseClass;
import helper.ScreenshotUtility;
import org.testng.annotations.Test;
import pages.CommodityCodePage;

public class XGLT_6753_Test extends BaseClass
{
    ScreenshotUtility st=new ScreenshotUtility();

    @Test(priority = 1,enabled = true)
    public void CreateCommodityCode()
    {
        CommodityCodePage co=new CommodityCodePage(driver);
        co.Addcommoditycode();
    }
}
