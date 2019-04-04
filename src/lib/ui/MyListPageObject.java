package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL;

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_name)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_name);
    }
    /* TEMPLATES METHODS */

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    //Открытие списка с названием name_of_folder
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        waitForElementAndClick(
                (folder_name_xpath),
                "Не удалось перейти в список c названием " + name_of_folder,
                5
        );
    };

    //Поиск в списке статьи с заголовком article_title
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                (article_xpath),
                "В списке не удалось найти статью с заголовком " + article_title,
                15
        );
    }

    //Проверка, что в списке отсутствует статья с заголовком article_title
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                (article_xpath),
                "В списке присутствует удаленная статья с заголовком " + article_title,
                15
        );
    }

    //Удаление статьи из списка и проверка, что она удалена (свайп влево)
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                (article_xpath),
                "В списке не удалось найти статью с заголовком " + article_title + " для удаления"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    article_xpath,
                    "Ошибка при сохранении статьи"
            );

        }
            this.waitForArticleToDisappearByTitle(article_title);

    }
}
