package test.search;

import constants.Constant;

import org.testng.annotations.Test;
import test.base.BaseTest;

import static constants.Constant.Urls.ROZETKA_HOME_PAGE;
import static constants.Constant.searchInput.NOTEBOOKS;

public class SearchNotebooksTest extends BaseTest {

    @Test(priority = 1, description = "Check if redirected to correct page and it contains 60 elements")
    public void checkIsRedirectedToNotebooksList() {

        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString(NOTEBOOKS);
        rozetkaListingPage.checkUrlContains(NOTEBOOKS);
        rozetkaListingPage.checkCountCards();

    }
    @Test(priority = 2, description = "Check if elements from all appropriate pages are sorted correct by CHEAP")
    public void checkSortingCheap() {
        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString(NOTEBOOKS);
        rozetkaListingPage.chooseSorting(Constant.SortOptions.CHEAP.getOption());
        rozetkaListingPage.isSortedAsc(
                rozetkaListingPage.getPricesOfElementsFromAllPages());

    }

    @Test(priority = 3, description = "Check if elements from all appropriate pages are sorted correct by EXPENSIVE")
    public void checkSortingExpensive() {
        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString(NOTEBOOKS);

        rozetkaListingPage.chooseSorting(Constant.SortOptions.EXPENSIVE.getOption());
        rozetkaListingPage.isSortedDesc(
                rozetkaListingPage.getPricesOfElementsFromAllPages());

    }




}
