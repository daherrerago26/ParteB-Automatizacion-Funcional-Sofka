package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.userinterfaces.CartPage;
import co.com.sofka.opencart.userinterfaces.StoreHeader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ViewCart implements Task {

    public static ViewCart ofTheStore() {
        return instrumented(ViewCart.class);
    }

    @Override
    @Step("{0} opens the shopping cart")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(StoreHeader.SHOPPING_CART_LINK),
                WaitUntil.the(CartPage.CHECKOUT_BUTTON, isVisible())
        );
    }
}
