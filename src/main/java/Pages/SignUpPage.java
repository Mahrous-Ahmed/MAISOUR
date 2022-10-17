package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SignUpPage {
    private WebDriver driver;
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//div[@class = 'form-control']")
    private WebElement birthdateDropdown;

    @FindBy(className = "iti__flag-container")
    private WebElement countryPhoneDropDown;

    @FindBy(id = "phone__number")
    private WebElement phoneField;

    @FindBy(xpath = "//i[@class = 'fa fw fa-eye-slash']")
    private List<WebElement> eyeIcon;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "promoCode")
    private WebElement promoCodeField;

    @FindBy(id = "create_password")
    private WebElement CreatePasswordField;

    @FindBy(id = "confirm_password")
    private WebElement confirmPasswordField;

    @FindBy(name = "privacy")
    private WebElement privacy;

    @FindBy(name = "terms")
    private WebElement terms;

    public SignUpPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void validateTermAndCondition() throws InterruptedException {
        driver.findElement(By.xpath("//button[@type = 'button' and text() = 'Terms and Conditions']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='custom-modal-close-lg' ]")).click();

    }

    public void validatePrivacyPolicy() throws InterruptedException {
        driver.findElement(By.xpath("//button[@type = 'button' and text() = 'Privacy policy']")).click();
        Thread.sleep(2000);
        driver.findElements(By.xpath("//div[@class='custom-modal-close-lg' ]")).get(1).click();

    }


    public boolean clickOnEyeIcon_IsVisible() {
        System.out.println(eyeIcon.size());
        eyeIcon.get(0).click();
        String Visible = CreatePasswordField.getAttribute("type");
        boolean a  = Visible.equals("text");

        eyeIcon.get(1).click();
        Visible = CreatePasswordField.getAttribute("type");
        return Visible.equals("text") && a;
    }

    public void selectCountryAndPhone(String phonenumber) {
        countryPhoneDropDown.click();
        driver.findElement(By.xpath("//span[@class = 'iti__country-name' and text() = 'Egypt (\u202Bمصر\u202C\u200E)' ]")).click();
        phoneField.sendKeys(phonenumber);
    }

    public void selectBirthdate(int year, int day) throws InterruptedException {
        driver.findElement(By.xpath("//i[@class = 'far fa-fw fa-calendar-alt']")).click();
        Thread.sleep(2000);
        Select select = new Select(driver.findElement(By.className("react-datepicker__year-select")));
        select.selectByVisibleText("" + year);
        Thread.sleep(2000);

        List<WebElement> elements = driver.findElements(By.xpath("//div[@role = 'button']"));
        for (WebElement e : elements) {
            if (e.getText().equals(day))
                e.click();
        }
    }

    public boolean validatePhoneNumber() {
        return driver.findElement(By.xpath("//div[text() = 'please enter a valid phone number']")).isDisplayed();
    }


    public String getURl() {
        return driver.getCurrentUrl();
    }


}
