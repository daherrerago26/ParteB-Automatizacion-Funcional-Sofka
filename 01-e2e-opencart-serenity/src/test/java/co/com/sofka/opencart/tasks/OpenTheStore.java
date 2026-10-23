package co.com.sofka.opencart.tasks;

import co.com.sofka.opencart.userinterfaces.StoreHeader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenTheStore implements Task {

    public static OpenTheStore homePage() {
        return instrumented(OpenTheStore.class);
    }

    @Override
    @Step("{0} opens the home page of the online store")
    public <T extends Actor> void performAs(T actor) {
        // La URL base sale de serenity.conf según el entorno activo (-Denvironment)
        String baseUrl = EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty("webdriver.base.url");
        actor.attemptsTo(
                Open.url(baseUrl),
                // El sitio demo a veces tarda en responder; se espera a que la tienda esté lista antes de seguir
                WaitUntil.the(StoreHeader.SEARCH_INPUT, isVisible())
        );
    }
}
