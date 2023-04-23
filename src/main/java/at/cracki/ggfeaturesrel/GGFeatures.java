package at.cracki.ggfeaturesrel;

import at.cracki.ggfeaturesrel.commands.PerksCMD;
import at.cracki.ggfeaturesrel.files.FileManager;
import at.cracki.ggfeaturesrel.listener.MainListener;
import at.cracki.ggfeaturesrel.utils.inventoryManager.PlayerMenuUtility;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class GGFeatures extends JavaPlugin {
    public static String version = "§r§fv4.0";

    @Getter
    static GGFeatures instance;
    private final ConsoleCommandSender cs = Bukkit.getConsoleSender();
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        instance.saveDefaultConfig();
        FileManager.createFiles(this);
        init();

        String Prefix = instance.getConfig().getString("GGFeatures.Settings.Prefix").replace("&", "§");
        cs.sendMessage(Prefix + "§aDas Plugin wurde erfolgreich gestartet!");
        cs.sendMessage(Prefix + "§6" + version + " §bby §aNiklas409 §b& §aCalledCracki");
    }

    @Override
    public void onDisable() {
        String Prefix = instance.getConfig().getString("GGFeatures.Settings.Prefix").replace("&", "§");
        cs.sendMessage(Prefix + "§aDas Plugin wurde §cgestoppt!");
        cs.sendMessage(Prefix + "§6" + version + " §bby §aNiklas409 §b& §aCalledCracki");
    }

    public void init() {
        new PerksCMD(this);

        new MainListener(this);
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(player))) { //See if the player has a PlayerMenuUtility "saved" for them

            //This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(player); //Return the object by using the provided player
        }
    }

    public static void reload() {
        Bukkit.getPluginManager().getPlugin("GGFeatures").getConfig();
        Bukkit.getPluginManager().getPlugin("GGFeatures").reloadConfig();
    }
}
