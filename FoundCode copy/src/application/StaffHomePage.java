package application;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.View.DiscussionPageView;
import application.View.ReviewerMessagingPage;
import application.View.ReviewerReviewListPage;
import application.View.StudentMessageReviewerPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import databasePart1.*;





/**
 * AdminPage class represents the user interface for the admin user.
 * This page displays a simple welcome message for the admin.
 */

public class StaffHomePage {
	/**
     * Displays the staff page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */

	public void show(Stage primaryStage) {

        Button messagingButton = new Button("Messaging");

        messagingButton.setOnAction(a -> {
            ReviewerMessagingPage messagingPage = new ReviewerMessagingPage("Jessica");
            messagingPage.show(primaryStage);
           
        });
        
        Button reviewListButton = new Button("reviewList");

        reviewListButton.setOnAction(a -> {
        	ReviewerReviewListPage reviewListPage = new ReviewerReviewListPage("Jessica");
            reviewListPage.show(primaryStage);
        });
        
        Button studentMessageReviewerPageButton = new Button("studentMessageReviewerPage");

        studentMessageReviewerPageButton.setOnAction(a -> {
        	StudentMessageReviewerPage studentMessageReviewerPage = new StudentMessageReviewerPage("Jessica");
            studentMessageReviewerPage.show(primaryStage);
           
        });
        
        Button discussionPageButton = new Button("discussionPage");

        discussionPageButton.setOnAction(a -> {
        	DiscussionPageView discussionPageView = new DiscussionPageView();
            discussionPageView.show(primaryStage);
        });


        VBox layout = new VBox(10, messagingButton, reviewListButton, studentMessageReviewerPageButton, discussionPageButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Staff Home page");
        primaryStage.show();
    }

}