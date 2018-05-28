package fr.pizzeria.model;

public class Pizza {

    private int id;
    private String code;
    private String libelle;
    private double prix;

    private static  int counter = 0;

    public Pizza(int id, String code, String libelle, double prix){
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    public Pizza(String code, String libelle, double prix){
        this.id = counter;
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;

        counter ++;
    }
}
