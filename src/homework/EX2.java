package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class EX2 extends CoreTestCase {

    @Test
    public void  testSearchTextInInput()
    {
        String expected_text_in_search_input = "Search…";
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);

        SearchPage.intSearchInput();
        String text_in_search_input =  SearchPage.getTextInSearchInput();

        assertEquals(
                "Ожидалось, что строке поиска присутствует текст  " + expected_text_in_search_input + "\n",
                text_in_search_input,
                expected_text_in_search_input
        );

    }
}