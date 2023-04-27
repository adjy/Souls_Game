package lsg.characters;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lsg.bags.Bag;
import lsg.bags.Collectible;
import lsg.bags.SmallBag;
import lsg.consumables.Consumable;
import lsg.consumables.drinks.Drink;
import lsg.consumables.food.Food;
import lsg.consumables.repair.RepairKit;
import lsg.exceptions.*;
import lsg.helpers.Dice;
import lsg.weapons.*;

import java.util.Locale;

import static lsg.bags.Bag.transfer;


abstract public class Character {
    private String name; // le nom de mon Hero
    private int life, maxLife , stamina , maxStamina; // les attributs de l'objet
    public static String LIFE_STAT_STRING = "life";
    public static String STAM_STAT_STRING = "stamina";
    public static String PROTECTION_STAT_STRING = "protection";
    public static String BUFF_STAT_STRING = "buff";
    protected Weapon weapon ;
    private Dice de;
    private Consumable consumable;
    protected Bag bag;
    private final SimpleDoubleProperty
			lifeRate = new SimpleDoubleProperty(),
			staminaRate = new SimpleDoubleProperty();

    abstract public float computeProtection();
    abstract public float computeBuff();


    // Les getters	&	les setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLife() {return life ;}
    protected void setLife(int life){this.life = life;
        lifeRate.set((double)life/maxLife);
    }

