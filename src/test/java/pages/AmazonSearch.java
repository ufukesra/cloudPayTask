package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethods;
import utilities.Driver;

import java.util.List;


public class AmazonSearch {


    WebDriver driver= Driver.get();
    double laptopPrice;
    double monitorPrice;


   public AmazonSearch(){
       PageFactory.initElements(driver,this);
   }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

   @FindBy(id = "nav-search-submit-button")
    public WebElement searchButton;

   @FindBy(xpath = "//*[contains(@class,'pagination-next')]")
   public WebElement paginationNext;

   @FindBy(xpath = "//div[@id='corePrice_desktop']/div/table/tbody/tr[2]/td[2]/span")
   public WebElement laptopPriceElement;

   @FindBy(css = "[id=add-to-cart-button]")
   public WebElement addBasketButton;

   @FindBy(xpath = "//*[contains(@class,'main-slot s-result-list s-search-results')]/div")
   public List<WebElement> monitorList;

   @FindBy(xpath = "//div[contains(@id,'corePriceDisplay')]/div")
   public WebElement monitorPriceElement;

   @FindBy(xpath = "(//div[contains(@id,'cart-count')]/span)[1]")
   public WebElement trolley;

   @FindBy(xpath = "(//span[contains(@id,'subtotal-amount')])[2]")
   public WebElement subTotalElement;

   @FindBy(xpath = "//span[contains(@id,'cart-subtotal')]")
   public WebElement chartSubtotal;

   @FindBy(xpath = "//a[contains(@id,'close_sideSheet-link')]")
   public WebElement closeSideChart;




    public void navigateLandingPageAndSearchProduct(String product){
        CommonMethods.waitForVisibility(searchBox,10);
        searchBox.sendKeys(product);
        searchButton.click();
    }



    public void clickRamCheckBox(String ram){
        WebElement checkBox= driver.findElement(By.xpath("//span[text()='"+ram+"']/../div/label/i"));

        checkBox.click();
    }




    public void selectProduct(String product){

        String searchResult= driver.findElement(By.xpath("//*[contains(@data-component-type,'result-info-bar')]/div/h1/div/div/div/div/span")).getText();
        int totalResult=Integer.parseInt(searchResult.split(" ")[2]);

        int totalPage=totalResult/24;
        int x=0;
        int y=0;
        int z=0;

        do {

            List<WebElement> prodList= driver.findElements(By.xpath("//*[contains(@class,'main-slot s-result-list s-search-results')]/div"));
            for (int i = 0; i < prodList.size(); i++) {

                CommonMethods.waitForVisibility(prodList.get(i),5);
                y++;


                if (!(prodList.get(i).getText()).contains("2020 Newest Dell Inspiron 15 3000 PC Laptop")) {

                    CommonMethods.waitForVisibility(paginationNext, 10);

                } else {
                    z++;

                    WebElement selectLaptop = driver.findElement(By.xpath("//*[contains(text(),'" + product + "')]"));
                    selectLaptop.click();
                    break;
                }


            }

            if (y==totalResult || z>=1){
                break;
            }

            prodList.clear();

            paginationNext.click();
            CommonMethods.waitFor(2);
            x++;

        }while (x<=totalPage);

        CommonMethods.waitForVisibility(laptopPriceElement,10);
            String priceString = laptopPriceElement.getText();
            laptopPrice = Double.parseDouble(priceString.substring(1));


    }


    public void checkTheProductPrice(String expectedPrice){

        String actualPrice= "$"+laptopPrice;

        Assert.assertEquals(expectedPrice,actualPrice);

    }


    public void addLaptopToBasket(){

        addBasketButton.click();
    }


    public void monitorSearch(String monitor){
        String monitorName="ASUS 27” 1080P Monitor (VA279HAE) - Full HD, Eye Care, Low Blue Light, Flicker Free, VESA Mountable, Anti-Glare, HDMI, D-Sub, VGA";

        searchBox.sendKeys(monitor);
        searchButton.click();
        CommonMethods.waitFor(2);
       List<WebElement> monitorList= driver.findElements(By.xpath("//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]/div/div/div/div[2]/div/div/div/h2/a"));

        for (int i = 0; i <monitorList.size() ; i++) {

            if (monitorList.get(i).getText().contains(monitorName)){
                monitorList.get(i).click();
                break;

            }


        }



        String priceString = monitorPriceElement.getText();
        String price= priceString.substring(1,4) + "."+priceString.substring(5);

        monitorPrice = Double.parseDouble(price);

        addBasketButton.click();

    }

    public void verifyTotalBasketValue(){

        closeSideChart.click();
        trolley.click();
        double actualSubtotal=Double.parseDouble(subTotalElement.getText().substring(2) );
        System.out.println("Subtotal "+actualSubtotal );

        double expectedSubtotal = laptopPrice+monitorPrice;

        Assert.assertEquals(expectedSubtotal,actualSubtotal,438.66);


    }



}
