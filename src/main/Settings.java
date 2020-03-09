package main;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tealeaf
 */
public class Settings {

    //INTERNAL SETTINGS
    public static boolean startMaximized = true;
    public static OpenedTab lastOpenedTab = OpenedTab.CHARACTERS;

    //USER MODIFIED SETTINGS
    public static boolean showCrashReports = true;
    public static Theme theme = Theme.DARK;


    public Settings() {}

    /**
     * Attempts to load the data, and then saves (so any settings not initialized will be saved)
     */
    public static void load() {
        Json.readObject(true, Settings.class, "Settings.json");
        save();
        if (theme == null) theme = Theme.LIGHT;
    }

    public static void save() {
        Json.saveObject(new Settings(), true, "Settings.json");
    }

    /**
     * Opens the Settings UI
     */
    public static void openSettings() {
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setWidth(500);

        //Get the Settings
        List<SettingObj> sobjs = getSettingObjects();

        //Create the Categories
        ListView<String> category = new ListView<>();
        category.setPrefWidth(150);
        for (SettingObj obj : sobjs) {
            if (!category.getItems().contains(obj.category)) {
                category.getItems().add(obj.category);
            }
        }

        GridPane sectionDisplay = new GridPane();
        sectionDisplay.setPadding(new Insets(10));
        sectionDisplay.setHgap(10);
        sectionDisplay.setVgap(10);

        //Create the Filter
        TextField filter = new TextField();
        filter.setPromptText("Filter Settings");
        filter.textProperty().addListener((e, o, n) -> {
            String currentSelected = category.getSelectionModel().getSelectedItem();
            List<String> categories = new ArrayList<>();

            for (SettingObj setting : sobjs) {
                if (setting.contains(n) && !categories.contains(setting.category)) {
                    categories.add(setting.category);
                }
            }

            category.getItems().setAll(categories);

            if (categories.contains(currentSelected)) {
                category.getSelectionModel().select(currentSelected);
            } else {
                category.getSelectionModel().select(0);
            }
        });

        //Generating Category Settings
        category.getSelectionModel().selectedItemProperty().addListener((e, o, n) -> {
            //Cleans GridPane
            sectionDisplay.getChildren().clear();

            //Gets a list of the setting objs to display
            int row = 0;
            for (SettingObj setting : sobjs) {
                if (setting.category.equals(n) && setting.contains(filter.getText())) {
                    if (setting.showName) {
                        sectionDisplay.add(new Text(setting.name), 0, row);
                        sectionDisplay.add(setting.node, 1, row);
                    } else {
                        sectionDisplay.add(setting.node, 0, row, 2, 1);
                    }
                    row++;
                }
            }
        });

        HBox center = new HBox(new VBox(filter, category), sectionDisplay);

        Scene scene = new Scene(center);
        Settings.theme.applyTheme(scene);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets all the setting objects, each one with a given category, node, and keywords
     *
     * @return List of {@link SettingObj SettingObjs}
     */
    private static List<SettingObj> getSettingObjects() {
        List<SettingObj> settingObs = new ArrayList<>();

        //Show Crash Reports
        CheckBox sShowCrashReports = new CheckBox("Show Crash Reports");
        sShowCrashReports.setSelected(showCrashReports);
        sShowCrashReports.selectedProperty().addListener((e, o, n) -> {
            showCrashReports = n.booleanValue();
            save();
        });
        sShowCrashReports.setTooltip(new Tooltip("When set to true, will display a crash screen with options to create a bug report on Github"));
        settingObs.add(new SettingObj("Show Crash Reports", false, sShowCrashReports, "Advanced", "github crash report crash log debug"));

        ComboBox<Theme> sTheme = new ComboBox<Theme>();
        sTheme.setItems(FXCollections.observableArrayList(Theme.values()));
        sTheme.getSelectionModel().select(theme);
        sTheme.getSelectionModel().selectedItemProperty().addListener((e, o, n) -> {
            theme = n;
            save();
            Theme.updateTheme();
        });
        settingObs.add(new SettingObj("Theme", true, sTheme, "Appearance", "theme color display style appearance"));

        return settingObs;
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

    //TODO comment this
    public enum Theme {
        LIGHT("Default Theme", null),
        DARK("Dark Theme", "darktheme.css");

        private static List<Scene> appliedThemes = new ArrayList<>();
        private String fileName;
        private String displayName;


        Theme(String displayName, String fileName) {
            this.displayName = displayName;
            this.fileName = fileName;
        }

        public static void updateTheme() {
            for (Scene scene : appliedThemes) {
                theme.applyTheme(scene);
            }
        }

        public String getFileName() {
            return fileName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String toString() {
            return getDisplayName();
        }

        public void applyTheme(Scene scene) {
            scene.getStylesheets().clear();
            if (fileName != null) {
                scene.getStylesheets().add(ClassLoader.getSystemResource(fileName).toExternalForm());
            }
            if (!appliedThemes.contains(scene)) appliedThemes.add(scene);
        }
    }

    /**
     * Tab that was last left opened
     */
    public enum OpenedTab {
        CHARACTERS(0), ITEMS(1), RAIDS(2);

        public int index;

        OpenedTab(int ind) {
            index = ind;
        }
    }

    /**
     * A modular setting class that includes a setting, it's visual element, and keywords
     *
     * @author Tealeaf
     */
    private static class SettingObj {
        private String name;
        private boolean showName = true;
        private Node node;
        private String category;
        private String keyWords;

        /**
         * Creates a SettingObj object with the given parameters
         * <br>{@Code showName} defaults to true with this constructor
         *
         * @param name     Name of the Setting
         * @param node     {@code JavaFX} {@link Node} of the setting
         * @param category Category of the Setting
         * @param keyWords Key Search Terms of the setting
         */
        public SettingObj(String name, Node node, String category, String keyWords) {
            this.name = name;
            this.node = node;
            this.category = category;
            this.keyWords = keyWords;
        }

        /**
         * Creates a SettingObj object with the given parameters
         *
         * @param name     Name of the Setting
         * @param showName Whether or not to incldue the name in the settings page
         * @param node     {@code JavaFX} {@link Node} of the setting
         * @param category Category of the Setting
         * @param keyWords Key Search Terms of the setting
         */
        public SettingObj(String name, boolean showName, Node node, String category, String keyWords) {
            this.name = name;
            this.showName = showName;
            this.node = node;
            this.category = category;
            this.keyWords = keyWords;
        }

        /**
         * Filters out if this setting matches the given filter
         *
         * @param contents String Filter
         * @return {@code True} if this setting matches the filter<br>{@code False} if it does not
         */
        public boolean contains(String contents) {
            return contents.contentEquals("") || name.contains(contents.toLowerCase()) || keyWords.contains(contents.toLowerCase()) || category.contains(contents.toLowerCase());
        }

        @Override
        /**
         * Returns a String object that contains the name and it's category
         * @return String representing the setting's name and category
         */
        public String toString() {
            return name + " (" + category + ")";
        }

        /**
         * Returns if the object is equal to another object
         *
         * @param other Other SettingObj to compare
         * @return {@code True} if they are equal<br>{@code False} if they are not equal
         */
        public boolean equals(SettingObj other) {
            return this.name.equals(other.name) && this.node.equals(other.node) && this.category.equals(other.category) && this.keyWords.equals(other.keyWords);
        }
    }
}
