package co.com.sofka.opencart.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// Marca una casilla solo si no está marcada; un clic simple la desmarcaría si el sitio la recuerda de un intento anterior
public class TickCheckbox implements Interaction {

    private final Target checkbox;

    public TickCheckbox(Target checkbox) {
        this.checkbox = checkbox;
    }

    public static TickCheckbox on(Target checkbox) {
        return instrumented(TickCheckbox.class, checkbox);
    }

    @Override
    @Step("{0} ticks the #checkbox")
    public <T extends Actor> void performAs(T actor) {
        if (!checkbox.resolveFor(actor).isSelected()) {
            actor.attemptsTo(Click.on(checkbox));
        }
    }
}
