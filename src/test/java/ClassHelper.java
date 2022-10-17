import Pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class ClassHelper {
     public static LoginPage loginPage;

    public static void LoginWithValidData(WebDriver driver) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.seteMail("mahrous2017@mobidevlabs.com");
        loginPage.setPassword("Mahrous&&99");
        loginPage.clickOnSignIn();
        Thread.sleep(2000);
    }

}
