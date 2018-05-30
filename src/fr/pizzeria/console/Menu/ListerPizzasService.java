package fr.pizzeria.console.Menu;

import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService implements MenuService {

    public boolean executeUC(PizzaMemDao pizzasManager){

        //show the list of all the pizza in the array.
        for(Pizza p : pizzasManager.findAllPizzas()){
            if (p != null){
                System.out.println(p.toString());
            }
        }

        //application don't end after this statement
        return false;
    }
}
