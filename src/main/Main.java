package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static final String VERSION = "0.0.1";

    private static ArrayList<String> contributors;

    public static void main(String[] args) {
        Json.load();
        Settings.load();
        Variables.load();
        DebugPrompt.setCrashReporting();

        launch(args);
    }

    public static MenuBar generateMenuBar() {
        MenuBar r = new MenuBar();


        //HELP menu
        Menu menuHelp = new Menu("Help");

        MenuItem helpAbout = new MenuItem("About");
        helpAbout.setOnAction(e -> windowAbout());

        menuHelp.getItems().addAll(helpAbout);

        //ADDING

        r.getMenus().addAll(menuHelp);

        return r;
    }

    public static void windowAbout() {
        Stage stage = new Stage();
        stage.setTitle("About DDO Manager " + VERSION);
        stage.setWidth(400);

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

        //Contributors File Section:
        List<List<String>> sections = new ArrayList<List<String>>();

        List<String> tempList = new ArrayList<String>();
        for (String line : getContributors()) {
            if (line.toCharArray()[0] != '\t') {
                sections.add(tempList);
                tempList = new ArrayList<String>();
                tempList.add(line);
            } else {
                tempList.add(line.substring(1));
            }
        }

        Text headerContributors = new Text("Contributors");
        content.add(headerContributors, 2, 0, 2, 1);

        //BUG

        int rowPos = 1;
        for (List<String> section : sections) {
            Text sectionHeader = new Text(section.get(0));
            content.add(sectionHeader, 2, rowPos, 1, section.size() - 1);

            for (int i = 1; i < section.size(); i++) {
                Text sectionItem = new Text(section.get(i));
                content.add(sectionHeader, 3, rowPos + i - 1);
            }
            rowPos += section.size() - 1;
        }


        stage.setScene(new Scene(content));
        stage.show();
    }

    private static List<String> getContributors() {
        if (contributors == null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("contributors")));

                contributors = new ArrayList<String>();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    contributors.add(line);
                }
            } catch (IOException e) {}
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

        stage.setScene(new Scene(content));
        stage.show();
        stage.setMaximized(true);
    }

}
