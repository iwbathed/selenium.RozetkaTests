package pages.rozetka_home;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class RozetkaHomePage extends BasePage {


    public RozetkaHomePage(WebDriver driver) {
        super(driver);
    }

    private final By search = By.xpath("//input[@name='search']");

    public RozetkaHomePage searchFieldClearAndEnterString(String inputText){
        WebElement searchField = driver.findElement(search);
        searchField.clear();
        searchField.sendKeys(inputText, Keys.ENTER);
        return this;
    }

}



























