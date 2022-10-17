import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected final static String Path = "webapp.maistest.space/login";
    protected static String URL = "";

    @BeforeSuite
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Set the username
        String username = "admin";
        //Set the password
        String password = "admin";
        URL = "https://" +username +":" +password +"@"+ Path;

        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @AfterSuite
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }

}
