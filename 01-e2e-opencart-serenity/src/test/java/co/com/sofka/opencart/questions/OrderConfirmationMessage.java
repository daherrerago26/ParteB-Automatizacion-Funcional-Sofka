package co.com.sofka.opencart.questions;

import co.com.sofka.opencart.userinterfaces.OrderSuccessPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public final class OrderConfirmationMessage {

    public static Question<String> displayed() {
        return Text.of(OrderSuccessPage.TITLE).describedAs("the order confirmation message");
    }

    private OrderConfirmationMessage() {
    }
}
