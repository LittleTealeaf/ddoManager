package main;

import javafx.application.Application;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Json.load();


        Settings.save();

        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("DDO Manager");


        stage.show();
        stage.setMaximized(true);
    }

    /**
     * Generates the Menu Bar to be displayed at the top of the screen
     *
     * @return
     */
    private static MenuBar getMenuBar() {
        MenuBar r = new MenuBar();


        return r;
    }
}
