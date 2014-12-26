package de.errorcraftlp.cryingobsidian.commands;

import java.util.ArrayList;
import java.util.List;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CryingObsidianCommand implements ICommand {
	
	/** A list of the command aliases */
	public List aliases;
	
	public CryingObsidianCommand() {
		
		this.aliases = new ArrayList();
		this.aliases.add("cryingobsidian");
		
		//German aliases
		this.aliases.add("weinenderobsidian");
		
	}
	
	public int compareTo(Object object) {
		
		return 0;
		
	}
	
	public String getName() {
		
		return "cryingobsidian";
		
	}
	
	public String getCommandUsage(ICommandSender iCommandSender) {
		
		return "/cryingobsidian";
		
	}
	
	public List getAliases() {
		
		return this.aliases;
		
	}
	
	public void execute(ICommandSender iCommandSender, String[] string) throws CommandException {
		
		iCommandSender.addChatMessage(new ChatComponentTranslation("Version: " + CryingObsidian.MOD_VERSION + " for Minecraft 1.8, Author: ErrorCraftLP"));
		
	}
	
	public boolean canCommandSenderUse(ICommandSender iCommandSender) {
		
		return true;
		
	}
	
	public List addTabCompletionOptions(ICommandSender iCommandSender, String[] string, BlockPos blockPos) {
		
		return null;
		
	}
	
	public boolean isUsernameIndex(String[] string, int index) {
		
		return false;
		
	}

}
