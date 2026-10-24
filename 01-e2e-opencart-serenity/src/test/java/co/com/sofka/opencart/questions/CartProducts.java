package co.com.sofka.opencart.questions;

import co.com.sofka.opencart.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Collection;

public final class CartProducts {

    public static Question<Collection<String>> displayed() {
        return Text.ofEach(CartPage.PRODUCT_NAMES).describedAs("the products in the cart");
    }

    private CartProducts() {
    }
}
