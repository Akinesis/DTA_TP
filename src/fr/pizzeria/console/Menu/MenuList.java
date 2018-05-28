package fr.pizzeria.console.Menu;

import fr.pizzeria.model.Pizza;

public class MenuList implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(Pizza[] pizzas){

        //show the list of all the pizza in the array.
        for(Pizza p : pizzas){
            if (p != null){
                System.out.println(p.toString());
            }
        }

        //application don't end after this statement
        return false;
    }
}
