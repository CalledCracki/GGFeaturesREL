package at.cracki.ggfeaturesrel.commands;

import at.cracki.ggfeaturesrel.GGFeatures;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class RenameCMD implements CommandExecutor {

    private final GGFeatures instance;

    public RenameCMD(GGFeatures instance) {
        this.instance = instance;
        instance.getCommand("rename").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            String prefix = instance.getConfig().getString("GGFeatures.Settings.Prefix").replaceAll("&", "§");
            if (player.hasPermission("gg.commands.rename")) {
                if (args.length >= 1) {
                    if (player.getItemInHand() != null && !player.getItemInHand().getType().equals(Material.AIR)) {
                        ItemStack itemStack = player.getItemInHand();
                        ItemMeta itemMeta = itemStack.getItemMeta();

                        StringBuilder stringBuilder = new StringBuilder("");
                        for (int i = 0; i < args.length; i++) {
                            if (i == args.length - 1) {
                                stringBuilder.append(args[i]);
                            } else {
                                stringBuilder.append(args[i]).append(" ");
                            }
                        }

                        String message = stringBuilder.toString();

                        if (player.hasPermission("gg.commands.rename.color")) {
                            message.replace('&', '§');
                        }

                        itemMeta.setDisplayName(message);
                        itemStack.setItemMeta(itemMeta);

                        player.setItemInHand(itemStack);
                        player.sendMessage(prefix + "§aDas Item wurde umbenannt.");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);

                    } else {
                        player.sendMessage(prefix + "§cDu musst ein Item in der Hand halten.");
                    }
                } else {
                    player.sendMessage(prefix + "§cNutze /rename <Name>");
                }
            } else {

            }
        }
        return false;
    }
}
