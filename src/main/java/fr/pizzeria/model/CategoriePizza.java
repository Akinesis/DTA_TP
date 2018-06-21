package fr.pizzeria.model;

public enum CategoriePizza {

    VIANDE ("Viande"),
    POISSON("Poisson"),
    SANS_VIANDE("Sans Viande");

    private String categorie;

    CategoriePizza(String s) {
        categorie = s;
    }

    public static CategoriePizza findByValue(String s) {
        if (s.toLowerCase().equals("viande")){
            return VIANDE;
        }else if((s.toLowerCase().equals("poisson"))){
            return POISSON;
        }else if((s.toLowerCase().equals("sans viande"))){
            return SANS_VIANDE;
        }else{
            return null;
        }
    }
}
