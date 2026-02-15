package view;

import constant.MessageConstants;
import helper.BrandTableFormatter;
import helper.CarTableFormatter;
import helper.InputValidator;
import java.util.List;
import model.Brand;
import model.Car;

public class ShowRoomView {

    private final InputValidator inputValidator;
    private final BrandTableFormatter brandTableFormatter;
    private final CarTableFormatter carTableFormatter;

    public ShowRoomView(InputValidator inputValidator,
            BrandTableFormatter brandTableFormatter,
            CarTableFormatter carTableFormatter) {
        this.inputValidator = inputValidator;
        this.brandTableFormatter = brandTableFormatter;
        this.carTableFormatter = carTableFormatter;
    }

    // ===== MENU =====
    public void displayMainMenu() {
        System.out.println("\n");
        System.out.println(MessageConstants.MAIN_MENU_TITLE);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_1);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_2);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_3);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_4);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_5);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_6);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_7);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_8);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_9);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_10);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_11);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_12);
        System.out.println(MessageConstants.MAIN_MENU_OPTION_13);
        System.out.print(MessageConstants.ENTER_CHOICE);
    }

    public int getChoice(int min, int max) {
        return inputValidator.validateIntInput(min, max);
    }

    // ===== BRAND =====
    public void displayBrands(List<Brand> brands) {
        if (brands == null || brands.isEmpty()) {
            System.out.println(MessageConstants.BRAND_NOT_FOUND);
            return;
        }
        brandTableFormatter.displayTable(brands);
    }

    public String getBrandIdInput() {
        return inputValidator.validateStringInput(MessageConstants.ENTER_BRAND_ID);
    }

    public Brand getBrandInput(String id) {

        String name = inputValidator.validateStringInput(MessageConstants.ENTER_BRAND_NAME);
        String sound = inputValidator.validateStringInput(MessageConstants.ENTER_BRAND_SOUND);
        String price = inputValidator.validatePriceInput(MessageConstants.ENTER_BRAND_PRICE);
        return new Brand(id, name, sound, price);
    }

    public Brand getUpdatedBrandInput(String id, Brand existingBrand) {
        System.out.println(MessageConstants.ENTER_NEW_DETAILS);

        String name = inputValidator.validateStringInput("Name (" + existingBrand.getName() + "): ");
        name = name.isEmpty() ? existingBrand.getName() : name;

        String sound = inputValidator.validateStringInput("Sound (" + existingBrand.getSound() + "): ");
        sound = sound.isEmpty() ? existingBrand.getSound() : sound;

        String priceInput = inputValidator.validateStringInput("Price (" + existingBrand.getPrice() + "): ");
        String price = priceInput.isEmpty() ? existingBrand.getPrice() : priceInput;

        return new Brand(id, name, sound, price);
    }

    public double getMaxPriceInput() {
        return inputValidator.validatePositiveDoubleInput(MessageConstants.ENTER_MAX_PRICE);
    }

    // ===== CAR =====
    public void displayCars(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            System.out.println(MessageConstants.CAR_NOT_FOUND);
            return;
        }
        carTableFormatter.displayTable(cars);
    }

    public String getCarIdInput() {
        return inputValidator.validateStringInput("Enter Car ID: ");
    }

    public Car getCarInput(String id, List<Brand> brands) {
        System.out.println("Enter new car details:");
        Brand brand = chooseBrandFromMenu(brands);
        if (brand == null) {
            return null;
        }
        String color = inputValidator.validateStringInput("Enter Color: ");

        String frameId;
        while (true) {
            frameId = inputValidator.validateStringInput("Enter Frame ID (F12345): ");
            if (inputValidator.isValidFrameId(frameId)) {
                break;
            }
            System.out.println(MessageConstants.FRAME_ID_ERROR);
        }

        String engineId;
        while (true) {
            engineId = inputValidator.validateStringInput("Enter Engine ID (E12345): ");
            if (inputValidator.isValidEngineId(engineId)) {
                break;
            }
            System.out.println(MessageConstants.FRAME_ID_ERROR);
        }

        return new Car(id, brand.getId(), color, frameId, engineId);
    }

    public String getColorInput() {
        return inputValidator.validateStringInput("Enter Color to filter: ");
    }

    public String getBrandNameInput() {
        return inputValidator.validateStringInput("Enter partial Brand Name to search cars: ");
    }

    public Car getUpdatedCarInput(String id, Car car) {
        System.out.println(MessageConstants.ENTER_NEW_DETAILS);

        String color = inputValidator.validateStringInput("Name: (" + car.getColor() + "): ");
        color = color.isEmpty() ? car.getColor() : color;

        String frameId;
        while (true) {
            String inputFrameId = inputValidator.validateStringInput("Frame ID (" + car.getFrameId() + "): ");
            if (inputFrameId.isEmpty()) {
                frameId = car.getFrameId();
                break;
            } else if (inputValidator.isValidFrameId(inputFrameId)) {
                frameId = inputFrameId;
                break;
            } else {
                System.out.println(MessageConstants.INVALID_FRAME_ID_FORMAT);
            }
        }

        String engineId;
        while (true) {
            String inputEngineId = inputValidator.validateStringInput("Engine ID (" + car.getEngineId() + "): ");
            if (inputEngineId.isEmpty()) {
                engineId = inputEngineId;
                break;
            } else if (inputValidator.isValidEngineId(inputEngineId)) {
                engineId = inputEngineId;
                break;
            } else {
                System.out.println(MessageConstants.INVALID_ENGINE_ID_FORMAT);
            }
        }

        return new Car(id, car.getBrandId(), color, frameId, engineId);
    }

    public Brand chooseBrandFromMenu(List<Brand> brands) {
        displayBrands(brands);

        while (true) {
            String brandId = inputValidator.validateStringInput(MessageConstants.ENTER_BRAND_ID).trim();
            for (Brand b : brands) {
                if (b.getId().trim().equalsIgnoreCase(brandId)) {
                    return b;
                }
            }
            System.out.println("Brand not found. Please try again.");
        }

    }

    // ===== MESSAGE =====
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getConfirmation(String message) {
        return inputValidator.validateStringInput(message);
    }

}
