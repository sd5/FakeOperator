package me.sd5.fakeoperator.config;

import org.bukkit.plugin.java.JavaPlugin;

public class MessagesConfig extends Config {

	private final String opPath = "messages.fake-op";
	private final String deopPath = "messages.fake-deop";
	
	public MessagesConfig(JavaPlugin plugin) {
		super(plugin, "messages.yml");
	}
	
	public String getFakeOpMessage() {
		return get().getString(opPath);
	}
	
	public String getFakeDeopMessage() {
		return get().getString(deopPath);
	}
	
}
