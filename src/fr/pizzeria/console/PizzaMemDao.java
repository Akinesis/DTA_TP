package fr.pizzeria.console;

import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {

    private Pizza[] pizzas;

    public PizzaMemDao() {
        pizzas = new Pizza[99];

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
    public Pizza[] findAllPizzas() {
        return pizzas;
    }

    @Override
    public void saveNewPizza(Pizza pizza) {

        int nextFreeSlot = 0;

        //iterate through the pizza array until a free slot is found
        while(pizzas[nextFreeSlot] != null){
            nextFreeSlot++;
        }

        //add the new pizza
        pizzas[nextFreeSlot] = pizza;

    }

    @Override
    public void updatePizza(String codePizza, Pizza pizza) {

        int i = 0;

        //Look for the index of the pizza to update and replace it
        for(Pizza p : pizzas){
            if (p != null){
                if(p.getCode().equals(codePizza)){
                    pizzas[i] = pizza;
                    break;
                }
            }
            i++;
        }
    }

    @Override
    public void deletePizza(String codePizza) {

        int i = 0;

        for(Pizza p : pizzas){
            if (p != null){
                if(p.getCode().equals(codePizza)){
                    pizzas[i] = null;
                    break;
                }
            }
            i++;
        }
    }

    @Override
    public Pizza findPizzaByCode(String codePizza) {

        for(Pizza p : pizzas){
            if (p != null){
                if(p.getCode().equals(codePizza)){
                    return p;
                }
            }
        }

        return null;
    }

    @Override
    public boolean pizzaExists(String codePizza) {

        for(Pizza p : pizzas){
            if (p != null){
                if(p.getCode().equals(codePizza)){
                    return true;
                }
            }
        }

        return false;
    }
}
