package co.com.sofka.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class SearchResultsPage {

    // {0}: nombre exacto del producto
    public static final Target PRODUCT_LINK = Target.the("product {0} in the search results")
            .locatedBy("//div[contains(@class,'product-thumb')]//h4/a[normalize-space()='{0}']");

    private SearchResultsPage() {
    }
}
