package lsg.buffs.rings;

import lsg.armor.ArmorItem;
import lsg.armor.DragonSlayerLeggings;

public class DragonSlayerRing extends Ring{
	private static String RING_NAME = "Dragon Slayer Ring";
	private static int RING_VALUE = 14;
	
	public DragonSlayerRing() {
		super(RING_NAME, RING_VALUE) ;
	}
	
	@Override
	public float computeBuffValue() {
		if(hero != null && hasDragonsSlayerItem())
			return power ;
		return 0 ;
	}
	
	private boolean hasDragonsSlayerItem(){
		ArmorItem[] items = hero.getArmorItems() ;
		for(ArmorItem item: items){
			if(item instanceof DragonSlayerLeggings)
				return true ;
		}
		return false ;
	}
	
}

