package at.cracki.ggfeaturesrel.inventories;

import at.cracki.ggfeaturesrel.files.FileManager;
import at.cracki.ggfeaturesrel.utils.Item;
import at.cracki.ggfeaturesrel.utils.inventoryManager.Menu;
import at.cracki.ggfeaturesrel.utils.inventoryManager.PlayerMenuUtility;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class Perks extends Menu {
    public Perks(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§6Perks";
    }

    @Override
    public int getSlots() {
        return 9*6;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        if(event.getView().getTitle().equalsIgnoreCase("§6Perks")) {
            event.setCancelled(true);
        }
    }

    @Override
    public void setMenuItems() {
        YamlDocument perks = FileManager.getPerkConfig();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        List<String> falldmglore = perks.getStringList("Perks.FallDamage.Lore");
        falldmglore.replaceAll(line -> line.replaceAll("&", "§"));
        falldmglore.replaceAll(line -> line.replaceAll("%PRICEFALLDMG%", formatter.format(perks.getInt("Perks.FallDamage.Price"))));

        ItemStack falldmg = new Item(Material.DIAMOND_BOOTS)
                .setName(perks.getString("Perks.FallDamage.Display").replaceAll("&", "§"))
                .setLore(falldmglore)
                .toItemStack();


        List<String> clearchatlore = perks.getStringList("Perks.ClearChat.Lore");
        clearchatlore.replaceAll(line -> line.replaceAll("&", "§"));
        clearchatlore.replaceAll(line -> line.replaceAll("%PRICECLEAR%", formatter.format(perks.getInt("Perks.ClearChat.Price"))));

        ItemStack clearchat = new Item(Material.PAPER)
                .setName(perks.getString("Perks.ClearChat.Display").replaceAll("&", "§"))
                .setLore(clearchatlore)
                .toItemStack();


        List<String> kicklore = perks.getStringList("Perks.StartKick.Lore");
        kicklore.replaceAll(line -> line.replaceAll("&", "§"));
        kicklore.replaceAll(line -> line.replaceAll("%PRICEKICK%", formatter.format(perks.getInt("Perks.StartKick.Price"))));

        ItemStack startkick = new Item(Material.OAK_SIGN)
                .setName(perks.getString("Perks.StartKick.Display").replaceAll("&", "§"))
                .setLore(kicklore)
                .toItemStack();


        List<String> slowlore = perks.getStringList("Perks.SlowChat.Lore");
        slowlore.replaceAll(line -> line.replaceAll("&", "§"));
        slowlore.replaceAll(line -> line.replaceAll("%PRICESLOW%", formatter.format(perks.getInt("Perks.SlowChat.Price"))));

        ItemStack slowchat = new Item(Material.SOUL_SAND)
                .setName(perks.getString("Perks.SlowChat.Display").replaceAll("&", "§"))
                .setLore(slowlore)
                .toItemStack();


        List<String> mutelore = perks.getStringList("Perks.Mute.Lore");
        mutelore.replaceAll(line -> line.replaceAll("&", "§"));
        mutelore.replaceAll(line -> line.replaceAll("%PRICEMUTE%", formatter.format(perks.getInt("Perks.Mute.Price"))));

        ItemStack mute = new Item(Material.BARRIER)
                .setName(perks.getString("Perks.Mute.Display").replaceAll("&", "§"))
                .setLore(mutelore)
                .toItemStack();


        List<String> nohungerlore = perks.getStringList("Perks.NoHunger.Lore");
        nohungerlore.replaceAll(line -> line.replaceAll("&", "§"));
        nohungerlore.replaceAll(line -> line.replaceAll("%PRICEHUNGER%", formatter.format(perks.getInt("Perks.NoHunger.Price"))));

        ItemStack nohunger = new Item(Material.COOKED_BEEF)
                .setName(perks.getString("Perks.NoHunger.Display").replaceAll("&", "§"))
                .setLore(nohungerlore)
                .toItemStack();

        ItemStack notpurchased = new Item(Material.YELLOW_STAINED_GLASS_PANE)
                .setName("§6Nicht verfügbar")
                .setLore(Arrays.asList("§7Kaufe dieses Perk, um Einstellungen vornehmen zu können."))
                .toItemStack();

        inventory.setItem(1, falldmg);
        inventory.setItem(2, clearchat);
        inventory.setItem(3, startkick);

        inventory.setItem(5, slowchat);
        inventory.setItem(6, mute);
        inventory.setItem(7, nohunger);

        inventory.setItem(10, notpurchased);
        inventory.setItem(11, notpurchased);
        inventory.setItem(12, notpurchased);

        inventory.setItem(14, notpurchased);
        inventory.setItem(15, notpurchased);
        inventory.setItem(16, notpurchased);

        setFillerGlass();
    }
}
