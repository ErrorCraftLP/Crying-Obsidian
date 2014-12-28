package de.errorcraftlp.cryingobsidian.utils;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import de.errorcraftlp.cryingobsidian.CryingObsidian;

import static org.apache.logging.log4j.Level.*;

/**
 * 
 * This class stores some log utils that I use in the Crying Obsidian Mod.
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 *
 */
public class LogUtils {
	
	public static void log(Level logLevel, Object object) {
		
		FMLLog.log(CryingObsidian.MOD_NAME, logLevel, String.valueOf(object));
		
	}
	
	/**
	 * All events should be logged.
	 */
	public static void all(Object object) {
		
		log(ALL, object);
		
	}
	
	/**
	 * An event for informational purposes.
	 */
	public static void info(Object object) {
		
		log(INFO, object);
		
	}
	
	/**
	 * A fine-grained debug message, typically capturing the flow through Minecraft.
	 */
	public static void trace(Object object) {
		
		log(TRACE, object);
		
	}
	
	/**
	 * A general debugging event.
	 */
	public static void debug(Object object) {
		
		log(DEBUG, object);
		
	}
	
	/**
	 * A event that might possible lead to an error.
	 */
	public static void warn(Object object) {
		
		log(WARN, object);
		
	}
	
	/**
	 * An error in Minecraft, possibly recoverable.
	 */
	public static void error(Object object) {
		
		log(ERROR, object);
		
	}
	
	/**
	 * A severe error that will prevent Minecraft from continuing.
	 */
	public static void fatal(Object object) {
		
		log(FATAL, object);
		
	}
	
}
