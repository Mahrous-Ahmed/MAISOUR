package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    private static WebDriver driver;

    @FindBy(id = "email")
    private WebElement eMailField;

    @FindBy(id = "password")
    private WebElement PasswordField;

    @FindBy(className = "fa-eye-slash")
    private WebElement EyeIcon;

    @FindBy(xpath = "//label[text() = 'eMail']")
    private WebElement LabelEmail;

    @FindBy(xpath = "//label[text() = 'Password']")
    private WebElement LabelPassword;

    @FindBy(xpath = "//a[text() = 'Forgot password?']")
    private WebElement ForgetPasswordLink;

    @FindBy(xpath = "//button [@type = 'button' and text() = 'Login']")
    private WebElement SignIn_btn;

    @FindBy(xpath = "//a[text() = 'Sign-up']")
    private static   WebElement SignUp_Link;

    @FindBy(xpath = "//div[text() = 'Login to your account']")
    private WebElement TitleOfCard;

    @FindBy(id = "custom_dropdown2")
    private WebElement Language;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getURl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getTitleOfCard() {
        return TitleOfCard.getText();
    }

    public void seteMail(String emailtext) {

        eMailField.sendKeys(emailtext);
    }

    public void setPassword(String PasswordText) {

        PasswordField.sendKeys(PasswordText);
    }

    public void clickOnSignIn() {

        SignIn_btn.click();
    }

    public boolean clickOnEyeIcon_IsVisible() {
        try {
            EyeIcon.click();
            String Visible = PasswordField.getAttribute("type");
            return Visible.equals("text");
        } catch (Exception e) {
            return false;
        }

    }

    public boolean validateEmail() {
        return driver.findElement(By.xpath("//div[text() = 'Please enter a valid email']")).isDisplayed();
    }

    public boolean validatePassword() {
        return driver.findElement(By.xpath("//div[text() = 'Password required']")).isDisplayed();
    }

    public boolean ErrorMessage() {
        String Message = driver.findElement(By.xpath("//div[@role = 'alert']")).getText();
        return Message.equals("Bad credentials");
    }

    public String getEmailLabel() {

        return LabelEmail.getText();
    }

    public String getPasswordLabel() {

        return LabelPassword.getText();
    }

    public ForgetPasswordPage clickOnForgetPassword() {
        ForgetPasswordLink.click();
        return new ForgetPasswordPage(driver);
    }

    public static SignUpPage clickOnSignUp() {
        SignUp_Link.click();
        return new SignUpPage(driver);
    }

    public void clearFields() {
        PasswordField.clear();
        eMailField.clear();
    }


}
