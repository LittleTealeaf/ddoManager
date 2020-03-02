package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        //Create Content Center
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(10));

        String aboutText = "Dungeons & Dragons: Online Manager; Version " + VERSION + "\n\n";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("contributors")));
            String line;
            while(!(line = reader.readLine()).contentEquals("")) {
                aboutText+=line + "\n";
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Failed Attempt to grab resources");
        }

        content.setCenter(new Text(aboutText));


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
