package pages;

import base.BaseClass;
import dataProvider.ConfigReader;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegionPage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuScheduleSettings')]")
    WebElement scheduleSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Schedule Settings

    @FindBy(xpath = "//div[@id='ScheduleSettings.Regions']")
    WebElement region;

    @FindBy(xpath = "//button[contains(@id,'regionsGridDrtv.add')]")
    WebElement addregion;

    @FindBy(xpath = "//div[@id='regionTypeRadiobutton']/div[contains(text(),'Soft Region')]")
    WebElement btnSoftRegion;

    @FindBy(xpath = "//div[@id='regionTypeRadiobutton']/div[contains(text(),'Hard Region')]")
    WebElement btnHardRegion;

    @FindBy(xpath = "//input[@id='treshold']")
    WebElement threshold;

    @FindBy(xpath = "//input[@id='region']")
    WebElement regionId;

    @FindBy(xpath = "//input[@id='description']")
    WebElement regiondesc;

    @FindBy(xpath = "//input[@id='sysCode']")
    WebElement regionSyscode;


    //Retail Weight Distribution
    @FindBy(xpath = "//div[contains(@id,'retailWeightDistribution')]/div[contains(text(),'By Subscriber Count')]")
    WebElement rdSubCount;

    @FindBy(xpath = "//div[contains(@id,'retailWeightDistribution')]/div[contains(text(),'By Region/Retail Weight')]")
    WebElement rdRegRetWeight;

    //Fractional retail proration
    @FindBy(xpath = "//div[contains(@id,'fractionalRetailProRation')]/div[contains(text(),'Round To Penny')]")
    WebElement rdRoundtopenny;

    @FindBy(xpath = "//div[contains(@id,'fractionalRetailProRation')]/div[contains(text(),'Round To Dollar')]")
    WebElement rdRoundtodollar;

    @FindBy(xpath = "//button[@id='region.add']")
    WebElement btnaddRegion;

    @FindBy(xpath = "//div[@id='editRegionRetailUnitOverlay.regionAutocomplete']/input")
    WebElement drodownRetailUnit;

    @FindBy(xpath = "//div[@class='auto-complete-list-drop-down ng-isolate-scope']")
    WebElement dropdownAutocomplete; // get all options from dropdown

    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement retailpopup;

    @FindBy(xpath = "//button[@id='cancelSaveOk.Save']")
    WebElement saveRetailunit;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement saveRegion;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement cobreadcrum;

    public RegionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public List getelemements()
    {
        List<WebElement> ele=driver.findElements(By.xpath("//div[@class='auto-complete-list-drop-down ng-isolate-scope']"));
        return ele;
    }
    public void addsoftregion()
    {
        SaveProjectData spd=new SaveProjectData();
        String retailunit=spd.getprojectData("Retail");
        config.click();
        scheduleSettings.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Schedule Settings"));
        region.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        addregion.click();
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        btnSoftRegion.click(); // select soft region
        regionId.sendKeys(init);
        regionSyscode.sendKeys(ConfigReader.getPropertyvalue("syscode"));
        String sp_name=init+" region";
        regiondesc.sendKeys(sp_name);
        Actions a = new Actions(driver);
        int i=9;
        while(i>0) {
            a.sendKeys(Keys.PAGE_DOWN).build().perform();i--;
        }
        JavaScriptExecutor js=new JavaScriptExecutor();
        js.clickElementByJS(rdSubCount);
        js.clickElementByJS(rdRoundtopenny);
        js.clickElementByJS(btnaddRegion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(retailpopup.isDisplayed())
        {
            drodownRetailUnit.click();
            List<WebElement> ele=driver.findElements(By.xpath("//div[@class='auto-complete-list-drop-down ng-isolate-scope']/div/span"));
            for (WebElement e:ele)
            {
                String str=e.getAttribute("title");
                String retailunitvalue=retailunit+" | "+retailunit+" retail unit";
                if(str.equalsIgnoreCase(retailunitvalue))
                {
                    System.out.println("In ele selection");
                    e.click();
                    break;
                }
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            saveRetailunit.click();
        }

        new Actions(driver).moveToElement(saveRegion).perform();
        js.highlightElement(saveRegion,ConfigReader.getPropertyvalue("Style"));
        js.clickElementByJS(saveRegion);
        System.out.println("RO is "+cobreadcrum.getText());
        Assert.assertTrue(cobreadcrum.getText().contains("New Region"));
        SaveProjectData sp=new SaveProjectData();
        sp.saveprojectData("Soft region",init);

    }
    public void addhardregion()
    {
        SaveProjectData spd=new SaveProjectData();
        String retailunit=spd.getprojectData("Retail");
        config.click();
        scheduleSettings.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Schedule Settings"));
        region.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        addregion.click();
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        btnHardRegion.click(); // select hard region
        threshold.sendKeys("90");
        regionId.sendKeys(init);
        regionSyscode.sendKeys(ConfigReader.getPropertyvalue("syscode"));
        String sp_name=init+" region";
        regiondesc.sendKeys(sp_name);
        JavaScriptExecutor js=new JavaScriptExecutor();
        Actions a = new Actions(driver);
        int i=9;
        while(i>0) {
            a.sendKeys(Keys.PAGE_DOWN).build().perform();i--;
        }
        js.clickElementByJS(rdSubCount);
        js.clickElementByJS(rdRoundtopenny);
        js.clickElementByJS(btnaddRegion);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(retailpopup.isDisplayed())
        {
            drodownRetailUnit.click();
            List<WebElement> ele=driver.findElements(By.xpath("//div[@class='auto-complete-list-drop-down ng-isolate-scope']/div/span"));
            for (WebElement e:ele)
            {
                String str=e.getAttribute("title");
                String retailunitvalue=retailunit+" | "+retailunit+" retail unit";
                if(str.equalsIgnoreCase(retailunitvalue))
                {
                    System.out.println("In ele selection");
                    e.click();
                    break;
                }
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            saveRetailunit.click();
        }

        new Actions(driver).moveToElement(saveRegion).perform();

        js.highlightElement(saveRegion,ConfigReader.getPropertyvalue("Style"));
        js.clickElementByJS(saveRegion);
        System.out.println("RO is "+cobreadcrum.getText());
        Assert.assertTrue(cobreadcrum.getText().contains("New Region"));
        SaveProjectData sp=new SaveProjectData();
        sp.saveprojectData("Hard region",init);
    }

}
