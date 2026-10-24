package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.userinterfaces.ProductPage;
import co.com.sofka.opencart.userinterfaces.SearchResultsPage;
import co.com.sofka.opencart.userinterfaces.StoreHeader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {

    private final String product;

    public AddProductToCart(String product) {
        this.product = product;
    }

    public static AddProductToCart named(String product) {
        return instrumented(AddProductToCart.class, product);
    }

    @Override
    @Step("{0} adds #product to the cart")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(product).into(StoreHeader.SEARCH_INPUT),
                Click.on(StoreHeader.SEARCH_BUTTON),
                Click.on(SearchResultsPage.PRODUCT_LINK.of(product)),
                Click.on(ProductPage.ADD_TO_CART_BUTTON),
                // El carrito se actualiza por AJAX; el aviso de éxito confirma que el producto quedó agregado
                WaitUntil.the(ProductPage.ADDED_TO_CART_ALERT.of(product), isVisible())
        );
    }
}
