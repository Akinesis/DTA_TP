package fr.pizzeria.console.Menu;

public class MenuAdd implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(){
        System.out.println("Ajout d'une nouvelle pizza");
        return false;
    }
}
