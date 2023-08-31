package listners;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebEventListener  extends BaseClass implements WebDriverEventListener
{

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

        logger.info("Before navigating to {url}");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.info("Navigated to {url}");
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        // Do nothing
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Trying to find Element By {by.toString()})");
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Found Element By {by.toString()}");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.info("Trying to click on: {element.toString()}");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.info("Clicked on: {element.toString()}");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
    // Do nothing
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // Do nothing
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        // Do nothing
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        // Do nothing
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        // Do nothing
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        // Do nothing
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        // Do nothing
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        // Do nothing
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        // Do nothing
    }
}
