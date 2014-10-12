package me.sd5.fakeoperator.config;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FakeopsConfig extends Config {
	
	private final String path = "fake-ops";
	private List<String> fakeOps = null;
	
	public FakeopsConfig(JavaPlugin plugin) {
		super(plugin, "fakeops.yml");
		this.fakeOps = get().getStringList(path);
	}
	
	public boolean isFakeOp(Player player) {
		return isFakeOp(player.getName());
	}
	
	public boolean isFakeOp(String name) {
		return fakeOps.contains(name);
	}
	
	public void fakeOp(Player player) {
		fakeOp(player.getName());
	}
	
	public void fakeOp(String name) {
		if(!isFakeOp(name)) {
			fakeOps.add(name);
		}
		save();
	}
	
	public void fakeDeop(Player player) {
		fakeOp(player.getName());
	}
	
	public void fakeDeop(String name) {
		if(isFakeOp(name)) {
			fakeOps.remove(name);
		}
		save();
	}
	
	@Override
	public void save() {
		get().set(path, fakeOps);
		super.save();
	}

}
