package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService implements MenuService {

    public boolean executeUC(PizzaMemDao pizzasManager){

        String oldCode;
        String tempCode,tempLibel;
        double tempPrice;
        int i = 0;

        //show the list of all the pizza in the array.
        for(Pizza p : pizzasManager.findAllPizzas()){
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

        //Update the pizza using the pizza DAO
        pizzasManager.updatePizza(oldCode, new Pizza(tempCode,tempLibel,tempPrice));


        //application don't end after this statement
        return false;
    }
}
