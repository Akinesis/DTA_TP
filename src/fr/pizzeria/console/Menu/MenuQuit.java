package fr.pizzeria.console.Menu;

import fr.pizzeria.model.Pizza;

public class MenuQuit implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(Pizza[] pizzas){
        System.out.println("Aurevoir ☹");

        //application end after this statement
        return true;
    }
}
