package fr.pizzeria.console;

import fr.pizzeria.exception.*;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaMemDao implements IPizzaDao {

    private List<Pizza> pizzas;

    public PizzaMemDao() {
        pizzas = new ArrayList<Pizza>();

        try {
            saveNewPizza(new Pizza("PEP", "Pépéroni", 12.5, CategoriePizza.VIANDE));
            saveNewPizza(new Pizza("MAR", "Margherita", 15,CategoriePizza.VIANDE));
            saveNewPizza(new Pizza("REIN", "La Reine", 11.5,CategoriePizza.SANS_VIANDE));
            saveNewPizza(new Pizza("FRO", "La 4 fromages", 12,CategoriePizza.SANS_VIANDE));
            saveNewPizza(new Pizza("CAN", "La cannibale", 12.5,CategoriePizza.VIANDE));
            saveNewPizza(new Pizza("SAV", "La savoyarde", 13,CategoriePizza.VIANDE));
            saveNewPizza(new Pizza("ORI", "L’orientale", 13.5,CategoriePizza.POISSON));
            saveNewPizza(new Pizza("IND", "L’indienne", 14,CategoriePizza.SANS_VIANDE));
        } catch (StockageException e) {
            e.printStackTrace();
        }
    }

    public List<Pizza> findAllPizzas() {
        return pizzas;
    }


    public void saveNewPizza(Pizza pizza)throws StockageException {

    	if(pizza != null){
        	if(pizza.getPrix() <= 0){
                throw new SavePizzaException("Prix pizza négatif");
            }
        	
            //add the new pizza
            pizzas.add(pizza);

            if(!pizzas.contains(pizza)){
                throw new SavePizzaException("Pizza non enregistrée.");
            }
    	}

    }


    public void updatePizza(String codePizza, Pizza pizza) throws StockageException {

        int i = 0;
        boolean pizzaIsFound = false;
        
        if(pizza.getPrix() <= 0){
            throw new UpdatePizzaException("Prix pizza négatif");
        }

        //Look for the index of the pizza to update and replace it
        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                pizzas.remove(p);
                pizzas.add(pizza);
                pizzaIsFound = true;
                break;
            }
        }

        if(!pizzaIsFound){
            throw new UpdatePizzaException("Pizza non trouvée.");
        }
    }


    public void deletePizza(String codePizza) throws StockageException {

        int i = 0;
        boolean pizzaIsFound = false;

        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                pizzas.remove(p);
                pizzaIsFound = true;
                break;
            }
        }

        if(!pizzaIsFound){
            throw new DeletePizzaException("Pizza non trouvée.");
            
        }
    }
    
    public void savePizzaInDb(Pizza pizza){
    	
    }


    public Pizza findPizzaByCode(String codePizza) {

        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                return p;
            }
        }

        return null;
    }


    public boolean pizzaExists(String codePizza) {

        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                return true;
            }
        }

        return false;
    }
}
