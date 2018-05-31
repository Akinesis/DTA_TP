package fr.pizzeria.console;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

import java.util.List;

public interface IPizzaDao {

    List<Pizza> findAllPizzas();

    void saveNewPizza(Pizza pizza)throws StockageException;

    void updatePizza(String codePizza, Pizza pizza) throws StockageException;

    void deletePizza(String codePizza)throws StockageException;

    Pizza findPizzaByCode(String codePizza);

    boolean pizzaExists(String codePizza);

}
