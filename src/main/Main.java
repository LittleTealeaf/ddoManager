package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

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

        BorderPane content = new BorderPane();

        TextArea info = new TextArea();
        info.setEditable(false);
        info.setText("DDO Manager Version 0.0.1");

        content.setCenter(info);

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

        TextArea debug = new TextArea();
        debug.setText(Internet.getContents("https://vaultofkundarak.com/item/Legendary_Bracers_of_the_Fallen_Hero"));
        debug.setWrapText(true);
        content.setCenter(debug);

        stage.setScene(new Scene(content));
        stage.show();
        stage.setMaximized(true);
    }

}
