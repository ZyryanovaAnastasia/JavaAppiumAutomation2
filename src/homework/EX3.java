package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class EX3 extends CoreTestCase {

    @Test
    public void  searchTextInInput()
    {
        String search_line = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "В результате поиска не найдено ни одной статьи",
                amount_of_search_results > 0
        );

        SearchPageObject.waitForCancelBtnToAppear();
        SearchPageObject.clickCanselSearch();
        SearchPageObject.waitForCancelBtnToDisappear();

        int amount_of_search_results_after_clean = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Не работается очистка поиска, отображается список статей",
                amount_of_search_results_after_clean == 0);
    }
}
