package fr.pizzeria.console.menu;

import fr.pizzeria.Utils.KeyboardReader;
import fr.pizzeria.console.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService implements MenuService {

	public boolean executeUC(IPizzaDao pizzasManager) throws StockageException{

		String oldCode;
		String tempCode,tempLibel;
		double tempPrice;
		CategoriePizza tempCateg;

		//show the list of all the pizza in the array.
		for (Pizza p : pizzasManager.findAllPizzas()) {
			System.out.println(p.toString());
		}

		try{
			System.out.println("Veuillez choisir le code de la pizza à modifier.");
			oldCode = KeyboardReader.readInput();

			System.out.println("Veuillez saisir le code :");
			tempCode = KeyboardReader.readInput();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			tempLibel = KeyboardReader.readInput();
			System.out.println("Veuillez saisir le prix :");
			tempPrice = Double.parseDouble(KeyboardReader.readInput());
			System.out.println("Veuillez saisir la catégorie (viande, poisson, sans viande :");
			tempCateg = CategoriePizza.findByValue(KeyboardReader.readInput().toLowerCase());
		}catch (Exception e) {
			throw new SavePizzaException("Problème de lecture des entrées.");
		}


		//Update the pizza using the pizza DAO
		pizzasManager.updatePizza(oldCode, new Pizza(tempCode,tempLibel,tempPrice,tempCateg));



		//application don't end after this statement
		return false;
	}
}