package com.mycompany.vehiclerentalsystem;

public class Type {
    private String code;
    private String type;
    private double price;

    public Type(String code, String type, double price) {
        this.code = code;
        this.type = type;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Type: %s, Price: %.2f", code, type, price);
    }
}
