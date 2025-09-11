package sage.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sage.main.Sage;

/**
 * Controller for the MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Sage sage;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/sage.png"));

    @FXML
    public void initialize() {
        // Configure ScrollPane to work properly
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Allow dialog container to grow
        dialogContainer.setMinHeight(100);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setMaxHeight(Double.MAX_VALUE);

        // Auto-scroll to bottom when content changes
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setVvalue(1.0));

        System.out.println("Dialog container initialized");
    }

    public void setSage(Sage sage) {
        this.sage = sage;

        // Show welcome message
        String welcomeMessage = sage.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getSageDialog(welcomeMessage, botImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return; // Don't process empty messages
        }

        String response = sage.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSageDialog(response, botImage)
        );
        userInput.clear();

        // Force scrolling to bottom
        scrollPane.setVvalue(1.0);

        // Close application if user types "bye"
        if (input.trim().equalsIgnoreCase("bye")) {
            // Add delay to allow seeing the goodbye message
            new Thread(() -> {
                try {
                    Thread.sleep(1500); // 1.5 second delay
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
