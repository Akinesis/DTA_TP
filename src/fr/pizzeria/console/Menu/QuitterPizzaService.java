package fr.pizzeria.console.Menu;

import fr.pizzeria.console.PizzaMemDao;

//Note, this class as been rename too to keep consistency
public class QuitterPizzaService implements MenuService {

    public boolean executeUC(PizzaMemDao pizzasManager){
        System.out.println("Aurevoir ☹");

        //application end after this statement
        return true;
    }
}
