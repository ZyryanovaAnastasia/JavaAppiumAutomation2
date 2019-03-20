package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class EX2 extends CoreTestCase {

    @Test
    public void  searchTextInInput()
    {
        String search_line = "Java";
        String expected_text_in_search_input = "Search…";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        String text_in_search_input =  SearchPageObject.getTextInSearchInput();

        assertEquals(
                "Ожидалось, что строке поиска присутствует текст  " + expected_text_in_search_input + "\n",
                text_in_search_input,
                expected_text_in_search_input
        );

    }
}