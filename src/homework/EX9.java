package homework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class EX9 extends CoreTestCase {

    @Test
    public void testSearchArticleWithTitleAndDescription()
    {
        String search_line = "Java";
        String searchTitleOne ="Java";
        String searchDescriptionOne = "Island of Indonesia";
        String searchTitleTwo ="JavaScript";
        String searchDescriptionTwo = "Programming language";
        String searchTitleThree ="Java (programming language)";
        String searchDescriptionThree = "Object-oriented programming language";
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.waitForListSearch();
        SearchPage.waitForElementByTitleAndDescription(searchTitleOne, searchDescriptionOne);
        SearchPage.waitForElementByTitleAndDescription(searchTitleTwo, searchDescriptionTwo);
        SearchPage.waitForElementByTitleAndDescription(searchTitleThree, searchDescriptionThree);
    }
}
