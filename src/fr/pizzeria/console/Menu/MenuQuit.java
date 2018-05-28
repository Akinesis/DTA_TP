package fr.pizzeria.console.Menu;

public class MenuQuit implements fr.pizzeria.console.Menu.MenuItem {

    public boolean handleChoice(){
        System.out.println("Aurevoir ☹");
        return true;
    }
}
