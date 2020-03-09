package main;

public class Variables {

    /**
     * Lists of Raids in the game
     */
    public static String[] raids = {"Curse of Strahd", "Old Baba's Hut", "Killing Time", "Riding the Storm Out",
            "Legendary Tempest Spine", "Tempest Spine", "Epic Vault of Night", "Vault of Night", "Fall of Truth",
            "Caught in the Web"};

    public Variables() {}

    public static void load() {
        Json.readObject(true, Variables.class, "Variables.json");
        save();
    }

    public static void save() {
        Json.saveObject(new Variables(), true, "Variables.json");
    }
}
