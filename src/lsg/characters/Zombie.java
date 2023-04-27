package lsg.characters;

import lsg.weapons.Weapon;

public class Zombie extends Monster{
    public Zombie(){
        super("Zombie");
        this.setWeapon(new Weapon("Zombie's hands",5,20,1,1000));
    }

}
