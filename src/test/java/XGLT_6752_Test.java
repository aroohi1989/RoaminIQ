import base.BaseClass;
import org.testng.annotations.Test;
import pages.RegionPage;

public class XGLT_6752_Test extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void CreateNewSoftRegion()
    {
        RegionPage rp=new RegionPage(driver);
        rp.addsoftregion();

    }
    @Test(priority = 1,enabled = true)
    public void CreateNewHardRegion()
    {
        RegionPage rp=new RegionPage(driver);
        rp.addhardregion();

    }
}
