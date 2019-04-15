package lib.ui;

import lib.Platform;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    MainPageObject MainPageObject = new MainPageObject(driver);

    protected static String
        TITLE,
        TITLE_ARTICLE_TPL,
        OPTIONS_BTN,
        OPTION_LISTS,
        OPTIONS_ADD_TO_MY_LIST_BTN,
        OPTIONS_REMOVE_FROM_MY_LIST_BTN,
        ADD_TO_MY_LIST_OVERLAY,
        MY_NEW_LIST_NAME_INPUT,
        SPECIAL_LIST_NAME_TPL,
        MY_LIST_OK_BTN,
        CLOSE_ARTICLE_BTN,
        FOOTER_ELEMENT;

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
    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    //Проверка, что заголовок статьи виден
    public boolean titleElementIsDisplayed()
    {
        return MainPageObject.ElementIsDisplayed((TITLE));
    }

    //Ожидание заголовка статьи
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(
                (TITLE),
                "Не удалось найти заголовок статьи",
                15);
    }

    //Нажатие на заголовок статьи
    public void clickArticleName(String article_name)
    {
        String article_name_xpath = getArticleName(article_name);
        this.waitForElementAndClick(
                (article_name_xpath),
                "Не удалось найти и нажать на заголовок статьи" + article_name_xpath,
                15);
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BTN,
                "Ошибка при добавлении статьи в список",
                5
        );
    }

    public void removeArticleFromSavedIfItAdded()
    {
        if (this.isElmPresent(OPTIONS_REMOVE_FROM_MY_LIST_BTN)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BTN,
                    "Ошибка при нажатии на кнопку удаления статьи",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BTN,
                    "Ожидалось, что кнопка добавления присутствует",
                    1
            );
        }
    }

    //Получение атребута text из элемента заголовка статьи
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }

    }

    //Поиск и открытие статьи
    public void searchAndOpenArticle(String search_line, String article_name)
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.intSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_name);
        this.waitForTitleElement();
    }

    //Добавление статьи в новый список
    public void addArticleToNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                (OPTIONS_BTN),
                "Не удалось найти и нажать на иконку дополнительных опций",
                5
        );

        this.waitForElementPresent(
                (OPTION_LISTS),
                "Не удалось найти элемент списка опций",
                5

        );

        this.waitForElementAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BTN),
                "Не удалось нажать на пункт в выпадающем меню для сохранения статью в список",
                5
        );

        this.waitForElementAndClick(
                (ADD_TO_MY_LIST_OVERLAY),
                "Не удалось нажать на кнопку добавления списка",
                5
        );

        this.waitForElementAndClear(
                (MY_NEW_LIST_NAME_INPUT),
                "Не удалось очистить поле ввода названия списка",
                5
        );

        this.waitForElementAndSendKeys(
                (MY_NEW_LIST_NAME_INPUT),
                name_of_folder,
                "Не удалось ввести текст в поле ввода названия списка",
                5
        );

        this.waitForElementAndClick(
                (MY_LIST_OK_BTN),
                "Не удалось нажать на кнопку ОК в диалоговом окне",
                5
        );
    }

    //Нажатие на список с названием name_of_folder
    public void choiceListAddByName(String name_of_list)
    {
        String list_name_xpath = getSpecialNameList(name_of_list);
        waitForElementAndClick(
                (list_name_xpath),
                "Не удалось нажать на список c названием " + name_of_list,
                5
        );
    }

    //Добавление статьи в существующий список
    public void addArticleToSpecialList(String name_of_list)
    {
        this.waitForElementAndClick(
                (OPTIONS_BTN),
                "Не удалось найти и нажать на иконку дополнительных опций",
                5
        );

        this.waitForElementPresent(
                (OPTION_LISTS),
                "Не удалось найти элемент списка опций",
                5

        );

        this.waitForElementAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BTN),
                "Не удалось нажать на пункт в выпадающем меню для сохранения статью в список",
                5
        );

        this.choiceListAddByName(name_of_list);
    }

    //Свайп статьи до футера
    public void swipeUpToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Не удается пролистать статью до конца",
                    40
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMENT,
                    "Не удается пролистать статью до конца",
                    40
            );
        } else {
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT,
                    "Не удается пролистать статью до конца",
                    10,
                    40
            );
        }
    }

    //Закрытие статьи с помощью X
    public void closeArticle()
    {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    (CLOSE_ARTICLE_BTN),
                    "Ошибка при нажатии на Х закрытия статьи",
                    5
            );
        } else {
            System.out.println("Метод closeArticle() не работает платформы " + Platform.getInstance().getPlatformVar());
        }
    }
}
