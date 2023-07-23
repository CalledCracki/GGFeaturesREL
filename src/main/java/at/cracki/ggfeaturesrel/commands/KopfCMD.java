package at.cracki.ggfeaturesrel.commands;

import at.cracki.ggfeaturesrel.GGFeatures;
import at.cracki.ggfeaturesrel.files.FileManager;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class KopfCMD implements CommandExecutor {

    private final GGFeatures instance;

    public KopfCMD(GGFeatures instance) {
        this.instance = instance;
        instance.getCommand("kopf").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            String prefix = instance.getConfig().getString("GGFeatures.Settings.Prefix").replaceAll("&", "§");
            if (args.length == 1) {
                if (player.hasPermission("gg.commands.kopf") || player.hasPermission("gg.commands.kopf.lite")) {
                    YamlDocument cooldownYaml = FileManager.getCooldownConfig();
                    if (cooldownYaml.get(player.getUniqueId().toString()) == null
                        || cooldownYaml.getLong(player.getUniqueId().toString()) < System.currentTimeMillis()
                        || !cooldownYaml.contains(player.getUniqueId().toString())
                        || player.hasPermission("gg.bypass.kopfcooldown")) {

                        String targetName = args[0];

                        //boolean isNewVersion = Arrays.stream(Material.values())
                        //        .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

                        //Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM")

                        Material type = Material.PLAYER_HEAD;

                        ItemStack itemStack = new ItemStack(type, 1);

                        /*
                        if (!isNewVersion) {
                            itemStack.setDurability((short) 3);
                        }
                         */
                        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

                        skullMeta.setOwner(targetName);

                        itemStack.setItemMeta(skullMeta);

                        if (player.getInventory().firstEmpty() != -1){
                            player.getInventory().addItem(itemStack);

                            player.sendMessage(prefix + "§aDu hast den Kopf von §e" + targetName + " §abekommen.");

                            int cooldown = player.hasPermission("gg.commands.kopf") ? 604800 : 1209600;
                            cooldownYaml.set(player.getUniqueId().toString(), System.currentTimeMillis() + cooldown * 1000);
                            try {
                                cooldownYaml.save();
                                Date date = new Date(cooldownYaml.getLong(player.getUniqueId().toString()));
                                String mmDDyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                String hourMin = new SimpleDateFormat("HH:mm").format(date);
                                player.sendMessage("§aAm §e" + mmDDyyy + " §aum §e" + hourMin + " §akannst du dir den nächsten Kopf abholen.");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                        } else {
                            player.sendMessage(prefix + "§cDu hast keinen Platz in deinem Inventar.");
                        }
                    } else {
                        Date date = new Date(cooldownYaml.getLong(player.getUniqueId().toString()));
                        String mmDDyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                        String hourMin = new SimpleDateFormat("HH:mm").format(date);
                        player.sendMessage(prefix + "§cDu kannst erst am §e" + mmDDyyy + " §cum §e" + hourMin + " §cwieder den /kopf-Befehl verwenden.");
                    }
                }
            } else {
                player.sendMessage(prefix + "§cNutze /kopf <Name>");
            }
        }
        return false;
    }
}
