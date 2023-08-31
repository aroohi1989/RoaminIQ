package pages;

import base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Network_programbreakformatPage extends BaseClass
{
    public Network_programbreakformatPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='NetworkEntity.BreakFormats']")
    WebElement program_break_format;
    @FindBy(xpath = "//button[@id='breakFormatGrid.add']")
    WebElement program_break_add;
    @FindBy(xpath = "//input[@id='breakFormatsProfile_title']")
    WebElement program_break_title;
    @FindBy(xpath = "//input[@id='breakFormatsProfile_episode']")
    WebElement program_break_episode;
    @FindBy(xpath = "//input[@id='breakFormatsProfile_length']")
    WebElement program_break_length;
    @FindBy(xpath = "//div[@id='breakFormatsProfile.visible']")
    WebElement program_break_visible;
    @FindBy(xpath = "//button[@id='breakFormatsProfile.add']")
    WebElement program_break_add_break;
    // Add break
    @FindBy(xpath = "//input[@id='breakFormatSegmentOverlay_name']")
    WebElement break_name;
    @FindBy(xpath = "//div[@id='breakFormatSegmentOverlay.inventoryAutocomplete']")
    WebElement break_inventory_type;
    @FindBy(xpath = "//div[@class='auto-complete-list-drop-down ng-isolate-scope']/div/span")
    WebElement break_inventory_options;
    @FindBy(xpath = "//input[@ng-model='data.startTime']")
    WebElement break_start_date;
    @FindBy(xpath = "//div[@class='cell-35']/input[@id='breakFormatSegmentOverlay_length']")
    WebElement break_length;
    @FindBy(xpath = "//button[@id='cancelSaveOk.Save']")
    WebElement break_save;
    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement program_break_save;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb2']")
    WebElement new_break_format;

    @FindBy(xpath = "//div[@class='popupWindowContent defDecorFrame']")
    WebElement popup_save_confirm;

    @FindBy(xpath = "//div[@class='popupWindowBody']/div")
    WebElement popupWindowBody;

    @FindBy(xpath = "//div[@class='popupWindowButtons buttonsArea']/button")
     WebElement popup_yes;

    public String create_break_format(String net,String choice)
    {
        String title=net+"_BF";
        System.out.println("Before");
        System.out.println(program_break_format.getCssValue("height"));
        System.out.println("After");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        //jsExecutor.executeScript("arguments[0].style.height = 'auto'; arguments[0].style.visibility = 'visible';", program_break_format);
        try {
            jsExecutor.executeScript("arguments[0].style.height = '200px';", program_break_format);
            String elementHeight = (String) jsExecutor.executeScript("return arguments[0].style.height;", program_break_format);
            System.out.println("Element height: " + elementHeight);
            System.out.println(program_break_format.getCssValue("height"));
            program_break_format.click();
        }
        catch(ElementNotInteractableException e)
        {
            jsExecutor.executeScript("arguments[0].style.height = '200px';", program_break_format);
            program_break_format.click();
        }
        catch(StaleElementReferenceException e)
        {
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.elementToBeClickable(program_break_format));
            program_break_format= driver.findElement(By.xpath("//div[@id='NetworkEntity.BreakFormats']"));
            program_break_format.click();

        }
        handle_Popup();
     //   System.out.println(program_break_format.getCssValue("width"));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        program_break_add.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        program_break_title.sendKeys(title);
        program_break_episode.sendKeys(title);
        program_break_length.sendKeys("01:00:00");
        program_break_visible.click();
        // Add break
        program_break_add_break.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        break_name.sendKeys(title);
        break_inventory_type.click();
        //move to a method
        List<WebElement> inventory_type=driver.findElements(By.xpath("//input[@ng-model='data.startTime']"));
        for (WebElement e:inventory_type)
        {
            if(e.getAttribute("title").contains(choice))
            {
                e.click();
                break;
            }
        }
        break_start_date.sendKeys("00:00:00");
        break_length.sendKeys("00:01:00");
        break_save.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        jsExecutor.executeScript("arguments[0].click();", program_break_save);
        //program_break_save.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(new_break_format.getAttribute("innerHTML"));
        Assert.assertTrue(new_break_format.isDisplayed());
        return title;
    }

    public void handle_Popup()
    {
        try{
            if(popup_save_confirm.isDisplayed())
            {
                if(popupWindowBody.getText().contains("Would you like to save your changes?"))
                {
                    driver.navigate().refresh();
                    popup_yes.click();
                }
            }
        }
        catch(NoSuchElementException e)
        {
            return;
        }
    }

}
