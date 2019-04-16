package homework;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class EX5 extends CoreTestCase {
    private static final String
            login = "nastia2019",
            password = "test123456";

    @Test
    public void testSaveAndDeleteTwoArticle()
    {
        String search_line = "Java";
        String article_name_one = "Island of Indonesia";
        String article_name_two = "Programming language";
        String name_of_folder = "Learning programming";

        SearchPageObject SearchPage = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePage = ArticlePageObjectFactory.get(driver);
        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        MyListPageObject MyListPage = MyListPageObjectFactory.get(driver);
        AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(article_name_one);

        ArticlePage.waitForTitleElement();
        String article_title = ArticlePage.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePage.addArticleToNewList(name_of_folder);
        } else if (Platform.getInstance().isMW()) {
            Auth.clickAuthBtn();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            assertEquals(
                    "Не удалось перейти на статью после логина",
                    article_title,
                    ArticlePage.getArticleTitle()
            );

            ArticlePage.addArticlesToMySaved();
        } else {
            ArticlePage.addArticlesToMySaved();
        }

        ArticlePage.closeArticle();

        SearchPage.intSearchInput();

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            SearchPage.typeSearchLine(search_line);
        }

        SearchPage.clickByArticleWithSubstring(article_name_two);

        ArticlePage.waitForTitleElement();

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
