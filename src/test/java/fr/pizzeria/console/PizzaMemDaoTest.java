package fr.pizzeria.console;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {

	@Test
	public void testPizzaMemDao() {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		List<Pizza> pizzas = pizzaDOATemp.findAllPizzas();
		Assert.assertTrue(!pizzas.isEmpty());
	}

	@Test
	public void testFindAllPizzas() {

		PizzaMemDao pizzaDOATemp = new PizzaMemDao();
		List<Pizza> pizzas = pizzaDOATemp.findAllPizzas();

		Assert.assertNotNull(pizzas);
	}

	@Test
	public void testSaveNewPizza() {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		Pizza pizzaTest = new Pizza("TEST", "Pizza Test", 25.4, CategoriePizza.SANS_VIANDE);

		try {
			pizzaDOATemp.saveNewPizza(pizzaTest);
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			fail("Erreur d'ajout de pizza");
		}
		
		Assert.assertTrue(pizzaDOATemp.findAllPizzas().contains(pizzaTest));
		
		pizzaTest = null;
		
		try {
			pizzaDOATemp.saveNewPizza(pizzaTest);
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			fail("Erreur d'ajout de pizza");
		}

	}

	@Test(expected = StockageException.class)
	public void testSaveNewPizzaException() throws StockageException {

		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		Pizza pizzaTest = new Pizza("TEST", "Pizza Test", -18, CategoriePizza.SANS_VIANDE);

		pizzaDOATemp.saveNewPizza(pizzaTest);

	}

	@Test
	public void testUpdatePizza() {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		Pizza pizzaTest = new Pizza("TEST", "Pizza Test", 25.4, CategoriePizza.SANS_VIANDE);

		try {
			pizzaDOATemp.updatePizza("ORI", pizzaTest);
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			fail("Erreur de MaJ de pizza");
		}

		Assert.assertTrue(pizzaDOATemp.findAllPizzas().contains(pizzaTest));
	}

	@Test(expected = StockageException.class)
	public void testUpdatePizzaException() throws StockageException {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		Pizza pizzaTest = new Pizza("TEST", "Pizza Test", -18, CategoriePizza.SANS_VIANDE);

		pizzaDOATemp.updatePizza("ORI", pizzaTest);
	}

	@Test
	public void testDeletePizza() {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		try {
			pizzaDOATemp.deletePizza("ORI");
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			fail("Erreur suppression de pizza");
		}

		Assert.assertTrue(!pizzaDOATemp.pizzaExists("ORI"));
	}

	@Test(expected = StockageException.class)
	public void testDeletePizzaException() throws StockageException {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		pizzaDOATemp.deletePizza("TEST");

	}

	@Test
	public void testFindPizzaByCode() {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		Pizza pizzaTest = pizzaDOATemp.findPizzaByCode("ORI");

		Assert.assertNotNull(pizzaTest);

		pizzaTest = pizzaDOATemp.findPizzaByCode("TEST");

		Assert.assertNull(pizzaTest);
	}

	@Test
	public void testPizzaExists() {
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		Assert.assertTrue(pizzaDOATemp.pizzaExists("ORI"));
		
		Assert.assertFalse(pizzaDOATemp.pizzaExists("TEST"));

	}

}
