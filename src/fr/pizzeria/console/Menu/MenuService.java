package fr.pizzeria.console.Menu;

import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public interface MenuService {
   boolean executeUC(PizzaMemDao pizzasManager);
}