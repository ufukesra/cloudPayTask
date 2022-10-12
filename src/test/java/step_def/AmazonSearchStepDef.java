package step_def;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AmazonSearch;

public class AmazonSearchStepDef {
AmazonSearch as = new AmazonSearch();


    @Given("User goes to landing page and search {string}")
    public void user_goes_to_landing_page_and_search(String product) {
        as.navigateLandingPageAndSearchProduct(product);
    }
    @When("Refines {string} on the left filter panel")
    public void refines_on_the_left_filter_panel(String ram) {
        as.clickRamCheckBox(ram);

    }
    @When("Select the product {string}")
    public void select_the_product(String productText) {

        as.selectProduct(productText);

    }
    @When("Check the price is {string}")
    public void check_the_price_is(String expectedPrice) {
        as.checkTheProductPrice(expectedPrice);

    }
    @When("Adds this laptop to the basket")
    public void adds_this_laptop_to_the_basket() {
        as.addLaptopToBasket();
        //CommonMethods.waitFor(2);

    }
    @When("Search a {string} and add it to basket")
    public void search_a_and_add_it_to_basket(String monitor) {
        as.monitorSearch(monitor);

        //CommonMethods.waitFor(2);
    }
    @Then("Verify if the total price is match in basket")
    public void verify_if_the_total_price_is_match_in_basket() {
        as.verifyTotalBasketValue();

    }

}
