package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Main class for the entire application, contains all misc / UI layouts of the application
 *
 * @author Tealeaf
 * @version Application Version: {@value VERSION}
 * @since 0.0.1
 */
public class Main extends Application {

    /**
     * Current version of the program
     */
    public static final String VERSION = "0.0.1";

    public static List<Scene> loadedScenes = new ArrayList<>();

    private static ArrayList<String> contributors;

    public static void main(String[] args) {
        Json.load();
        Settings.load();
        Variables.load();
        DebugPrompt.setCrashReporting();

        launch(args);
    }

    /**
     * Generates the menu bar
     * @return Menu Bar to display at the top of the program
     */
    public static MenuBar generateMenuBar() {
        MenuBar r = new MenuBar();


        //HELP menu
        Menu menuHelp = new Menu("Help");

        MenuItem helpSettings = new MenuItem("Settings");
        helpSettings.setOnAction(e -> Settings.openSettings());

        MenuItem helpAbout = new MenuItem("About");
        helpAbout.setOnAction(e -> windowAbout());


        menuHelp.getItems().addAll(helpSettings, helpAbout);

        //ADDING

        r.getMenus().addAll(menuHelp);

        return r;
    }

    /**
     * Opens the "About" window
     */
    public static void windowAbout() {
        Stage stage = new Stage();
        stage.setTitle("About DDO Manager " + VERSION);

        GridPane content = new GridPane();
        content.setHgap(7.5);
        content.setVgap(7.5);
        content.setPadding(new Insets(10));

        Text labelAppInfo = new Text("App Details");
        content.add(labelAppInfo, 0, 0, 1, 2);

        Text labelAppVersion = new Text("Application Version: " + VERSION);
        content.add(labelAppVersion, 1, 0);

        Text crashDialogs = new Text("Crash Dialogs: " + (Settings.showCrashReports ? "Enabled" : "Disabled"));
        content.add(crashDialogs, 1, 1);


        //Contributors
        int rowPos = 0;
        final char SEP = '-';
        List<String> temp = new ArrayList<>();
        for (String line : getContributors()) {
            if (line.toCharArray()[0] == SEP) {
                if (temp.size() > 1) {
                    content.add(new Text(temp.get(0)), 2, rowPos, 1, temp.size() - 1);

                    for (int i = 1; i < temp.size(); i++) {
                        content.add(new Text(temp.get(i)), 3, rowPos + i - 1);
                    }
                    rowPos += temp.size() - 1;
                }
                temp = new ArrayList<>();
                temp.add(line.substring(1));
            } else {
                temp.add(line);
            }

        }

        //Links
        Hyperlink linkGithub = new Hyperlink("Github Repository");
        linkGithub.setOnAction(e -> openLink("https://github.com/LittleTealeaf/paceManager"));
        content.add(linkGithub, 0, 2);

        Scene scene = new Scene(content);
        Settings.theme.applyTheme(scene);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets the contents of the contributor file, located under resources.
     * <br>Stores the contents in a private variable in order to prevent fetching the resources too often
     *
     * @return List of the contributors file
     */
    private static List<String> getContributors() {
        if (contributors == null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("contributors"))));

                contributors = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    contributors.add(line);
                }
                contributors.add("-asf");
            } catch (Exception ignored) {}
        }

        return contributors;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("DDO Manager");

        //Remember Maximized Setting
        stage.setMaximized(Settings.startMaximized);
        stage.maximizedProperty().addListener((e, o, n) -> {
            Settings.startMaximized = n;
            Settings.save();
        });

        //Initialize the UI

        BorderPane content = new BorderPane();
        content.setTop(generateMenuBar());


        Scene scene = new Scene(content);
        Settings.theme.applyTheme(scene);

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    /**
     * Opens a link in the desktop default browser
     * <br><b>Source:</b> <a href="https://stackoverflow.com/a/10967469">Stackoverflow Answer</a>
     *
     * @param url Site URL to directly open
     */
    public static void openLink(String url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
