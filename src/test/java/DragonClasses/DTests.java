package DragonClasses;

import TestBase.DragonVars;
import TestBase.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DTests extends WebDriverTestBase{

    private WikiPage wikiPage;

    @BeforeClass
    public void initPages() {
        wikiPage = PageFactory.initElements(driver, WikiPage.class);
    }

    @Test
    public void actorMovieCorrelationTest(){
        wikiPage.openMoviePage();
        wikiPage.checkActorMovieCorrelation();
    }
}
