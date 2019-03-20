package test;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTest extends CoreTestCase {

    @Test
    //Поиск, открытие, проверка, что заголовок статьи такой как ожидалось
    public void testCompareArticleTitle()
    {
        String search_line = "Java";
        String expected_title = "Object-oriented programming lauguage";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(expected_title);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Ожидалось, что заголовок статьи будет " + expected_title,
                expected_title,
                article_title
        );
    }

    //Поиск, открытие, свайп статьи до футера
    @Test
    public void testSwipeArticleToFooter()
    {
        String search_line = "Appium";
        String expected_title = "Appium";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(expected_title);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitlteElement();
        ArticlePageObject.swipeUpToFooter();
    }
}
