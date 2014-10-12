package me.sd5.fakeoperator;

import java.util.HashMap;
import java.util.logging.Level;

import me.sd5.fakeoperator.commands.FakeCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class FakeCommandListener implements Listener {

	private FakeOperator plugin;
	private HashMap<String, FakeCommand> fakeCommands;
	
	public FakeCommandListener(FakeOperator plugin) {
		this.plugin = plugin;
		this.fakeCommands = new HashMap<String, FakeCommand>();
	}
	
	public void registerFakeCommand(FakeCommand fc) {
		if(!fakeCommands.containsKey(fc.name())) {
			fakeCommands.put(fc.name(), fc);
			Bukkit.getLogger().log(Level.INFO, "Registered fake command: '" + fc.name() + "'");
		} else {
			Bukkit.getLogger().log(Level.WARNING, "Fake command '" + fc.name() + "' is already registered!");
		}
	}
	
	/**
	 * Called when a player tries to execute a command.
	 * Highest priority so that we can manage the event and cancel it afterwards.
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String fullCommand = event.getMessage();
		String command = extractCommand(fullCommand).toLowerCase();
		String[] args = extractArguments(fullCommand);
		if(! player.isOp()) {
			if(plugin.getFakeopsConfig().isFakeOp(player)) {
				FakeCommand fakeCommand = fakeCommands.get(command);
				if(fakeCommand != null) {
					Bukkit.getLogger().log(Level.INFO, "Fake operator " + player.getName() + " issued fake command: " + fullCommand);
					player.sendMessage(fakeCommand.onCall(player, args));
					event.setCancelled(true);
				}
			}
		}
	}
	
	private static String extractCommand(String fullCommand) {
		String command = fullCommand;
		int firstSpaceIndex = command.indexOf(" ");
		if(firstSpaceIndex >= 0) {
			command = command.split(" ")[0];
		}
		command = command.substring(1);
		return command;
	}
	
	private static String[] extractArguments(String fullCommand) {
		String[] args;
		int firstSpaceIndex = fullCommand.indexOf(" ");
		if(firstSpaceIndex >= 0 && firstSpaceIndex + 1 < fullCommand.length()) {
			args = fullCommand.substring(firstSpaceIndex + 1).split(" ");
		} else {
			args = new String[] {};
		}
		return args;
	}
	
}
