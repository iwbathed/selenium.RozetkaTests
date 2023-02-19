package pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static constants.Constant.TimeOutVariable.EXPLICITLY_WAIT;

public class BasePage {
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void open(String url){
        driver.get(url);
    }

    public void waitElementIsVisibleFluent(By byValue){
        FluentWait wait = new FluentWait(driver);

        wait.withTimeout(Duration.ofSeconds(EXPLICITLY_WAIT));
        wait.pollingEvery(Duration.ofMillis(100));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byValue));
    }
    public void waitElementIsVisible(By byValue){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(byValue));
    }

    public void waitElementIsClickable(By byValue){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT)).until(ExpectedConditions.elementToBeClickable(byValue));
    }

    public void waitUrlContains(String text){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT)).until(ExpectedConditions.urlContains(text));
    }

    public void checkUrlContains(String text) {

        boolean contains;
        try {

            contains= java.net.URLDecoder.decode(driver.getCurrentUrl(), StandardCharsets.UTF_8.name()).contains(text);

        }catch (UnsupportedEncodingException e){
            contains=false;
        }
        Assert.assertTrue(contains);

    }
}
