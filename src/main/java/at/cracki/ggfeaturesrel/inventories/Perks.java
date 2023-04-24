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


        List<String> speedlore = perks.getStringList("Perks.Speed.Lore");
        speedlore.replaceAll(line -> line.replaceAll("&", "§"));
        speedlore.replaceAll(line -> line.replaceAll("%PRICESPEED%", formatter.format(perks.getInt("Perks.Speed.Price"))));

        ItemStack speed = new ItemStack(Material.POTION);
        PotionMeta speedmeta = (PotionMeta) speed.getItemMeta();
        speedmeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 1), true);
        speedmeta.setDisplayName(perks.getString("Perks.Speed.Display").replaceAll("&", "§"));
        speedmeta.setLore(speedlore);
        speed.setItemMeta(speedmeta);


        List<String> firelore = perks.getStringList("Perks.FireRes.Lore");
        firelore.replaceAll(line -> line.replaceAll("&", "§"));
        firelore.replaceAll(line -> line.replaceAll("%PRICEFIRERES%", formatter.format(perks.getInt("Perks.FireRes.Price"))));

        ItemStack fire = new ItemStack(Material.POTION);
        PotionMeta firemeta = (PotionMeta) fire.getItemMeta();
        firemeta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 1), true);
        firemeta.setDisplayName(perks.getString("Perks.FireRes.Display").replaceAll("&", "§"));
        firemeta.setLore(firelore);
        fire.setItemMeta(firemeta);


        List<String> respirationlore = perks.getStringList("Perks.Respiration.Lore");
        respirationlore.replaceAll(line -> line.replaceAll("&", "§"));
        respirationlore.replaceAll(line -> line.replaceAll("%PRICERESPIRATION%", formatter.format(perks.getInt("Perks.Respiration.Price"))));

        ItemStack respiration = new ItemStack(Material.POTION);
        PotionMeta resipirationmeta = (PotionMeta) respiration.getItemMeta();
        resipirationmeta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 1), true);
        resipirationmeta.setDisplayName(perks.getString("Perks.Respiration.Display").replaceAll("&", "§"));
        resipirationmeta.setLore(respirationlore);
        respiration.setItemMeta(resipirationmeta);


        List<String> strengthlore = perks.getStringList("Perks.Strength.Lore");
        strengthlore.replaceAll(line -> line.replaceAll("&", "§"));
        strengthlore.replaceAll(line -> line.replaceAll("%PRICESTRENGTH%", formatter.format(perks.getInt("Perks.Strength.Price"))));

        ItemStack strength = new ItemStack(Material.POTION);
        PotionMeta strengthMeta = (PotionMeta) strength.getItemMeta();
        strengthMeta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1), true);
        strengthMeta.setDisplayName(perks.getString("Perks.Strength.Display").replaceAll("&", "§"));
        strengthMeta.setLore(strengthlore);
        strength.setItemMeta(strengthMeta);


        List<String> jumplore = perks.getStringList("Perks.Jump.Lore");
        jumplore.replaceAll(line -> line.replaceAll("&", "§"));
        jumplore.replaceAll(line -> line.replaceAll("%PRICEJUMP%", formatter.format(perks.getInt("Perks.Jump.Price"))));

        ItemStack jump = new ItemStack(Material.POTION);
        PotionMeta jumpMeta = (PotionMeta) jump.getItemMeta();
        jumpMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 1), true);
        jumpMeta.setDisplayName(perks.getString("Perks.Jump.Display").replaceAll("&", "§"));
        jumpMeta.setLore(jumplore);
        jump.setItemMeta(jumpMeta);


        List<String> hotbarlore = perks.getStringList("Perks.Hotbar.Lore");
        hotbarlore.replaceAll(line -> line.replaceAll("&", "§"));
        hotbarlore.replaceAll(line -> line.replaceAll("%PRICEHOTBAR%", formatter.format(perks.getInt("Perks.Hotbar.Price"))));

        ItemStack hotbar = new Item(Material.CHEST)
                .setName(perks.getString("Perks.Hotbar.Display").replaceAll("&", "§"))
                .setLore(hotbarlore)
                .toItemStack();


        List<String> keepxplore = perks.getStringList("Perks.KeepXP.Lore");
        keepxplore.replaceAll(line -> line.replaceAll("&", "§"));
        keepxplore.replaceAll(line -> line.replaceAll("%PRICEXP%", formatter.format(perks.getInt("Perks.KeepXP.Price"))));

        ItemStack keepxp = new Item(Material.EXPERIENCE_BOTTLE)
                .setName(perks.getString("Perks.KeepXP.Display").replaceAll("&", "§"))
                .setLore(keepxplore)
                .toItemStack();


        List<String> doublexplore = perks.getStringList("Perks.DoubleXP.Lore");
        doublexplore.replaceAll(line -> line.replaceAll("&", "§"));
        doublexplore.replaceAll(line -> line.replaceAll("%PRICEDOUBLEXP%", formatter.format(perks.getInt("Perks.DoubleXP.Price"))));

        ItemStack doublexp = new Item(Material.EXPERIENCE_BOTTLE)
                .setName(perks.getString("Perks.DoubleXP.Display").replaceAll("&", "§"))
                .setLore(doublexplore)
                .toItemStack();


        List<String> nightvisionlore = perks.getStringList("Perks.NightVision.Lore");
        nightvisionlore.replaceAll(line -> line.replaceAll("&", "§"));
        nightvisionlore.replaceAll(line -> line.replaceAll("%PRICENIGHT%", formatter.format(perks.getInt("Perks.NightVision.Price"))));

        ItemStack nightvision = new Item(Material.GLOWSTONE_DUST)
                .setName(perks.getString("Perks.NightVision.Display").replaceAll("&", "§"))
                .setLore(nightvisionlore)
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

        inventory.setItem(20, speed);
        inventory.setItem(21, fire);
        inventory.setItem(22, respiration);
        inventory.setItem(23, strength);
        inventory.setItem(24, jump);

        inventory.setItem(38, hotbar);
        inventory.setItem(39, keepxp);
        inventory.setItem(41, doublexp);
        inventory.setItem(42, nightvision);

        inventory.setItem(10, notpurchased);
        inventory.setItem(11, notpurchased);
        inventory.setItem(12, notpurchased);

        inventory.setItem(14, notpurchased);
        inventory.setItem(15, notpurchased);
        inventory.setItem(16, notpurchased);

        inventory.setItem(29, notpurchased);
        inventory.setItem(30, notpurchased);
        inventory.setItem(31, notpurchased);
        inventory.setItem(32, notpurchased);
        inventory.setItem(33, notpurchased);

        inventory.setItem(47, notpurchased);
        inventory.setItem(48, notpurchased);
        inventory.setItem(50, notpurchased);
        inventory.setItem(51, notpurchased);
        setFillerGlass();
    }
}
