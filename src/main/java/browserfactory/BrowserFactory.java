package browserfactory;

import base.BaseClass;
import com.fasterxml.jackson.databind.ser.Serializers;
import dataProvider.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import listners.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BrowserFactory extends BaseClass
{


    public static WebDriver getBrowserInstance() {
        return driver;
    }

    public static WebDriver startBrowser(String browserName, String applicationURL)
    {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        //Create instance of ChromeOptions Class
        ChromeOptions handlingSSL = new ChromeOptions();
        //Using the accept insecure cert method with true as parameter to accept the untrusted certificate
        handlingSSL.setAcceptInsecureCerts(true);

        if (browserName.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(handlingSSL);

        } else if (browserName.contains("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.contains("Firefox")) {
            System.setProperty("webdriver.gecko.driver", ConfigReader.getPropertyvalue("GeckoexePath"));
            driver = new FirefoxDriver();
        }
        //Event Listener initialization
        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;
        //end of event listener initialization

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(applicationURL);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
        return driver;
    }
}
