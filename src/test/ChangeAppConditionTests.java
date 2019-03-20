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
        String expected_title = "Object-oriented programming lauguage";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(expected_title);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "После поворота экрана изменился заголовок статьи",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "После поворота экрана изменился заголовок статьи",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    //Проверка, что заголовок статьи не изменяется после ухода приложение в фоновый режим
    @Test
    public void testCheckSearchArticleInBackgrouns()
    {
        String search_line = "Java";
        String expected_title = "Object-oriented programming lauguage";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(expected_title);
        this.backgroundApp(2);
        SearchPageObject.clickByArticleWithSubstring(expected_title);
    }
}
