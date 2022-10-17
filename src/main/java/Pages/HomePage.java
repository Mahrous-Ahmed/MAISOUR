package Pages;

import APIServices.APIShowAllProperty;
import Modals.AllProperties;
import Modals.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class HomePage {
     private WebDriver driver;
    @FindBy(id = "status")
    private WebElement Selection;

    @FindBy(id = "react-select-2-input")
    private WebElement StatusDropdown;

    @FindBy(className = "card-item-container")
    private List<WebElement> Cards;

    @FindBy(className = "item-status")
    private WebElement propertyStatusElement;

    @FindBy(className = "item-title")
    private WebElement propertyTitleElement;

    @FindBy(className = "item-desc")
    private WebElement propertyDescriptionElement;

    @FindBy(xpath = "//a[@href = '/dashboard' and text() ='MY DASHBOARD']")
    private WebElement dashboard;

    AllProperties properties = APIShowAllProperty.getAllProperties();

    public HomePage(WebDriver driver) throws JsonProcessingException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public DashBoard clickOnDashboard() throws JsonProcessingException {
        dashboard.click();
        return new DashBoard(driver);
    }

    public int propertyOnScreen(){
        return Cards.size();
    }
    public boolean checkAllPropertyDetailsOnScreen() {
        //initial value
        boolean AllDisplayedCorrect = false;

        if(properties.properties.size() != Cards.size())
            return false;

        // Two Length now are equal , We can take any one of them

        for (int i = 0; i < properties.properties.size(); i++) {

           //System.out.println(Cards.get(i).findElements(By.className("info-content")).size());
            String propertyFuture = "";
           try {
               propertyFuture = Cards.get(i).findElements(By.className("info-content")).get(0).findElements(By.tagName("div")).get(0).getText();
           }catch (Exception e)
           {
               propertyFuture = "";

           }
            String buttonTitle = Cards.get(i).findElement(By.className("btn-item-action")).getText();
            String propertyStatus =  Cards.get(i).findElement(By.className("item-status")).getText();
            String propertyPrice =  Cards.get(i).findElements(By.className("info-content")).get(1).findElements(By.tagName("div")).get(1).getText();
            String grossYield =  Cards.get(i).findElements(By.className("info-content")).get(2).findElements(By.tagName("div")).get(1).getText();
            String netDividendYield =  Cards.get(i).findElements(By.className("info-content")).get(3).findElements(By.tagName("div")).get(1).getText();
            String fiveYearsROI =  Cards.get(i).findElements(By.className("info-content")).get(4).findElements(By.tagName("div")).get(1).getText();
            String Code = Cards.get(i).findElement(By.className("code")).getText();
            boolean propertyFutureEqual = true;
            if(!propertyFuture.equals(""))
                propertyFutureEqual = propertyFuture.contains(properties.properties.get(i).propertyFeatures.get(0).nameInEnglish);


            double f = properties.properties.get(i).fiveYearExpectedReturn;
            boolean ROIEqual = fiveYearsROI.contains(""+f);
            Double gross = (properties.properties.get(i).rentAmount/properties.properties.get(i).totalPrice)*100;
            DecimalFormat  df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            boolean grossEqual = grossYield.contains(""+df.format(gross));


            double netDividend = getNetDividend(i);
            boolean netDividendEqual = netDividendYield.contains(""+df.format(netDividend));
            boolean codeEqual = Code.contains(properties.properties.get(i).code);

            propertyPrice = propertyPrice.replaceAll("AED","");
            propertyPrice = propertyPrice.replace(",","");
            boolean priceEqual = properties.properties.get(i).totalPrice.equals(Double.valueOf(propertyPrice));

            propertyStatus = propertyStatus.replace(" ","");
            propertyStatus = propertyStatus.toLowerCase();

            properties.properties.get(i).propertyStatus = properties.properties.get(i).propertyStatus.toLowerCase();
            boolean StatusEqual = properties.properties.get(i).propertyStatus.equals(propertyStatus);



            AllDisplayedCorrect = titleButtonRelatedToStatus(propertyStatus,buttonTitle) && priceEqual && StatusEqual && codeEqual && netDividendEqual && grossEqual && ROIEqual && propertyFutureEqual;
            if(!AllDisplayedCorrect)
                return false;
        }
      return AllDisplayedCorrect;
    }
    public PropertyPage selectProperty(int idx) throws InterruptedException, JsonProcessingException {
        Cards.get(idx).findElement(By.className("btn-item-action")).click();
        Thread.sleep(2000);
        double netDividend = getNetDividend(idx);
        return new PropertyPage(driver,properties.properties.get(idx),netDividend,idx+1);


    }

    private boolean titleButtonRelatedToStatus(String status , String Title){
        if(status.equals("funded") && Title.equals("VIEW DETAILS") )
            return true;
        else if(status.equals("live") && Title.equals("INVEST NOW"))
            return true;
        else if(status.equals("comingsoon") && Title.equals("PRE-ORDER"))
            return true;
        else
            return false;
    }

    private double getNetDividend(int i){
        Double OtherCost = 0.0;
        for (int j = 0 ; j < properties.properties.get(i).propertyRentalOtherCosts.size() ; j++) {

            if (properties.properties.get(i).propertyRentalOtherCosts.get(j).rentalOtherCostConstants.type.equals("Precentage")) {
                OtherCost += ((properties.properties.get(i).propertyRentalOtherCosts.get(j).rentalOtherCostConstants.value) / 100) * properties.properties.get(i).rentAmount;
            } else {
                OtherCost += properties.properties.get(i).propertyRentalOtherCosts.get(j).actualValue;
            }
        }
        return   ((properties.properties.get(i).rentAmount - properties.properties.get(i).propertyManagementFee - OtherCost)/properties.properties.get(i).totalPrice)*100;
    }

    public boolean validateSelectStatus(String status) throws InterruptedException, JsonProcessingException {
        Selection.click();
        StatusDropdown.sendKeys(status);
        StatusDropdown.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        int count = 0;
        for (Property p:properties.properties) {
            if(p.propertyStatus.equals(status))
                count++;
        }

        int cardStatus = 0;
        for (WebElement e: Cards ) {
           if(e.findElement(By.className("item-status")).getText().equals(status))
               cardStatus++;
        }

        return  cardStatus == count;



    }
}
