package me.sd5.fakeoperator.config;

import me.sd5.fakeoperator.commands.FakeCommand;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagesConfig extends Config {

	private final String opPath = "messages.fake-op";
	private final String deopPath = "messages.fake-deop";
	private final String commandsPath = "commands.";
	
	public MessagesConfig(JavaPlugin plugin) {
		super(plugin, "messages.yml");
	}
	
	public String getFakeOpMessage() {
		return get().getString(opPath);
	}
	
	public String getFakeDeopMessage() {
		return get().getString(deopPath);
	}
	
	public ConfigurationSection getCommandSection(FakeCommand command) {
		return get().getConfigurationSection(commandsPath + command.name());
	}
	
}
