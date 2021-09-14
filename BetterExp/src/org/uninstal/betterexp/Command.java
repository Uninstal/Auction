package org.uninstal.betterexp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.uninstal.betterexp.util.Utils;
import org.uninstal.betterexp.util.Values;

public class Command implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if(args.length >= 3) {
			
			if(args[0].equalsIgnoreCase("create")) {
				
				//У игрока нет права.
				if(!sender.hasPermission("exp.create")) {
					
					sender.sendMessage(Values.PERMISSION);
					return false;
				}
				
				//Необходимый уровень в 1-ой бутылке.
				int level = Integer.valueOf(args[1]);
				
				//Игрок указал уровень не кратный 10.
				if(level <= 0 || level % 10 != 0) {
					
					sender.sendMessage(Values.NEED_MULTIPLE);
					return false;
				}
				
				//Необходимое количество бутылок.
				int amount = Integer.valueOf(args[2]);
				
				//Игрок указал неверное количество бутылок.
				if(amount <= 0 || amount > 64) {
					
					sender.sendMessage(Values.AMOUNT);
					return false;
				}
				
				//Общее количество опыта.
				int total = Utils.getTotalXp(level) * amount;
				
				//Проверка опыта игрока.
				if(player.getTotalExperience() < total) {
					
					sender.sendMessage(Values.LOW_EXP);
					return false;
				}
				
				//Создаем предмет в нужных количествах.
				ItemStack bottles = Utils.item(level, amount);
				player.getInventory().addItem(bottles);
				player.giveExp(-total);
				
				//Фиксим значение.
				if(player.getTotalExperience() == 
					Utils.getTotalXp(player.getLevel() + 1)) {
					player.setLevel(player.getLevel() + 1);
					player.setExp(0f);
				}
				
				return false;
			}
			
			if(args[0].equalsIgnoreCase("forcecreate")) {
				
				//У игрока нет права.
				if(!sender.hasPermission("exp.forcecreate")) {
					
					sender.sendMessage(Values.PERMISSION);
					return false;
				}
				
				//Необходимый уровень в 1-ой бутылке.
				int level = Integer.valueOf(args[1]);
				//Необходимое количество бутылок.
				int amount = Integer.valueOf(args[2]);
				
				//Создаем предмет в нужных количествах.
				ItemStack bottles = Utils.item(level, amount);
				player.getInventory().addItem(bottles);
				
				return false;
			}
		}
		
		//У игрока нет права.
		if(!sender.hasPermission("exp.help")) {
					
			sender.sendMessage(Values.PERMISSION);
			return false;
		}
		
		sender.sendMessage(Values.HELP);
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		
		if(args.length == 1) {
			List<String> types = Arrays
			.asList("create", "forcecreate", "help");
			List<String> out = new ArrayList<>();
			
			for(String type : types) {
				
				if(type.startsWith(args[0])
					&& sender.hasPermission("exp." + type))
					out.add(type);
			}
			
			return out;
		}
		
		return new ArrayList<>();
	}
}