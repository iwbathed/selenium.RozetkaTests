package test.search;


import org.testng.annotations.Test;
import test.base.BaseTest;

import static constants.Constant.SortOptions.CHEAP;
import static constants.Constant.SortOptions.EXPENSIVE;
import static constants.Constant.Urls.ROZETKA_HOME_PAGE;
import static constants.Constant.searchInput.COMPUTERS;


public class SearchComputersTest extends BaseTest {

    @Test(priority = 1, description = "Check if redirected to correct page and it contains 60 elements")
    public void checkIsRedirectedToComputerList() {

        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString(COMPUTERS);
        rozetkaListingPage.checkUrlContains(COMPUTERS);
        rozetkaListingPage.checkCountCards();

    }
    @Test(priority = 2, description = "Check if elements from all appropriate pages are sorted correct by CHEAP")
    public void checkSortingCheap() {

        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString(COMPUTERS);
        rozetkaListingPage.chooseSorting(CHEAP.getOption());
        rozetkaListingPage.isSortedAsc(
                rozetkaListingPage.getPricesOfElementsFromAllPages(CHEAP.toString()));

    }

    @Test(priority = 3, description = "Check if elements from all appropriate pages are sorted correct by EXPENSIVE")
    public void checkSortingExpensive() {
        basePage.open(ROZETKA_HOME_PAGE);
        rozetkaHomePage.searchFieldClearAndEnterString(COMPUTERS);

        rozetkaListingPage.chooseSorting(EXPENSIVE.getOption());
        rozetkaListingPage.isSortedDesc(
                rozetkaListingPage.getPricesOfElementsFromAllPages(EXPENSIVE.toString()));

    }




}
