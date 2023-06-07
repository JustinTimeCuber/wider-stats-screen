package barker.justin.wss;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("wider-stats-screen");
	public static final Path configPath = FileSystems.getDefault().getPath("config", "wider-stats-screen.txt");
	public static List<String> configLines;
	public static double scale = 1.75;

	@Override
	public void onInitialize() {
		loadConfig();
	}

	/**
	 * Load the configuration file and set corresponding values.
	 * If the file does not exist, attempt to create one.
	 * If that fails, continue with the hard-coded defaults.
	 * @return true if the file exists, false if it has to be created
	 */
	public static boolean loadConfig() {
		boolean scaleSet = false;
		try {
			configLines = Files.readAllLines(configPath);
		} catch(IOException e) {
			LOGGER.info("[WSS] Config file not found, attempting to create one...");
			createConfigFile();
			return false;
		}
		for(String str : configLines) {
			if(str.isBlank()) {
				// Line is empty - skip.
				continue;
			}
			if(str.trim().charAt(0) == '#') {
				// Line is a comment - skip.
				continue;
			}
			try {
				String key = str.split(":")[0].trim();
				String value = str.split(":")[1].trim();
				switch(key) {
					case "scale":
						scale = Double.parseDouble(value);
						scaleSet = true;
						break;
					default:
						LOGGER.warn("[WSS] Unknown config option: " + key);
				}
			} catch(Exception e) {
				LOGGER.warn("[WSS] Failed to load config line \"" + str + "\" due to: " + e.getMessage());
			}
		}
		// If any configuration value isn't set, update the configuration file
		if(!scaleSet) {
			createConfigFile();
		}
		LOGGER.info("[WSS] Config loaded.");
		return true;
	}

	/**
	 * Create a configuration file with the current loaded values.
	 * If the config hasn't been loaded, this will just create a new config file with the default values.
	 * @return true if the file is successfully written
	 */
	private static boolean createConfigFile() {
		configLines = new ArrayList<>();
		configLines.add("# Set the horizontal spacing of the items screen.");
		configLines.add("# 1 is vanilla, and the suggested range is 1.5 to 2.5.");
		configLines.add("scale: " + scale);
		try {
			Files.deleteIfExists(configPath);
			Files.createFile(configPath);
			for (String str : configLines) {
				Files.writeString(configPath, str + System.lineSeparator(), StandardOpenOption.APPEND);
			}
			LOGGER.info("[WSS] Config created.");
			return true;
		} catch(IOException e) {
			LOGGER.error("[WSS] Failed to create config file: " + e.getMessage());
			return false;
		}
	}
}
