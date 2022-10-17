import APIServices.APIShowAllProperty;
import Modals.AllProperties;
import Pages.HomePage;
import Pages.LoginPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest{
    HomePage homePage;

    @BeforeClass
    public void Initialization() throws InterruptedException, JsonProcessingException {
        ClassHelper.LoginWithValidData(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void validatedPropertyOnScreen() throws InterruptedException, JsonProcessingException {
        AllProperties properties = APIShowAllProperty.getAllProperties();
        Assert.assertEquals(properties.properties.size(),homePage.propertyOnScreen());

    }

    @Test(priority = 2)
    public void validatedPropertyDetailsOnScreen() throws InterruptedException, JsonProcessingException {
        System.out.println(homePage.checkAllPropertyDetailsOnScreen());
    }
    @Test(priority = 2)
    public void SelectLive() throws InterruptedException, JsonProcessingException {
        homePage.validateSelectStatus("Live");
    }


    @Test(priority = 3)
    public void SelectFunded() throws InterruptedException, JsonProcessingException {
        homePage.validateSelectStatus("Funded");
    }


    @Test(priority = 4)
    public void SelectComingSoon() throws InterruptedException, JsonProcessingException {
        homePage.validateSelectStatus("Coming soon");
    }
}
