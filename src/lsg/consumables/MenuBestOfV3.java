package lsg.consumables;

import lsg.consumables.drinks.*;
import lsg.consumables.food.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public class MenuBestOfV3 extends HashSet<Consumable>{

    public MenuBestOfV3() {
        super();
        this.add(new Hamburger());
        this.add(new Wine());
        this.add(new Americain());
        this.add(new Coffee());
        this.add(new Whisky());
    }



    public String toString() {
        String mot = String.format("%s : \n", this.getClass().getSimpleName());
        int i = 1;
        for (Consumable item : this){
            mot += String.format("%d : %s\n", i, item.toString() );
            i++;

        }
        return mot;
    }
    public void printStats(){
        System.out.println(this.toString());
    }

    public static void main (String[] args) {
        MenuBestOfV3 menu = new MenuBestOfV3();
        menu.printStats();


    }


}
