package main;

/**
 * Stored Variables to be initialized at first, however allows the user to edit them to their own liking.<p>
 * Think of it like settings, except it's variables
 * </p>
 *
 * @author Tealeaf
 * @version 0.0.1
 * @since 0.0.1
 */
public class Variables {

    /**
     * Lists of Raids in the game
     * @since 0.0.1
     * @version 0.0.1
     */
    public static String[] raids = {"Curse of Strahd", "Old Baba's Hut", "Killing Time", "Riding the Storm Out",
            "Legendary Tempest Spine", "Tempest Spine", "Epic Vault of Night", "Vault of Night", "Fall of Truth",
            "Caught in the Web"};

    public Variables() {}

    /**
     * Loads the given Variables, then calls the {@link #save()} method
     *
     * @see #save()
     */
    public static void load() {
        Json.readObject(true, Variables.class, "Variables.json");
        save();
    }

    /**
     * Saves the variables
     *
     * @see #load()
     */
    public static void save() {
        Json.saveObject(new Variables(), true, "Variables.json");
    }
}
