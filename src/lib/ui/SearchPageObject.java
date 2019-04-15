package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BTN,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_BY_TITLE_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ALL_TITLE_ELM;

    //Инициализация драйвера
    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElementByTitle(String title)
    {
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{TITLE}", title);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description)
    {
        String resultSearchByTitle = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title);
        return resultSearchByTitle.replace("{DESCRIPTION}", description);

    }
    /* TEMPLATES METHODS */

    //Нажатие на строку поиска на главном экране
    public void intSearchInput()
    {
        this.waitForElementPresent(
                (SEARCH_INIT_ELEMENT),
                "На странице нет элемента",
                5
        );
        this.waitForElementAndClick(
                (SEARCH_INIT_ELEMENT),
                "Не удалось найти и кликнуть на элемент поиска на главном экране",
                5
        );
    }

    //Получение текста из элемента
    public String getTextInSearchInput()
    {
        this.waitForElementPresent(
                (SEARCH_INPUT),
                "На странице нет элемента строки поиска",
                10
        );
        return this.waitForElementAndGetAttribute(
                (SEARCH_INPUT),
                "text",
                "Не удалось получить указанный атрибут элемента" ,
                5
        );
    }

    //Проверка, что элемент отмены поиска Х присутствует на странице
    public void  waitForCancelBtnToAppear()
    {
        this.waitForElementPresent(
                (SEARCH_CANCEL_BTN),
                "Не удалось  найти Х отмены поиска",
                5
        );
    }

    //Проверка, что элемент отмены поиска Х отсутствует на странице
    public void  waitForCancelBtnToDisappear()
    {
        this.waitForElementNotPresent(
                (SEARCH_CANCEL_BTN),
                "Ожидалось, что Х отмены поиска отсутствует на странице",
                5
        );
    }

    //Нажатие на элемент отмены поиска Х
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(
                (SEARCH_CANCEL_BTN),
                "Не удалось нажать на Х отмены поиска",
                5
        );
    }

    //Ввод значения в поле поиска статей
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(
                (SEARCH_INPUT),
                search_line,
                "Не удалось ввести текст в поле поиска",
                5
        );
    }

    //Ожидание списка статей в поиске
    public void waitForListSearch()
    {
        this.waitForElementPresent(
                (SEARCH_RESULT_ELEMENT),
                "Ошибка при получении результата по поиску ",
                15
        );
    }

    //Ожидание статьи по заголовку
    public void waitForElementByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementPresent(
                (search_result_xpath),
                "Не удалось найти результат поиска c названием " + substring,
                10
        );
    }

    //Ожидание статьи по заголовку и описанию
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(
                (search_result_xpath),
                "Не удалось найти элемент с заголовком " + title + " и описанием " + description,
                10
        );
    }

    //Нажатие на элемент в результате поиска
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementAndClick(
                (search_result_xpath),
                "Не удалось найти и нажать на строку c названием " + substring,
                10
        );
    }

    //Получение количества найденных статей
    public int getAmountOfFoundArticles()
    {
        return this.getAmountOfElements(
                (SEARCH_RESULT_ELEMENT)
        );
    }

    //Проверка всех заголовков статей в результате поиска
    public void checkAllTitleArticle (String search_title)
    {
        List<WebElement> elements = getAllElements((SEARCH_RESULT_ALL_TITLE_ELM));

        for(WebElement element: elements)
        {
            Assert.assertTrue(
                    "Ожидалось, что в каждом заголовке статьи присутствует " + search_title,
                    element.getText().contains(search_title)
            );
        };
    }

    //Ожидание пустого результата по поиску статей
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                (SEARCH_EMPTY_RESULT_ELEMENT),
                "Ошибка при получении пустого результата по поиску ",
                15
        );
    }

    public void waitElementNoResultsFound()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Ожидалось, что на странице присутствует заглушка 'No results found'",
                5
        );
    }
}
