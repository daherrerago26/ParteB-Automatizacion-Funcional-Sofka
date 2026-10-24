package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.interactions.WaitForPendingRequests;
import co.com.sofka.opencart.userinterfaces.CheckoutPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ConfirmOrder implements Task {

    private static final String SUCCESS_ROUTE = "route=checkout/success";

    public static ConfirmOrder now() {
        return instrumented(ConfirmOrder.class);
    }

    @Override
    @Step("{0} confirms the order")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.CONFIRM_ORDER_BUTTON, isClickable()),
                WaitForPendingRequests.toFinish(),
                Click.on(CheckoutPage.CONFIRM_ORDER_BUTTON),
                // La página de checkout también tiene un h1, así que se espera la redirección a la página de éxito
                WaitUntil.the(ExpectedConditions.urlContains(SUCCESS_ROUTE))
        );
    }
}
