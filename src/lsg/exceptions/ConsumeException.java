package lsg.exceptions;

import lsg.consumables.Consumable;

abstract public class ConsumeException extends Exception{
    private Consumable consumable;

    public Consumable getConsumable() {return consumable;}


    public	ConsumeException(String	message, Consumable consumable){
        super(message);
        this.consumable = consumable;
    }
}
