package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerPage extends BaseClass
{
    @FindBy(id="header.menuOrders")
    WebElement menuOrders;

    @FindBy(id="header.subMenuCustomers")
    WebElement menuCustomers;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue;

    @FindBy(xpath = "//button[@id='customersGrid.add']")
    WebElement addCustomers;

    @FindBy(xpath = "//div[@id='customersProfile.type']/div[@class='iconContainer']/i")
    WebElement typedropdwn;

    @FindBy(xpath = "//div[@id='customersProfile.type']/div[1]")
    WebElement typeValue;

    @FindBy(xpath = "//div[@id='customersProfile.type']/div[last()]/div/div")
    WebElement custtypevalue;//Standard,Agency,Rep

    @FindBy(xpath = "//div[@id='customersProfile.checkBoxCreditHold']")
    WebElement creditholdchckbx;

    @FindBy(xpath = "//div[@id='customersProfile.active']")
    WebElement inactivechckbox;

    @FindBy(xpath = "//input[@id='customersProfile.custNumber']")
    WebElement custid;

    @FindBy(xpath = "//input[@id='customersProfile.company']")
    WebElement custname;

    @FindBy(xpath = "//div[@id='customersProfile.salespersonVid']/div[last()]")
    WebElement salespersondrpdwn;

    @FindBy(xpath = "//div[@id='customersProfile.salespersonVid']/div[last()]/div/div")
    WebElement spdropdownvalues;

    @FindBy(xpath = "//div[@id='customersProfile.secondaryCommodityVid']/div[last()]/i")
    WebElement commoditydrpdown;

    @FindBy(xpath = "//div[@id='customersProfile.secondaryCommodityVid']/div[last()]/div/div")
    WebElement commodityvalues;

    @FindBy(xpath = "//button[@id='customer.cancelSave.save']")
    WebElement savecustbtn;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement custbreadcrum;// New Customer

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean selectCustType(String type)
    {
        Boolean flag=false;
        Actions ac=new Actions(driver);
        JavaScriptExecutor js=new JavaScriptExecutor();
        js.clickElementByJS(typedropdwn);
        while(!custtypevalue.isDisplayed()) {
            new Actions(driver).moveToElement(typedropdwn).perform();
            ac.click(typedropdwn);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if(custtypevalue.isDisplayed()) {
            List<WebElement> ele = driver.findElements(By.xpath("//div[@id='customersProfile.type']/div[last()]/div/div"));
            if (type.equalsIgnoreCase("Agency"))
            {
                WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[contains(text(),'Agency')]"));
               try {
                   Robot robot = new Robot();
                   robot.mouseMove(ele1.getLocation().getX(), ele1.getLocation().getY()); // Move mouse to the dropdown
                   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
               }
               catch (Exception e)
               {
                   ExceptionHandling.handleException(e);
               }
                /*new Actions(driver).moveToElement(ele1).perform();
                ac.click(ele1);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                js.clickElementByJS(ele.get(0));*/
                flag = true;
            }
            else if (type.equalsIgnoreCase("Standard"))
            {
                WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[contains(text(),'Standard')]"));
                new Actions(driver).moveToElement(ele1).perform();
                ac.click(ele1);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                js.clickElementByJS(ele.get(2));
                flag = true;
            } else if (type.equalsIgnoreCase("Rep"))
            {
                WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[contains(text(),'Rep')]"));
                try {
                    Robot robot = new Robot();
                    robot.mouseMove(ele1.getLocation().getX(), ele1.getLocation().getY()); // Move mouse to the dropdown
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                catch (Exception e)
                {
                    ExceptionHandling.handleException(e);
                }
               /* new Actions(driver).moveToElement(ele1).perform();
                ac.click(ele1);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                js.clickElementByJS(ele.get(1));*/
                flag = true;
            }
        }
        return flag;
    }
    public boolean selectSalesperson()
    {
        Boolean flag=false;
        List<WebElement> ele=driver.findElements(By.xpath("//div[@id='customersProfile.salespersonVid']/div[last()]/div/div"));
        for (WebElement e:ele)
        {
               e.click();
                flag=true;
                break;
        }
        return flag;
    }
    public boolean selectcommodity()
    {
        Boolean flag=false;
        List<WebElement> ele=driver.findElements(By.xpath("//div[@id='customersProfile.secondaryCommodityVid']/div[last()]/div/div"));
        for (WebElement e:ele)
        {
            e.click();
            flag=true;
            break;
        }
        return flag;
    }
    public void createStandardCustomer()
    {
        menuOrders.click();
        menuCustomers.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        addCustomers.click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        sa.assertTrue(selectCustType("Standard"));
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        custname.click();
        custname.sendKeys(init);
        salespersondrpdwn.click();
        sa.assertTrue(selectSalesperson());
        commoditydrpdown.click();
        sa.assertTrue(selectcommodity());
        savecustbtn.click();
        if(custbreadcrum.isDisplayed())
        {
            Assert.assertTrue(custbreadcrum.getText().contains("New Customer"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Standard Cust",init);
        }
    }
    public void createAgencyCustomer()
    {
        menuOrders.click();
        menuCustomers.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        addCustomers.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        sa.assertTrue(selectCustType("Agency"));
        Assert.assertEquals(typeValue.getText(),"Agency");
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        custname.click();
        custname.sendKeys(init);
        System.out.println("Customer name is "+init);
        salespersondrpdwn.click();
        sa.assertTrue(selectSalesperson());
        commoditydrpdown.click();
        sa.assertTrue(selectcommodity());
        savecustbtn.click();
        if(custbreadcrum.isDisplayed())
        {
            Assert.assertTrue(custbreadcrum.getText().contains("New Customer"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Agency Cust",init);
        }
    }
    public void createRepCustomer()
    {
        menuOrders.click();
        menuCustomers.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        addCustomers.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        sa.assertTrue(selectCustType("Rep"));
        Assert.assertEquals(typeValue.getText(),"Rep");
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        custname.click();
        custname.sendKeys(init);
        salespersondrpdwn.click();
        sa.assertTrue(selectSalesperson());
        commoditydrpdown.click();
        sa.assertTrue(selectcommodity());
        savecustbtn.click();
        if(custbreadcrum.isDisplayed())
        {
            Assert.assertTrue(custbreadcrum.getText().contains("New Customer"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Rep Cust",init);
        }

    }






}
