package edu.penzgtu.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements MenuInterface {
    @Override
    public void clear() {
        System.out.print("\u001b[H\u001b[2J");
    }
    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }
    @Override
    public int inputInteger() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("int: ");
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer");
                scanner.nextLine();
            }
        }
    }
}
