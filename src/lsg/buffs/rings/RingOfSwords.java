package lsg.buffs.rings;

import lsg.characters.Hero;
import lsg.weapons.Sword;

public class RingOfSwords extends Ring{
	private static String RING_NAME = "Ring of Swords";
	private static int RING_VALUE = 10;
	public RingOfSwords() {
		super(RING_NAME, RING_VALUE) ;
	}
	
	@Override
	public float computeBuffValue() {
		if (hero != null && (hero.getWeapon() instanceof Sword) )
			return power ;
		else
			return 0f ;
		
	}
	
	/**
	 * Un test...
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		Hero hero = new Hero() ;
		RingOfSwords r = new RingOfSwords() ;
		hero.setRing(r, 1);
		hero.setWeapon(new Sword());
		System.out.println(r);
	}
}
