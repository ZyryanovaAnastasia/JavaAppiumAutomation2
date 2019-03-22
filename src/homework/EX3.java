package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

public class EX3 extends CoreTestCase {

    @Test
    public void  testSearchTextInInput()
    {
        String search_line = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForListSerach();

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "В результате поиска не найдено ни одной статьи",
                amount_of_search_results > 0
        );

        SearchPageObject.waitForCancelBtnToAppear();
        SearchPageObject.clickCanselSearch();

        int zero_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Ожидалось, что в результате поиска не будет ни одной статьи",
                zero_of_search_results == 0
        );
    }
}
