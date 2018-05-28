package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.model.Pizza;

import java.security.Key;

public class MenuDelete implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(Pizza[] pizzas){

        String delCode;
        int i = 0;

        //show the list of all the pizza in the array.
        for(Pizza p : pizzas){
            if (p != null){
                System.out.println(p.toString());
            }
        }

        System.out.println("Veuillez choisir le code de la pizza à supprimer :");
        delCode = KeyboardReader.readInput();

        //Search for the array's index of the pizza to delete
        for(Pizza p : pizzas){
            if (p != null){
                if(p.getCode().equals(delCode)){
                    pizzas[i] = null;
                    break;
                }
            }
            i++;
        }

        //application don't end after this statement
        return false;
    }
}
