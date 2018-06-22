package fr.pizzeria.console;

import java.io.IOException;

import fr.pizzeria.Utils.ConfigReader;
import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.menu.ListerPizzasService;
import fr.pizzeria.console.menu.MenuServiceFactory;
import fr.pizzeria.exception.StockageException;


public class PizzeriaAdminConsoleApp {


    private MenuServiceFactory menu;
    private IPizzaDao pizzasManager;

    private boolean applicationIsTerminated;

    public static void main(String[] args) {
    	try {
			ConfigReader.loadConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	PizzeriaAdminConsoleApp app = new PizzeriaAdminConsoleApp();

        int userInput = -1;

        while (!app.isTerminated()) {

            app.startScreen();

            //read the user input and parse it to Integer
            userInput = Integer.parseInt(KeyboardReader.readInput());

            if ((userInput > 0 && userInput < 6) || userInput == 99) {

                app.setMenuChoice(userInput);
                app.executeMenuLine();
            }
        }

    }

    private void setMenuChoice(int userInput) {
        //note : Required implementation hse been encapsulated directly inside the "executeMenuLine" function of the factory.
        menu.setMenuChoice(userInput);
    }

    public PizzeriaAdminConsoleApp() {
        //default state is set to list all pizza
        menu = new MenuServiceFactory(new ListerPizzasService());
        //boolean value to check if the application must stop.
        applicationIsTerminated = false;
        //create the Pizza doa object. First pizzas are initiate int the constructor
        pizzasManager = new PizzaDbDao();
        
        System.out.println(pizzasManager.pizzaExists("MAR"));
    }

    //execute the code for the user choice
    public void executeMenuLine() {
        try {
            applicationIsTerminated = menu.executeMenuLine(pizzasManager);
        } catch (StockageException e) {
            e.printStackTrace();
        }
    }

    private void startScreen() {
        System.out.println("\n***** Pizzeria Administration *****");
        System.out.println("1. Lister les pizzas \n" +
                "2. Ajouter une nouvelle pizza\n" +
                "3. Mettre à jour une pizza\n" +
                "4. Supprimer une pizza\n" +
                "5. Sauvegarder toutes les pizzas en base\n" +
                "99. Sortir\n");
    }

    public boolean isTerminated() {
        return applicationIsTerminated;
    }

}
