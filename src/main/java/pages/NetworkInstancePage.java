package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NetworkInstancePage extends BaseClass
{
    WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class,'defaultRadioInputs')]")
    By netSumRadio; //Current Week,Next 4 Weeks,Custom Dates

    @FindBy(xpath = "//div[contains(@class,'inputCalendar')]")
    WebElement inputCalender;



    public NetworkInstancePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    }
