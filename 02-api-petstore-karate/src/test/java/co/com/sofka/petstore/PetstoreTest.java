package co.com.sofka.petstore;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Ejecuta los features de PetStore y genera el reporte HTML de Karate, Cucumber JSON y JUnit XML en target/karate-reports
class PetstoreTest {

    @Test
    void runPetstoreFeatures() {
        Results results = Runner.path("classpath:co/com/sofka/petstore")
                .outputCucumberJson(true)
                .outputJunitXml(true)
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
