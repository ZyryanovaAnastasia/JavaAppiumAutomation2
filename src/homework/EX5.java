package homework;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUi;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import org.junit.Test;

public class EX5 extends CoreTestCase {

    @Test
    public void testSaveAndDeleteTwoArticle()
    {
        String search_line = "Java";
        String article_name_one = "JavaScript";
        String article_name_two = "Java";
        String name_of_folder = "Learning programming";

        ArticlePageObject ArticlePage = ArticlePageObjectFactory.get(driver);
        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        MyListPageObject MyListPage = MyListPageObjectFactory.get(driver);

        ArticlePage.searchAndOpenArticle(search_line, article_name_one);
        ArticlePage.addArticleToNewList(name_of_folder);
        ArticlePage.closeArticle();

        ArticlePage.searchAndOpenArticle(search_line, article_name_two);
        ArticlePage.addArticleToSpecialList(name_of_folder);
        ArticlePage.closeArticle();

        NavigationUi.clickMyLists();
        MyListPage.openFolderByName(name_of_folder);

        MyListPage.swipeByArticleToDelete(article_name_one);
        MyListPage.waitForArticleToAppearByTitle(article_name_two);

        ArticlePage.clickArticleName(article_name_two);
        String gotten_article_title = ArticlePage.getArticleTitle();

        assertEquals(
                "Ожидалось, что заголовок статьи будет " + article_name_two,
                article_name_two,
                gotten_article_title
        );

    }
}
