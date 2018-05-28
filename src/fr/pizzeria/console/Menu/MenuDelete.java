package fr.pizzeria.console.Menu;

public class MenuDelete implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(){
        System.out.println("Supprimer une pizza");
        return false;
    }
}
