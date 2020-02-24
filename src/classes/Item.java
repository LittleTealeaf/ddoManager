package classes;

public class Item {

    String name;
    int minLevel;
    BindStatus bindStatus;
    String bonuses;


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
