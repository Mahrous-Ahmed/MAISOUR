package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgetPasswordPage {
    private static WebDriver driver;

    @FindBy(xpath = "//div[text() = 'Forgot password?']")
    private WebElement CardTitle;

    @FindBy(xpath = "//label[text() = 'eMail']")
    private WebElement EmailLabel;

    @FindBy(xpath = "//button[@type = 'button' and text() = 'SUBMIT']")
    private WebElement SubmitButton;

    @FindBy(xpath = "//a[text() = 'Back to login page']")
    private static WebElement BackToLoginLink;

    @FindBy(id = "email")
    private WebElement eMailField;

    public ForgetPasswordPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getURl() {

        return driver.getCurrentUrl();
    }


    public String getCardTitle() {
        return CardTitle.getText();
    }

    public String getLabelText() {
        return EmailLabel.getText();
    }

    public LoginPage clickOnBackToLoginPage() {
        BackToLoginLink.click();
        return new LoginPage(driver);
    }

    public void clickOnSubmit() {
        SubmitButton.click();
    }

    public boolean validateEmail() {
        return driver.findElement(By.xpath("//div[text() = 'Please enter a valid email']")).isDisplayed();
    }

    public boolean ErrorMessage() {
        String Message = driver.findElement(By.xpath("//div[@role = 'alert']")).getText();
        return Message.equals("Password reset requested for non existing mail");
    }

    public void seteMail(String emailtext) {

        eMailField.sendKeys(emailtext);
    }

    public void clearFields() {

        eMailField.clear();
    }



}
