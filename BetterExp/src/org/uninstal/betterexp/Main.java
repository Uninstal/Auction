package org.uninstal.betterexp;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.uninstal.betterexp.util.Values;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
		//Загрузка файлов.
		Files files = new Files(this);
		YamlConfiguration config = files.registerNewFile("config");
		
		//Загрузка значений из конфига.
		Values.setConfig(config);
		Values.read();
		
		//Регистрация обработчика событий.
		Bukkit.getPluginManager().registerEvents(new Handler(), this);
		
		//Регистрация команды.
		PluginCommand command = getCommand("exp");
		if(command == null) return;
		
		Command cmd = new Command();
		command.setExecutor(cmd);
		command.setTabCompleter(cmd);
	}
}