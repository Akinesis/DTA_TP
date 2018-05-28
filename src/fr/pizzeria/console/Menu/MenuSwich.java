package fr.pizzeria.console.Menu;

import fr.pizzeria.model.Pizza;

public class MenuSwich {

    private MenuItem menuChoice;

    public MenuSwich(MenuItem choice){
        menuChoice = choice;
    }

    //Execute all the code for the current menu choice
    public boolean executeMenuLine(Pizza[] pizzas){
        return menuChoice.handleChoice(pizzas);
    }

    // Link the correct menu strategy based on the user selection
    // Incorrect value should not be possible
    public void setMenuChoice(int choice){
        switch (choice) {
            case 1:
                menuChoice = new MenuList();
                break;
            case 2:
                menuChoice = new MenuAdd();
                break;
            case 3:
                menuChoice = new MenuUpdate();
                break;
            case 4:
                menuChoice = new MenuDelete();
                break;
            case 99:
                menuChoice = new MenuQuit();
                break;
            default:
                break;
        }
    }
}
