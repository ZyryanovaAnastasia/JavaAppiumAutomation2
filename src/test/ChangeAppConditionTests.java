package test;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    //Проверка, что заголовок статьи не изменяется после поворта экрана
    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        String search_line = "Java";
        String expected_title = "Java (programming language)";
        SearchPageObject SearchPage = new SearchPageObject(driver);
        ArticlePageObject ArticlePage = new ArticlePageObject(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(expected_title);

        String title_before_rotation = ArticlePage.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePage.getArticleTitle();

        assertEquals(
                "После поворота экрана изменился заголовок статьи",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePage.getArticleTitle();

        assertEquals(
                "После поворота экрана изменился заголовок статьи",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    //Проверка, что заголовок статьи не изменяется после ухода приложение в фоновый режим
    @Test
    public void testCheckSearchArticleInBackground()
    {
        String search_line = "Java";
        String expected_title = "Java (programming language)";
        SearchPageObject SearchPage = new SearchPageObject(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.waitForElementByTitle(expected_title);
        this.backgroundApp(2);
        SearchPage.waitForElementByTitle(expected_title);
    }
}
