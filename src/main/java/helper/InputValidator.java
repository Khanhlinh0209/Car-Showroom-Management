package helper;

import constant.MessageConstants;
import java.util.Scanner;
import model.BrandDAO;
import model.CarDAO;

public class InputValidator {

    private final InputPrompter prompter;
    private final Scanner scanner = new Scanner(System.in);

    public InputValidator(InputPrompter prompter) {
        this.prompter = prompter;
    }

    public int validateIntInput(String prompt, int min, int max) {
        while (true) {
            String input = prompter.promptForString(prompt);
            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf(MessageConstants.INVALID_INPUT_RANGE, min, max);
            } catch (NumberFormatException e) {
                System.out.println(MessageConstants.INVALID_INPUT_NUMBER);
            }
        }
    }

    public int validateIntInput(int min, int max) {
        while (true) {
            String input = prompter.promptForStringNoMessage();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf(MessageConstants.INVALID_INPUT_RANGE, min, max);
            } catch (NumberFormatException e) {
                System.out.println(MessageConstants.INVALID_INPUT_NUMBER);
            }
        }
    }

    public double validateDoubleInput(String prompt) {
        while (true) {
            String input = prompter.promptForString(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(MessageConstants.INVALID_INPUT_NUMBER);
            }
        }
    }

    public double validatePositiveDoubleInput(String prompt) {
        while (true) {
            double value = validateDoubleInput(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println(MessageConstants.INVALID_INPUT_POSITIVE);
        }
    }

    public String validateStringInput(String prompt) {
        return prompter.promptForString(prompt);
    }

    public String validateUniqueIdInput(String prompt, BrandDAO brandDAO, CarDAO carDAO) {
        while (true) {
            String id = prompter.promptForString(prompt).trim();

            if (id.isEmpty()) {
                System.out.println(MessageConstants.ID_CANNOT_BE_EMPTY);
                continue;
            }

            if (brandDAO.findBrandByID(id) != null) {
                System.out.println(MessageConstants.ID_ALREADY_EXISTS + " (Brand ID)");
                continue;
            }

            if (carDAO.findCarById(id) != null) {
                System.out.println(MessageConstants.ID_ALREADY_EXISTS + " (Car ID)");
                continue;
            }

            return id;
        }

    }

    /*
    "^F\\d{5}$"
    ^E\\d{5}$
    */
    public boolean isValidFrameId(String frameId) {
        return frameId != null && frameId.matches("^F\\d{5}$");
    }

    public boolean isValidEngineId(String engineId) {
        return engineId != null && engineId.matches("^E\\d{5}$");
    }

    public String validatePriceInput(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return input; 
                } else {
                    System.out.println("Invalid input. Price must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

}
