package fr.pizzeria.console.Menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaService implements MenuService {

    public boolean executeUC(PizzaMemDao pizzasManager) throws StockageException {

        String delCode;
        int i = 0;

        //show the list of all the pizza in the array.
        for (Pizza p : pizzasManager.findAllPizzas()) {
            System.out.println(p.toString());
        }

        System.out.println("Veuillez choisir le code de la pizza à supprimer :");
        delCode = KeyboardReader.readInput();

        //Delete the pizza using the pizza DAO
        pizzasManager.deletePizza(delCode);

        //application don't end after this statement
        return false;
    }
}
