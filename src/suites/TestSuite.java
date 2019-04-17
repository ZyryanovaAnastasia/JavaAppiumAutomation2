package suites;

import org.junit.runners.Suite;
import test.*;

@Suite.SuiteClasses({
        ArticleTest.class,
        ChangeAppConditionTests.class,
        GetStartedTest.class,
        MyListsTests.class,
        SearchTests.class
})

public class TestSuite {
}
