package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class Prebilling_processing extends BaseClass
{
    public  Prebilling_processing(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//div[@id='header.menuReconciliation']")
    public WebElement Recon;
    //By Recon = By.xpath("//div[@id='header.menuReconciliation']");
    By Pre_billing = By.xpath("//div[@id='header.subMenuPreInvoiceProcessing']");
    By DAT =By.xpath("//div//p[contains(text(),'Discrepancy Analysis')]");
    By DAT_Analyse=By.xpath("//div//button[contains(text(),'Analyze')]");
    By Review_moved_units= By.xpath("//div//p[contains(text(),'Review Moved Ad Units')]");
    By Monthly_pre_bill= By.xpath("//div//p[contains(text(),'Monthly Pre-Billing')]");
    By Billing_submission =By.xpath("//div//p[contains(text(),'Billing Submission')]");
    By Billing_period_review= By.xpath("//div//p[contains(text(),'Billing Period Review')]");

    public boolean test_privilege(String Cat, String Priv, String Grant)
    {
        boolean flag=false;
        SoftAssert sa = new SoftAssert();
        //Recon
        if(Cat.equalsIgnoreCase("Reconciliation"))
        {
            if(Priv.equalsIgnoreCase("Reconciliation Pre-Billing Processing"))
            {
                if(Grant.equalsIgnoreCase("Y"))
                {
                    flag = Recon.isDisplayed();
                    sa.assertTrue(Recon.isDisplayed(), "Reconciliation is displayed");
                    sa.assertEquals(Recon.isEnabled(), "Reconciliation is enabled");
                }
                else if(Grant.equalsIgnoreCase("N"))
                {
                    flag=!(Recon.isDisplayed());
                }
            }
        }

        Recon.click();
        driver.findElement(Pre_billing).click();
        if(Cat.equalsIgnoreCase("Pre-Billing Processing") && Grant.equalsIgnoreCase("Y"))
        {
            flag = driver.findElement(Pre_billing).isDisplayed();

                if (Priv.equalsIgnoreCase("Undo Ad Unit Movement") || Priv.equalsIgnoreCase("View Moved Ad Units Detail") || Priv.equalsIgnoreCase("View Moved Ad Units List"))
                {
                    if(Grant.equalsIgnoreCase("Y"))
                        {
                        flag = driver.findElement(Review_moved_units).isDisplayed();
                        sa.assertTrue(driver.findElement(Review_moved_units).isDisplayed(), "Review moved units is displayed");
                        sa.assertTrue(driver.findElement(Review_moved_units).isEnabled(), "Review moved units is enabled");
                        Reporter.log("No data available to test View Moved Ad Units Detail, View Moved Ad Units List,Undo Ad Unit Movement");
                        }
                    else {flag=!(driver.findElement(Review_moved_units).isDisplayed());}
                }
                else if (Priv.equalsIgnoreCase("Run Discrepency Analyzer Tool (DAT)"))
                {
                    if(Grant.equalsIgnoreCase("Y"))
                    {
                    flag = driver.findElement(DAT).isDisplayed();
                    sa.assertTrue(driver.findElement(DAT).isDisplayed(), "DAT is displayed");
                    sa.assertTrue(driver.findElement(DAT).isEnabled(), "DAT privilage is granted");
                    // driver.findElement(DAT).click();
                    //Assert.assertTrue(driver.findElement(DAT_Analyse).isEnabled(),"DAT Analyse is enabled");
                    Reporter.log("DAT is granted");}
                    else {flag=!(driver.findElement(DAT).isDisplayed());}
                }
                else if (Priv.equalsIgnoreCase("View Monthly Pre-Billing"))
                {
                    if (Grant.equalsIgnoreCase("Y"))
                    {
                        flag = driver.findElement(Monthly_pre_bill).isDisplayed();
                        sa.assertTrue(driver.findElement(Monthly_pre_bill).isDisplayed(), "Monthly pre bill is displayed");
                        sa.assertTrue(driver.findElement(Monthly_pre_bill).isEnabled(), "Monthly pre bill is enabled");
                    }
                    else {flag=!(driver.findElement(Monthly_pre_bill).isDisplayed());}
                }
        }
        else if (Cat.equalsIgnoreCase("Pre-Billing Processing") && Grant.equalsIgnoreCase("N"))
        {
            flag=!(driver.findElement(Pre_billing).isDisplayed());
        }


        if (Cat.equalsIgnoreCase("Billing Submission"))
        {
            if(Priv.equalsIgnoreCase("Review Billing Period List"))
            {
                if(Grant.equalsIgnoreCase("Y")) {
                    flag = driver.findElement(Billing_period_review).isDisplayed();
                    sa.assertTrue(driver.findElement(Billing_period_review).isDisplayed(), "Billing period review is displayed");
                    sa.assertTrue(driver.findElement(Billing_period_review).isEnabled(), "Billing period review is enabled");
                }
                else {flag=!(driver.findElement(Billing_period_review).isDisplayed());}
            }

        }
        return flag;
    }

    
}
