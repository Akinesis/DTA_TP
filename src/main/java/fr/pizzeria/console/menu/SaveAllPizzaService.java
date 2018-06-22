package fr.pizzeria.console.menu;

import fr.pizzeria.console.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class SaveAllPizzaService implements MenuService {

	public boolean executeUC(IPizzaDao pizzasManager) throws StockageException {
		
		pizzasManager.saveAllPizzaIntoDb();
		
		return false;
	}
	
}
