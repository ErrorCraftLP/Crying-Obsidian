package de.errorcraftlp.cryingobsidian.command;

import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.Loader;

import com.google.common.collect.Lists;

import de.errorcraftlp.cryingobsidian.CryingObsidian;

/**
 * 
 * This is the class of the <code>'/cryingobsidian'</code> command.</br>
 * If you use this command, it shows the mod id of the mod, the name of the mod, the version of the mod, the mc version and the author of the mod.
 * 
 * @see ICommand
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 *
 */
public class CommandCryingObsidian implements ICommand {
	
	/** A list of the command aliases */
	private List aliases;
	
	public CommandCryingObsidian() {
		
		this.aliases = Lists.newArrayList();
		this.aliases.add("cryingobsidian");
		this.aliases.add("cryingObsidian");
		this.aliases.add("crying_obsidian");
		this.aliases.add("crying-obsidian");
		
		//German aliases
		this.aliases.add("weinenderobsidian");
		this.aliases.add("weinenderObsidian");
		this.aliases.add("weinender_obsidian");
		this.aliases.add("weinender-obsidian");
		
	}
	
	@Override
	public int compareTo(Object comparedObject) {
		
		return 0;
		
	}
	
	@Override
	public String getName() {
		
		return "cryingobsidian";
		
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender) {
		
		return "/cryingobsidian";
		
	}
	
	@Override
	public List getAliases() {
		
		return this.aliases;
		
	}
	
	@Override
	public void execute(ICommandSender iCommandSender, String[] string) throws CommandException {
		
		iCommandSender.addChatMessage(new ChatComponentTranslation(StatCollector.translateToLocal("chat.cryingObsidian"), CryingObsidian.MOD_ID, CryingObsidian.MOD_NAME, CryingObsidian.MOD_VERSION, Loader.MC_VERSION));
		
	}
	
	@Override
	public boolean canCommandSenderUse(ICommandSender iCommandSender) {
		
		return true;
		
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender iCommandSender, String[] string, BlockPos blockPos) {
		
		return null;
		
	}
	
	@Override
	public boolean isUsernameIndex(String[] string, int index) {
		
		return false;
		
	}

}
