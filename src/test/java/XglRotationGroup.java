import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class XglRotationGroup
{
    WebDriver driver;
    WebDriverWait wait;
    @Parameters({"username","password","URL"})
    @Test(priority = 1)
    public void LoginToXgl(String uname, String pwrd , String URL) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        try
        {
            FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./screenshots/Screenshot_"+".png"));
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong "+e.getMessage());
        }
       /* driver.findElement(By.xpath("//input[@id='login.login']")).sendKeys(uname);
        driver.findElement(By.xpath("//input[@id='login.password']")).sendKeys(pwrd);
        driver.findElement(By.xpath("//button[@id='login.loginButton']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        Thread.sleep(500);
        wait=new WebDriverWait(driver,Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='header.missionControl']")));
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getCurrentUrl().contains("MissionControl"));
        Assert.assertEquals(driver.getCurrentUrl().contains("MissionControl"),true);
        Reporter.log("Pass: Login Successfull");*/
    }
    @Parameters({"Version"})
    @Test(priority = 2, dependsOnMethods = "LoginToXgl",enabled = false)
    public void about(String version)
    {
        driver.findElement(By.xpath("//div[@id='header.menuHelp']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement ele1= driver.findElement(By.xpath("//div[@id='header.subMenuAbout/profile']"));
        Assert.assertTrue(ele1.isDisplayed(),"About is displayed");
        ele1.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue( driver.findElement(By.xpath("//div[@class='popupWindowBody']")).isDisplayed(),"About pop up is loaded");
        String str=driver.findElement(By.xpath("//div[@class='popupWindowBody']/h2")).getText();
        Assert.assertEquals(str,version);
        System.out.println(" Header is "+driver.findElement(By.xpath("//div[@class='popupWindowBody']/h2")).getText());
        List<WebElement> arr= driver.findElements(By.xpath("//div[@class='popupWindowBody']//table//tr"));
        for(int i=0;i<arr.size();i++)
        {
            List<WebElement> at=arr.get(i).findElements(By.tagName("td"));
            String about= "";
            for(int j=0;j<at.size();j++)
            {
                about= about + " " + at.get(j).getText();
            }
            System.out.println(about);
            Reporter.log(about);
        }
        Reporter.log("Pass: About information read");
        //Assert.assertTrue(false,"Failed");
    }
    @Test(priority = 3,enabled = false)
    public void createCustomer()
    {
        driver.findElement(By.xpath("//div[@id='header.menuOrders']")).click();
        driver.findElement(By.xpath("//div[@id='header.subMenuCustomers']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='customersGrid.add']")));
        driver.findElement(By.xpath("//button[@id='customersGrid.add']")).click();
        driver.findElement(By.xpath("//div[@id='customersProfile.type']//i[@class='fa fa-angle-down']")).click();
        driver.findElement(By.xpath("//div[@class='dropDown']//div[3]")).click();
        String cNum=driver.findElement(By.xpath("//input[@id='customersProfile.custNumber']")).getText();
        System.out.println(cNum);
        driver.findElement(By.xpath("//input[@id='customersProfile.company']")).sendKeys("Ximba");
        driver.findElement(By.xpath("//div[@id='customersProfile.secondaryCommodityVid']//div[@class='iconContainer']")).click();
    }

    @AfterClass
    public void close()
    {
        driver.quit();
    }
}
