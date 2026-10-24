package co.com.sofka.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

// El checkout es un acordeón: cada paso se carga por AJAX al continuar el anterior
public final class CheckoutPage {

    // Paso 1: opciones de compra
    public static final Target GUEST_CHECKOUT_OPTION = Target.the("guest checkout option")
            .locatedBy("#collapse-checkout-option input[name='account'][value='guest']");

    public static final Target ACCOUNT_CONTINUE_BUTTON = Target.the("continue button of the checkout options")
            .locatedBy("#button-account");

    // Paso 2: datos de facturación del invitado
    public static final Target FIRST_NAME = Target.the("first name field").locatedBy("#input-payment-firstname");
    public static final Target LAST_NAME = Target.the("last name field").locatedBy("#input-payment-lastname");
    public static final Target EMAIL = Target.the("email field").locatedBy("#input-payment-email");
    public static final Target TELEPHONE = Target.the("telephone field").locatedBy("#input-payment-telephone");
    public static final Target ADDRESS = Target.the("address field").locatedBy("#input-payment-address-1");
    public static final Target CITY = Target.the("city field").locatedBy("#input-payment-city");
    public static final Target POSTCODE = Target.the("post code field").locatedBy("#input-payment-postcode");
    public static final Target COUNTRY = Target.the("country list").locatedBy("#input-payment-country");
    public static final Target REGION = Target.the("region list").locatedBy("#input-payment-zone");

    // {0}: nombre de la región; la lista se llena por AJAX después de elegir el país
    public static final Target REGION_OPTION = Target.the("region option {0}")
            .locatedBy("//select[@id='input-payment-zone']/option[normalize-space()='{0}']");

    public static final Target GUEST_CONTINUE_BUTTON = Target.the("continue button of the billing details")
            .locatedBy("#button-guest");

    // Paso 4: método de envío ({0}: texto de la opción)
    public static final Target DELIVERY_METHOD_OPTION = Target.the("delivery method {0}")
            .locatedBy("//div[@id='collapse-shipping-method']//label[contains(normalize-space(),'{0}')]/input");

    public static final Target DELIVERY_METHOD_CONTINUE_BUTTON = Target.the("continue button of the delivery method")
            .locatedBy("#button-shipping-method");

    // Paso 5: método de pago ({0}: texto de la opción)
    public static final Target PAYMENT_METHOD_OPTION = Target.the("payment method {0}")
            .locatedBy("//div[@id='collapse-payment-method']//label[contains(normalize-space(),'{0}')]/input");

    public static final Target TERMS_AND_CONDITIONS = Target.the("terms and conditions checkbox")
            .locatedBy("#collapse-payment-method input[name='agree']");

    public static final Target PAYMENT_METHOD_CONTINUE_BUTTON = Target.the("continue button of the payment method")
            .locatedBy("#button-payment-method");

    // Paso 6: confirmación del pedido
    public static final Target CONFIRM_ORDER_BUTTON = Target.the("confirm order button")
            .locatedBy("#button-confirm");

    private CheckoutPage() {
    }
}
