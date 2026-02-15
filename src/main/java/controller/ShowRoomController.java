package controller;

import exception.FileOperationException;
import model.Brand;
import service.BrandService;
import service.CarService;
import view.ShowRoomView;

import java.util.List;


public class ShowRoomController {
    
    private final BrandService brandService;
    private final CarService carService;
    private final ShowRoomView view;

    public ShowRoomController(ShowRoomView view, BrandService brandService, CarService carService) {
        this.view = view;
        this.brandService = brandService;
        this.carService = carService;
    }

    public void start() {
        while (true) {
            view.displayMainMenu();
            int choice = view.getChoice(1, 13);

            switch (choice) {
                case 1: // List all brands
                    List<Brand> brands = brandService.getAllBrands();
                    view.displayBrands(brands);
                    break;

                case 2: // Add brand
                    brandService.addBrand();
                    break;

                case 3: // Search brand
                    brandService.findBrandByID();
                    break;

                case 4: // Update brand
                    brandService.updateBrandById();
                    break;

                case 5: // Filter brands by price
                    
                    brandService.listBrandsByMaxPrice();
                    break;

                case 6: // List cars sorted by brand name
                    carService.listCarsByBrandNameAsc();
                    break;

                case 7: // Search cars by partial brand name
                    carService.findByBrandName();
                    break;

                case 8: // Add car
                    carService.addCar();
                    break;

                case 9: // Remove car
                    carService.deleteCarById();
                    break;

                case 10: // Update car
                    carService.updateCarById();
                    break;

                case 11: // List cars by color
                    carService.listCarsByColor();
                    break;

                case 12: // Save to files
                try {
                    boolean savedBrands = brandService.saveToFile();
                    boolean savedCars = carService.saveToFile();
                    if (savedBrands && savedCars) {
                        view.displayMessage("Data saved successfully.");
                    } else {
                        view.displayMessage("Failed to save data.");
                    }
                } catch (FileOperationException e) {
                    view.displayMessage("Error saving data: " + e.getMessage());
                }
                break;

                case 13: // Quit
                    view.displayMessage("Exiting program...");
                    return;

                default:
                    view.displayMessage("Invalid choice.");
                    break;
            }
        }
    }
}
