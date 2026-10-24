package co.com.sofka.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class CartPage {

    public static final Target PRODUCT_NAMES = Target.the("product names in the cart")
            .locatedBy("//div[@id='content']//form//tbody/tr/td[@class='text-left']/a");

    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .locatedBy("//div[@id='content']//a[normalize-space()='Checkout']");

    private CartPage() {
    }
}
