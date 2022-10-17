import Pages.LoginPage;
import Pages.SignUpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {
    SignUpPage signUpPage;
    @BeforeClass
    public void Initialization(){
        LoginPage loginPage = new LoginPage(driver);
        signUpPage = loginPage.clickOnSignUp();
    }
    @BeforeMethod
    public void refresh(){
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void t() throws InterruptedException {
        signUpPage.selectBirthdate(1999,7);
    }

    @Test(priority = 2)
    public void t2() throws InterruptedException {
        signUpPage.selectCountryAndPhone("01146352689");
    }

    @Test(priority = 3)
    public void t3() throws InterruptedException {
        signUpPage.clickOnEyeIcon_IsVisible();
    }

    @Test(priority = 4)
    public void t4() throws InterruptedException {
        signUpPage.validateTermAndCondition();
        signUpPage.validatePrivacyPolicy();

    }



}
