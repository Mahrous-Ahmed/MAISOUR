import Pages.ForgetPasswordPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgetPasswordTest extends BaseTest {
    ForgetPasswordPage forgetPasswordPage;

    @BeforeClass
    public void Initialization(){
    LoginPage loginPage = new LoginPage(driver);
    forgetPasswordPage = loginPage.clickOnForgetPassword();

    }
    @BeforeMethod
    public void refresh(){
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void validateDesign(){
        Assert.assertEquals(forgetPasswordPage.getURl() , "https://admin:admin@webapp.maistest.space/forgot-password");
        Assert.assertEquals(forgetPasswordPage.getCardTitle(),"Forgot password?");
        Assert.assertEquals(forgetPasswordPage.getLabelText(),"eMail");
    }

    @Test(priority = 2)
    public void forgetPasswordWithEmptyEmail(){
        forgetPasswordPage.seteMail("");
        forgetPasswordPage.clickOnSubmit();
        Assert.assertTrue(forgetPasswordPage.validateEmail());
    }

    @Test(priority = 3)
    public void forgetPasswordWithInvalidEmail() throws InterruptedException {
        forgetPasswordPage.seteMail("aaaa@gmailcom");
        forgetPasswordPage.clickOnSubmit();
        Assert.assertTrue(forgetPasswordPage.validateEmail());

        forgetPasswordPage.clearFields();

        forgetPasswordPage.seteMail("aaaagmail.com");
        forgetPasswordPage.clickOnSubmit();
        Assert.assertTrue(forgetPasswordPage.validateEmail());

        //In case Non exist
        forgetPasswordPage.seteMail("Test@MADLAB.com");
        forgetPasswordPage.clickOnSubmit();
        Thread.sleep(2000);
        Assert.assertTrue(forgetPasswordPage.ErrorMessage());
    }

    @Test(priority = 3)
    public void forgetPasswordWithvalidEmail() throws InterruptedException {
        String email = "toqa.shedeed@gmail.com";
        forgetPasswordPage.seteMail(email);
        forgetPasswordPage.clickOnSubmit();
        Thread.sleep(2000);
        String Message = driver.findElement(By.xpath("//div[@role = 'alert']")).getText();
        Assert.assertEquals(Message,"Please check your email");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'title']")).getText(),"Check your email");
        Assert.assertEquals(driver.findElement(By.className("email-sent-to")).getText(),"We have sent password recovery instructions to your email"+email);
        Assert.assertTrue(driver.findElement(By.id("user__email")).getAttribute("class").equals("green-color"));

    }


    @Test(priority = 4)
    public void clickONBackToLoginPage() throws InterruptedException {
        LoginPage page = forgetPasswordPage.clickOnBackToLoginPage();
        Assert.assertEquals(page.getURl(),"https://admin:admin@webapp.maistest.space/login");
    }





}
