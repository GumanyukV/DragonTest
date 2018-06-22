package DragonClasses;

import TestBase.DragonVars;
import TestBase.WebDriverTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class WikiPage extends WebDriverTestBase implements DragonVars{

    private String findActorXPATHpattern = "//h2/span[@id='" +
            DragonVars.actorFlag + "']/../following-sibling::ul[1]/li/a";
    private String findMovieXPATHstart = "//span[@id='";
    private String findMovieXPATHend = "']/../following-sibling::*//a";

    public void openMoviePage(){
        driver.get(DragonVars.website + DragonVars.mainPage);
    }

    public void openActorPage(String actorUrl){
        String tempTitle = driver.getTitle();
        driver.get(actorUrl);
        waitForPageToLoad();
        Assert.assertFalse(driver.getTitle().equals(tempTitle));
    }

    private ArrayList<String> getActorURLs(){
        ArrayList<String> urls = new ArrayList<>();

        List<WebElement> tempList = driver.findElements(By.xpath(findActorXPATHpattern));
        Assert.assertTrue(tempList.size() > 0);

        for (WebElement t: tempList) {
            if(!t.getAttribute("href").isEmpty())
                urls.add(t.getAttribute("href")); }
        Assert.assertTrue(urls.size() > 0);

        return urls;
    }

    private String checkCorrectMovieFlag(){
        String tempFlag = "";
        for(String flag : DragonVars.filmographyFlag){
            if(driver.findElements(By.xpath("//span[@id='" + flag + "']")).size() > 0)
                tempFlag = flag;
        }
        Assert.assertFalse(tempFlag.isEmpty(), "All Movie Flags are wrong!");
        return tempFlag;
    }

    private void checkMovieInTheList(){
        String movieFlag = checkCorrectMovieFlag();

        List<WebElement> movies = driver.findElements(By.xpath(findMovieXPATHstart +
        movieFlag + findMovieXPATHend));
        Assert.assertTrue(movies.size() > 0);

        int i = 0;
        for(WebElement film : movies){
            if(film.getAttribute("href").contains(DragonVars.mainPage)){
                i++;
                return;
            }
        }
        Assert.assertFalse(i==0);
    }

    public void checkActorMovieCorrelation(){
        ArrayList<String> actors = getActorURLs();
        for(String star : actors){
            openActorPage(star);
            checkMovieInTheList();
        }
    }

    private static void waitForPageToLoad() {
        ExpectedCondition<Boolean> pageLoad = driver1 -> ((JavascriptExecutor) driver1)
                .executeScript("return document.readyState").equals("complete");
    }
}
