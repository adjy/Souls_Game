package lsg.bags;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.Ring;
import lsg.consumables.Consumable;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Food;
import lsg.consumables.food.Hamburger;
import lsg.exceptions.BagFullException;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.util.HashSet;
import java.util.Locale;

import static lsg.LearningSoulsGame.BULLET_POINT;

public class Bag {
    private int capacity; //nombre de kilos	pouvant	être transportés
    private int weight; // un nombre de	kilos utilisés
    private  HashSet<Collectible> items;

    public int getCapacity() { return capacity; }
    public int getWeight() { return weight; }

    private void setWeight(int weight) { this.weight = weight; }

    public Bag (int capacity){
        this.capacity = capacity;
        this.items = new HashSet<>(capacity);
    }

    public void push(Collectible itm) throws BagFullException {

        if(getWeight() + itm.getWeight() <= getCapacity()) {
            this.items.add(itm);
            setWeight(getWeight() + itm.getWeight() );
        }
        else
            throw new BagFullException(this);


    }

    public Collectible pop(Collectible	itm){
        for(Collectible col: this.items)
            if(col == itm){
                this.items.remove(col);
                setWeight(getWeight() - col.getWeight() );
                return col;
            }
        return null;
    }
    public boolean contains(Collectible	itm){
        for(Collectible col: this.items)
            if(col == itm)
                return true;
        return false;
    }

    public Collectible[] getItems(){
        Collectible[] tab = new Collectible[this.items.size()];
        int i = 0;
        for(Collectible col: this.items)
            tab[i++] = col;
        return tab;
    }

    public	static void	transfer(Bag from, Bag into){

        if(from == null || into == null)
            return ;
        try {
            for(Collectible col: from.items)
                into.push(col);

            for(Collectible col: into.items)
                if(into.contains(col) )
                    from.pop(col);
        }
        catch (BagFullException e){}




    }

    public String toString(){
        if(this.items.size() == 0)
            return BULLET_POINT + " empty\n";

        String msg = String.format("%s [ %d items | %d/%d kg ]\n",
                getClass().getSimpleName(), this.items.size(), getWeight(), getCapacity());
        for(Collectible col: this.items)
            msg += String.format("%s %s [ %d kg ]\n",BULLET_POINT,col.toString(), col.getWeight());
        return msg;
    }






}
