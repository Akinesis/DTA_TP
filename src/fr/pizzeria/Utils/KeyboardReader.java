package fr.pizzeria.Utils;

import java.util.Scanner;

/*
Small utility class to read user input.
It can be use without having the need to instantiate it so it can be used from anywhere.
 */

public class KeyboardReader {

    private static Scanner scan = new Scanner(System.in);

    public static String readInput(){

        return scan.nextLine();
    }
}
