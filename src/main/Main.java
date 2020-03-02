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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String VERSION = "0.0.1";

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

        //Header for Version and Links
        Text headerVersion = new Text("Version and Links");
        headerVersion.setFont(Font.font("Ubuntu", 20));
        content.add(headerVersion, 0, 0, 2, 1);

        //Versions and Links
        Text tVersion = new Text("Version " + VERSION);
        Hyperlink linkGithub = new Hyperlink("Github");


        stage.setScene(new Scene(content));
        stage.show();
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
