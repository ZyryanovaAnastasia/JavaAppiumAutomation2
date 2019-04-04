package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class EX3 extends CoreTestCase {

    @Test
    public void  testSearchTextInInput()
    {
        String search_line = "Java";

        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.waitForListSearch();

        int amount_of_search_results = SearchPage.getAmountOfFoundArticles();

        assertTrue(
                "В результате поиска не найдено ни одной статьи",
                amount_of_search_results > 0
        );

        SearchPage.waitForCancelBtnToAppear();
        SearchPage.clickCancelSearch();

        int zero_of_search_results = SearchPage.getAmountOfFoundArticles();

        System.out.println(zero_of_search_results);
        assertTrue(
                "Ожидалось, что в результате поиска не будет ни одной статьи",
                zero_of_search_results == 0
        );
    }
}
