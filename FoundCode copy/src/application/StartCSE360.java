package application;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.SQLException;
import application.View.ReviewerMessagingPage;
import application.View.ReviewerReviewListPage;
import application.View.StudentMessageReviewerPage;


import application.View.DiscussionPageView;
import databasePart1.DatabaseHelper;


public class StartCSE360 extends Application {

	private static final DatabaseHelper databaseHelper = new DatabaseHelper();
	
	public static void main( String[] args )
	{
		 launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        databaseHelper.connectToDatabase();

	        if (databaseHelper.isDatabaseEmpty()) {
	            new FirstPage(databaseHelper).show(primaryStage);
	        } else {
	            String currentReviewer = "alice";

	            Stage Stafftage = new Stage();
	            StaffHomePage staffHomePage = new StaffHomePage();
	            staffHomePage.show(Stafftage);
	            
	            ReviewerMessagingPage messagingPage = new ReviewerMessagingPage(currentReviewer);
	            messagingPage.show(primaryStage);

	            Stage reviewStage = new Stage();
	            ReviewerReviewListPage reviewListPage = new ReviewerReviewListPage(currentReviewer);
	            reviewListPage.show(reviewStage);

	            
	            Stage StudentMessageStage = new Stage();
	            StudentMessageReviewerPage studentMessageReviewerPage = new StudentMessageReviewerPage(currentReviewer);
	            studentMessageReviewerPage.show(StudentMessageStage);
	            
	            Stage discussionStage = new Stage();
	            DiscussionPageView discussionPageView = new DiscussionPageView();
	            discussionPageView.show(discussionStage);
	        }

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}

	

}

