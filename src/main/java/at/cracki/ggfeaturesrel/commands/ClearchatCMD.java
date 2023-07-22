package at.cracki.ggfeaturesrel.commands;

import at.cracki.ggfeaturesrel.GGFeatures;
import at.cracki.ggfeaturesrel.files.FileManager;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ClearchatCMD implements CommandExecutor {
    private final GGFeatures instance;
    private final YamlDocument perkDataConfig = FileManager.getPerkDataConfig();
    private final HashMap<UUID, Long> cooldown;

    public ClearchatCMD(GGFeatures instance) {
        this.instance = instance;
        this.cooldown = new HashMap<>();
        instance.getCommand("clearchat").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
            if(sender instanceof Player player) {
                String prefix = instance.getConfig().getString("GGFeatures.Settings.Prefix").replaceAll("&", "§");
                String name = player.getName();
                if(args.length == 0) {
                    if(player.hasPermission("gg.bypass.clearchat")) {
                        if (perkDataConfig.getString("Perks.ClearChat." + player.getUniqueId()) == null) {
                        } else
                            player.sendMessage(prefix + "§aKaufe dir diesen Befehl mit §5/perks§7.");
                    } else {

                    }
                }
            }
        return false;
    }
}