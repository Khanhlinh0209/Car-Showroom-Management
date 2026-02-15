package helper;

import java.util.Scanner;

public class InputPrompter {

    private final Scanner scanner = new Scanner(System.in);

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public String promptForStringNoMessage() {
        return scanner.nextLine();
    }
}
