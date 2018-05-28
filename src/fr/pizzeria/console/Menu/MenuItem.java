package fr.pizzeria.console.Menu;

import fr.pizzeria.model.Pizza;

public interface MenuItem {
   boolean handleChoice(Pizza[] pizzas);
}
