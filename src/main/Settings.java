package main;

public class Settings {

    public static boolean startMaximized = true;

    public static boolean showCrashReports = true;

    public static WikiSource defaultSource = WikiSource.COMPENDIUM; //do I really need this?

    public static OpenedTab lastOpenedTab = OpenedTab.CHARACTERS;


    public Settings() {}

    /**
     * Attempts to load the data, and then saves (so any settings not initialized will be saved)
     */
    public static void load() {
        Json.readObject(true, Settings.class, "Settings.json");
        save();
    }

    public static void save() {
        Json.saveObject(new Settings(), true, "Settings.json");
    }

    /**
     * Default Source of Information for auto-generated information
     * <ul><li><b>{@link #COMPENDIUM Compendium}</b> - <a href="https://ddocompendium.com">https://ddocompendium.com</a></li>
     * <li><b>{@link #WIKI Wiki}</b> - <a href="https://ddowiki.com">https://ddowiki.com</a></li>
     * </ul>
     */
    public enum WikiSource {
        NONE("None"), COMPENDIUM("DDO Compendium"), WIKI("DDO Wiki");

        private String name;

        WikiSource(String name) {
            this.name = name;
        }

        /**
         * Gets the name of the Wiki Source
         *
         * @return Name of the Wiki Source
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Tab that was last left opened
     */
    public enum OpenedTab {
        CHARACTERS(0),ITEMS(1),RAIDS(2);

        public int index;

        OpenedTab(int ind) {
            index = ind;
        }
    }
}
