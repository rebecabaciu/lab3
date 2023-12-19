package UI;

import Domain.Car;
import Domain.Rent;
import Service.RentService;
import Service.CarService;
import Exception.RentException;
import Exception.CarException;

import java.io.IOException;
import java.util.*;

public class RentUI {
    private RentService rentService;
    private CarService carService;
    private Scanner scanner;

    public RentUI(RentService rentService, CarService carService) {
        this.rentService = rentService;
        this.carService = carService;
        this.scanner = new Scanner(System.in);
    }

    public void runMenu() throws IOException {
        while (true) {
            System.out.println("Rent Menu");
            System.out.println("1. Add rent");
            System.out.println("2. Show All Rents");
            System.out.println("3. Find Rent by ID");
            System.out.println("4. Update Rent");
            System.out.println("5. Delete Rent");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addRent();
                    break;
                case 2:
                    showAllRents();
                    break;
                case 3:
                    findRentById();
                    break;
                case 4:
                    updateRent();
                    break;
                case 5:
                    deleteRent();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option... try again!");
            }
        }
    }

    public void addRent() throws IOException {
        System.out.println("Enter Rent ID: ");
        int id = scanner.nextInt();
        Car car = new Car(-1, "", "");

        try {
            System.out.println("Enter Car ID: ");
            int carId = scanner.nextInt();
            car = carService.getCarById(carId);
        } catch (CarException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (car != null) {
            System.out.print("Enter Start Date: ");
            String startDate = scanner.next();
            System.out.print("Enter End Date: ");
            String endDate = scanner.next();

            if (car.getId() != -1) {
                try {
                    rentService.addRent(id, car, startDate, endDate);
                    System.out.println("Rent added successfully.");
                } catch (RentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Car not found, cannot add rent.");
            }
        } else {
            System.out.println("Car not found, cannot add rent.");
        }
    }



    public void showAllRents() {
        List<Rent> rents = rentService.getAllRents();
        for(Rent rent: rents) {
            System.out.println(rent.toString());
        }
    }

    public void findRentById() {
        System.out.println("Enter Rent ID: ");
        int id = scanner.nextInt();
        try {
            Rent rent = rentService.getRentById(id);
            System.out.println(rent.toString());
        } catch (RentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateRent() throws IOException {
        System.out.print("Enter Rent ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter New Start Date: ");
        String newStartDate = scanner.next();
        System.out.print("Enter New End Date: ");
        String newEndDate = scanner.next();

        try {
            rentService.updateRent(id, newStartDate, newEndDate);
            System.out.println("Rent updated successfully.");
        } catch (RentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteRent() throws IOException {
        System.out.print("Enter Rent ID: ");
        int id = scanner.nextInt();
        try {
            rentService.deleteRent(id);
            System.out.println("Rent deleted successfully.");
        } catch (RentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

