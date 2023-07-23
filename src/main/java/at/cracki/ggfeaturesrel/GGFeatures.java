package at.cracki.ggfeaturesrel;

import at.cracki.ggfeaturesrel.commands.ClearchatCMD;
import at.cracki.ggfeaturesrel.commands.KopfCMD;
import at.cracki.ggfeaturesrel.commands.PerksCMD;
import at.cracki.ggfeaturesrel.files.FileManager;
import at.cracki.ggfeaturesrel.listener.MainListener;
import at.cracki.ggfeaturesrel.listener.MenuListener;
import at.cracki.ggfeaturesrel.utils.inventoryManager.PlayerMenuUtility;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;

/**

    Continuation of Niklas' GrieferGamesFeatures Plugin, made by CalledCracki.
    Check out Niklas' YouTube Channel and Discord Server and leave some love,
    for the original version of GG Features <3
    <i> When downloading the source code, please leave this notice </i>
    <i> and credit Niklas & me when using one of our code snippets :) </i>

 */


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
        new ClearchatCMD(this);
        new KopfCMD(this);

        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        new MainListener(this);
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(player))) {

            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(player);
        }
    }

    public static void reload() {
        Bukkit.getPluginManager().getPlugin("GGFeatures").getConfig();
        Bukkit.getPluginManager().getPlugin("GGFeatures").reloadConfig();
    }
}
