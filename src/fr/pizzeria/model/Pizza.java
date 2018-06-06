package fr.pizzeria.model;

import fr.pizzeria.Utils.ToString;

public class Pizza {

	private int id;

	@ToString(upperCase = true)
	private String code;

	@ToString()
	private String libelle;

	@ToString()
	private double prix;

	@ToString()
	private CategoriePizza category;

	private static  int counter = 0;

	public Pizza(int id, String code, String libelle, double prix){
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}

	public Pizza(String code, String libelle, double prix, CategoriePizza category){
		this.prix = prix;
		this.category = category;
		this.id = counter;
		this.code = code;
		this.libelle = libelle;


		counter ++;
	}

	public String toString(){
		return code + " -> " + libelle + "("+prix+" €) Type : "+category;
	}

	public String getCode(){
		return code;
	}

	public int getID(){
		return id;
	}

	@Override
	//Implementation of equals for more efficient usage of the List
	public boolean equals(Object obj) {

		if(obj instanceof Pizza){
			Pizza objTemp = (Pizza)obj;

			return (objTemp.getCode() == this.code ? true : false);

		}else{
			return false;
		}
	}
}
