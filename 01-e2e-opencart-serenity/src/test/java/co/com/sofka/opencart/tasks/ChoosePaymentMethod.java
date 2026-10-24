package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.interactions.TickCheckbox;
import co.com.sofka.opencart.interactions.WaitForPendingRequests;
import co.com.sofka.opencart.userinterfaces.CheckoutPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ChoosePaymentMethod implements Task {

    private final String paymentMethod;

    public ChoosePaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static ChoosePaymentMethod called(String paymentMethod) {
        return instrumented(ChoosePaymentMethod.class, paymentMethod);
    }

    @Override
    @Step("{0} pays with #paymentMethod and accepts the terms and conditions")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.PAYMENT_METHOD_OPTION.of(paymentMethod), isClickable()),
                Click.on(CheckoutPage.PAYMENT_METHOD_OPTION.of(paymentMethod)),
                TickCheckbox.on(CheckoutPage.TERMS_AND_CONDITIONS),
                WaitForPendingRequests.toFinish(),
                Click.on(CheckoutPage.PAYMENT_METHOD_CONTINUE_BUTTON)
        );
    }
}
