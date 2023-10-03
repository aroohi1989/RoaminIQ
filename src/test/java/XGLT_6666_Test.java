import base.BaseClass;
import org.testng.annotations.Test;
import pages.CustomerPage;

public class XGLT_6666_Test extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void CreateNewStandardCust()
    {
        CustomerPage cp=new CustomerPage(driver);
        cp.createStandardCustomer();
    }

    @Test(priority = 2,enabled = false)
    public void CreateNewAgencyCust()
    {
        CustomerPage cp=new CustomerPage(driver);
        cp.createAgencyCustomer();
    }

    @Test(priority = 3,enabled = false)
    public void CreateNewRepCust()
    {
        CustomerPage cp=new CustomerPage(driver);
        cp.createRepCustomer();
    }
}
