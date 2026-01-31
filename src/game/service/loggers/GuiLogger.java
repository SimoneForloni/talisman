package game.service.loggers;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class GuiLogger implements GameLogger {
    private final TextArea textArea;

    public GuiLogger(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void log(String message) {
        // Usiamo Platform.runLater per essere sicuri di aggiornare la UI dal thread corretto
        Platform.runLater(() -> {
            textArea.appendText(message + "\n");
        });
    }
}
