import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumTest2
{
    WebDriver driver;
    @Parameters({"username","password","browser"})
    @Test(priority = 1)
    public void logintoHRM(String uname,String pswd,@Optional("Chrome") String browser1)
    {
        String url= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        System.out.println(browser1);

        if(browser1.equals("Chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        else if (browser1.equals("Edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
        else if (browser1.equals("Firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys(uname);
        driver.findElement(By.name("password")).sendKeys(pswd);
        driver.findElement(By.tagName("button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Boolean flag=driver.getCurrentUrl().contains("dashboard");
        Assert.assertEquals(flag,true);
    }


    @Test(dependsOnMethods = "logintoHRM",priority = 2)
    public void logout()
    {
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/i")).isDisplayed());
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/i")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"),"Logout successfull");
        //Assert.assertEquals(12,13);
    }

    @AfterClass
    public void close()
    {
        driver.quit();
    }
}
