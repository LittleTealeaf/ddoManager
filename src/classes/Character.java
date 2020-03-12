package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tealeaf
 * @version 0.0.1
 * @see Item
 * @since 0.0.1
 */
public class Character {

    private String name = "";

    private int inventorySlots = 40;
    private int bankSlots = 20;

    private List<Item> inventory = new ArrayList<>();
    private List<Item> bank = new ArrayList<>();

    private List<String> flaggedRaids = new ArrayList<>();
    private List<RaidTimer> raidTimers = new ArrayList<>();

    public Character() {}

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventorySlots() {
        return inventorySlots;
    }

    public void setInventorySlots(int inventorySlots) {
        this.inventorySlots = inventorySlots;
    }

    public int getBankSlots() {
        return bankSlots;
    }

    public void setBankSlots(int bankSlots) {
        this.bankSlots = bankSlots;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Item> getBank() {
        return bank;
    }

    public void setBank(List<Item> bank) {
        this.bank = bank;
    }

    /**
     * Grabs all items on the character
     *
     * @return List of all items, both in the inventory and the bank<p>
     * Items in the Inventory are listed first, followed by items in the bank
     * </p>
     */
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>(inventory);
        items.addAll(bank);
        return items;
    }

    public List<String> getFlaggedRaids() {
        return flaggedRaids;
    }

    public void setFlaggedRaids(List<String> flaggedRaids) {
        this.flaggedRaids = flaggedRaids;
    }

    public void clearRaidFlags() {
        flaggedRaids.clear();
    }

    public List<RaidTimer> getRaidTimers() {
        return raidTimers;
    }

    public void setRaidTimers(List<RaidTimer> raidTimers) {
        this.raidTimers = raidTimers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return inventorySlots == character.inventorySlots &&
                bankSlots == character.bankSlots &&
                Objects.equals(name, character.name) &&
                Objects.equals(inventory, character.inventory) &&
                Objects.equals(bank, character.bank) &&
                Objects.equals(flaggedRaids, character.flaggedRaids) &&
                Objects.equals(raidTimers, character.raidTimers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, inventorySlots, bankSlots, inventory, bank, flaggedRaids, raidTimers);
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", inventorySlots=" + inventorySlots +
                ", bankSlots=" + bankSlots +
                ", inventory=" + inventory +
                ", bank=" + bank +
                ", flaggedRaids=" + flaggedRaids +
                ", raidTimers=" + raidTimers +
                '}';
    }
}
