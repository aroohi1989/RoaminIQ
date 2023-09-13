package pages;

import base.BaseClass;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static helper.WaitUtility.waittillElementInteractable;

public class RevenueTypesPage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuOrderSettings')]")
    WebElement orderSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Order Settings

    @FindBy(xpath = "//div[@id='OrderSettings.RevenueTypes']")
    WebElement revenuetype;

    @FindBy(xpath = "//button[contains(@id,'revenueTypeGrid.add')]")
    WebElement addrevenuetype;

    @FindBy(xpath = "//input[@id='revenueTypeProfile_typeCode']")
    WebElement revenuetypeid;

    @FindBy(xpath = "//input[@id='revenueTypeProfile_description']")
    WebElement revenuetypedesc;

    @FindBy(xpath = "//div[@id='revenueTypeProfile_revenueCategory']/div[@class='iconContainer']")
    WebElement revenueCategorydrpbutton;

    @FindBy(xpath = "//button[contains(@id,'save')]")
    WebElement rtSave;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement spbreadcrum;

    public RevenueTypesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Addrevenuetype()
    {
        config.click();
        orderSettings.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Order Settings"));
        revenuetype.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        addrevenuetype.click();
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        revenuetypeid.sendKeys(init);
        String sp_name=init+"revenue type";
        revenuetypedesc.sendKeys(sp_name);
        waittillElementInteractable(driver,20,"//div[@id='revenueTypeProfile_revenueCategory']/div[@class='iconContainer']");

        new Actions(driver).moveToElement(revenueCategorydrpbutton).perform();
        revenueCategorydrpbutton.click();
        SalesPersonPage sp= new SalesPersonPage(driver);
        sp.selectdropdownvalue();
        rtSave.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(spbreadcrum.isDisplayed())
        {
            System.out.println("RT value is "+spbreadcrum.getText());
            Assert.assertTrue(spbreadcrum.getText().contains("New Revenue Type"));
        }

    }
}
