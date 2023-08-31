package pages;

import base.BaseClass;
import helper.UploadFileExample;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ImportSchedulePage extends BaseClass
{
    public ImportSchedulePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@id='header.menuConfiguration']")
    WebElement configuration;

    @FindBy(xpath="//div[@id='header.subMenuScheduleSettings.Dayparts']")
            WebElement Schedule_settings;

    @FindBy(xpath="//p[normalize-space()='Import Schedule']")
            WebElement Import_Schedule;

    @FindBy(xpath="//button[normalize-space()='Edit']")
            WebElement Import_Schedule_Edit;

    @FindBy(xpath = "//button[@class='brwseBtn ng-binding']")
        WebElement browse;

    public void setImport_Schedule(String file_path) throws AWTException
    {
        configuration.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Schedule_settings.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Import_Schedule.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Import_Schedule_Edit.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        UploadFileExample.upload_file(browse,file_path);
    }

}
