package homework;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import org.junit.Test;

public class Ex6 extends CoreTestCase {

    @Test
    public void testAssertTitle()
    {
        String search_line = "Java";
        String article_name_one = "JavaScript";
        ArticlePageObject ArticlePage = new ArticlePageObject(driver);

        ArticlePage.searchAndOpenArticle(search_line, article_name_one);

        assertTrue(
                "Ожидалось, что на странице виден заголовок",
                ArticlePage.titleElementIsDisplayed());
    }
}
