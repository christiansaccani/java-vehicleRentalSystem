package com.mycompany.vehiclerentalsystem;

import java.util.ArrayList;
import java.util.List;

public class TypesDatabase {
    private List<Type> types;

    public TypesDatabase() {
        types = new ArrayList<>();
        
        types.add(new Type("CIT", "CITY CAR", 65.90));
        types.add(new Type("BER", "BERLINA", 123.50));
        types.add(new Type("UTI", "UTILITARIA", 83.50));
        types.add(new Type("SUV", "SUV", 159.70));
        types.add(new Type("SPO", "SPORT", 275.20));
    }

    public double getTypePriceByCode(String code) {
        return types.stream()
                    .filter(type -> type.getCode().equals(code))
                    .map(Type::getPrice)
                    .findFirst()
                    .orElse(0.0);
    }

    public Type getTypeByCode(String code) {
        return types.stream()
                    .filter(type -> type.getCode().equals(code))
                    .findFirst()
                    .orElse(null);
    }

    public List<Type> getTypes() {
        return types;
    }
}

