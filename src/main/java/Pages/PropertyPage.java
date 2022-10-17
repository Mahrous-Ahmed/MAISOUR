package Pages;

import APIServices.APIPropertyDetails;
import Modals.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PropertyPage {

    private WebDriver driver;

    @FindBy(className = "breadcrumb-item")
    private WebElement breadcrumb;

    @FindBy(className = "info-img")
    private List<WebElement> images;

    @FindBy(id = "info-main-image")
    private WebElement activeImg;

    @FindBy(className = "info-code")
    private WebElement code;

    @FindBy(className = "info-title")
    private WebElement title;

    @FindBy(className = "info-location")
    private WebElement location;

    @FindBy(className = "content-css")
    private WebElement content;

    @FindBy(className = "info-item-action")
    private List<WebElement> investNowButtons;

    @FindBy(name = "invest_amount")
    private WebElement amount;

    @FindBy(name = "invest_annual")
    private WebElement invest_annual;


    @FindBy(name = "invest_net_dividend")
    private WebElement invest_net_dividend;

    @FindBy(name = "invest_period")
    private WebElement invest_period;


    double netDiv;
    private Property selectedProperty = null;
    int position = 0;
    Map<String,String> m ;
    public PropertyPage(WebDriver driver, Property property, double netDividend, int i) throws JsonProcessingException {
        this.driver = driver;
        selectedProperty = property;
        PageFactory.initElements(driver, this);
        netDiv = netDividend;
        position = i;
        m =   APIPropertyDetails.getDetails(position);
    }

    private boolean defaultActiveImage() {
        //index should equal 0
        return activeImg.getAttribute("src").contains(selectedProperty.propertyImages.get(0).imageUrl);
    }

    public boolean selectImage(int number) {
        //index should equal 0
        try {
            images.get(number - 1).click();
            String text = images.get(number - 1).getAttribute("style");
            text = text.substring(23, text.length() - 3);
            return text.contains(activeImg.getAttribute("src"));
        } catch (Exception e) {
            System.out.println("Select image from " + images.size() + " images");
            return false;
        }
    }


    private boolean sizeIsEqual() {
        return images.size() == selectedProperty.propertyImages.size();
    }


    public boolean CheckImageLinksAndDefaultActiveImage() {
        //Same size in case of validate scenario
        if (sizeIsEqual() == false || defaultActiveImage() == false)
            return false;
        for (int i = 0; i < images.size(); i++) {
            String text = images.get(i).getAttribute("style");
            //text = text.substring(23,text.length()-3);
            if (!(text.contains(selectedProperty.propertyImages.get(i).imageUrl)))
                return false;
        }
        return true;
    }

    public boolean CheckInfoData() {
        return CheckCode() && checkTitle() && checkLocation();
    }

    private boolean CheckCode() {

        return code.getText().contains(selectedProperty.code);
    }

    private boolean checkTitle() {

        return title.getText().contains(selectedProperty.titleInEnglish);
    }

    private boolean checkLocation() {
        String loc = location.getText();
        String[] sublocation = loc.split("/");

        return sublocation[0].contains(selectedProperty.countryInEnglish) &&
                sublocation[1].contains(selectedProperty.cityInEnglish);
    }


    private boolean checkDisableFields() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String NetDividend = df.format(netDiv);

        if (Boolean.valueOf(invest_annual.getAttribute("disabled")) == false || (Double.valueOf(invest_annual.getAttribute("value")).toString().equals(selectedProperty.expectedAnnualAppreciation)) == false) {

            return false;
        } else if (Boolean.valueOf(invest_net_dividend.getAttribute("disabled")) == false || (Double.valueOf(invest_net_dividend.getAttribute("value")).toString().equals(NetDividend)) == false) {
            return false;
        } else if (Boolean.valueOf(invest_period.getAttribute("disabled")) == false || (invest_period.getAttribute("value").equals("5")) == false) {
            return false;
        } else
            return true;


    }

    public boolean addInvest(double num) {
        if (checkDisableFields()) {
            double minValue = Double.valueOf(amount.getAttribute("min"));
            if (num < minValue) {
                System.out.println("NO Change in graph");
                return false;
            }
            amount.clear();
            amount.sendKeys("" + num);

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);

            double Net_dividends = Double.valueOf(df.format(((netDiv / 100) * num) * 5).replaceAll(",", ""));
            double Gross_capital_appreciation = Double.valueOf(df.format((Math.pow(Double.valueOf(selectedProperty.expectedAnnualAppreciation) / 100.0 + 1, 5) * num) - num).replaceAll(",", ""));
            double Total_expected_after_5_years = Net_dividends + Gross_capital_appreciation + num;
            double ROI = Net_dividends + Gross_capital_appreciation;
            double Annualized_return = ((ROI / num) * 100) / 5;

            String txt1 = driver.findElements(By.xpath("//div[@class = 'val']")).get(0).getText();
            txt1 = txt1.replaceAll(",", "");

            String txt2 = driver.findElements(By.xpath("//div[@class = 'val']")).get(1).getText();
            txt2 = txt2.replaceAll(",", "");

            String txt3 = driver.findElements(By.xpath("//div[@class = 'val']")).get(2).getText();
            txt3 = txt3.replaceAll(",", "");

            String txt4 = driver.findElements(By.xpath("//div[@class = 'val']")).get(3).getText();
            txt4 = txt4.replaceAll(",", "");

            String txt5 = driver.findElements(By.xpath("//div[@class = 'val']")).get(4).getText();

            return txt1.contains("" + Total_expected_after_5_years) && txt2.contains("" + Net_dividends) && txt3.contains("" + Gross_capital_appreciation) && txt4.contains("" + ROI) && txt5.contains("" + Annualized_return);

        } else {
            System.out.println("This fields ara enabled");
            return false;

        }


    }

    public boolean Netrentalyield() throws JsonProcessingException {
        //Call Api

        // from 0 up to 2 belong to propertyKeyInfo
        String Min_investment_amount = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(0).getText();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        boolean Condition_1 =  Min_investment_amount.contains(df.format(Double.valueOf(m.get("minInvestmentAmount"))));
        String Number_of_investors = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(1).getText();
        boolean Condition_2 = Number_of_investors.contains(m.get("numberOfInvestors"));

        boolean Condition_3 =driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(3).getText().contains("5");

        String Gross_rent  = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(3).getText();
        boolean Condition_4 =Gross_rent.contains(df.format(selectedProperty.rentAmount));

        return Condition_1 && Condition_2 && Condition_3 && Condition_4;
    }

    public boolean propertyKeyInfo() throws JsonProcessingException {
        String Gross_rent  = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(4).getText();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        boolean Condition_1 = Gross_rent.contains(df.format((selectedProperty.rentAmount/selectedProperty.totalPrice)*100));
        String Net_dividend_yield  = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(5).getText();
        boolean Condition_2 = Net_dividend_yield.contains(df.format(netDiv));
        String ROI  = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(6).getText();
        df.setMaximumFractionDigits(0);
        boolean Condition_3 = ROI.contains(df.format(selectedProperty.fiveYearExpectedReturn));
        String Dividend_distribution_frequency  = driver.findElements(By.xpath("//div[@class= 'card-info']/div[3]")).get(7).getText();

        boolean Condition_4 = Dividend_distribution_frequency.contains(m.get("dividedFrequency"));

       return Condition_1 && Condition_2 && Condition_3 && Condition_4;
    }



}
