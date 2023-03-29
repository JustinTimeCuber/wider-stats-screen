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
		LOGGER.info("Wider Stats Screen loaded.");
	}

	public static void loadConfig() {
		try {
			configLines = Files.readAllLines(configPath);
		} catch(IOException e) {
			LOGGER.info("Config file not found, attempting to create one...");
			createDefaultConfig();
		}
		for(String str : configLines) {
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
						break;
					default:
						LOGGER.warn("Unknown config option: " + key);
				}
			} catch(Exception e) {
				LOGGER.warn("Failed to load config line \"" + str + "\" due to: " + e.getMessage());
			}
		}
	}

	private static void createDefaultConfig() {
		configLines = new ArrayList<>();
		configLines.add("# Set the horizontal spacing of the items screen.");
		configLines.add("# 1 is vanilla, and the suggested range is 1.5 to 2.5.");
		configLines.add("scale: 1.75");
		try {
			Files.createFile(configPath);
			for (String str : configLines) {
				Files.writeString(configPath, str + System.lineSeparator(), StandardOpenOption.APPEND);
			}
		} catch(IOException e) {
			LOGGER.error("Failed to create config file: " + e.getMessage());
		}
	}
}
