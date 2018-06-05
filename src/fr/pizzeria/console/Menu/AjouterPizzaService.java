package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService implements MenuService {

    public boolean executeUC(PizzaMemDao pizzasManager) throws StockageException {

        String tempCode,tempLibel;
        double tempPrice;
        CategoriePizza tempCateg;

        int nextFreeSlot = 0;

        System.out.println("Veuillez saisir le code :");
        tempCode = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le nom (sans espace) :");
        tempLibel = KeyboardReader.readInput();
        System.out.println("Veuillez saisir le prix :");
        tempPrice = Double.parseDouble(KeyboardReader.readInput());
        System.out.println("Veuillez saisir la catégorie (viande, poisson, sans viande :");
        tempCateg = CategoriePizza.findByValue(KeyboardReader.readInput());

        //Save the new pizza using th pizza dao
        pizzasManager.saveNewPizza(new Pizza(tempCode,tempLibel,tempPrice,tempCateg));

        //application don't end after this statement
        return false;
    }
}
