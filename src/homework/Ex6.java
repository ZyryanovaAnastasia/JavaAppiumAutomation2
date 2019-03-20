package homework;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import org.junit.Test;

public class Ex6 extends CoreTestCase {

    @Test
    public void assertTitle()
    {
        String search_line = "Java";
        String article_name_one = "JavaScript";
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.searchAndOpenArticle(search_line, article_name_one);

        assertTrue(
                "Ожидалось, что на странице виден заголовок",
                ArticlePageObject.titleElementIsDisplayed());
    }
}
