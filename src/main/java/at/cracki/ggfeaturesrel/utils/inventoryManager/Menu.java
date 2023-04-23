package at.cracki.ggfeaturesrel.utils.inventoryManager;

import at.cracki.ggfeaturesrel.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/*
    Defines the behavior and attributes of all menus in our plugin
 */
public abstract class Menu implements InventoryHolder {

    //Protected values that can be accessed in the menus
    protected PlayerMenuUtility playerMenuUtility;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS2 = new Item(Material.GRAY_STAINED_GLASS_PANE, 1, (byte) 7).setName(" ").toItemStack();

    // Constructor for Menu. Pass in a PlayerMenuUtility so that
    // we have information on who's menu this is and
    // what info is to be transferred
    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    // let each menu decide their name
    public abstract String getMenuName();

    // let each menu decide their slot amount
    public abstract int getSlots();

    // let each menu decide how the items in the menu will be handled when clicked
    public abstract void handleMenu(InventoryClickEvent event);

    // let each menu decide what items are to be placed in the inventory menu
    public abstract void setMenuItems();

    // When called, an inventory is created and opened for the player
    public void open() {
        // The owner of the inventory created is the Menu itself,
        // so we are able to reverse engineer the Menu object from the
        // inventoryHolder in the MenuListener class when handling clicks
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        // grab all the items specified to be used for this menu and add to inventory
        this.setMenuItems();

        // open the inventory for the player
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    // Overridden method from the InventoryHolder interface
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    // Helpful utility method to fill all remaining slots with "filler glass"
    public void setFillerGlass(){
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null){
                inventory.setItem(i, FILLER_GLASS2);
            }
        }
    }

    // Set the border and menu buttons for the menu
    public void addMenuBorder(){
        inventory.setItem(49, new Item(Material.BARRIER).setName("§c§lClose").toItemStack());

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS2);
            }
        }

        inventory.setItem(17, FILLER_GLASS2);
        inventory.setItem(18, FILLER_GLASS2);
        inventory.setItem(26, FILLER_GLASS2);
        inventory.setItem(27, FILLER_GLASS2);
        inventory.setItem(35, FILLER_GLASS2);
        inventory.setItem(36, FILLER_GLASS2);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS2);
            }
        }
    }
}