package at.cracki.ggfeaturesrel.inventories;

import at.cracki.ggfeaturesrel.GGFeatures;
import at.cracki.ggfeaturesrel.files.FileManager;
import at.cracki.ggfeaturesrel.utils.Item;
import at.cracki.ggfeaturesrel.utils.inventoryManager.Menu;
import at.cracki.ggfeaturesrel.utils.inventoryManager.PlayerMenuUtility;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return;
        if(!event.getView().getTitle().equalsIgnoreCase(getMenuName())) return;
        if(!item.getType().equals(Material.YELLOW_STAINED_GLASS_PANE)) return;
        int clickedSlot = event.getRawSlot();
        int aboveSlot = clickedSlot - 9;
        if(aboveSlot < 0 ) {
            aboveSlot = -1;
        }
        PerkPurchase.slot = aboveSlot;
        new PerkPurchase(GGFeatures.getPlayerMenuUtility(player)).open();
    }

    @Override
    public void setMenuItems() {
        YamlDocument perks = FileManager.getPerkConfig();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);        Material material;
        ItemStack item;

        String[] mainItemKeys = {
                "FallDamage", "ClearChat", "StartKick", "SlowChat", "Mute",
                "NoHunger", "Speed", "FireRes", "Respiration", "Strength",
                "Jump", "Hotbar", "KeepXP", "DoubleXP", "NightVision"
        };

        int[] mainItemSlots = {
                1, 2, 3, 5, 6, 7, 20, 21, 22, 23, 24, 38, 39, 41, 42
        };

        ItemStack[] mainItems = new ItemStack[mainItemKeys.length];
        ItemStack notPurchasedItem = new Item(Material.YELLOW_STAINED_GLASS_PANE)
                .setName("§6Nicht verfügbar")
                .setLore(Arrays.asList("§7Kaufe dieses Perk, um Einstellungen vornehmen zu können."))
                .toItemStack();

        // Set the main items
        for (int i = 0; i < mainItemKeys.length; i++) {
            String key = mainItemKeys[i];
            List<String> lore = perks.getStringList("Perks." + key + ".Lore");
            lore.replaceAll(line -> line.replace("&", "§"));
            lore.replaceAll(line -> line.replace("%PRICE" + key.toUpperCase() + "%", formatter.format(perks.getInt("Perks." + key + ".Price"))));

            material = Material.valueOf(perks.getString("Perks." + key + ".Material"));
            item = new Item(material)
                .setName(perks.getString("Perks." + key + ".Display").replace("&", "§"))
                .setLore(lore)
                .toItemStack();

            if (material == Material.POTION) {
                PotionEffectType potionType = PotionEffectType.getByName(perks.getString("Perks." + key + ".PotionType"));
                if (potionType != null) {
                    PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
                    potionMeta.addCustomEffect(new PotionEffect(potionType, 3600, 1), true);
                    item.setItemMeta(potionMeta);
                }
            }

            int slot = mainItemSlots[i];
            inventory.setItem(slot, item);
            inventory.setItem(slot + 9, notPurchasedItem);
        }

        int[] placeholderSlots = { 10, 11, 12, 14, 15, 16, 29, 30, 31, 32, 33, 47, 48, 50, 51 };
        for (int slot : placeholderSlots) {
            inventory.setItem(slot, notPurchasedItem);
        }

        setFillerGlass();
    }
}
