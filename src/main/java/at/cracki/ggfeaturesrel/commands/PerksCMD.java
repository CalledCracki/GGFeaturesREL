package at.cracki.ggfeaturesrel.commands;

import at.cracki.ggfeaturesrel.GGFeatures;
import at.cracki.ggfeaturesrel.files.FileManager;
import at.cracki.ggfeaturesrel.inventories.Perks;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class PerksCMD implements CommandExecutor {
    private final GGFeatures instance;
    YamlDocument perks = FileManager.getPerkConfig();
    YamlDocument data = FileManager.getPerkDataConfig();

    public PerksCMD(GGFeatures instance) {
        this.instance = instance;
        instance.getCommand("perks").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String prefix = instance.getConfig().getString("GGFeatures.Settings.Prefix").replaceAll("&", "§");

        if(!(sender instanceof Player player)) return true;
            String name = player.getName();
            new Perks(GGFeatures.getPlayerMenuUtility(player)).open();
        return false;
    }
}