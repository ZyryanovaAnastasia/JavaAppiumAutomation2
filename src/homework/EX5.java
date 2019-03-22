package homework;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUi;
import org.junit.Test;

public class EX5 extends CoreTestCase {

    @Test
    public void testSaveAndDeleteTwoArticle()
    {
        String search_line = "Java";
        String article_name_one = "JavaScript";
        String article_name_two = "Java";
        String name_of_folder = "Learning programming";

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUi NavigationUi = new NavigationUi(driver);
        MyListPageObject MyListPageObject = new MyListPageObject(driver);

        ArticlePageObject.searchAndOpenArticle(search_line, article_name_one);
        ArticlePageObject.addArticleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        ArticlePageObject.searchAndOpenArticle(search_line, article_name_two);
        ArticlePageObject.addArticleToSpecialList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUi.clickMyLists();
        MyListPageObject.openFolderByName(name_of_folder);

        MyListPageObject.swipeByArticleToDelete(article_name_one);
        MyListPageObject.waitForArticleToAppearByTitle(article_name_two);

        ArticlePageObject.clickArticleName(article_name_two);
        String gotten_article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Ожидалось, что заголовок статьи будет " + article_name_two,
                article_name_two,
                gotten_article_title
        );

    }
}
