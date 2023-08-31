package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrangeHRM_login extends BaseClass
{
    public OrangeHRM_login(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@name='username']")
            WebElement HRMUname;
    @FindBy(xpath = "//input[@name='password']")
            WebElement HRMPwd;
    @FindBy(xpath = "//button[@type='submit']")
            WebElement HRMLoginbtn;
   // @FindBy(xpath = "//a[@href='/web/index.php/admin/viewAdminModule']")
     //       WebElement HRMMenuAdmin;

    //By HRMUname= By.xpath("//input[@name='username']");
    //By HRMPwd =By.xpath("//input[@name='password']");
    //By HRMLoginbtn =By.xpath("//button[@type='submit']");
    By HRMMenuAdmin= By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");
    //Click on dropdown
    By HRMJobClick= By.xpath("//li[2]/span/i");

    By HRMJobDropdown= By.xpath("//li[2]/ul");
    By HRMJobDropdownValue= By.xpath("//li[2]/ul/li/a");
    

    public void HRMLogin(String uname,String password)
    {
        HRMUname.sendKeys(uname);
        HRMPwd.sendKeys(password);
        HRMLoginbtn.click();
    }
    
    public boolean HRMSelect(String choice) {
        boolean flag = false;
        driver.findElement(HRMMenuAdmin).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(HRMJobClick).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //with iterator
        List<WebElement> dropdn = driver.findElements(HRMJobDropdownValue);
        for (WebElement e:dropdn )
        {
            System.out.println(e.getText());
            if (e.getText().equalsIgnoreCase(choice))
            {
                e.click();
                flag=true;
                break;
            }
        }
        //With select
        return flag;
    }
}
