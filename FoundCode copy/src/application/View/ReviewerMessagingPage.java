package application.View;

import application.model.Message;
import application.model.MessageStore;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class ReviewerMessagingPage{
	
	private final String currentReviewer;
	
	public ReviewerMessagingPage(String currentReviewer) {
		this.currentReviewer = currentReviewer;
	}
	
	public void show(Stage stage) {
		VBox layout = new VBox(10);
		layout.setStyle("-fx-padding: 20; -fx-alignment: center");
		
		Label title = new Label("Your Messages");
		ListView<String> messageList = new ListView<>();
		
		TextArea replyArea = new TextArea();
		replyArea.setPromptText("Write Your Reply...");
		Button sendReply = new Button("Send Reply");
		
		Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction(e -> {
			messageList.getItems().clear();
			List<Message> messages = MessageStore.getMessagesForUser(currentReviewer);
			for(Message m : messages) {
				messageList.getItems().add(m.toString());
			}
		});
		
		List<Message> messages = MessageStore.getMessagesForUser(currentReviewer);
		for(Message m : messages) {
			messageList.getItems().add(m.toString());
		}		
		
		sendReply.setOnAction(e -> {
			String selectedMessage = messageList.getSelectionModel().getSelectedItem();
			if (selectedMessage != null) {
				String toUser = extractSender(selectedMessage);
				String replyText = replyArea.getText();

				Message reply = new Message(currentReviewer, toUser, replyText, "Now");
				MessageStore.addMessage(reply);

				replyArea.clear();
				messageList.getItems().add(reply.toString()); // Add it visually right away
			}
		});

		
		layout.getChildren().addAll(title, messageList, refreshButton, replyArea, sendReply);
		stage.setScene(new Scene(layout, 800, 400));
		stage.setTitle("Reviewer Messages");
		stage.show();
	}
	
	private String extractSender(String messageText) {
		return messageText.split(":")[0];
	}
}