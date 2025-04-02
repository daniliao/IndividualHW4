package application.View;
import application.model.Message;
import application.model.MessageStore;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentMessageReviewerPage{
	
	private final String studentUsername;
	
	public StudentMessageReviewerPage(String studentUsername) {
		this.studentUsername = studentUsername;
	}
	
	public void show(Stage stage) {
		VBox layout = new VBox(10);
		layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
		
		Label prompt = new Label("Message a Reviewer");
		TextField reviewerField = new TextField();
		reviewerField.setPromptText("Enter Reviewer username");
		TextArea messageArea = new TextArea();
		messageArea.setPromptText("Enter Your Message...");
		Button send = new Button("Send");
		
		send.setOnAction(e -> {
			
			String reviewer = reviewerField.getText();
			String message = messageArea.getText();
			
			Message m = new Message(studentUsername, reviewer, message, "Now");
			MessageStore.addMessage(m);
			
			reviewerField.clear();
			messageArea.clear();
		});
		
		layout.getChildren().addAll(prompt, reviewerField, messageArea, send);
		stage.setScene(new Scene(layout, 800, 400));
		stage.setTitle("Student Messaging Pane");
		stage.show();
		
	}
}