package pages;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public RegionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
