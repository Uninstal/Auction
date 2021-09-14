package org.uninstal.betterexp;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.uninstal.betterexp.util.Utils;
import org.uninstal.betterexp.util.Values;

public class Handler implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		
		if(e.getAction().name().contains("RIGHT")
			&& item != null && item.hasItemMeta()
			&& item.getItemMeta().hasDisplayName()
			&& item.getType() == Material.EXPERIENCE_BOTTLE) {
			e.setCancelled(true);
			
			String before = Values.ITEM_NAME.substring(0, Values.ITEM_NAME.indexOf("<level>"));
			String after = Values.ITEM_NAME.substring(before.length() + "<level>".length());
			
			String dispName = item.getItemMeta().getDisplayName();
			String cut = new String(dispName);
			cut = cut.substring(before.length());
			cut = cut.substring(0, cut.indexOf(after));
			
			int level = Integer.valueOf(cut);
			int total = Utils.getTotalXp(level);
			
			player.giveExp(total);
			player.playSound(player.getLocation(), 
			Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
			item.setAmount(item.getAmount() - 1);
			
			if(player.getTotalExperience() == 
				Utils.getTotalXp(player.getLevel() + 1)) {
				player.setLevel(player.getLevel() + 1);
				player.setExp(0f);
			}
			
			return;
		}
	}
}
