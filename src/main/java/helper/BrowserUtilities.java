package helper;

import base.BaseClass;

public class BrowserUtilities extends BaseClass
{
    public void refreshbrowser()
    {
        driver.navigate().refresh();
        Utility ut=new Utility();
        ut.waitforPagetoLoad(driver,50);
    }
}
