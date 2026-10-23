package co.com.sofka.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class ProductPage {

    public static final Target ADD_TO_CART_BUTTON = Target.the("add to cart button")
            .locatedBy("#button-cart");

    // {0}: nombre del producto que aparece en el mensaje de éxito
    public static final Target ADDED_TO_CART_ALERT = Target.the("alert confirming that {0} was added to the cart")
            .locatedBy("//div[contains(@class,'alert-success')][contains(normalize-space(),'{0}')]");

    private ProductPage() {
    }
}
