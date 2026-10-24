package co.com.sofka.opencart.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// Espera a que terminen las peticiones AJAX de jQuery que la página tenga en curso.
// En el checkout del sitio, una petición que sigue en curso puede sobrescribir la sesión con datos viejos
// y borrar lo que guardó el paso siguiente; por eso se espera antes de cada "Continue".
public class WaitForPendingRequests implements Interaction {

    private static final ExpectedCondition<Boolean> NO_PENDING_AJAX = driver -> (Boolean)
            ((JavascriptExecutor) driver).executeScript("return window.jQuery !== undefined && jQuery.active === 0;");

    public static WaitForPendingRequests toFinish() {
        return instrumented(WaitForPendingRequests.class);
    }

    @Override
    @Step("{0} waits for the pending page requests to finish")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(NO_PENDING_AJAX));
    }
}
