package Pages;

import APIServices.AllTransactionDetailsAPI;
import APIServices.SumOfInvestment;
import Modals.AllTransactionDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;

public class DashBoard {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class = 'colored-cards-container']/div[1]")
    private WebElement WalletBalance;

    @FindBy(xpath = "//div[@class = 'colored-cards-container']/div[2]")
    private WebElement Portfolio;


    @FindBy(xpath = "//div[@class = 'colored-cards-container']/div[3]")
    private WebElement Performance;

    AllTransactionDetails allTransactionDetails;
    double sumOfPendingInvestment;
    double sumOfSuccessfulInvestment;

    public DashBoard(WebDriver driver) throws JsonProcessingException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        allTransactionDetails = AllTransactionDetailsAPI.getAllTransactionDetails();
        sumOfPendingInvestment = SumOfInvestment.getSumOfPendingInvestment();
        sumOfSuccessfulInvestment = SumOfInvestment.getSumOfSuccessfulInvestment();
    }

    public boolean checkPerformanceCard() {
        Performance.click();

        String Dividends  = driver.findElement(By.xpath("//div[@class = 'info-container']/div[1]/div[2]")).getText();
        String saleProceeds = driver.findElement(By.xpath("//div[@class = 'info-container']/div[2]/div[2][1]")).getText();

       return Dividends.contains(twoDecimalDigits(allTransactionDetails.dividends)) &&
               saleProceeds.contains(twoDecimalDigits(allTransactionDetails.saleProceeds)) &&
               Performance.getText().contains(twoDecimalDigits(allTransactionDetails.saleProceeds + allTransactionDetails.dividends));

    }

        public boolean checkPortfolioCard() {
        Portfolio.click();

        String currentInvestment = driver.findElement(By.xpath("//div[@class = 'info-container']/div[1]/div[2]")).getText();
        String pendingInvestments = driver.findElement(By.xpath("//div[@class = 'info-container']/div[2]/div[2][1]")).getText();
       return currentInvestment.contains(twoDecimalDigits(sumOfSuccessfulInvestment)) &&
               pendingInvestments.contains(twoDecimalDigits(sumOfPendingInvestment)) &&
               Portfolio.getText().contains(twoDecimalDigits(sumOfSuccessfulInvestment));
    }


    public boolean checkWalletBalanceCard() throws JsonProcessingException, InterruptedException {
        WalletBalance.click();
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'info-container']/div[1]/div[2]")));
        Thread.sleep(7000);

        String deposit = driver.findElement(By.xpath("//div[@class = 'info-container']/div[1]/div[2]")).getText();
        String Withdrawals = driver.findElement(By.xpath("//div[@class = 'info-container']/div[2]/div[2][1]")).getText();
        String Dividends = driver.findElement(By.xpath("//div[@class = 'info-container']/div[3]/div[2]")).getText();
        String Rewards = driver.findElement(By.xpath("//div[@class = 'info-container']/div[4]/div[2]")).getText();
        String usedRewards = driver.findElement(By.xpath("//div[@class = 'info-container']/div[5]/div[2]")).getText();
        String saleProceeds = driver.findElement(By.xpath("//div[@class = 'info-container']/div[6]/div[2]")).getText();
        String otherAdjustedAmounts = driver.findElement(By.xpath("//div[@class = 'info-container']/div[7]/div[2]")).getText();

        // System.out.println(twoDecimalDigits(allTransactionDetails.deposits));

        return deposit.contains(twoDecimalDigits(allTransactionDetails.deposits)) &&
                Withdrawals.contains(twoDecimalDigits(allTransactionDetails.withDrawals)) &&
                Dividends.contains(twoDecimalDigits(allTransactionDetails.dividends)) &&
                Rewards.contains(twoDecimalDigits(allTransactionDetails.promotionRewards)) &&
                usedRewards.contains(twoDecimalDigits(allTransactionDetails.rewardsUsed)) &&
                saleProceeds.contains(twoDecimalDigits(allTransactionDetails.saleProceeds)) &&
                otherAdjustedAmounts.contains(twoDecimalDigits(allTransactionDetails.otherAdjustedAmounts)) &&
                WalletBalance.getText().contains(twoDecimalDigits(allTransactionDetails.walletBalance));
    }

    private String twoDecimalDigits(double value) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(value);
    }


    private String removeComa(String str) {
        str = str.replaceAll(",", "");
        return str;
    }


}
