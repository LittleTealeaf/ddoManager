package classes;

import java.util.Objects;

/**
 * Represents an item in DDO
 * <p>An item contains the following fields: It's name, the bind status, and the bonsues that contains</p>
 * <p>{@code Bonuses} refers to anything, such as mythic / reaper bonuses, crafting, slotted augments, etc.</p>
 * @author Tealeaf
 */
public class Item {

    private String name = "";
    private BindStatus bindStatus = BindStatus.BTA;
    private String bonuses = "";

    /**
     * Creates an empty {@code Item Object}
     * <p>The default values created with this constructor:</p>
     * <ul><li>{@code name} = {@code ""}</li>
     * <li>{@code Bind Status} = {@code Bound to Account}</li>
     * <li>{@code Bonuses} = {@code ""}</li></ul>
     */
    public Item() {}

    /**
     * Creates an empty {@code Item Object} with a given name
     * <p>The default values created with this constructor:</p>
     * <ul><li>{@code Bind Status} = {@code Bound to Account}</li>
     * <li>{@code Bonuses} = {@code ""}</li></ul>
     * @param name Complete name of the item. This includes any prefixes or suffixes,
     *             such that "Bracers of the Fallen Hero" and "Legendary Bracers of the
     *             Fallen Hero" count as two separate items
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Creates an empty {@code Item Object} with a given name and bind status
     * <p>The default values created with this constructor:</p>
     * <ul>
     * <li>{@code Bonuses} = {@code ""}</li></ul>
     * @param name Complete name of the item. This includes any prefixes or suffixes,
     *             such that "Bracers of the Fallen Hero" and "Legendary Bracers of the
     *             Fallen Hero" count as two separate items
     * @param bindStatus Bind Status of the Item. {@link BindStatus} is an enumerator with the following values:
     *                   <p>{@link BindStatus#UNBOUND Unbound}, {@link BindStatus#BTA Bound to Account},
     *                   {@link BindStatus#BTC Bound to Character}</p>
     */
    public Item(String name, BindStatus bindStatus) {
        this.name = name;
        this.bindStatus = bindStatus;
    }

    /**
     * Creates an empty {@code Item Object} with a given name and bonuses
     * <p>The default values created with this constructor:</p>
     * <ul><li>{@code Bind Status} = {@code Bound to Account}</li></ul>
     * @param name Complete name of the item. This includes any prefixes or suffixes,
     *             such that "Bracers of the Fallen Hero" and "Legendary Bracers of the
     *             Fallen Hero" count as two separate items
     * @param bonuses Any bonuses attached to the item. This includes any {@code Mythic Bonuses}, {@code Reaper Bonuses},
     *                or anything crafted onto the item
     */
    public Item(String name, String bonuses) {
        this.name = name;
        this.bonuses = bonuses;
    }

    /**
     * Creates an empty {@code Item Object} with all set parameters
     * @param name Complete name of the item. This includes any prefixes or suffixes,
     *             such that "Bracers of the Fallen Hero" and "Legendary Bracers of the
     *             Fallen Hero" count as two separate items
     * @param bindStatus Bind Status of the Item. {@link BindStatus} is an enumerator with the following values:
     *                   <p>{@link BindStatus#UNBOUND Unbound}, {@link BindStatus#BTA Bound to Account},
     *                   {@link BindStatus#BTC Bound to Character}</p>
     * @param bonuses Any bonuses attached to the item. This includes any {@code Mythic Bonuses}, {@code Reaper Bonuses},
     *                or anything crafted onto the item
     */
    public Item(String name, BindStatus bindStatus, String bonuses) {
        this.name = name;
        this.bindStatus = bindStatus;
        this.bonuses = bonuses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BindStatus getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(BindStatus bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getBonuses() {
        return bonuses;
    }

    public void setBonuses(String bonuses) {
        this.bonuses = bonuses;
    }

    public boolean filters(String filter) {
        filter = filter.toLowerCase();
        return name.toLowerCase().contains(filter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                bindStatus == item.bindStatus &&
                Objects.equals(bonuses, item.bonuses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bindStatus, bonuses);
    }

    @Override
    public String toString() {
        return name + " (" + bindStatus.getShort() + ") + Bonuses: \n " + bonuses;
    }

    enum BindStatus {

        BTC("BTC","Bind to Character"),
        BTA("BTA","Bind to Account"),
        UNBOUND("N/A","Unbound");

        public String nameShort;
        public String nameLong;

        BindStatus(String nameShort, String nameLong) {
            this.nameShort = nameShort;
            this.nameLong = nameLong;
        }

        public String getShort() {
            return nameShort;
        }

        public String getLong() {
            return nameLong;
        }
    }
}
