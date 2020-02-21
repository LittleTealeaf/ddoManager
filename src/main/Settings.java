package main;

public class Settings {

    public static boolean startMaximized = true;

    public static WikiSource defaultSource = WikiSource.COMPENDIUM;


    public Settings() {}

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

        public String getName() {
            return name;
        }
    }
}
