package at.cracki.ggfeaturesrel.listener;

import at.cracki.ggfeaturesrel.GGFeatures;
import at.cracki.ggfeaturesrel.Scoreboard;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MainListener implements Listener {
    private final GGFeatures instance;

    public MainListener(GGFeatures instance) {
        this.instance = instance;
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = GGFeatures.getInstance().getConfig();
        String prefix = config.getString("GGFeatures.Settings.Prefix").replaceAll("&", "§");
        Scoreboard.sendScoreboard(player);
        try {
            if(GGFeatures.getInstance().getConfig().getBoolean("GGFeatures.Settings.AdOnJoin")) {
                TextComponent textComponent = new TextComponent();
                textComponent.setText(prefix + GGFeatures.version + " §bby §aNiklas409 §b& §aCalledCracki. §fClick for details");
                textComponent.setBold(true);
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gg"));
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        (new ComponentBuilder("§6Click for details")).create()));
                player.spigot().sendMessage(textComponent);
            }
        } catch (Exception exception) {exception.printStackTrace();}
    }

}
