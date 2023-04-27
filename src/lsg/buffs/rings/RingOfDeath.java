package lsg.buffs.rings;
import lsg.characters.Hero;

public class RingOfDeath extends Ring{
	
	private static float LIMIT = 0.5f ;
	private static String RING_NAME = "Ring of Death";
	private static int RING_VALUE = 10000;

	public RingOfDeath() {
		super(RING_NAME, RING_VALUE) ;
	}

	@Override
	public float computeBuffValue() {
		if (hero != null){
			float gauge = (float)hero.getLife() / hero.getMaxLife() ;
			if(gauge <= LIMIT)
				return power ;
			return 0f ;
		}
		return 0f ;
	}
	
	/**
	 * Un test...
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		Hero hero = new Hero() ;
		Ring r = new RingOfDeath() ;
		hero.setRing(r, 1);
		System.out.println(hero.getHitWith(60)) ; // pour abaisser les PV du hero
		System.out.println(hero);
		System.out.println(r);

	}
	
}
