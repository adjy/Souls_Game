package lsg.exceptions;

import lsg.bags.Bag;

public class BagFullException extends Exception{
    private Bag bag;

    public Bag getBag() {
        return bag;
    }

    public BagFullException(Bag bag){
        super(String.format("%s is full", bag.getClass().getSimpleName()));
        this.bag = bag;

    }
}
