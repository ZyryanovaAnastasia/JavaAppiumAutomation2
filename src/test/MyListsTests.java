package test;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    //Добавление статьи в список и удаление из него
    @Test
    public void testSaveArticleToMyListAndDelete()
    {
        String search_line = "Java";
        String article_name = "Java (programming language)";
        String name_of_folder = "Learning programming";
        SearchPageObject SearchPage = new SearchPageObject(driver);
        ArticlePageObject ArticlePage = new ArticlePageObject(driver);
        NavigationUi NavigationUi = new NavigationUi(driver);
        MyListPageObject MyListPage = new MyListPageObject(driver);

        SearchPage.intSearchInput();
        SearchPage.typeSearchLine(search_line);
        SearchPage.clickByArticleWithSubstring(article_name);

        ArticlePage.waitForTitleElement();
        String article_title = ArticlePage.getArticleTitle();
        ArticlePage.addArticleToNewList(name_of_folder);
        ArticlePage.closeArticle();

        NavigationUi.clickMyLists();

        MyListPage.openFolderByName(name_of_folder);
        MyListPage.swipeByArticleToDelete(article_title);
    }
}
