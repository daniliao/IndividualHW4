package application.View;

import application.model.Review;
import application.model.ReviewStore;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ReviewerReviewListPage{
	private final String reviewer;
	
	public ReviewerReviewListPage(String reviewer) {
		this.reviewer = reviewer;
	}
	
	public void show(Stage stage) {
		  VBox layout = new VBox(10);
	        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

	        Label title = new Label("Your Reviews");
	        ListView<String> reviewList = new ListView<>();

	        List<Review> reviews = ReviewStore.getReviewsByReviewer(reviewer);
	        for (Review r : reviews) {
	            reviewList.getItems().add(r.toString());
	        }

	        layout.getChildren().addAll(title, reviewList);
	        stage.setScene(new Scene(layout, 800, 400));
	        stage.setTitle("My Reviews");
	        stage.show();
	}
}