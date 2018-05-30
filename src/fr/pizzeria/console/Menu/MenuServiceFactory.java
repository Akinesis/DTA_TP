package fr.pizzeria.console.Menu;

import fr.pizzeria.console.PizzaMemDao;

public class MenuServiceFactory {

    private MenuService menuChoice;

    public MenuServiceFactory(MenuService choice){
        menuChoice = choice;
    }

    //Execute all the code for the current menu choice
    public boolean executeMenuLine(PizzaMemDao pizzasManager){
        return menuChoice.executeUC(pizzasManager);
    }

    // Link the correct menu strategy based on the user selection
    // Incorrect value should not be possible
    public void setMenuChoice(int choice){
        switch (choice) {
            case 1:
                menuChoice = new ListerPizzasService();
                break;
            case 2:
                menuChoice = new AjouterPizzaService();
                break;
            case 3:
                menuChoice = new ModifierPizzaService();
                break;
            case 4:
                menuChoice = new SupprimerPizzaService();
                break;
            case 99:
                menuChoice = new QuitterPizzaService();
                break;
            default:
                break;
        }
    }
}
