package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    /* TEMPLATES METHOOS */
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_name)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_name);
    }
    /* TEMPLATES METHOOS */

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    //Открытие списка с названием name_of_folser
    public void openFolderByName(String name_of_folser)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folser);
        waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Не удалось перейти в список c названием" + name_of_folser,
                5
        );
    };

    //Поиск в списке статьи с заголовком article_title
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "В списке не удалось найти статью с заголовком " + article_title,
                15
        );
    }

    //Проверка, что в списке отсутствует статья с заголовком article_title
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
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
                By.xpath(article_xpath),
                "В списке не удалось найти статью с заголовком " + article_title + " для удаления"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }
}