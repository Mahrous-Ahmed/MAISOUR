import Pages.DashBoard;
import Pages.HomePage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    DashBoard dashBoard;
    @BeforeClass
    public void initialization() throws InterruptedException, JsonProcessingException {
        ClassHelper.LoginWithValidData(driver);
        HomePage homePage = new HomePage(driver);
        dashBoard = homePage.clickOnDashboard();
    }

    @Test(priority = 1)
    public void validateWalletBalance() throws JsonProcessingException, InterruptedException {
        Assert.assertTrue(dashBoard.checkWalletBalanceCard());
    }


    @Test(priority = 2)
    public void validatePortfolio() throws JsonProcessingException, InterruptedException {
        Assert.assertTrue(dashBoard.checkPortfolioCard());
    }

    @Test(priority = 3)
    public void validatePerformance() throws JsonProcessingException, InterruptedException {
        Assert.assertTrue(dashBoard.checkPerformanceCard());
    }

}
