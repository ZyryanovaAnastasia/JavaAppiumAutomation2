package test;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTest extends CoreTestCase {

    @Test
    //Поиск, открытие, проверка, что заголовок статьи такой как ожидалось
    public void testCompareArticleTitle()
    {
        String search_line = "Java";
        String expected_title = "ava (programming language)";
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePage = ArticlePageObjectFactory.get(driver);

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
        String search_line = "Java";
        String expected_title = "ava (programming language)";
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePage = ArticlePageObjectFactory.get(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(expected_title);

        ArticlePage.waitForTitleElement();
        ArticlePage.swipeUpToFooter();
    }
}
