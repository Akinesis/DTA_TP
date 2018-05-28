package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.model.Pizza;

public class MenuAdd implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(Pizza[] pizzas){

        String tempCode,tempLibel;
        double tempPrice;

        int nextFreeSlot = 0;

        System.out.println("Veuillez saisir le code :");
        tempCode = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le nom (sans espace) :");
        tempLibel = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le prix :");
        tempPrice = Double.parseDouble(KeyboardReader.readInput());

        //iterate through the pizza array until a free slot is found
        while(pizzas[nextFreeSlot] != null){
            nextFreeSlot++;
        }

        pizzas[nextFreeSlot] = new Pizza(tempCode,tempLibel,tempPrice);

        //application don't end after this statement
        return false;
    }
}
