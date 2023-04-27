package lsg.consumables;

import lsg.consumables.drinks.*;
import lsg.consumables.food.*;
import lsg.consumables.repair.RepairKit;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Locale;

public class MenuBestOfV4 extends LinkedHashSet<Consumable>{

    public RepairKit cadeau = new RepairKit();
    public MenuBestOfV4() {
        super();
        this.add(new Hamburger());
        this.add(new Wine());
        this.add(new Americain());
        this.add(new Coffee());
        this.add(new Whisky());
    }

//        Consumable[] menu = {new Hamburger(),new Wine(), new Americain(), new Coffee(), new Whisky()};


//        for (int i = 0; i < menu.length; i++) linkedHashSet.add(menu[i]);
//        iterator = linkedHashSet.iterator();

//    }

    public String toString() {
        String mot = String.format("%s : \n", this.getClass().getSimpleName());
        int i = 1;
        for (Consumable item : this){
            mot += String.format("%d : %s\n", i, item.toString() );
            i++;

        }
        return mot;
    }

    /*
    public String toString() {
        int indice = 1;
        String mot = String.format("%s : \n", this.getClass().getSimpleName());
//        iterator = linkedHashSet.iterator();

//        while(iterator.hasNext()){
//            mot += String.format("%d : ", i );
//            mot += iterator.next().toString() + "\n";
//            i++;
//        }
        for (Object i: linkedHashSet) {
            mot += String.format("%d : ", indice );
            mot += i + "\n";
            indice++;
        }
        return mot;
    }

     */
    public void printStats(){
        System.out.println(this.toString());
    }



    public static void main (String[] args) {
        MenuBestOfV4 menu = new MenuBestOfV4();
        menu.printStats();
    }


}
