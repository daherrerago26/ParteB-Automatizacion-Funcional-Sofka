package co.com.sofka.opencart.stepdefinitions;

import co.com.sofka.opencart.models.GuestCustomer;
import co.com.sofka.opencart.questions.CartProducts;
import co.com.sofka.opencart.questions.OrderConfirmationMessage;
import co.com.sofka.opencart.tasks.AddProductToCart;
import co.com.sofka.opencart.tasks.CheckoutAsGuest;
import co.com.sofka.opencart.tasks.ChooseDeliveryMethod;
import co.com.sofka.opencart.tasks.ChoosePaymentMethod;
import co.com.sofka.opencart.tasks.ConfirmOrder;
import co.com.sofka.opencart.tasks.OpenTheStore;
import co.com.sofka.opencart.tasks.ViewCart;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

// Pegamento entre el Gherkin y Screenplay: aquí no hay lógica, solo se delega en tasks y questions
public class GuestCheckoutStepDefinitions {

    private static final String ADDED_PRODUCTS = "addedProducts";

    @DataTableType
    public GuestCustomer guestCustomer(Map<String, String> row) {
        return new GuestCustomer(
                row.get("firstName"),
                row.get("lastName"),
                row.get("email"),
                row.get("telephone"),
                row.get("address"),
                row.get("city"),
                row.get("postcode"),
                row.get("country"),
                row.get("region"));
    }

    @Given("{word} is browsing the online store")
    public void isBrowsingTheOnlineStore(String actorName) {
        theActorCalled(actorName).wasAbleTo(OpenTheStore.homePage());
    }

    @When("they add the following products to the cart")
    public void addTheFollowingProductsToTheCart(List<String> products) {
        products.forEach(product -> theActorInTheSpotlight().attemptsTo(AddProductToCart.named(product)));
        theActorInTheSpotlight().remember(ADDED_PRODUCTS, products);
    }

    @When("they review their shopping cart")
    public void reviewTheirShoppingCart() {
        theActorInTheSpotlight().attemptsTo(ViewCart.ofTheStore());
    }

    @Then("their cart should contain the products they added")
    public void theirCartShouldContainTheProductsTheyAdded() {
        List<String> addedProducts = theActorInTheSpotlight().recall(ADDED_PRODUCTS);
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CartProducts.displayed()).containsExactlyInAnyOrderElementsFrom(addedProducts));
    }

    @When("they check out as a guest with the following details")
    public void checkOutAsAGuest(List<GuestCustomer> customers) {
        theActorInTheSpotlight().attemptsTo(CheckoutAsGuest.withDetails(customers.get(0)));
    }

    @When("they choose the {string} delivery method")
    public void chooseTheDeliveryMethod(String deliveryMethod) {
        theActorInTheSpotlight().attemptsTo(ChooseDeliveryMethod.called(deliveryMethod));
    }

    @When("they pay with {string} accepting the terms and conditions")
    public void payWith(String paymentMethod) {
        theActorInTheSpotlight().attemptsTo(ChoosePaymentMethod.called(paymentMethod));
    }

    @When("they confirm the order")
    public void confirmTheOrder() {
        theActorInTheSpotlight().attemptsTo(ConfirmOrder.now());
    }

    @Then("they should see the order confirmation {string}")
    public void shouldSeeTheOrderConfirmation(String expectedMessage) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(OrderConfirmationMessage.displayed()).isEqualTo(expectedMessage));
    }
}
