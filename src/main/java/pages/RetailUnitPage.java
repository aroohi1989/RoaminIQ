package pages;

import base.BaseClass;
import dataProvider.ConfigReader;
import helper.Read_write_Excel;
import helper.SaveProjectData;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class RetailUnitPage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuScheduleSettings')]")
    WebElement scheduleSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Schedule Settings

    @FindBy(xpath = "//div[@id='ScheduleSettings.RetailUnits']")
    WebElement retailunits;

    @FindBy(xpath = "//button[contains(@id,'regionsGridDrtv.add')]")
    WebElement addretailunit;

    @FindBy(xpath = "//input[@id='data.retailUnitCode']")
    WebElement RUid;

    @FindBy(xpath = "//input[@id='data.description']")
    WebElement RUdesc;

    @FindBy(xpath = "//input[@id='data.syscode']")
    WebElement RUsyscode;

    @FindBy(xpath = "//button[@id='retailUnit.add']")
    WebElement RUaddheadend;

    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement RUheadendpopup;

    @FindBy(xpath = "//div[contains(@class,'ui-widget-content')][last()]//div[@class='checkBoxSelection']/i")
    WebElement addheadendchckbx;

    @FindBy(xpath = "//button[@id='cancelSaveOk.Save']")
    WebElement saveheadend;

    @FindBy(xpath = "//button[contains(@id,'save')]")
    WebElement ruSave;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement cobreadcrum;

    public RetailUnitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void AddretailUnit()
    {

        config.click();
        scheduleSettings.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Schedule Settings"));
        retailunits.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        addretailunit.click();
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        RUid.sendKeys(init);
        String sp_name=init+" retail unit";
        RUdesc.sendKeys(sp_name);
        RUsyscode.sendKeys(ConfigReader.getPropertyvalue("syscode"));
        RUaddheadend.click();
        addheadendchckbx.click();
        if(RUheadendpopup.isDisplayed())
        {
            new Actions(driver).moveToElement(addheadendchckbx).perform();
            addheadendchckbx.click();
            new Actions(driver).moveToElement(saveheadend).perform();
            saveheadend.click();
        }
        if(ruSave.isDisplayed())
        {
            ruSave.click();
        }
        System.out.println("RO is "+cobreadcrum.getText());
        Assert.assertTrue(cobreadcrum.getText().contains("New Retail Unit"));
        SaveProjectData sp=new SaveProjectData();
        sp.saveprojectData("Retail",init);
    }
}
