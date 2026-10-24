package co.com.sofka.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

// Elementos del encabezado, presentes en todas las páginas de la tienda
public final class StoreHeader {

    public static final Target SEARCH_INPUT = Target.the("search box")
            .locatedBy("#search input[name='search']");

    public static final Target SEARCH_BUTTON = Target.the("search button")
            .locatedBy("#search button");

    public static final Target SHOPPING_CART_LINK = Target.the("shopping cart link")
            .locatedBy("#top-links a[title='Shopping Cart']");

    private StoreHeader() {
    }
}
