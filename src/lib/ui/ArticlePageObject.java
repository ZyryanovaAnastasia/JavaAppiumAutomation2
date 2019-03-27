package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    MainPageObject MainPageObject = new MainPageObject(driver);

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        TITLE_ARTICLE_TPL = "//*[@text='{ARTICLE_NAME}']" ,
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BTN = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BTN = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_NEW_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        SPECIAL_LIST_NAME_TPL = "//android.widget.TextView[@text='{SPECIAL_NAME_LIST}']",
        MY_LIST_OK_BTN = "//*[@text='OK']",
        CLOSE_ARTICLE_BTN = "//android.widget.ImageButton[@content-desc='Navigate up']";

    /* TEMPLATES METHODS */
    private static String getSpecialNameList(String name_of_list)
    {
        return SPECIAL_LIST_NAME_TPL.replace("{SPECIAL_NAME_LIST}", name_of_list);
    }

    private static String getArticleName(String article_name)
    {
        return TITLE_ARTICLE_TPL.replace("{ARTICLE_NAME}", article_name);
    }
    /* TEMPLATES METHODS */

    //Инициализация драйвера
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    //Ожидание заголовка статьи
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                By.id(TITLE),
                "Не удалось найти заголовок статьи",
                15);
    }

    //Проверка, что заголовок статьи виден
    public boolean titleElementIsDisplayed()
    {
        return MainPageObject.ElementIsDisplayed(By.id(TITLE));
    }

    //Нажатие на заголовок статьи
    public void clickArticleName(String article_name) {
        String article_name_xpath = getArticleName(article_name);
        this.waitForElementAndClick(
                By.xpath(article_name_xpath),
                "Не удалось найти и нажать на заголовок статьи" + article_name_xpath,
                15);
    }

    //Поиск и открытие статьи
    public void searchAndOpenArticle(String search_line, String article_name)
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_name);
        this.waitForTitleElement();
    }

    //Нажатие на список с названием name_of_folder
    public void choiceListAddByName(String name_of_list)
    {
        String list_name_xpath = getSpecialNameList(name_of_list);
        waitForElementAndClick(
                By.xpath(list_name_xpath),
                "Не удалось нажать на список c названием " + name_of_list,
                5
        );
    };

    //Получение атребута text из элемента заголовка статьи
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    //Свайп статьи до футера
    public void swipeUpToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Не удается пролистать статью до конца",
                20
        );
    }

    //Добавление статьи в новый список
    public void addArticleToNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BTN),
                "Не удалось найти и нажать на иконку дополнительных опций",
                5
        );

        this.waitForElementPresent(
                By.className("android.widget.ListView"),
                "Не удалось найти элемент списка опций",
                5

        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BTN),
                "Не удалось нажать на пункт в выпадающем меню для сохранения статью в список",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Не удалось нажать на кнопку добавления списка",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_NEW_LIST_NAME_INPUT),
                "Не удалось очистить поле ввода названия списка",
                5
        );


        this.waitForElementAndSendKeys(
                By.id(MY_NEW_LIST_NAME_INPUT),
                name_of_folder,
                "Не удалось ввести текст в поле ввода названия списка",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BTN),
                "Не удалось нажать на кнопку ОК в диалоговом окне",
                5
        );
    }

    //Добавление статьи в существующий список
    public void addArticleToSpecialList(String name_of_list)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BTN),
                "Не удалось найти и нажать на иконку дополнительных опций",
                5
        );

        this.waitForElementPresent(
                By.className("android.widget.ListView"),
                "Не удалось найти элемент списка опций",
                5

        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BTN),
                "Не удалось нажать на пункт в выпадающем меню для сохранения статью в список",
                5
        );

        this.choiceListAddByName(name_of_list);
    }

    //Закрытие статьи с помощью X
    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BTN),
                "Ошибка при нажатии на Х закрытия статьи",
                5
        );
    }
}
