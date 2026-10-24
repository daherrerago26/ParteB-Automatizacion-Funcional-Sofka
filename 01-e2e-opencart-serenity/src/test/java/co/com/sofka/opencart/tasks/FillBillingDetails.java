package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.interactions.WaitForPendingRequests;
import co.com.sofka.opencart.models.GuestCustomer;
import co.com.sofka.opencart.userinterfaces.CheckoutPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillBillingDetails implements Task {

    private final GuestCustomer customer;

    public FillBillingDetails(GuestCustomer customer) {
        this.customer = customer;
    }

    public static FillBillingDetails of(GuestCustomer customer) {
        return instrumented(FillBillingDetails.class, customer);
    }

    @Override
    @Step("{0} fills in the billing details")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.FIRST_NAME, isVisible()),
                Enter.theValue(customer.firstName()).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(customer.lastName()).into(CheckoutPage.LAST_NAME),
                Enter.theValue(customer.email()).into(CheckoutPage.EMAIL),
                Enter.theValue(customer.telephone()).into(CheckoutPage.TELEPHONE),
                Enter.theValue(customer.address()).into(CheckoutPage.ADDRESS),
                Enter.theValue(customer.city()).into(CheckoutPage.CITY),
                Enter.theValue(customer.postcode()).into(CheckoutPage.POSTCODE),
                SelectFromOptions.byVisibleText(customer.country()).from(CheckoutPage.COUNTRY),
                // Las regiones se recargan por AJAX al cambiar el país
                WaitUntil.the(CheckoutPage.REGION_OPTION.of(customer.region()), isPresent()),
                SelectFromOptions.byVisibleText(customer.region()).from(CheckoutPage.REGION),
                WaitForPendingRequests.toFinish(),
                Click.on(CheckoutPage.GUEST_CONTINUE_BUTTON)
        );
    }
}
