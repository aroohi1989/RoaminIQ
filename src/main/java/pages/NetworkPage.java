package pages;

import base.BaseClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class NetworkPage extends BaseClass
{
    public NetworkPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='header.menuConfiguration']")
    WebElement configuration;
    @FindBy(xpath = "//div[@id='header.subMenuScheduleSettings.Dayparts']")
    WebElement subMenuScheduleSettings;
    @FindBy(xpath = "//div[@id='ScheduleSettings.Networks']")
    WebElement network;
    @FindBy(xpath = "//i[@class='fa fa-angle-down']")
    WebElement search_network;
    @FindBy(xpath = "//div[@class='dropDown']/div[2]")
    WebElement id_search;
    @FindBy(xpath = "//*[contains(@class, 'ng-pristine') and @ng-model='tempModel.value']")
    //ng-valid ng-dirty ng-valid-max-length-vldr ng-touched ng-empty ng-valid-parse
    WebElement search_box;
    @FindBy(xpath = "//b[@class='loupe']")
    WebElement search_button;
    @FindBy(xpath = "//div[@class='textContent']/h1") //No Items to Display
    WebElement NoDataMessage;

    @FindBy(xpath = "//button[@id='networksGridDrtv.add']")// + button
    WebElement add_network;

    @FindBy(xpath = "//input[@id='networkSetup.network']")// netowrk id
    WebElement network_id;

    @FindBy(xpath = "//input[@id='networkSetup.description']")//network description
    WebElement network_desc;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement network_save;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement network_label;

    public boolean search_network(String net)
    {
        boolean flag=true;
        configuration.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        subMenuScheduleSettings.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        network.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        search_network.click();
        id_search.click();
        search_box.clear();
        search_button.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        search_box.sendKeys(net);
        search_button.click();
        try
        {
            NoDataMessage.isDisplayed();
        }
        catch(NoSuchElementException e)
        {
         flag=false;
        }
        return flag;
    }
    public boolean create_Network(String net)
    {
        String desc=net+"Automation network";
        add_network.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        network_id.sendKeys(net);
        network_desc.sendKeys(desc);
        network_save.click();
        Boolean result=network_label.getText().contains("New Network");
        return result;
    }

}
