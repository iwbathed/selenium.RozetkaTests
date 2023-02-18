package test.searchNotebooks;

import constants.Constant;

import org.testng.annotations.Test;
import test.base.BaseTest;

import static constants.Constant.Urls.ROZETKA_HOME_PAGE;

public class SearchNotebooksTest extends BaseTest {

    @Test(priority = 1)
    public void checkIsRedirectedToNotebooksList() {

        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString("Ноутбуки");
        rozetkaNotebooksListingPage.checkCountCards();

    }
    @Test(priority = 2)
    public void checkSortingCheap() throws InterruptedException {
        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString("Ноутбуки");
        rozetkaNotebooksListingPage.chooseSorting(Constant.SortOptions.CHEAP.getOption());
        rozetkaNotebooksListingPage.isSortedAsc(
                rozetkaNotebooksListingPage.getPricesOfElementsFromAllPages());

    }

    @Test(priority = 3)
    public void checkSortingExpensive() throws InterruptedException {
        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString("Ноутбуки");

        rozetkaNotebooksListingPage.chooseSorting(Constant.SortOptions.EXPENSIVE.getOption());
        rozetkaNotebooksListingPage.isSortedDesc(
                rozetkaNotebooksListingPage.getPricesOfElementsFromAllPages());

    }




}
