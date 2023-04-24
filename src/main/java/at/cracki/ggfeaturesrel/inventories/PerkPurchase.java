package at.cracki.ggfeaturesrel.inventories;

import at.cracki.ggfeaturesrel.GGFeatures;
import at.cracki.ggfeaturesrel.files.FileManager;
import at.cracki.ggfeaturesrel.utils.Item;
import at.cracki.ggfeaturesrel.utils.inventoryManager.Menu;
import at.cracki.ggfeaturesrel.utils.inventoryManager.PlayerMenuUtility;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PerkPurchase extends Menu {
    public static int slot;
    public PerkPurchase(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§6Perk - Kauf";
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
        if(item.getType().equals(Material.RED_WOOL)) {
            new Perks(GGFeatures.getPlayerMenuUtility(player)).open();
        }
    }

    @Override
    public void setMenuItems() {
        String lore = "§7Willst du dieses Perk wirklich kaufen?";
        YamlDocument perks = FileManager.getPerkConfig();

        ItemStack falldmg = new Item(Material.DIAMOND_BOOTS)
                .setName(perks.getString("Perks.FallDamage.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack clearchat = new Item(Material.PAPER)
                .setName(perks.getString("Perks.ClearChat.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack startkick = new Item(Material.OAK_SIGN)
                .setName(perks.getString("Perks.StartKick.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack slowchat = new Item(Material.SOUL_SAND)
                .setName(perks.getString("Perks.SlowChat.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack mute = new Item(Material.BARRIER)
                .setName(perks.getString("Perks.Mute.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack nohunger = new Item(Material.COOKED_BEEF)
                .setName(perks.getString("Perks.NoHunger.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack speed = new ItemStack(Material.POTION);
        PotionMeta speedmeta = (PotionMeta) speed.getItemMeta();
        speedmeta.setDisplayName(perks.getString("Perks.Speed.Display").replaceAll("&", "§"));
        speedmeta.setLore(Collections.singletonList(lore));
        speed.setItemMeta(speedmeta);

        ItemStack fire = new ItemStack(Material.POTION);
        PotionMeta firemeta = (PotionMeta) speed.getItemMeta();
        firemeta.setDisplayName(perks.getString("Perks.FireRes.Display").replaceAll("&", "§"));
        firemeta.setLore(Collections.singletonList(lore));
        fire.setItemMeta(firemeta);

        ItemStack respiration = new ItemStack(Material.POTION);
        PotionMeta respirationmeta = (PotionMeta) speed.getItemMeta();
        respirationmeta.setDisplayName(perks.getString("Perks.Respiration.Display").replaceAll("&", "§"));
        respirationmeta.setLore(Collections.singletonList(lore));
        respiration.setItemMeta(respirationmeta);

        ItemStack strength = new ItemStack(Material.POTION);
        PotionMeta strengthmeta = (PotionMeta) speed.getItemMeta();
        strengthmeta.setDisplayName(perks.getString("Perks.Strength.Display").replaceAll("&", "§"));
        strengthmeta.setLore(Collections.singletonList(lore));
        strength.setItemMeta(strengthmeta);

        ItemStack jump = new ItemStack(Material.POTION);
        PotionMeta jumpmeta = (PotionMeta) speed.getItemMeta();
        jumpmeta.setDisplayName(perks.getString("Perks.Jump.Display").replaceAll("&", "§"));
        jumpmeta.setLore(Collections.singletonList(lore));
        jump.setItemMeta(jumpmeta);

        ItemStack hotbar = new Item(Material.CHEST)
                .setName(perks.getString("Perks.Hotbar.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack keepxp = new Item(Material.EXPERIENCE_BOTTLE)
                .setName(perks.getString("Perks.KeepXP.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack doublexp = new Item(Material.EXPERIENCE_BOTTLE)
                .setName(perks.getString("Perks.DoubleXP.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack nightvision = new Item(Material.GLOWSTONE_DUST)
                .setName(perks.getString("Perks.NightVision.Display").replaceAll("&", "§"))
                .setLore(Collections.singletonList(lore))
                .toItemStack();

        ItemStack buy = new Item(Material.GREEN_WOOL)
                .setName("§aJa").setLore(Arrays.asList("§7Klicke, um den Kauf zu bestätigen.")).toItemStack();

        ItemStack cancel = new Item(Material.RED_WOOL)
                .setName("§cNein").setLore(Arrays.asList("§7Klicke, um den Kauf abzubrechen.")).toItemStack();

        switch (slot) {
            case 1 -> inventory.setItem(13, falldmg);
            case 2 -> inventory.setItem(13, clearchat);
            case 3 -> inventory.setItem(13, startkick);
            case 5 -> inventory.setItem(13, slowchat);
            case 6 -> inventory.setItem(13, mute);
            case 7 -> inventory.setItem(13, nohunger);
            case 20 -> inventory.setItem(13, speed);
            case 21 -> inventory.setItem(13, fire);
            case 22 -> inventory.setItem(13, respiration);
            case 23 -> inventory.setItem(13, strength);
            case 24 -> inventory.setItem(13, jump);
            case 38 -> inventory.setItem(13, hotbar);
            case 39 -> inventory.setItem(13, keepxp);
            case 41 -> inventory.setItem(13, doublexp);
            case 42 -> inventory.setItem(13, nightvision);
        }
        inventory.setItem(38, buy);
        inventory.setItem(42, cancel);
        setFillerGlass();
    }
}
