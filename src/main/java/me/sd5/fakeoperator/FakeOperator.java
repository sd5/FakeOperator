package me.sd5.fakeoperator;

import me.sd5.fakeoperator.config.FakeopsConfig;
import me.sd5.fakeoperator.config.MessagesConfig;

import org.bukkit.plugin.java.JavaPlugin;

public class FakeOperator extends JavaPlugin {

	private FakeopsConfig fakeops = null;
	private MessagesConfig messages = null;
	
	@Override
	public void onEnable() {
		fakeops = new FakeopsConfig(this);
		messages = new MessagesConfig(this);
	}
	
	@Override
	public void onDisable() {
		fakeops.save();
		messages.save();
	}
	
}
