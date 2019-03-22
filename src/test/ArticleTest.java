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
        String expected_title = "Java (programming language)";
        SearchPageObject SearchPage = new SearchPageObject(driver);
        ArticlePageObject ArticlePage = new ArticlePageObject(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(expected_title);
        String article_title = ArticlePage.getArticleTitle();

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
        SearchPageObject SearchPage = new SearchPageObject(driver);
        ArticlePageObject ArticlePage = new ArticlePageObject(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(expected_title);

        ArticlePage.waitForTitleElement();
        ArticlePage.swipeUpToFooter();
    }
}
