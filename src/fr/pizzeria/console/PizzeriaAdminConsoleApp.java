package fr.pizzeria.console;

import fr.pizzeria.console.Menu.MenuList;
import fr.pizzeria.console.Menu.MenuSwich;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

    public Scanner scan;
    private MenuSwich menu;

    private boolean applicationIsTerminated;

    public static void main(String[] args) {
        PizzeriaAdminConsoleApp app = new PizzeriaAdminConsoleApp();

        int userInput = -1;

        while (!app.isTerminated()) {

            app.startScreen();

            userInput = app.scan.nextInt();

            if ((userInput > 0 && userInput < 5) || userInput == 99) {

                app.setMenuChoice(userInput);
                app.executeMenuLine();
            }
        }

    }

    private void setMenuChoice(int userInput) {
        menu.setMenuChoice(userInput);
    }

    public PizzeriaAdminConsoleApp() {
        scan = new Scanner(System.in);
        //default state is set to list all pizza
        menu = new MenuSwich(new MenuList());
        //boolean value to check if the application must stop.
        applicationIsTerminated = false;
    }

    //execute the code for the user choice
    public void executeMenuLine() {
        applicationIsTerminated = menu.executeMenuLine();
    }

    private void startScreen() {
        System.out.println("\n***** Pizzeria Administration *****");
        System.out.println("1. Lister les pizzas \n" +
                "2. Ajouter une nouvelle pizza\n" +
                "3. Mettre à jour une pizza\n" +
                "4. Supprimer une pizza\n" +
                "99. Sortir\n");
    }

    public boolean isTerminated(){
        return applicationIsTerminated;
    }
}
