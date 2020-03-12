package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author Tealeaf
 * @version 0.0.1
 * @since 0.0.1
 */
public class DebugPrompt {
    /**
     * Enables the crash-reporting custom script
     * <p>Whenever a crash or error occurs, it stores the crash error text into a file under the crashlogs folder.</p>
     * <p>If {@link Settings#showCrashReports} is true, it will open up a dialog, via {@link #showPrompt(List)}</p>
     */
    public static void setCrashReporting() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            String filename = "crash_log" + new SimpleDateFormat("yyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".txt";

            File file = Json.getFile("crashlogs", filename);
            if (!file.getParentFile().mkdirs()) {
                System.out.println("Could not make directory " + file.getParentFile().getPath());
            }

            try {
                PrintStream stream = new PrintStream(file);
                stream.print(e.getMessage() + "\n");
                e.printStackTrace(stream);

                if (Settings.showCrashReports) showPrompt(Files.readAllLines(file.toPath()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
    }

    /**
     * Displays the prompt of the crash report
     * <p>This contains a link that will copy the error ({@code lines}) and open up the crash report template</p>
     *
     * @param lines Error Report
     */
    private static void showPrompt(List<String> lines) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Crash Dialog");
        alert.setHeaderText("We have encountered an error!");
        alert.setContentText("You can either ignore this error, or report it!");

        StringBuilder exceptionText = new StringBuilder();
        for (String s : lines) exceptionText.append(s).append("\n");
        final String exception = exceptionText.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        Button reportLink = new Button("Report Issue");
        reportLink.setOnAction(e -> {
            StringSelection stringSelection = new StringSelection(exception);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            Main.openLink("https://github.com/LittleTealeaf/ddoManager/issues/new?assignees=LittleTealeaf&labels=crash&template=crash-report.md&title=%5BCRASH%5D+");
        });

        Label warning = new Label("Warning: This will use your clipboard");

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        expContent.add(reportLink, 0, 2);
        expContent.add(warning, 0, 3);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true);

        alert.showAndWait();
    }
}
