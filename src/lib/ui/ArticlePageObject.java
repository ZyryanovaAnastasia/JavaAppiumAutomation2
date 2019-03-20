package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BTN = "//android.widget.ImageView[@content_desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BTN = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboading_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BTN = "//*[@text='OK']",
        CLOSE_ARTICLE_BTN = "//android.widget.ImageButton[@content_desc='Navigate up']";


    //Инициализация драйвера
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    //Ожидание заголовка статьи
    public WebElement waitForTitlteElement() {
        return this.waitForElementPresent(
                By.id(TITLE),
                "Не удалось найти заголовок статьи",
                15);
    }

    //Получение атребута text из элемента заголовка статьи
    public String getArticleTitle() {
        WebElement title_element = waitForTitlteElement();
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
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BTN),
                "Не удалось найти и нажать на иконку дополнительных опций",
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
                By.id(MY_LIST_NAME_INPUT),
                "Не удалось очистить поле ввода названия списка",
                5
        );


        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
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

    //Закрытие статьи с помощью х
    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BTN),
                "Ошибка при нажатии на Х закрытия статьи",
                5
        );
    }
}
