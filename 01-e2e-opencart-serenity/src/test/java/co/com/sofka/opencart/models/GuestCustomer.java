package co.com.sofka.opencart.models;

// Datos de facturación del cliente invitado; llegan desde la tabla del feature
public record GuestCustomer(
        String firstName,
        String lastName,
        String email,
        String telephone,
        String address,
        String city,
        String postcode,
        String country,
        String region) {
}
