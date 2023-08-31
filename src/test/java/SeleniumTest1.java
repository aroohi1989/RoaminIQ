    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;
    import io.github.bonigarcia.wdm.WebDriverManager;
    import org.testng.Assert;
    import org.testng.annotations.AfterClass;
    import org.testng.annotations.Parameters;
    import org.testng.annotations.Test;

    import java.time.Duration;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.concurrent.TimeUnit;


    public class SeleniumTest1
    {
        WebDriver driver;
        @Test(priority = 1)
        public void sum()
        {
            System.out.println("First Test from Maven");
            int a=10; int b=20;
            Assert.assertEquals(30 ,a+b);
        }
        @Test(priority = 2)
        public void mul()
        {
            System.out.println("Second Test from Maven");
            int a=10; int b=10;
            Assert.assertEquals(100,a*b);
        }
        @Test(priority = 3)
        public void login()
        {
            String url= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
            WebDriverManager.chromedriver().setup();
            WebDriver driver=new ChromeDriver();
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.tagName("button")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Boolean flag=driver.getCurrentUrl().contains("dashboard");
            Assert.assertEquals(flag,true);
            //driver.quit();
            this.driver=driver;
        }

        @Test(priority = 4, enabled = false)
        public void menuItems()
        {
            SeleniumTest1 st=new SeleniumTest1();
            List<WebElement> arr=driver.findElements(By.xpath("//a[contains(@class,'oxd-main-menu-item')]"));
            int len=arr.size();
            for (WebElement ele:arr)
            {
                System.out.println(ele.getText());
            }
            Assert.assertEquals(len,11);
        }
        @AfterClass
        public void closed()
        {
            driver.quit();
        }
    }