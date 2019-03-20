package test;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    //Поиск статьи
    @Test
    public void testSearch()
    {
        String search_line = "Appium";
        String expected_title = "Appium";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(expected_title);
    }

    //Отмена поиска
    @Test
    public void testCanselSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.waitForCancelBtnToAppear();
        SearchPageObject.clickCanselSearch();
        SearchPageObject.waitForCancelBtnToDisappear();
    }

    //Проверка, что в результате поиска есть результаты
    @Test
    public void testAmountOfNotEmptySearch()
    {
        String search_line = "Linkin Park Diskography";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "В результате поиска не найдено ни одной статьи",
                amount_of_search_results > 0
        );
    }

    //Проверка, что в результате поиска пустой результат
    @Test
    public void testAmountOfEmptySearch()
    {
        String search_line = "123456789qaz";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
