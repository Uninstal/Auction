package org.uninstal.betterexp.util;

import org.bukkit.configuration.file.YamlConfiguration;

public class Values {
	
	private static YamlConfiguration config;

	public static void setConfig(YamlConfiguration file) {
		config = file;
	}
	
	public static void read() {
		
		//Загрузка сообщений.
		HELP = config.getString("messages.help").replace("&", "§");
		NEED_MULTIPLE = config.getString("messages.need-multiple").replace("&", "§");
		LOW_EXP = config.getString("messages.low-exp").replace("&", "§");
		AMOUNT = config.getString("messages.amount").replace("&", "§");
		PERMISSION = config.getString("messages.permission").replace("&", "§");
		ITEM_NAME = config.getString("item").replace("&", "§");
	}

	public static String HELP;
	public static String NEED_MULTIPLE;
	public static String LOW_EXP;
	public static String AMOUNT;
	public static String PERMISSION;
	public static String ITEM_NAME;
}


