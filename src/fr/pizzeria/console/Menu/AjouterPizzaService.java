package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService implements MenuService {

    public boolean executeUC(PizzaMemDao pizzasManager){

        String tempCode,tempLibel;
        double tempPrice;

        int nextFreeSlot = 0;

        System.out.println("Veuillez saisir le code :");
        tempCode = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le nom (sans espace) :");
        tempLibel = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le prix :");
        tempPrice = Double.parseDouble(KeyboardReader.readInput());

        //Save the new pizza using th pizza dao
        pizzasManager.saveNewPizza(new Pizza(tempCode,tempLibel,tempPrice));

        //application don't end after this statement
        return false;
    }
}
