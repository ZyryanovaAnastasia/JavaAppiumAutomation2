package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(
                (STEP_LEARN_MORE_LINK),
                "error massage",
                10
        );
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(
                (STEP_NEW_WAYS_TO_EXPLORE_TEXT),
                "error massage",
                10
        );
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(
                (STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK),
                "error massage",
                10
        );
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(
                (STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK),
                "error massage",
                10
        );
    }

    public void clickNextButton()
    {
        this.waitForElementPresent(
                (NEXT_LINK),
                "error massage",
                10
        );
    }

    public void clickGetStartedButton()
    {
        this.waitForElementPresent(
                (GET_STARTED_BUTTON),
                "error massage",
                10
        );
    }
}
