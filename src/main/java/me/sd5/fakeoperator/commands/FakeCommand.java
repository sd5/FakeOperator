package me.sd5.fakeoperator.commands;

import me.sd5.fakeoperator.FakeOperator;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public abstract class FakeCommand {

	private String name = null;
	private FakeOperator plugin = null;
	private ConfigurationSection configSection = null;
	
	public FakeCommand(String name, FakeOperator plugin) {
		this.plugin = plugin;
		this.name = name;
		this.configSection = plugin.getMessagesConfig().getCommandSection(this);
	}
	
	public final ConfigurationSection getConfig() {
		return configSection;
	}
	
	public final String name() {
		return name;
	}
	
	public final FakeOperator getPlugin() {
		return plugin;
	}
	
	public abstract String onCall(Player executor, String[] args);
	
}
