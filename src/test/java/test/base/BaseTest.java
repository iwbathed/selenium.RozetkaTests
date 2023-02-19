package test.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import pages.base.BasePage;
import pages.listing.RozetkaListingPage;
import pages.rozetka_home.RozetkaHomePage;

import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static common.Config.QUIT_BROWSER;

public class BaseTest {
    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver);

    protected RozetkaListingPage rozetkaListingPage = new RozetkaListingPage(driver);
    @AfterTest
    public void clearCookiesAndLocalStorage(){
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void close() {
        if (QUIT_BROWSER){
            driver.quit();
        }
    }

}
