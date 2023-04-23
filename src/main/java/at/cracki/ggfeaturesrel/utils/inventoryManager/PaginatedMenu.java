package at.cracki.ggfeaturesrel.utils.inventoryManager;

import at.cracki.ggfeaturesrel.utils.Item;
import org.bukkit.Material;

/*

A class extending the functionality of the regular Menu, but making it Paginated

This pagination system was made from Jer's code sample. <3

 */

public abstract class PaginatedMenu extends Menu {

    // Keep track of what page the menu is on
    protected int page = 0;
    // 28 is max items because with the border set below,
    // 28 empty slots are remaining.
    protected int maxItemsPerPage = 28;
    // the index represents the index of the slot
    // that the loop is on
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    // Set the border and menu buttons for the menu
    public void addPaginatedMenuBorder(){
        inventory.setItem(48, new Item(Material.STONE_BUTTON).setName("§aLeft").toItemStack());

        inventory.setItem(49, new Item(Material.BARRIER).setName("§c§lClose").toItemStack());

        inventory.setItem(50, new Item(Material.STONE_BUTTON).setName("§aRight").toItemStack());

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS2);
            }
        }

        inventory.setItem(17, super.FILLER_GLASS2);
        inventory.setItem(18, super.FILLER_GLASS2);
        inventory.setItem(26, super.FILLER_GLASS2);
        inventory.setItem(27, super.FILLER_GLASS2);
        inventory.setItem(35, super.FILLER_GLASS2);
        inventory.setItem(36, super.FILLER_GLASS2);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS2);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
}