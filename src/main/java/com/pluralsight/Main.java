package com.pluralsight;

import java.text.NumberFormat;
import java.util.*;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static List<Vehicle> inventory = new ArrayList<>();

    static void main() {

//        Vehicle fordExplorer = new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500);
//        Vehicle toyotaCamary = new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000);
//        Vehicle chevroletMalibu = new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700);
//        Vehicle hondaCivic = new Vehicle(101124, "Honda Civic", "White", 70000, 7500);
//        Vehicle subaruOutback = new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500);
//        Vehicle jeepWrangler = new Vehicle(101126, "Jeep Wrangler", "Yellow", 35000, 16500);



        inventory.add(new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500));
        inventory.add(new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000));
        inventory.add(new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700));
        inventory.add(new Vehicle(101124, "Honda Civic", "White", 70000, 7500));
        inventory.add(new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500));
        inventory.add(new Vehicle(101126, "Jeep Wrangler", "Yellow", 35000, 16500));
        inventory.add(new Vehicle(201123, "Tesla Model S", "White", 0, 94990));

        int userBuyer = vehicleSystem();

        while (userBuyer != 6){
            switch (userBuyer) {
                case 1: // List all vehicles
                    System.out.println("Selected 1");
                    listAllVehicles(inventory, inventory.size() );
                    break;
                case 2:
                    searchByMake();
                    break;
                case 3:
                    searchByPriceRange();
                    break;
                case 4:
                    searchByColor();
                    break;
                case 5:
                    addAVehicle();
                    break;
                case 6: // Exit
                    System.out.println("Thank you, goodbye!");
                    break;
            }
            userBuyer = vehicleSystem();


        }

    }


    private static void addAVehicle() {
        String addNewVehicleToInventory = askString("What vehicles are you adding? Format: VehicleID|Make Model|Color|Odometer|Price ");
        String[] vehicle =  addNewVehicleToInventory.split("\\|");
        long vehicleId = Long.parseLong(vehicle[0]);
        String vehicleMakeModel = vehicle[1];
        String vehicleColor = vehicle[2];
        int vehicleOdometer = Integer.parseInt(vehicle[3]);
        float vehiclePrice = Float.parseFloat(vehicle[4]);
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.printf("\n Adding a new vehicle to the inventory... \n Vehicle ID     Make / Model       Color     Mileage        Price \n");
        System.out.printf("%10d %20s %10s %,10d %15s\n",vehicleId, vehicleMakeModel, vehicleColor, vehicleOdometer, usFormat.format(vehiclePrice));
        inventory.add(new Vehicle(vehicleId, vehicleMakeModel, vehicleColor, vehicleOdometer, vehiclePrice));
    }



    private static List<Vehicle> searchByColor() {
        String askColor = askString("What vehicle color are you looking for");
        List<Vehicle> matchingVehicle = new ArrayList<>();

        for (Vehicle v : inventory){
            if (askColor.equalsIgnoreCase(v.getColor())){
                matchingVehicle.add(v);
            }
        }

        System.out.println("Found: " + matchingVehicle.size() + (matchingVehicle.size() == 1 ? " result" : " results"));
        displayVehicles( matchingVehicle.toArray(new Vehicle[0]), matchingVehicle.size());
        return matchingVehicle;
    }


    private static List<Vehicle> searchByMake() {
        String askModel = askString("What vehicle model are you looking for");
        List<Vehicle> matchingVehicle = new ArrayList<>();

        for(Vehicle v : inventory){
            if (askModel.equalsIgnoreCase(v.getMakeModel())){
                matchingVehicle.add(v);
            }
        }
        System.out.println("Found: " + matchingVehicle.size() + (matchingVehicle.size() == 1 ? " result" : " results"));
        displayVehicles( matchingVehicle.toArray(new Vehicle[0]), matchingVehicle.size());
        return matchingVehicle;
    }

    private static void searchByPriceRange() {

        float priceFilterSystem = priceFilterSystem();
        switch ((int) priceFilterSystem) {
            case 1:
                filterByMinPrice();
                break;
            case 2:
                filterByMaxPrice();
                break;
            case 3:
                filterByMinMax();
                break;
        }


    }


    private static List<Vehicle> filterByMinPrice() {
        float askPrice = askFloat("Whats the minimum price");
        List<Vehicle> matchingVehicle = new ArrayList<>();

        for(Vehicle v : inventory){
            if (askPrice < (v.getPrice())){
                matchingVehicle.add(v);
            }
        }
        System.out.println("Found: " + matchingVehicle.size() + (matchingVehicle.size() == 1 ? " result" : " results"));
        displayVehicles( matchingVehicle.toArray(new Vehicle[0]), matchingVehicle.size());
        return matchingVehicle;
    }

    private static List<Vehicle> filterByMaxPrice() {
        float askPrice = askFloat("Whats the maximum price");
        List<Vehicle> matchingVehicle = new ArrayList<>();

        for(Vehicle v : inventory){
            if (askPrice > (v.getPrice())){
                matchingVehicle.add(v);
            }
        }
        System.out.println("Found: " + matchingVehicle.size() + (matchingVehicle.size() == 1 ? " result" : " results"));
        displayVehicles( matchingVehicle.toArray(new Vehicle[0]), matchingVehicle.size());
        return matchingVehicle;
    }
    private static List<Vehicle> filterByMinMax() {
        float askMinPrice = askFloat("Whats the minimum price: ");
        float askMaxPrice = askFloat("Whats the maximum price: ");
        List<Vehicle> matchingVehicle = new ArrayList<>();

        for(Vehicle v : inventory){
            if (askMaxPrice > (v.getPrice()) && askMinPrice < v.getPrice()){
                matchingVehicle.add(v);
            }
        }
        System.out.println("Found: " + matchingVehicle.size() + (matchingVehicle.size() == 1 ? " result" : " results"));
        displayVehicles( matchingVehicle.toArray(new Vehicle[0]), matchingVehicle.size());
        return matchingVehicle;
    }

    private static int priceFilterSystem() {
        boolean validInput = false;
        int input = 0;



        while(!validInput){
            try {
                System.out.printf("\n How would you like to filter by?" +
                        "\n 1. Minimum Price" +
                        "\n 2. Maximum Price" +
                        "\n 3. Minimum and Maximum Price"

                );
                input = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e){
                System.out.printf("That is not a valid input. Please try again");
                scanner.nextLine();
            }

        }

        return input;

    }


    private static void listAllVehicles(List<Vehicle> vehicles, int numberOfVehicles) {
        System.out.println("List Vehicles");
        displayVehicles( vehicles.toArray(new Vehicle[0]), numberOfVehicles);


    }

    private static List <Vehicle> findVehiclesByPrice() {
        return null;
    }

    private static void displayVehicles(Vehicle[] vehicles, int numberOfVehicles) {

        System.out.printf("Vehicle ID     Make / Model       Color     Mileage        Price \n");
        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicle v = vehicles[i];
            NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
            System.out.printf("%10d %20s %10s %,10d %15s\n",
                    v.getVehicleId(),
                    v.getMakeModel(),
                    v.getColor(),
                    v.getOdometer(),
                    usFormat.format(v.getPrice())
            );
        }
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

                input = scanner.nextInt();
                scanner.nextLine();
                validInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Sorry that is not a valid input. Please try again");
                scanner.nextLine();


            }
        }
        return input;
    }

    public static String askString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();

    }

    public static float askFloat(String prompt){
        System.out.println(prompt);
        return scanner.nextFloat();

    }
    public static int askInt(String prompt){
        System.out.println(prompt);
        return scanner.nextInt();

    }
}


