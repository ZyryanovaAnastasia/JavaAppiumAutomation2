package test;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    //Добавление статьи в список и удаление из него
    @Test
    public void testSaveArticleToMyListAndDelete()
    {
        String search_line = "Java";
        String article_name = "Java (programming language)";
        String name_of_folder = "Learning programming";
        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePage = ArticlePageObjectFactory.get(driver);
        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        MyListPageObject MyListPage = MyListPageObjectFactory.get(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(article_name);

        ArticlePage.waitForTitleElement();
        String article_title = ArticlePage.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePage.addArticleToNewList(name_of_folder);
        } else {
            ArticlePage.addArticlesToMySaved();
        }

        ArticlePage.closeArticle();

        NavigationUi.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            MyListPage.openFolderByName(name_of_folder);
        }

        MyListPage.swipeByArticleToDelete(article_title);
    }
}
