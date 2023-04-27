package lsg.consumables;

import lsg.bags.Collectible;
import lsg.exceptions.ConsumeEmptyException;

import java.util.Locale;

public class Consumable implements Collectible {
    private String	name;
    private int capacity;
    private String stat;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getStat() { return stat; }

    protected void setStat(String stat) { this.stat = stat; }

    public Consumable(String name, int capacity, String stat){
        setName(name);
        setCapacity(capacity);
        setStat(stat);
    }

    public void printStats(){
        System.out.println(this.toString());
    }
    public String toString() { return String.format(Locale.US,"CONSUMABLE : %s [%d %s point(s)]",getName(),getCapacity(), getStat());}

    public int use() throws ConsumeEmptyException{
        if(getCapacity() == 0)
            throw new ConsumeEmptyException(this);
        int capacite = getCapacity();
        setCapacity(0);
        return  capacite;
    }
    public int getWeight(){
        return 1;
    }

}
