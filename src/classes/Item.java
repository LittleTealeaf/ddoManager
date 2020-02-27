package classes;

import java.util.List;

public class Item {

    private String name = "";
    private int minLevel = 1;
    private BindStatus bindStatus = BindStatus.BTA;
    private String bonuses = "";
    private List<Enhancement> enhancements = null;


    public Item() {}


    /**
     * Bind status of the item
     */
    public enum BindStatus {
        BTA("Bound to Account", "BTA"),
        BTC("Bound to Character", "BTC");

        public String fullName;
        public String shortName;

        BindStatus(String Full, String Short) {
            this.fullName = Full;
            this.shortName = Short;
        }
    }

    /**
     * The Slot that the item equips to
     */
    public enum EquipSlot {
        GOGGLES("Goggles"),
        HELMET("Helmet"),
        NECKLACE("Necklace"),
        TRINKET("Trinket"),
        ARMOR("Armor"),
        CLOAK("Cloak"),
        BRACERS("Bracers"),
        BELT("Belt"),
        RING("Ring"),
        BOOTS("Boots"),
        GLOVES("Gloves");

        public String name;

        EquipSlot(String name) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
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

    public List<Enhancement> getEnhancements() {
        return enhancements;
    }

    public void setEnhancements(List<Enhancement> enhancements) {
        this.enhancements = enhancements;
    }
}
