package main;

public class Settings {

    public static boolean startMaximized = true;

    public Settings() {}

    public static void load() {
        Json.readObject(true, Settings.class, "Settings.json");
        save();
    }

    public static void save() {
        Json.saveObject(new Settings(), true, "Settings.json");
    }
}
