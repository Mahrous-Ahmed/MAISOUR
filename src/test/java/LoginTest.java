import APIServices.APILogin;
import Pages.ForgetPasswordPage;
import Pages.LoginPage;
import Pages.SignUpPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    boolean firstTime = true;

    @BeforeClass
    public void Initialization() {
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void Refresh() {
        if(firstTime){
            firstTime = false;
            return;
        }
        driver.navigate().to(URL);
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void validateDesign() {
        //Verify URL
        Assert.assertEquals(loginPage.getURl(), "https://admin:admin@webapp.maistest.space/login");
        //Verify Title Of The Card
        Assert.assertEquals(loginPage.getTitleOfCard(), "Login to your account");
        //Verify Labels
        Assert.assertEquals(loginPage.getEmailLabel(), "eMail");
        Assert.assertEquals(loginPage.getPasswordLabel(), "Password");
        //validate EyeIcon in case of the state is Visible
        loginPage.setPassword("123");
        Assert.assertTrue(loginPage.clickOnEyeIcon_IsVisible());
        //validate EyeIcon in case of the state is not visible
        Assert.assertFalse(loginPage.clickOnEyeIcon_IsVisible());

        //LoginTitle
        System.out.println(loginPage.getTitle());

    }


    @Test(priority = 2)
    public void SignInWithEmptyData() {
        //SignIn with empty Pass & Email
        loginPage.clickOnSignIn();
        Assert.assertTrue(loginPage.validateEmail());
        Assert.assertTrue(loginPage.validatePassword());
        //SignIn with empty email
        loginPage.setPassword("123456");
        Assert.assertTrue(loginPage.validateEmail());
        loginPage.clearFields();
        //SignIn with empty password
        loginPage.seteMail("mahrous@gmail.com");
        Assert.assertTrue(loginPage.validatePassword());
    }


    @Test(priority = 3)
    public void SignInWithInValidData() throws InterruptedException {
        //SignIn with invalid Email TestData(Without @ / Without ./ )
        loginPage.seteMail("mahrous.com");
        Assert.assertTrue(loginPage.validateEmail());
        loginPage.clearFields();
        loginPage.seteMail("mahrous@com");
        Assert.assertTrue(loginPage.validateEmail());
        loginPage.clearFields();
        //Invalid Name and Password
        loginPage.seteMail("mahrous@gamil.com");
        loginPage.setPassword("123456");
        loginPage.clickOnSignIn();
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.ErrorMessage());
    }

    @Test(priority = 4)
    public void validateForgetPasswordLink() throws InterruptedException {
       ForgetPasswordPage forgetPasswordPage = loginPage.clickOnForgetPassword();
       Assert.assertEquals(forgetPasswordPage.getURl() , "https://admin:admin@webapp.maistest.space/forgot-password");
    }

    @Test(priority = 5)
    public void validateSignUpLink() throws InterruptedException {
        SignUpPage signUpPage = loginPage.clickOnSignUp( );
        Assert.assertEquals(signUpPage.getURl() , "https://admin:admin@webapp.maistest.space/register");
    }


    @Test(priority = 6)
    public void LoginWithValidData() throws InterruptedException, JsonProcessingException {
        ClassHelper.LoginWithValidData(driver);
        APILogin.getToken("hos.mahros@gmail.com","Mahrous&&99");
    }










}
