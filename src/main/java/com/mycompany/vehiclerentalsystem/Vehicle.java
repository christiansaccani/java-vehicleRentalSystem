package com.mycompany.vehiclerentalsystem;

public class Vehicle {
    private String brand;
    private String model;
    private String code;
    private String transmission;
    private String seats;

    public Vehicle(String brand, String model, String code, String transmission, String seats) {
        this.brand = brand;
        this.model = model;
        this.code = code;
        this.transmission = transmission;
        this.seats = seats;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCode() {
        return code;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getSeats() {
        return seats;
    }

    // Metodo per ottenere il tipo completo
    public String getType(TypesDatabase typesDatabase) {
        Type type = typesDatabase.getTypeByCode(code);
        return type != null ? type.getType() : "Unknown";
    }
    
    public double getPrice(TypesDatabase typesDatabase) {
        return typesDatabase.getTypePriceByCode(code);
    }

    @Override
    public String toString() {
        // Rimuovi il riferimento a TypesDatabase.getInstance() e usa direttamente l'istanza passata
        return String.format("Brand: %s, Model: %s, Code: %s, Transmission: %s, Seats: %s, Price: %.2f",
                brand, model, code, transmission, seats, getPrice(null));
    }
}
