package pages.listing;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RozetkaNotebooksListingPage extends BasePage {
    public RozetkaNotebooksListingPage(WebDriver driver) {
        super(driver);
    }

    private final By card = By.xpath("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted']");
    private final By nextPageBtn = By.xpath("//a[@class='button button--gray button--medium " +
            "pagination__direction pagination__direction--forward ng-star-inserted']");
    private final By previousPageBtn = By.xpath("//a[@class='button button--gray button--medium pagination__direction ng-star-inserted']");
    private final By lastPageNavigationBtn = By.xpath("//li[@class='pagination__item ng-star-inserted'][last()]" +
            "//a[@class='pagination__link ng-star-inserted']");
    private final By price = By.xpath("//span[@class='goods-tile__price-value']");

    private final By lastCardElement = By.xpath("//li[contains(@class, 'catalog-grid__cell ')][last()]");
    private final By selectDrpSorting = By.xpath("//select[contains(@class, 'select-css')]");

    public By price(int i){

        return By.xpath("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted'][" + i + "]//span[@class='goods-tile__price-value']");
    }
    public RozetkaNotebooksListingPage checkCountCards(){
//        waitElementIsVisibleFluent(nextPageBtn);
        waitElementIsVisible(nextPageBtn);
        int countCards = driver.findElements(card).size();
        Assert.assertEquals(countCards, 60 );
        return this;
    }

    public int getPagesNumber(){
        waitElementIsVisibleFluent(lastPageNavigationBtn);
        return Integer.parseInt(driver.findElement(lastPageNavigationBtn).getText());
    }

    public RozetkaNotebooksListingPage goNextPage(){
        waitElementIsVisibleFluent(nextPageBtn);
        driver.findElement(nextPageBtn).click();
        return this;
    }

    public RozetkaNotebooksListingPage goPreviousPage(){
        waitElementIsVisibleFluent(previousPageBtn);
        driver.findElement(previousPageBtn).click();
        return this;
    }

    public RozetkaNotebooksListingPage chooseSorting(String sortType){
        waitElementIsVisible(selectDrpSorting);

        for (int i=0; i< 3; i++){
            try {
                new Select(driver.findElement(selectDrpSorting)).selectByVisibleText(sortType);
                break;
            } catch(StaleElementReferenceException e) {}
        }

        return this;

    }

    public RozetkaNotebooksListingPage isSortedAsc(List<Integer> prices){

        List<Integer> pricesCopy = new ArrayList<>(prices);
        Collections.sort(pricesCopy);

        System.out.println(prices);
        System.out.println(pricesCopy);
        Assert.assertEquals(prices, pricesCopy);
        return this;
    }

    public RozetkaNotebooksListingPage isSortedDesc(List<Integer> prices){

        List<Integer> pricesCopy = new ArrayList<>(prices);
        Collections.sort(pricesCopy, Collections.reverseOrder());

        Assert.assertEquals(prices, pricesCopy);
        return this;
    }

    public List <Integer> getPricesOfElementsFromPage()  {

        waitElementIsVisibleFluent(lastCardElement);

        List <Integer> pricesValue = new ArrayList<>();
        String priceText;
        int cardsNumber = driver.findElements(price).size();
        System.out.println("cardsNumber="+cardsNumber);
        WebElement price;
        for (int i=1; i < cardsNumber; i++){
            waitElementIsVisible(price(i));
            for(int ii=0;;ii++){
                try {
                    price=driver.findElement(price(i));
                    priceText=price.getText();
                    break;
                }catch (StaleElementReferenceException e){
                    System.out.println("ii="+ii);
                }

            }

            priceText=priceText.replaceFirst("₴", "");
            pricesValue.add(Integer.parseInt( String.join("", priceText.split(" " ))));
        }
//        System.out.println("prices.size() " + pricesValue.size());
        return pricesValue;
    }



    public List <Integer> getPricesOfElementsFromAllPages() {
        List<Integer> allPrices = new ArrayList<>();

        int pagesNumber = getPagesNumber();
        for (int i = 1; i < pagesNumber; i++){
            allPrices.addAll(getPricesOfElementsFromPage());
            goNextPage();
            waitUrlContains("page="+(i+1));
            System.out.println("page = " + (i+1));
        }
        return allPrices;
    }


}
