package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BTN = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";

    //Инициализация драйвера
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHOOS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATES METHOOS */

    //Нажатие на строку поиска на главном экране
    public void intSearchInput()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "На странице нет элемента",
                5
        );
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Не удалось найти и кликнуть на элемент поиска на главном экране",
                5
        );
    }

    //Проверка, что элемент отмены поиска Х присутствует на странице
    public void  waitForCancelBtnToAppear()
    {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BTN),
                "Не удалось  найти Х отмены поиска",
                5
        );
    }

    //Проверка, что элемент отмены поиска Х отсутствует на странице
    public void  waitForCancelBtnToDisappear()
    {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BTN),
                "Ожидалось, что Х отмены поиска отсутствует на странице",
                5
        );
    }

    //Нажатие на элемент отмены поиска Х
    public void clickCanselSearch()
    {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BTN),
                "Не удалось нажать на Х отмены поиска",
                5
        );
    }

    //Ввод значения в поле поиска статей
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(
                By.id(SEARCH_INPUT),
                search_line,
                "Не удалось ввести текст в поле поиска",
                5
        );
    }

    //Ожидание результата поиска
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Не удалось найти результат поиска c названием " + substring,
                5
        );
    }

    //Нажатие на элемент в результате поиска
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Не удалось найти и нажать на строку c названием " + substring,
                10
        );
    }

    //Ожидание списка статей в поиска
    public void waitForListSerach()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Ошибка при получении результата по поиску ",
                15
        );
    }

    //Получение количества найденных статей
    public int getAmountOfFoundArticles()
    {
        return this.getAmountOfElements(
                By.xpath(SEARCH_RESULT_ELEMENT)
        );

    }

    //Ожидание пустого результата по поиску статей
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Ошибка при получении пустого результата по поиску ",
                15
        );

    }

    //Проверка, что результат поиска пустой
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Ожидалось, что результат поиска будет пустым"
        );
    }

    //Получение текста из элемента
    public String getTextInSearchInput()
    {
        this.waitForElementPresent(
                By.id(SEARCH_INPUT),
                "На странице нет элемента строки поиска",
                10
        );
        return this.waitForElementAndGetAttribute(
                By.id(SEARCH_INPUT),
                "text",
                "Не удалось получить указанный атрибут элемента" ,
                5
        );
    }
}
