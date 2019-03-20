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
        String expected_title = "Object-oriented programming lauguage";
        String name_of_folder = "Learning programming";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(expected_title);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitlteElement();
        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = new NavigationUi(driver);
        NavigationUi.clickMyLists();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
}
