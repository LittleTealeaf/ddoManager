package classes;

public class Item {

    String name = "";
    int minLevel = 1;
    BindStatus bindStatus = BindStatus.BTA;
    String bonuses = "";

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
}
