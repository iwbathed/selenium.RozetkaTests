package pages.listing;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constants.Constant.ListingData.EXPECTED_ELEMENTS_NUMBER;

public class RozetkaListingPage extends BasePage {
    public RozetkaListingPage(WebDriver driver) {
        super(driver);
    }

    private final By card = By.xpath(
            "//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted']");
    private final By nextPageBtn = By.xpath("//a[@class='button button--gray button--medium " +
            "pagination__direction pagination__direction--forward ng-star-inserted']");
    private final By previousPageBtn = By.xpath(
            "//a[@class='button button--gray button--medium pagination__direction ng-star-inserted']");
    private final By lastPageNavigationBtn = By.xpath("//li[@class='pagination__item ng-star-inserted'][last()]" +
            "//a[@class='pagination__link ng-star-inserted']");
    private final By price = By.xpath("//span[@class='goods-tile__price-value']");
    private final By lastCardElement = By.xpath("//li[contains(@class, 'catalog-grid__cell ')][60]");
    private final By selectDrpSorting = By.xpath("//select[contains(@class, 'select-css')]");

    public By price(int i){

        return By.xpath(
                "//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted'][" + i + "]" +
                        "//span[@class='goods-tile__price-value']");
    }

    public RozetkaListingPage checkCountCards(){
        waitElementIsVisible(nextPageBtn);

        int countCards = driver.findElements(card).size();
        Assert.assertEquals(countCards, EXPECTED_ELEMENTS_NUMBER);
        return this;
    }

    public int getPagesNumber(){
        waitElementIsVisibleFluent(lastPageNavigationBtn);
        return Integer.parseInt(driver.findElement(lastPageNavigationBtn).getText());
    }

    public RozetkaListingPage goNextPage(){
        waitElementIsVisibleFluent(nextPageBtn);
        driver.findElement(nextPageBtn).click();
        return this;
    }

    public RozetkaListingPage goPreviousPage(){
        waitElementIsVisibleFluent(previousPageBtn);
        driver.findElement(previousPageBtn).click();
        return this;
    }

    public RozetkaListingPage chooseSorting(String sortType){
        waitElementIsVisible(selectDrpSorting);

        for (int i=0;; i++){
            try {
                new Select(driver.findElement(selectDrpSorting)).selectByVisibleText(sortType);
                break;
            } catch(StaleElementReferenceException e) {
                System.out.println(i + " " + e.getMessage());
            }
        }
        return this;
    }

    public RozetkaListingPage isSortedAsc(List<Integer> prices){
        List<Integer> pricesCopy = new ArrayList<>(prices);
        Collections.sort(pricesCopy);
        System.out.println(prices);
        System.out.println(pricesCopy);

        Assert.assertEquals(prices, pricesCopy);
        return this;
    }

    public RozetkaListingPage isSortedDesc(List<Integer> prices){
        List<Integer> pricesCopy = new ArrayList<>(prices);
        Collections.sort(pricesCopy, Collections.reverseOrder());
        System.out.println(prices);
        System.out.println(pricesCopy);
        Assert.assertEquals(prices, pricesCopy);
        return this;
    }

    public List <Integer> getPricesOfElementsFromPage()  {


        List <Integer> pricesValue = new ArrayList<>();
        String priceText;
        int cardsNumber = driver.findElements(price).size();
        WebElement price;
        for (int i=1; i <=cardsNumber; i++){
            waitElementIsVisible(price(i));

            for(int ii=0;;ii++){
                try {
                    price=driver.findElement(price(i));
                    priceText=price.getText();
                    break;
                }catch (StaleElementReferenceException e){
                    System.out.println(ii + " " + e.getMessage());
                }
            }
            priceText=priceText.replaceFirst("₴", "");
            pricesValue.add(Integer.parseInt( String.join("", priceText.split(" " ))));
        }


        return pricesValue;
    }

    public List <Integer> getPricesOfElementsFromAllPages(String sortType) {
        List<Integer> allPrices = new ArrayList<>();
        waitUrlContains(sortType.toLowerCase());
        int pagesNumber = getPagesNumber();
        for (int currentPage = 1; currentPage <= pagesNumber; currentPage++){
            if (currentPage==pagesNumber){
                allPrices.addAll(getPricesOfElementsFromPage());
            }else {
                waitElementIsVisibleFluent(lastCardElement);
                allPrices.addAll(getPricesOfElementsFromPage());
                goNextPage();
                waitUrlContains("page="+(currentPage+1));
            }

        }
        System.out.println(allPrices.size());
        return allPrices;
    }


}
