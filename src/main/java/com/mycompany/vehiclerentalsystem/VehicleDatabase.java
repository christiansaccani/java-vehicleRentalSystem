package com.mycompany.vehiclerentalsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleDatabase {
    private List<Vehicle> vehicles;
    private Map<String, Type> typesMap;

    public VehicleDatabase(TypesDatabase typesDatabase) {
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("FORD", "FIESTA", "UTI", "M", "5"));
        vehicles.add(new Vehicle("BMW", "X5", "SUV", "A", "5"));
        vehicles.add(new Vehicle("AUDI", "A3", "BER", "A", "5"));
        vehicles.add(new Vehicle("PORSCHE", "911", "SPO", "A", "2"));
        vehicles.add(new Vehicle("FIAT", "500", "CIT", "M", "4"));
        vehicles.add(new Vehicle("RENAULT", "TWINGO", "CIT", "M", "4"));
        vehicles.add(new Vehicle("FORD", "FOCUS", "BER", "A", "5"));
        vehicles.add(new Vehicle("SMART", "FORTWO", "CIT", "M", "2"));
        vehicles.add(new Vehicle("BMW", "SERIE 3", "BER", "A", "5"));
        vehicles.add(new Vehicle("AUDI", "RS6", "SPO", "A", "5"));
        vehicles.add(new Vehicle("KIA", "SPORTAGE", "SUV", "M", "5"));
        vehicles.add(new Vehicle("OPEL", "CORSA", "UTI", "M", "5"));
        vehicles.add(new Vehicle("JEEP", "COMPASS", "SUV", "M", "5"));
        vehicles.add(new Vehicle("BMW", "M3", "SPO", "A", "5"));
        vehicles.add(new Vehicle("TOYOTA", "YARIS", "UTI", "A", "5"));

        typesMap = new HashMap<>();
        for (Type type : typesDatabase.getTypes()) {
            typesMap.put(type.getCode(), type);
        }
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles.stream()
            .map(this::applyTypeDetails)
            .collect(Collectors.toList());
    }

    public List<Vehicle> filterVehicles(String typeFilter, String seatsFilter, String transmissionFilter) {
        return vehicles.stream()
            .map(this::applyTypeDetails)
            .filter(vehicle -> (typeFilter.isEmpty() || vehicle.getCode().equalsIgnoreCase(typeFilter)) &&
                               (seatsFilter.isEmpty() || vehicle.getSeats().equalsIgnoreCase(seatsFilter)) &&
                               (transmissionFilter.isEmpty() || vehicle.getTransmission().equalsIgnoreCase(transmissionFilter)))
            .collect(Collectors.toList());
    }

    private Vehicle applyTypeDetails(Vehicle vehicle) {
        Type type = typesMap.get(vehicle.getCode());
        if (type != null) {
            // Include the type details without changing the price
            return new Vehicle(vehicle.getBrand(), vehicle.getModel(), type.getCode(), vehicle.getTransmission(), vehicle.getSeats());
        }
        // Return the original vehicle if the type is not found
        return vehicle;
    }

    public List<Vehicle> getVehiclesSortedByBrand() {
        return vehicles.stream()
            .map(this::applyTypeDetails)
            .sorted((v1, v2) -> v1.getBrand().compareToIgnoreCase(v2.getBrand()))
            .collect(Collectors.toList());
    }
}
