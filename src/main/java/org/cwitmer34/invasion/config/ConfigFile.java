package org.cwitmer34.invasion.config;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.SneakyThrows;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.util.ConsoleUtil;
import org.cwitmer34.invasion.util.LocationUtil;

public class ConfigFile {

  private final String configName;
  protected File file;
  protected YamlConfiguration config;

  @SneakyThrows
  public ConfigFile(File folder, String configName) {
    this.configName = configName;
    file = new File(folder, configName);

    this.init();
  }

  public void init() throws IOException {
    if (!file.exists()) {
      file.createNewFile();
      loadDefaults();
    }

    config = YamlConfiguration.loadConfiguration(file);
  }

  public void loadDefaults() throws IOException {
    InputStream is = getClass().getResourceAsStream("/" + configName);
    if (is != null) {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      writer.write(readFile(is));
      writer.close();
    }
  }

  public void set(String path, Object value) {
    config.set(path, value);
    try {
      save();
    } catch (IOException e) {
      ConsoleUtil.severe(NamedTextColor.RED + "There was an error saving the configuration file " + configName);
      ConsoleUtil.severe(e.getMessage());
    }
  }

  public void addDefault(String key, Object value) {
    if (!config.contains(key)) {
      config.set(key, value);
    }
  }

  public String readFile(InputStream inputStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String content = "";
    String line;
    while ((line = reader.readLine()) != null) {
      content += line + "\n";
    }

    reader.close();
    return content.trim();
  }

  public void save() throws IOException {
    if (!file.exists()) file.createNewFile();

    config.save(file);
  }

  public String getString(String path) {
    return this.config.getString(path);
  }

  public int getInt(String path) {
    return this.config.getInt(path);
  }

  public long getLong(String path) {
    return this.config.getLong(path);
  }

  public double getDouble(String path) {
    return this.config.getDouble(path);
  }

  public boolean getBoolean(String path) {
    return this.config.getBoolean(path);
  }

  public Collection<String> getSection(String section) {
    return this.config.getConfigurationSection(section).getKeys(false);
  }

  public List<String> getStringList(String path) {
    return this.config.getStringList(path);
  }

  public YamlConfiguration getConfig() {
    return config;
  }

  public void reloadConfig() {
    try {
      init();
    } catch (IOException e) {
      ConsoleUtil.severe("There was an error reloading the file " + configName);
      ConsoleUtil.severe(e.getMessage());
    }
  }

  public void overrideWithDefaults() {
    try {
      loadDefaults();
      config = YamlConfiguration.loadConfiguration(file);
    } catch (IOException e) {
      ConsoleUtil.severe("There was an error overriding the file " + configName + " with defaults.");
      ConsoleUtil.severe(e.getMessage());
    }
  }

  public List<Location> getLocations(String s) {
    ConfigurationSection section = config.getConfigurationSection(s);
    return LocationUtil.parse(section);
  }
}
