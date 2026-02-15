package constant;

public class MessageConstants {

    private MessageConstants() {
        // Prevent instantiation
    }

    // ================= MENU =================
    public static final String MAIN_MENU_TITLE = "\n=== Car Showroom Management ===";
    public static final String MAIN_MENU_OPTION_1 = "1. List all brands";
    public static final String MAIN_MENU_OPTION_2 = "2. Add a new brand";
    public static final String MAIN_MENU_OPTION_3 = "3. Search brand by ID";
    public static final String MAIN_MENU_OPTION_4 = "4. Update brand by ID";
    public static final String MAIN_MENU_OPTION_5 = "5. List brands with price <= value";
    public static final String MAIN_MENU_OPTION_6 = "6. List cars by brand name (ascending)";
    public static final String MAIN_MENU_OPTION_7 = "7. Search cars by partial brand name";
    public static final String MAIN_MENU_OPTION_8 = "8. Add a new car";
    public static final String MAIN_MENU_OPTION_9 = "9. Remove a car by ID";
    public static final String MAIN_MENU_OPTION_10 = "10. Update a car by ID";
    public static final String MAIN_MENU_OPTION_11 = "11. List cars by color";
    public static final String MAIN_MENU_OPTION_12 = "12. Save data to file";
    public static final String MAIN_MENU_OPTION_13 = "13. Quit program";
    public static final String ENTER_CHOICE = "Enter your choice: ";

    // ================ INPUT PROMPTS =================
    public static final String ENTER_BRAND_DETAILS = "\nEnter Brand Details:";
    public static final String ENTER_BRAND_ID = "Brand ID: ";
    public static final String ENTER_BRAND_NAME = "Brand Name: ";
    public static final String ENTER_BRAND_PRICE = "Brand Price: ";
    public static final String ENTER_BRAND_SOUND = "Enter Sound System: ";

    public static final String ENTER_CAR_DETAILS = "\nEnter Car Details:";
    public static final String ENTER_CAR_ID = "Car ID: ";
    public static final String ENTER_CAR_BRAND_ID = "Brand ID: ";
    public static final String ENTER_CAR_COLOR = "Color: ";
    public static final String ENTER_CAR_FRAME_ID = "Frame ID (Format: Fxxxxx): ";
    public static final String ENTER_CAR_ENGINE_ID = "Engine ID (Format: Exxxxx): ";
    public static final String ENTER_CAR_PRICE = "Car Price: ";

    public static final String ENTER_SEARCH_BRAND_ID = "Enter Brand ID to search: ";
    public static final String ENTER_SEARCH_BRAND_NAME = "Enter Brand Name (partial): ";
    public static final String ENTER_SEARCH_CAR_ID = "Enter Car ID to search: ";
    public static final String ENTER_SEARCH_COLOR = "Enter Color: ";
    public static final String ENTER_MAX_PRICE = "Enter maximum price (unit: B, e.g. 3.749): ";

    public static final String FRAME_ID_ERROR = "Frame ID must be in format F12345 (F + 5 digits). Try again!";
    public static final String ENGINE_ID_ERROR = "Engine ID must be in format E12345 (E + 5 digits). Try again!";

    public static final String ENTER_NEW_DETAILS = "Enter new details (press Enter to keep old value)";

    // ================ SUCCESS =================
    public static final String BRAND_ADDED_SUCCESS = "Brand added successfully!";
    public static final String BRAND_UPDATED_SUCCESS = "Brand updated successfully!";
    public static final String CAR_ADDED_SUCCESS = "Car added successfully!";
    public static final String CAR_UPDATED_SUCCESS = "Car updated successfully!";
    public static final String CAR_DELETED_SUCCESS = "Car deleted successfully!";
    public static final String DATA_SAVED_SUCCESS = "Data saved to file successfully!";

    // ================ ERRORS =================
    public static final String BRAND_NOT_FOUND = "Brand not found!";
    public static final String CAR_NOT_FOUND = "Car not found!";
    public static final String FAILED_TO_ADD_BRAND = "Failed to add brand.";
    public static final String BRAND_UPDATE_FAILED = "Failed to update brand.";
    public static final String FAILED_TO_ADD_CAR = "Failed to add car.";
    public static final String CAR_UPDATE_FAILED = "Failed to update car.";
    public static final String CAR_DELETE_FAILED = "Failed to delete car.";

    public static final String BRAND_ID_EXISTS = "Brand ID already exists!";
    public static final String CAR_ID_EXISTS = "Car ID already exists!";
    public static final String INVALID_INPUT_NUMBER = "Invalid input. Please enter a number.";
    public static final String INVALID_RANGE = "Please enter a number between %d and %d.";
    public static final String INVALID_POSITIVE = "Value must be greater than 0.";
    public static final String EMPTY_INPUT = "Input cannot be empty!";
    public static final String DATA_SAVE_FAILED = "Failed to save data to file!";
    public static final String NO_BRAND_UNDER_PRICE
            = "No brand found with price less than or equal to ";

    // ================ CONFIRMATION =================
    public static final String DELETE_CONFIRMATION = "Are you sure you want to delete? (y/n): ";

    // ================ DISPLAY =================
    public static final String BRAND_LIST_TITLE = "\n=== Brand List ===";
    public static final String CAR_LIST_TITLE = "\n=== Car List ===";

    public static final String INVALID_INPUT_POSITIVE = "Value must be greater than 0!";

    public static final String ID_CANNOT_BE_EMPTY = "ID cannot be empty!";
    public static final String ID_ALREADY_EXISTS = "ID already exists!";

    public static final String INVALID_INPUT_RANGE = "Please enter a number between %d and %d: ";
    public static final String INVALID_FRAME_ID_FORMAT = "Frame ID must follow format F00000.";
    public static final String INVALID_ENGINE_ID_FORMAT = "Engine ID must follow format E00000.";

}
