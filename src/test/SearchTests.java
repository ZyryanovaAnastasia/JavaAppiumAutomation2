package test;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    //Поиск статьи
    @Test
    public void testSearch()
    {
        String search_line = "Appium";
        String expected_title = "ppium";

        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.waitForElementByTitle(expected_title);
    }

    //Отмена поиска
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        SearchPage.intSearchInput();
        SearchPage.waitForCancelBtnToAppear();
        SearchPage.clickCancelSearch();
        SearchPage.waitForCancelBtnToDisappear();
    }

    //Проверка, что в результате поиска есть результаты
    @Test
    public void testAmountOfNotEmptySearch()
    {
        String search_line = "Linkin Park Diskography";

        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        int amount_of_search_results = SearchPage.getAmountOfFoundArticles();

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

        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.waitForEmptyResultsLabel();
        SearchPage.waitElementNoResultsFound();
    }
}
