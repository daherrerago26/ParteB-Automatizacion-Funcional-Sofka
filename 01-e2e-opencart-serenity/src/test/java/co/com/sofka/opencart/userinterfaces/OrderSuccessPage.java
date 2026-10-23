package co.com.sofka.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class OrderSuccessPage {

    public static final Target TITLE = Target.the("order confirmation title")
            .locatedBy("#content h1");

    private OrderSuccessPage() {
    }
}
