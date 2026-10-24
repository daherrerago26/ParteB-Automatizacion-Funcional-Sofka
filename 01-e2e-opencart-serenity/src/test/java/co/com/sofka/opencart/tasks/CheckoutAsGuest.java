package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.interactions.WaitForPendingRequests;
import co.com.sofka.opencart.models.GuestCustomer;
import co.com.sofka.opencart.userinterfaces.CartPage;
import co.com.sofka.opencart.userinterfaces.CheckoutPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class CheckoutAsGuest implements Task {

    private final GuestCustomer customer;

    public CheckoutAsGuest(GuestCustomer customer) {
        this.customer = customer;
    }

    public static CheckoutAsGuest withDetails(GuestCustomer customer) {
        return instrumented(CheckoutAsGuest.class, customer);
    }

    @Override
    @Step("{0} starts the checkout as a guest")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPage.CHECKOUT_BUTTON),
                WaitUntil.the(CheckoutPage.GUEST_CHECKOUT_OPTION, isClickable()),
                Click.on(CheckoutPage.GUEST_CHECKOUT_OPTION),
                WaitForPendingRequests.toFinish(),
                Click.on(CheckoutPage.ACCOUNT_CONTINUE_BUTTON),
                FillBillingDetails.of(customer)
        );
    }
}
