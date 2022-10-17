import Pages.HomePage;
import Pages.LoginPage;
import Pages.PropertyPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertyPageTest extends BaseTest{
    PropertyPage propertyPage;
    @BeforeClass
    public void Initialization() throws InterruptedException, JsonProcessingException {
        ClassHelper.LoginWithValidData(driver);
        HomePage homePage = new HomePage(driver);
        propertyPage =  homePage.selectProperty(1);
    }

    @Test(priority = 1)
    public void ValidateImages(){
        Assert.assertTrue(propertyPage.CheckImageLinksAndDefaultActiveImage());
    }

    @Test(priority = 2)
    public void ValidateSelectImages(){
        Assert.assertTrue(propertyPage.selectImage(2));
    }

    @Test(priority = 3)
    public void ValidateInfo() throws JsonProcessingException {
        Assert.assertTrue(propertyPage.CheckInfoData());
        propertyPage.addInvest(80000);
        propertyPage.propertyKeyInfo();
        propertyPage.Netrentalyield();
    }







}
