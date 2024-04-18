package edu.penzgtu.view;

import edu.penzgtu.model.api.CryptoCompare;

public class ConsolePrinter {

    public static void printUserChoice() {
        System.out.println(
                "+-------------------------+\n" +
                "| Data Loading Method     |\n" +
                "+-------------------------+\n" +
                "|1.Load from File         |\n" +
                "|2.Load from Internet     |\n" +
                "+-------------------------+"
        );
    }

    public static String resultInputUserChoice(CryptoCompare cryptoCompare) {
        return "Currency pair:\n" +
                cryptoCompare.getCurrency1() +
                "-"+cryptoCompare.getCurrency2();
    }
}