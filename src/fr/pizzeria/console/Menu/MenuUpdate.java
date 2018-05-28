package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.model.Pizza;

public class MenuUpdate implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(Pizza[] pizzas){

        String oldCode;
        String tempCode,tempLibel;
        double tempPrice;
        int i = 0;

        //show the list of all the pizza in the array.
        for(Pizza p : pizzas){
            if (p != null){
                System.out.println(p.toString());
            }
        }

        System.out.println("Veuillez choisir le code de la pizza à modifier.");
        oldCode = KeyboardReader.readInput();

        System.out.println("Veuillez saisir le code :");
        tempCode = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le nom (sans espace) :");
        tempLibel = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le prix :");
        tempPrice = Double.parseDouble(KeyboardReader.readInput());

        //Look for the index of the pizza to delete and replace it
        for(Pizza p : pizzas){
            if (p != null){
                if(p.getCode().equals(oldCode)){
                    pizzas[i] = new Pizza(p.getID(),tempCode,tempLibel,tempPrice);
                    break;
                }
            }
            i++;
        }

        //application don't end after this statement
        return false;
    }
}
