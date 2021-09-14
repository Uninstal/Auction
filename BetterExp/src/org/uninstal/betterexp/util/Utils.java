package org.uninstal.betterexp.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

	public static ItemStack item(int level, int amount) {
		
		ItemStack bottle = new ItemStack(
		Material.EXPERIENCE_BOTTLE, amount);
		ItemMeta meta = bottle.getItemMeta();
		meta.setDisplayName(Values.ITEM_NAME.replace("<level>", String.valueOf(level)));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addEnchant(Enchantment.SOUL_SPEED, 1, false);
		bottle.setItemMeta(meta);
		
		return bottle;
	}
	
	public static int getTotalXp(int level) {
		
		if(level < 17) return sq(level) + 6 * level;
		if(level < 31) return (int) (2.5 * sq(level) - 40.5 * level + 360);
		else return (int) (4.5 * sq(level) - 162.5 * level + 2220);
	}
	
	private static int sq(int value) {
		return value * value;
	}
}
