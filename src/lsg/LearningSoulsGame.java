package lsg;

import lsg.armor.*;
import lsg.bags.Bag;
import lsg.bags.SmallBag;
import lsg.buffs.rings.*;
import lsg.buffs.talismans.*;
import lsg.characters.Character;
import lsg.characters.*;
import lsg.consumables.food.*;
import lsg.consumables.repair.RepairKit;
import lsg.exceptions.*;
import lsg.weapons.*;

//import java.util.Iterator;
//import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;


public class LearningSoulsGame {
        static Hero hero = new Hero(); // initialise un hero
        static Monster  monster = new Monster(); // initialise un monstre
        static Scanner scanner = new Scanner(System.in); // methode pour l'entrer de valeur
        static public String  BULLET_POINT = "\u2219";


//        public static void refresh(){ // Affiche les statistiques pour chaque charactere
//            hero.printStats();
//            System.out.print(BULLET_POINT); hero.getWeapon().printStats();
//            System.out.print(BULLET_POINT); hero.getConsumable().printStats(); System.out.println();
//
//            monster.printStats();
//            System.out.println();
//        }

    public void refresh(){ // Affiche les statistiques pour chaque charactere
        hero.printStats();

        hero.printArmors();
        hero.printRings();
        hero.printConsumable();
        hero.printWeapon();


        System.out.println();
        monster.printStats();
        monster.printWeapon();
        System.out.println();
    }
    public String winner(){

        if(hero.isAlive()) return hero.getName();
        if(monster.isAlive()) return monster.getName();
        return "Personne";
    }

    public  boolean play(Character character1, Character character2){
            return  ( ( character1.isAlive()  && character2.isAlive() ) && (character2.getStamina() != 0 || character1.getStamina() != 0) );
    }
    public  void attack(Character att, Character def) throws WeaponNullException {
        int attack;
        try{
             attack = att.attack();
        }
        catch (WeaponNullException e){
            System.out.println("\nWARNING: no weapon has been equipped !!!\n");
            attack = 0;
        }
        catch (WeaponBrokenException e){
            System.out.printf("\nWARNING: %s is broken !!!  \n", e.getWeapon().getName());
            attack = 0;
        }
        catch (StaminaEmptyException e){
            System.out.println("ACTION HAS NO EFFECT: no more stamina !!!");
            attack = 0;
        }
        int damage = def.getHitWith(attack);
        String damageString = "";
        if (damage < 100) damageString = "0" + damage;
        if (damage < 10) damageString = "00" + damage;
        else damageString = ""+damage;


        System.out.printf("!!!%s attack %s with %s (%d) !!! -> Effective DMG: %s PV\n", att.getName(),
                def.getName(), att.getWeapon(), attack, damageString);
    }

    public  void fight1v1() throws WeaponNullException, ConsumeNullException {
            while (true) {

                if(!this.play(hero,monster)){  break; }
                this.refresh();
                //System.out.print("Hit enter key for next move >");
                //scanner.nextLine();
                int action = 0;
                while (action != 1 && action !=2) {
                    System.out.print("Hero's action for next move : (1) attack | (2) consume > ");
                    action = scanner.nextInt();
                }
                if (action == 1 )
                    attack(hero, monster);

//                if (action == 2) hero.use(hero.getConsumable());
                if (action == 2) {
                    try {
                        hero.consume();

                    }
                    catch (ConsumeNullException e){

                        System.out.println("IMPOSSIBLE ACTION: no consumable has been equipped !");
                    }
                    catch (ConsumeEmptyException e){
                        System.out.printf("ACTION HAS NO EFFECT: %s is empty\n", e.getConsumable().getName());
                    }
                    catch (ConsumeRepairNullWeaponException e){
                        System.out.println("IMPOSSIBLE ACTION: no weapon has been equipped !");

                    }
                }


                System.out.println();

                refresh();


            if(!this.play(monster,hero)){ break;}
            attack(monster, hero);


            System.out.println();
        }
        System.out.println();
        refresh();
        System.out.printf("--- %s WINS", winner());
    }


    public  void init(){ // Permet d'initialiser la partie
        Weapon sword = new Sword();
        hero.setWeapon(sword);
        hero.setConsumable(new Hamburger());
//            Weapon claw = new Claw();
//            Weapon arme = new ShotGun();
        monster.setWeapon(new Claw());
    }
    public  void title(){
            String mot = "##################################\n" +
                                        "#\tTHE LEARNING SOULS GAME\t\t #\n"
                                      +"##################################";
            System.out.println(mot);
    }
    public   void play_v1() throws WeaponNullException, ConsumeNullException {
            init();
            fight1v1();
    }
    public   void play_v2() throws WeaponNullException, ConsumeNullException {
        init();
        hero.setArmortItem(new BlackWitchVeil(),1);
        hero.setArmortItem(new DragonSlayerLeggings(),2);

        fight1v1();
    }
    public   void play_v3() throws WeaponNullException, ConsumeNullException {
        init();
        hero.setArmortItem(new BlackWitchVeil(),1);
        hero.setArmortItem(new DragonSlayerLeggings(),2);
        monster = new Lycanthrope();

        fight1v1();

    }

    public   void play_v4() throws WeaponNullException, ConsumeNullException {
        init();
        hero.setArmortItem(new BlackWitchVeil(),1);
        hero.setArmortItem(new DragonSlayerLeggings(),2);
        hero.setArmortItem(new RingedKnightArmor(),3);


        hero.setRing(new RingOfSwords(),3);
        hero.setRing(new DragonSlayerRing(),1);
        hero.setRing(new RingOfDeath(),2);

        monster = new Lycanthrope();
        monster.setTalisman(new MoonStone(),1);

        fight1v1();
    }

    public  void createExhaustedHero() throws WeaponNullException {
        System.out.println("Create exhausted hero: ");
        hero.getHitWith(99);
        hero.setWeapon(new Weapon("Grosse Arme", 0, 0, 1000, 100));
        try {
            hero.attack();
        }
        catch (WeaponNullException | WeaponBrokenException e ){
            e.printStackTrace();
        }
        catch (StaminaEmptyException e){
            e.printStackTrace();
        }


        hero.printStats();  System.out.println();
    }

    public void testExceptions(){
//        hero.setStamina(0);
//        hero.setConsumable(null);
//            hero.setConsumable(new Hamburger());
//        hero.setWeapon(null);
//        Weapon pelle = new Weapon("Pelle Cassee", 0,100,2,0);
//        hero.setConsumable(new RepairKit());
//       hero.setWeapon(null);
        hero.printBag();
        hero.setBag(null);
        try {
            hero.getBagCapacity();
        }
        catch (NoBagException e){
            System.out.println("No bag");
        }
            hero.printBag();


    }
    public static void main (String[] args) throws WeaponNullException, ConsumeNullException {
        LearningSoulsGame game = new LearningSoulsGame();
        game.init();
        game.testExceptions();

//        game.fight1v1();

    }


}