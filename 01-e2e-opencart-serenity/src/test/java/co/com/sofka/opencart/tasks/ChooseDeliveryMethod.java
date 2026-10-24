package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.interactions.WaitForPendingRequests;
import co.com.sofka.opencart.userinterfaces.CheckoutPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ChooseDeliveryMethod implements Task {

    private final String deliveryMethod;

    public ChooseDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public static ChooseDeliveryMethod called(String deliveryMethod) {
        return instrumented(ChooseDeliveryMethod.class, deliveryMethod);
    }

    @Override
    @Step("{0} chooses the #deliveryMethod delivery method")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.DELIVERY_METHOD_OPTION.of(deliveryMethod), isClickable()),
                Click.on(CheckoutPage.DELIVERY_METHOD_OPTION.of(deliveryMethod)),
                // Aquí estaba la carrera: el paso "Delivery Details" sigue cargando por AJAX y borraba el envío elegido
                WaitForPendingRequests.toFinish(),
                Click.on(CheckoutPage.DELIVERY_METHOD_CONTINUE_BUTTON)
        );
    }
}
