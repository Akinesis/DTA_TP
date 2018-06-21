package fr.pizzeria.console.menu;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.exception.StockageException;

public class ModifierPizzaServiceTest {

	/** Création d'une "Rule" qui va permettre
	 * de substituer le System.in utilisé par le Scanner
	 * par un mock: systemInMock */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	
	@Test
	public void testExecuteUC() {
		MenuService serviceTest = new ModifierPizzaService();
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		systemInMock.provideLines("ORI", "TEST", "La test", "25.17", "viande");

		try {
			Assert.assertFalse(serviceTest.executeUC(pizzaDOATemp));
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			fail("Erreur d'ajout de pizza");
		}

	}
	
	@Test(expected = StockageException.class)
	public void testExecuteUCException() throws StockageException {
		
		MenuService serviceTest = new ModifierPizzaService();
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();
		
		systemInMock.provideLines("TEST", "La test", "25", "viande");

		serviceTest.executeUC(pizzaDOATemp);

	}

}
