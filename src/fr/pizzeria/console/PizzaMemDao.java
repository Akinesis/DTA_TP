package fr.pizzeria.console;

import fr.pizzeria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaMemDao implements IPizzaDao {

    private List<Pizza> pizzas;

    public PizzaMemDao() {
        pizzas = new ArrayList<Pizza>();

        saveNewPizza(new Pizza("PEP", "Pépéroni", 12.5));
        saveNewPizza(new Pizza("MAR", "Margherita", 15));
        saveNewPizza(new Pizza("REIN", "La Reine", 11.5));
        saveNewPizza(new Pizza("FRO", "La 4 fromages", 12));
        saveNewPizza(new Pizza("CAN", "La cannibale", 12.5));
        saveNewPizza(new Pizza("SAV", "La savoyarde", 13));
        saveNewPizza(new Pizza("ORI", "L’orientale", 13.5));
        saveNewPizza(new Pizza("IND", "L’indienne", 14));
    }

    @Override
    public List<Pizza> findAllPizzas() {
        return pizzas;
    }

    @Override
    public void saveNewPizza(Pizza pizza) {

        //add the new pizza
        pizzas.add(pizza);

    }

    @Override
    public void updatePizza(String codePizza, Pizza pizza) {

        int i = 0;

        //Look for the index of the pizza to update and replace it
        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                pizzas.remove(p);
                pizzas.add(pizza);
                break;
            }

        }
    }

    @Override
    public void deletePizza(String codePizza) {

        int i = 0;

        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                pizzas.remove(p);
                break;
            }
        }
    }

    @Override
    public Pizza findPizzaByCode(String codePizza) {

        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                return p;
            }
        }

        return null;
    }

    @Override
    public boolean pizzaExists(String codePizza) {

        for (Pizza p : pizzas) {
            if (p.getCode().equals(codePizza)) {
                return true;
            }
        }

        return false;
    }
}