    public int getMaxLife() {return maxLife ;}
    protected void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
		lifeRate.set((double)life/maxLife);
	}

    public int getStamina() {return stamina ;}
    protected void setStamina(int stamina){
        this.stamina = stamina;
        staminaRate.set((double)stamina/maxStamina);
    }

    public int getMaxStamina() {return maxStamina ;}
    protected void setMaxStamina(int maxStamina){
        this.maxStamina = maxStamina;
        staminaRate.set((double)stamina/maxStamina);
    }

    public Weapon getWeapon() {
        return weapon; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public Consumable getConsumable() { return consumable; }
    public void setConsumable(Consumable consumable) { this.consumable = consumable; }

    public DoubleProperty lifeRateProperty() {
		return lifeRate;
	}

	public DoubleProperty staminaRateProperty() {
		return staminaRate;
	}
    public Character(String name){ // renseigne les informations de mes characteres
        setName(name);
        this.bag = new SmallBag();
        this.de = new Dice(101);

    }
    private int attackWith(Weapon weapon) throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {

        if(weapon == null)
            throw new WeaponNullException();

        if(weapon.isBroken())
            throw new WeaponBrokenException(weapon);
        if(getStamina() == 0)
            throw new StaminaEmptyException();

        //renvoie le nb de degats fourni
        int  damage = ((de.roll() * (weapon.getMaxDamage() - weapon.getMinDamage())) / 100 ) + weapon.getMinDamage();
        if (getStamina() >= weapon.getStamCost()) {
            this.stamina = getStamina() - weapon.getStamCost();
        }
        else {
            damage = damage * getStamina() / weapon.getStamCost();
            this.stamina = 0 ;
        }

        damage = damage + (damage * Math.round(computeBuff())/100 );
        weapon.use(); // diminue la durabilite
        return  damage; // retourne le nb de degats
    }

    public int attack() throws WeaponNullException, WeaponBrokenException, StaminaEmptyException { // methode qui fait l'attaque
        return attackWith(weapon);
    }

    public int getHitWith(int value){ // metode qui renvoie le nb de	 points	 de	 vie (PV) effectivement	retirés
        value = computeProtection() >=100? 0 :  Math.round( (100-computeProtection()) *value / 100);
        value = getLife() - value >= 0? value : getLife();
        this.life = getLife() - value; // met a jour la vie
        return value;
    }

    public boolean isAlive(){ // Verifie si le hero est en vie
        return this.life != 0;
    } // verifie si le charactere est en vie

    public void printStats(){
        System.out.println(this.toString());
    }
    public String toString() {
        String name = this.getClass().getSimpleName(); // recupere le nom de la classe
        String vie = (isAlive()) ? "ALIVE" : "DEAD";
        return String.format(Locale.US,"[ %-7s ] %-15s %s: %-4d %s: %-4d %s: %-4.2f %s: %.2f (%s)  ",name,this.name, LIFE_STAT_STRING,
                this.life,STAM_STAT_STRING,getStamina(), PROTECTION_STAT_STRING,this.computeProtection(),BUFF_STAT_STRING,this.computeBuff(),vie);
    }

    private void drink(Drink boisson ) throws ConsumeNullException, ConsumeEmptyException { // permet de faire boire un charactere
        if(boisson == null)
            throw new ConsumeNullException();
        System.out.println(String.format("%s drinks %s", getName(), boisson.toString()) );
        int stamBoisson = boisson.use(); // recupere la valeur du boisson

        System.out.println(String.format("%d/%d/%d", stamBoisson, getStamina(), getMaxStamina()) );
        if (  stamBoisson + getStamina()  <= getMaxStamina() ) setStamina( getStamina() + stamBoisson);
        else  setStamina( getMaxStamina() );
    }

    private void eat(Food food) throws ConsumeNullException, ConsumeEmptyException { // permet de faire manger un charactere
        if(food == null)
            throw new ConsumeNullException();

        System.out.println(String.format("%s eats %s", getName(), food.toString()) );

        int stamFood = food.use(); // recupere la valeur de la nourriture

        if (  stamFood + getLife()  <= getMaxLife() ) setLife( getLife() + stamFood);
        else  setLife( getMaxLife() );
    }

    private void repairWeaponWith(RepairKit kit) throws WeaponNullException, ConsumeNullException { // permet de reparer l'arme du charactere
        if(weapon == null)
            throw new WeaponNullException();
        System.out.println(String.format("%s repairs %s with %s", getName(), weapon.toString() ,  kit.toString()) );
        weapon.repairWith(kit);
    }
    public void	 use(Consumable	 consumable) throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException {
        if (consumable == null) throw new ConsumeNullException();
        else if (consumable instanceof Food) eat((Food) consumable);
        else if (consumable instanceof Drink) drink((Drink) consumable);
        else if (consumable instanceof RepairKit) {
            try {
                repairWeaponWith((RepairKit) consumable);
            } catch (WeaponNullException e) {
                throw new ConsumeRepairNullWeaponException(consumable);
            }
        }

    }

    public void consume() throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException {
            use(getConsumable());

    }

    public void pickUp(Collectible item) throws NoBagException, BagFullException {
        if(this.bag == null)
            throw new NoBagException();

        if(bag.getWeight() + item.getWeight() <= bag.getCapacity())
            System.out.println(String.format("%s picks up %s",this.getName(), item.toString() ));
        this.bag.push(item);

    }

    public int getBagCapacity() throws NoBagException {
        if(this.bag == null)
            throw new NoBagException();
        return this.bag.getCapacity();
    }
    public int getBagWeight() throws NoBagException {
        if(this.bag == null)
            throw new NoBagException();
        return this.bag.getWeight();
    }

    public Collectible[] getBagItems() throws NoBagException {
        if(this.bag == null)
            throw new NoBagException();
        return this.bag.getItems();
    }

    public Collectible pullOut(Collectible	item) throws NoBagException {
        if(this.bag == null)
            throw new NoBagException();
        Collectible col = this.bag.pop(item);
        if(col != null)
            System.out.print(String.format("%s pulls out %s ",this.getName(), item.toString()));
        return col;
    }

    public Bag setBag(Bag bag){
        String nameBag = null;
        if(bag != null){
            nameBag = bag.getClass().getSimpleName();
        }

        System.out.print(String.format("%s changes %s for %s\n", this.getName(), this.bag.getClass().getSimpleName(),
                nameBag ));
        Bag bg = this.bag;
        transfer(bg, bag);
        this.bag = bag;
        return bg;
    }

    public void equip(Weapon weapon) throws NoBagException {
        Collectible arme = this.pullOut(weapon);
        if(arme == null)
            return;
        System.out.print("and equips it !");
        setWeapon((Weapon) arme);
    }

    public void equip(Consumable consumable) throws NoBagException {
        Collectible cons = this.pullOut(consumable);
        if(cons == null)
            return;
        setConsumable( (Consumable) (cons) );
    }
    public void printBag(){
        System.out.println(String.format("BAG: %s",this.bag));
    }

    private Consumable fastUseFirst(Class <? extends Consumable> type)
            throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException, NoBagException {
        for(Collectible col: getBagItems()) {
            if (type.isInstance(col)) {
                Consumable cons = (Consumable) col;
                use(cons);
                if (cons.getCapacity() == 0)
                    pullOut(col);
                return cons;
            }
        }
        return null;
    }
    public Drink fastDrink() throws ConsumeNullException, ConsumeEmptyException, NoBagException{

        try {
            System.out.println(String.format("%s drinks Fast: ", this.getName()));

            return (Drink) fastUseFirst(Drink.class );

        }
        catch (ConsumeRepairNullWeaponException e){

        }
        return null;
    }

    public Food	fastEat() throws ConsumeNullException, ConsumeEmptyException, NoBagException{
        try {
            System.out.println(String.format("%s eats Fast: ", this.getName()));
            return (Food) fastUseFirst(Food.class);
        }
        catch (ConsumeRepairNullWeaponException e){

        }
        return null;
    }
    public RepairKit fastRepair() throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException, NoBagException {

        System.out.println(String.format("%s repairs Fast: ", this.getName()));
        return (RepairKit) fastUseFirst(RepairKit.class);
    }
    public void printWeapon(){
        System.out.println(getWeapon());
    }
    public void printConsumable(){
        System.out.println(getConsumable());
    }
}
