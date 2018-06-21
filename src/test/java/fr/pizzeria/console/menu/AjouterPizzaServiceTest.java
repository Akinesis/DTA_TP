package fr.pizzeria.console.menu;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.console.PizzaMemDao;
import fr.pizzeria.exception.StockageException;

import static org.junit.Assert.fail;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AjouterPizzaServiceTest {

	/** Création d'une "Rule" qui va permettre
	 * de substituer le System.in utilisé par le Scanner
	 * par un mock: systemInMock */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	
	
	@Before
	public void resetScanner(){
		TextFromStandardInputStream.emptyStandardInputStream();
	}
	
	
	@Test
	public void testExecuteUC() {
		AjouterPizzaService serviceTest = new AjouterPizzaService();
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();

		systemInMock.provideLines("TEST", "Latest", "25.17", "viande");

		try {
			Assert.assertFalse(serviceTest.executeUC(pizzaDOATemp));
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			fail("Erreur d'ajout de pizza");
		}
		
		Assert.assertTrue(pizzaDOATemp.findAllPizzas().size() == 9);


	}
	
	@Test(expected = StockageException.class)
	public void testExecuteUCException() throws StockageException {
		
		AjouterPizzaService serviceTest = new AjouterPizzaService();
		PizzaMemDao pizzaDOATemp = new PizzaMemDao();
		
		systemInMock.provideLines("TEST", "Latest", "", "viande");

		serviceTest.executeUC(pizzaDOATemp);

	}
	

}
