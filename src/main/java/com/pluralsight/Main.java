package com.pluralsight;

import java.io.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static List<Vehicle> inventory = new ArrayList<>();

    static void main() {

        Vehicle fordExplorer = new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500);
        Vehicle toyotaCamary = new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000);
        Vehicle chevroletMalibu = new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700);
        Vehicle hondaCivic = new Vehicle(101124, "Honda Civic", "White", 70000, 7500);
        Vehicle subaruOutback = new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500);
        Vehicle jeepWrangler = new Vehicle(101126, "Jeep Wrangler", "Yellow", 35000, 16500);

        int userBuyer = vehicleSystem();

        inventory.add(new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500));
        switch (userBuyer) {
            case 1: // List all vehicles
                System.out.println("Selected 1");
                listAllVehicles(inventory, inventory.size() );
            case 2:
                findVehiclesByPrice();
            case 3:
                searchByPriceRange();
            case 4:
                searchByColor();
            case 5:
                addAVehicle();
            case 6: // Exit
                System.out.println("Goodbye!");
            default:
                System.out.println("Wrong");
        }
    }

    private static void addAVehicle() {
    }


    private static void displayVehicles(Vehicle[] vehicles, int numberOfVehicles) {

        System.out.println("Vehicle ID   Make / Model        Color       Mileage        Price");
        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicle v = vehicles[i];
            System.out.printf("%10d %-20s %-10s %10d %14.2f\n",
                    v.getVehicleId(),
                    v.getMakeModel(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());
        }
    }


    private static void searchByColor() {
    }

    private static void searchByPriceRange() {
    }

    private static void listAllVehicles(List<Vehicle> vehicles, int numberOfVehicles) {
        System.out.println("List Vehicles");
        displayVehicles( vehicles.toArray(new Vehicle[0]), numberOfVehicles);


    }

    private static void findVehiclesByPrice() {
    }

    public static int vehicleSystem() {
        boolean validInput = false;
        int input = 0;
        while (!validInput) {
            try {
                System.out.println("What do you want to do?\n" +
                        "1 - List all vehicles\n" +
                        "2 - Search by make/model\n" +
                        "3 - Search by price range\n" +
                        "4 - Search by color\n" +
                        "5 - Add a vehicle\n" +
                        "6 - Quit\n" +
                        "Enter your command:"

                );
                input = Main.scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Sorry that is not a valid input. Please try again");
                Main.scanner.nextLine();


            }
        }
        return input;
    }
}


