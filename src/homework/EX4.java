package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class EX4 extends CoreTestCase {

    @Test
    public void testCheckWordsInSearch()
    {
        String search_line = "Java";
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.waitForListSearch();
        SearchPage.checkAllTitleArticle(search_line);
    }
}
