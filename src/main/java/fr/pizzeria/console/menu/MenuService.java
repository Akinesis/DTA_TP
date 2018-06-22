package fr.pizzeria.console.menu;

import fr.pizzeria.console.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public interface MenuService {
   boolean executeUC(IPizzaDao pizzasManager) throws StockageException;
}