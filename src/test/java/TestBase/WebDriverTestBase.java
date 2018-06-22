package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class WebDriverTestBase {
    protected static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eich\\IdeaProjects\\Hillel\\drivers\\chromedriver.exe");
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized", "--incognito"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterTest(alwaysRun = true)
    public void finish() {
        driver.close();
    }

}