package me.sd5.fakeoperator;

import org.bukkit.ChatColor;

public class ColorReplace {

	private static final char DEFAULT_COLOR_IDENTIFIER = '$';
	
	private String unformattedMessage = null;
	private String formattedMessage = null;
	private char colorIdentifier;
	
	public ColorReplace(String message) {
		this(message, DEFAULT_COLOR_IDENTIFIER);
	}
	
	public ColorReplace(String message, char colorIdentifier) {
		this.unformattedMessage = message;
		this.colorIdentifier = colorIdentifier;
		formatMessage();
	}
	
	public String getUnformattedMessage() {
		return unformattedMessage;
	}
	
	public String getFormattedMessage() {
		return formattedMessage;
	}
	
	public char getColorIdentifier() {
		return colorIdentifier;
	}
	
	private void formatMessage() {
		formattedMessage = unformattedMessage;
		for(ChatColor color : ChatColor.values()) {
			formattedMessage.replaceAll(String.valueOf(new char[] {colorIdentifier, color.getChar()}), color.toString());
		}
	}
	
}
