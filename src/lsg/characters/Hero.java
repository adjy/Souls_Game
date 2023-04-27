package lsg.characters;

import lsg.armor.*;
import lsg.bags.Collectible;
import lsg.buffs.rings.*;
import lsg.exceptions.NoBagException;
import lsg.weapons.Weapon;

public class Hero extends Character {

    private static int MAX_ARMOR_PIECES = 3; // Max Armoe
    private static int MAX_RING_PIECES = 2; // Max Ring
    private static int DEFAULT_life = 100,  DEFAULT_maxLife = 100 , DEFAULT_stamina = 50 ,  DEFAULT_maxStamina = 50;
    private static String DEFAULT_NAME = "Gregooninator";


    private ArmorItem[] armor; // Tableau representant l'ensemble des armors
    private Ring[] ring; // Tableau representant l'ensemble des Rings

    public Hero(String name) {
        super(name);
        setLife(DEFAULT_life);
        setMaxLife(DEFAULT_maxLife);
        setStamina(DEFAULT_stamina);
        setMaxStamina(DEFAULT_maxStamina);

        armor = new ArmorItem[MAX_ARMOR_PIECES]; // Tableau representant l'ensemble des armors
        ring = new Ring[MAX_RING_PIECES];
    }

    public Hero() {
        this(DEFAULT_NAME); // nom par defaut
    }
    public float computeBuff(){
        return  getTotalRing();
    }


    public void setArmortItem(ArmorItem armorEntrer, int slot) { // Permet de stocker les armors entrer
        slot--; // diminue l'indice de 1
        if (slot >= 0 && slot < MAX_ARMOR_PIECES) { // si c inferieur a la valeur max et superieur ou egal a 0
            armor[slot] = armorEntrer;
        }
    }

    public float getTotalArmor() { // Permet de faire la somme des valeurs des armors qui sont present dans le tableau
        float som = 0.f;
        for(ArmorItem arm: armor)
            if(arm != null)
                som += arm.getArmorValue();
        return som;
    }

    public String armorToString() { // Permet d'afficher les caracteristiques des armors present dans le tableau
        String ar = "ARMOR ";

        int indice = 1;
        for(ArmorItem arm: armor){
            ar += String.format("\t%d:", indice++);
            if(arm !=  null)
                ar += arm.toString();
            else
                ar += "empty";
            ar += "\t";
        }
        ar += String.format("\tTOTAL: %.2f",getTotalArmor());
        return ar;
    }

    public ArmorItem[] getArmorItems(){ // Construit un autre tableu qui stocke les armors effectivent du tableau
        int j = 0;

        for(ArmorItem arm: armor)
            if(arm != null)
                j++;

        ArmorItem[] newArmor = new ArmorItem[j]; // construit un tableau qui va stocker les armors reels
        int t = 0;
        for(ArmorItem arm: armor)
            if(arm != null)
                newArmor[t++] = arm;

        return newArmor;
    }
    public float computeProtection(){
        return  getTotalArmor();
    } // renvoie la valeur totale de l'ensemble des armors

    public void setRing(Ring ringEntrer, int slot) {
        slot--;
        if (slot >= 0 && slot < MAX_RING_PIECES) {
            ring[slot] = ringEntrer;
            ringEntrer.setHero(this);
        }
    }

    public float getTotalRing() {
        float som = 0;
        for(Ring r: ring)
            if (r != null)
                som += r.computeBuffValue();

        return som;
    }



    public String RingToString() {
        String ar = "RING ";
        int indice = 1;
        for(Ring r: ring){
            ar += String.format("\t%d:", indice++);

            if (r != null)
                ar += r.toString();
            else
                ar += "empty";
            ar += "\t";
        }
        ar += String.format("\tTOTAL: %.2f",getTotalRing());
        return ar;
    }



    public Ring[] getRingItems(){
        int j = 0;
        for(Ring r: ring)
            if (r != null)
                j++;

        Ring[] newRing = new Ring[j];
        int t = 0;
        for(Ring r: ring)
            if (r != null)
                newRing[t++] = r;
        return newRing;
    }

    public void equip(ArmorItem item, int slot) throws NoBagException {
        if(this.bag == null)
            throw new NoBagException();
        ArmorItem armor = (ArmorItem) this.pullOut(item);
        if(armor == null)
            return;
        System.out.print("and equips it !");
        setArmortItem(armor, slot);
    }

    public void equip(Ring ring , int slot) throws NoBagException {
        if(this.bag == null)
            throw new NoBagException();
        Ring ri = (Ring) this.pullOut(ring);
        if(ri == null)
            return;
        System.out.print("and equips it !");
        setRing(ri, slot);
    }
    public void printRings(){
        System.out.println(RingToString());
    }

    public void printArmors(){
        System.out.println(armorToString());
    }


    public static void main (String[] args) {
        Hero hero = new Hero();

        ArmorItem armor1 = new BlackWitchVeil();
        ArmorItem armor2 = new DragonSlayerLeggings();
        ArmorItem armor3 = new RingedKnightArmor();

        hero.setArmortItem( armor3, 3);
//        hero.setArmortItem( armor1, 2);
        hero.setArmortItem( armor2, 1);

//        System.out.println(hero.armorToString());

//
        for(ArmorItem armor: hero.getArmorItems())
            System.out.println(armor.toString() );
//        System.out.println(hero.getTotalArmor());

//        hero.printStats();

    }
}

//    public int life = 100, maxLife = 100, stamina = 50, maxStamina = 50; // les attributs de l'objet
//    private String DEFAULT_NAME = "Gregooninator"; // nom par defaut

//    int nbr_hero = 7;
//    int nbrTabNom_Hero = 3;
//
//    String name[] = new String[nbrTabNom_Hero];
//        name[0] = "paul";
//                name[1] = "Jack";
//                name[2] = "Pierre";
//
//
//                Hero tabHero[] = new Hero[nbr_hero];
//
//                for(int i=0, nbr = nbr_hero; i<nbr; i++){
//
//        Hero h = (i < nbrTabNom_Hero) ? new Hero(name[i]) : new Hero();
//        tabHero[i] = h;
//        }
//        for(int i = 0, nbr = nbr_hero; i<nbr; i++){
//        tabHero[i].printStats();
//        }