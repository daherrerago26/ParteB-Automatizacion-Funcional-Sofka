package co.com.sofka.opencart.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class StageHooks {

    // Antes de cada escenario prepara el escenario de Screenplay: cada actor recibe su propio navegador
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
