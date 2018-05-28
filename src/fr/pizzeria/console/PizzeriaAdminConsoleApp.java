package fr.pizzeria.console;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.Menu.MenuList;
import fr.pizzeria.console.Menu.MenuSwich;
import fr.pizzeria.model.Pizza;


public class PizzeriaAdminConsoleApp {


    private MenuSwich menu;
    private Pizza[] pizzas = new Pizza[99];

    private boolean applicationIsTerminated;

    public static void main(String[] args) {
        PizzeriaAdminConsoleApp app = new PizzeriaAdminConsoleApp();

        app.initiatePizzaArray();

        int userInput = -1;

        while (!app.isTerminated()) {

            app.startScreen();

            //read the user input and parse it to Integer
            userInput = Integer.parseInt(KeyboardReader.readInput());

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
        //default state is set to list all pizza
        menu = new MenuSwich(new MenuList());
        //boolean value to check if the application must stop.
        applicationIsTerminated = false;
    }

    //execute the code for the user choice
    public void executeMenuLine() {
        applicationIsTerminated = menu.executeMenuLine(pizzas);
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

    private void initiatePizzaArray() {

        pizzas[0] = new Pizza("PEP", "Pépéroni", 12.5);
        pizzas[1] = new Pizza("MAR", "Margherita", 15);
        pizzas[2] = new Pizza("REIN", "La Reine", 11.5);
        pizzas[3] = new Pizza("FRO", "La 4 fromages", 12);
        pizzas[4] = new Pizza("CAN", "La cannibale", 12.5);
        pizzas[5] = new Pizza("SAV", "La savoyarde", 13);
        pizzas[6] = new Pizza("ORI", "L’orientale", 13.5);
        pizzas[7] = new Pizza("IND", "L’indienne", 14);
    }
}
