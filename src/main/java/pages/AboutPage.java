package pages;

import base.BaseClass;
import dataProvider.ConfigReader;
import helper.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

public class AboutPage extends BaseClass
{
    public AboutPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
        @FindBy(xpath = "//div[@id='header.menuHelp']")
         WebElement help;
        @FindBy(xpath = "//div[@id='header.subMenuAbout/profile']")
                WebElement profile;
        @FindBy(xpath = "//div[@class='popupWindowBody']/h2")
                WebElement about_version;
        @FindBy(xpath = "//div[@class='popupWindowBody']//table//tr")
                List<WebElement> about_content;
        @FindBy(xpath = "//div[@class='popupWindowButtons buttonsArea']//button[@value='ok']")
                WebElement about_ok;
    public void clickhelp(){
        help.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }
    public WebElement getProfile()
    {
       return profile;
    }
    public void clickAbout(){
        profile.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }
    public String getVersion(){
        return about_version.getText();
    }
    public void printAboutDetails(){
        JavaScriptExecutor jse=new JavaScriptExecutor();
        StringBuilder sb= new StringBuilder();
        //  print about
        List<WebElement> arr= about_content;
        for(int i=0;i<arr.size();i++)
        {
            List<WebElement> at=arr.get(i).findElements(By.tagName("td"));
            for(int j=0;j<at.size();j++)
            {
                sb= sb.append(at.get(j).getText());
            }
            Reporter.log(sb.toString());
        }
            jse.highlightElement(about_version, ConfigReader.getPropertyvalue("Style"));
    }
    public void click_about_Ok()
    {
        about_ok.click();
    }
}
