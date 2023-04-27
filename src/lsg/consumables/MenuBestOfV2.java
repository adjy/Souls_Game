package lsg.consumables;

import lsg.consumables.drinks.*;
import lsg.consumables.food.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public class MenuBestOfV2 {
    static HashSet<Consumable> menu = new HashSet<Consumable>();

    public MenuBestOfV2() {
        menu = new HashSet<Consumable>();
        menu.add(new Hamburger());
        menu.add(new Wine());
        menu.add(new Americain());
        menu.add(new Coffee());
        menu.add(new Whisky());
    }

    public String toString() {
        String mot = String.format("%s : \n", this.getClass().getSimpleName());
        int i = 1;
        for (Consumable item : menu){
            mot += String.format("%d : %s\n", i, item.toString() );
            i++;

        }
        return mot;
    }
    public void printStats(){
        System.out.println(this.toString());
    }

    public static void main (String[] args) {
        MenuBestOfV2 menu = new MenuBestOfV2();
        menu.printStats();



    }


}
