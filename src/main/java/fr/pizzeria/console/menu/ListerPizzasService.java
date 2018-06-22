package fr.pizzeria.console.menu;

import fr.pizzeria.console.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService implements MenuService {

    public boolean executeUC(IPizzaDao pizzasManager) {

        //show the list of all the pizza in the array.
        for (Pizza p : pizzasManager.findAllPizzas()) {
            System.out.println(p.toString());
        }

        //application don't end after this statement
        return false;
    }
}
