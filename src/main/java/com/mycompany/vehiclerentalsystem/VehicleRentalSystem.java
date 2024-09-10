package com.mycompany.vehiclerentalsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class VehicleRentalSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TypesDatabase typesDatabase = new TypesDatabase();
        VehicleDatabase database = new VehicleDatabase(typesDatabase);

        System.out.println("Do you want to filter vehicles or view all sorted? (filter/sorted): ");
        String choice = scanner.nextLine().trim();

        List<Vehicle> vehicles;
        if ("filter".equalsIgnoreCase(choice)) {
            System.out.println("Enter vehicle type to filter (or leave blank for no filter): ");
            String typeFilter = scanner.nextLine().trim();

            System.out.println("Enter number of seats to filter (or leave blank for no filter): ");
            String seatsFilter = scanner.nextLine().trim();

            System.out.println("Enter transmission type to filter (or leave blank for no filter): ");
            String transmissionFilter = scanner.nextLine().trim();

            vehicles = database.filterVehicles(typeFilter, seatsFilter, transmissionFilter);
            displayVehicles(vehicles, typesDatabase);

        } else if ("sorted".equalsIgnoreCase(choice)) {
            vehicles = database.getVehiclesSortedByBrand();
            displayVehicles(vehicles, typesDatabase);

        } else {
            System.out.println("Invalid choice.");
            vehicles = List.of(); // No vehicles to display
        }

        generateHtml(vehicles, typesDatabase);

        scanner.close();
    }

    private static void displayVehicles(List<Vehicle> vehicles, TypesDatabase typesDatabase) {
    if (vehicles.isEmpty()) {
        System.out.println("No vehicles found.");
    } else {
        System.out.printf("%-10s %-15s %-15s %-10s %-5s %-7s%n", 
                          "Brand", "Model", "Type", "Transmission", "Seats", "Price");
        System.out.println("------------------------------------------------------------");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-10s %-15s %-15s %-10s %-5s %-7.2f%n", 
                              vehicle.getBrand(), 
                              vehicle.getModel(), 
                              vehicle.getType(typesDatabase),
                              vehicle.getTransmission(),
                              vehicle.getSeats(), 
                              vehicle.getPrice(typesDatabase));
        }
    }
}


    private static void generateHtml(List<Vehicle> vehicles, TypesDatabase typesDatabase) {
        try (FileWriter writer = new FileWriter("vehicles.html")) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html lang=\"en\">\n");
            writer.write("<head>\n");
            writer.write("    <meta charset=\"UTF-8\">\n");
            writer.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            writer.write("    <title>Vehicle Rental System</title>\n");
            writer.write("    <style>\n");
            writer.write("        table {\n");
            writer.write("            width: 100%;\n");
            writer.write("            border-collapse: collapse;\n");
            writer.write("        }\n");
            writer.write("        table, th, td {\n");
            writer.write("            border: 1px solid black;\n");
            writer.write("        }\n");
            writer.write("        th, td {\n");
            writer.write("            padding: 8px;\n");
            writer.write("            text-align: left;\n");
            writer.write("        }\n");
            writer.write("        th {\n");
            writer.write("            background-color: #f2f2f2;\n");
            writer.write("        }\n");
            writer.write("    </style>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            writer.write("    <h1>Vehicle Rental System</h1>\n");

            // Table of vehicles
            writer.write("    <table>\n");
            writer.write("        <thead>\n");
            writer.write("            <tr>\n");
            writer.write("                <th>Brand</th>\n");
            writer.write("                <th>Model</th>\n");
            writer.write("                <th>Type</th>\n");
            writer.write("                <th>Transmission</th>\n");
            writer.write("                <th>Seats</th>\n");
            writer.write("                <th>Price</th>\n");
            writer.write("            </tr>\n");
            writer.write("        </thead>\n");
            writer.write("        <tbody>\n");

            for (Vehicle vehicle : vehicles) {
                writer.write(String.format("            <tr>\n" +
                                           "                <td>%s</td>\n" +
                                           "                <td>%s</td>\n" +
                                           "                <td>%s</td>\n" +
                                           "                <td>%s</td>\n" +
                                           "                <td>%s</td>\n" +
                                           "                <td>%.2f</td>\n" +
                                           "            </tr>\n",
                                           vehicle.getBrand(), vehicle.getModel(), vehicle.getCode(), vehicle.getTransmission(),
                                           vehicle.getSeats(), vehicle.getPrice(typesDatabase)));
            }

            writer.write("        </tbody>\n");
            writer.write("    </table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");

        } catch (IOException e) {
            System.err.println("Error writing HTML file: " + e.getMessage());
        }
    }
}
