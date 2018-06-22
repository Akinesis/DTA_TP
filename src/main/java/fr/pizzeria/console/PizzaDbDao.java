package fr.pizzeria.console;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.Utils.JdbcConnector;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDbDao implements IPizzaDao {

	private JdbcConnector pizzaDb;

	public PizzaDbDao(){
		pizzaDb = new JdbcConnector();
		
		try {
			saveNewPizza(new Pizza("PEP", "Pépéroni", 12.5, CategoriePizza.VIANDE));
			saveNewPizza(new Pizza("MAR", "Margherita", 15,CategoriePizza.VIANDE));
			saveNewPizza(new Pizza("REI", "La Reine", 11.5,CategoriePizza.SANS_VIANDE));
			saveNewPizza(new Pizza("FRO", "La 4 fromages", 12,CategoriePizza.SANS_VIANDE));
			saveNewPizza(new Pizza("CAN", "La cannibale", 12.5,CategoriePizza.VIANDE));
			saveNewPizza(new Pizza("SAV", "La savoyarde", 13,CategoriePizza.VIANDE));
			saveNewPizza(new Pizza("ORI", "L’orientale", 13.5,CategoriePizza.POISSON));
			saveNewPizza(new Pizza("IND", "L’indienne", 14,CategoriePizza.SANS_VIANDE));

		} catch (StockageException e) {
			e.printStackTrace();
		}
		 
	}

	@Override
	public List<Pizza> findAllPizzas() {
		ArrayList<Pizza> temp = new ArrayList<>();

		ArrayList<ArrayList<Object>> selectResult = null;
		try {
			selectResult = pizzaDb.selectFromDb("SELECT * FROM pizza");
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int id;
		String code,libelle;
		double prix;
		CategoriePizza category;

		if(selectResult != null){
			for(int i = 0; i < selectResult.get(0).size();++i){
				id = (int)selectResult.get(0).get(i);//int : id
				code = (String)selectResult.get(1).get(i);//String : code
				libelle = (String)selectResult.get(2).get(i);//String : libelle
				prix = ((BigDecimal)selectResult.get(3).get(i)).doubleValue();//bigDecimal : prix
				category = CategoriePizza.valueOf((String)selectResult.get(4).get(i));//String : categorie

				temp.add(new Pizza(id, code, libelle, prix, category));
			}
		}

		return temp;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws StockageException {
		
		if(!pizzaExists(pizza.getCode())){
			ArrayList<String> fields = new ArrayList<>();
			ArrayList<Object> values = new ArrayList<>();

			fields.add("code");
			fields.add("libelle");
			fields.add("prix");
			fields.add("categorie");

			values.add(pizza.getCode());
			values.add(pizza.getLibelle());
			values.add(pizza.getPrix());
			values.add(pizza.getCategory().name());

			pizzaDb.insertIntoDb("pizza", fields, values);
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws StockageException {
		ArrayList<String> fields = new ArrayList<>();
		ArrayList<Object> values = new ArrayList<>();

		fields.add("code");
		fields.add("libelle");
		fields.add("prix");
		fields.add("categorie");

		values.add(pizza.getCode());
		values.add(pizza.getLibelle());
		values.add(pizza.getPrix());
		values.add(pizza.getCategory().name());
		
		String whereCondition = "code='"+codePizza+"'";

		pizzaDb.alterFromDb("pizza", fields, values, whereCondition);

	}

	@Override
	public void deletePizza(String codePizza) throws StockageException {
		pizzaDb.deleteFromDb("pizza", "code='"+codePizza+"'");

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		ArrayList<ArrayList<Object>> selectResult = null;

		try {
			selectResult = pizzaDb.selectFromDb("SELECT * FROM PIZZA WHERE code='"+codePizza+"'");
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int id;
		String code,libelle;
		double prix;
		CategoriePizza category;

		if(selectResult != null){
			for(int i = 0; i < selectResult.get(0).size();++i){
				id = (int)selectResult.get(0).get(i);//int : id
				code = (String)selectResult.get(1).get(i);//String : code
				libelle = (String)selectResult.get(2).get(i);//String : libelle
				prix = ((BigDecimal)selectResult.get(3).get(i)).doubleValue();//bigDecimal : prix
				category = CategoriePizza.valueOf((String)selectResult.get(4).get(i));//String : categorie

				return new Pizza(id, code, libelle, prix, category);
			}
		}

		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		ArrayList<ArrayList<Object>> selectResult = null;

		try {
			selectResult = pizzaDb.selectFromDb("SELECT * FROM pizza WHERE code='"+codePizza+"'");
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return selectResult.get(0).size() != 0;
	}

}
