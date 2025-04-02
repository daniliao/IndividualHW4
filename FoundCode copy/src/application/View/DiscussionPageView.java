package application.View;

import application.viewModel.DiscussionPageViewModel;
import application.model.Question;

import java.util.ArrayList;
import java.util.List;

import application.model.Answer;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;

public class DiscussionPageView {

    private final DiscussionPageViewModel viewModel = new DiscussionPageViewModel();
    private ListView<Question> questionInListView;
    private TreeView<Answer> answerTreeView;


    public void show(Stage primaryStage) {
        TextField questionTextBox = new TextField();
        // Leahy, P. (2019, July 3). JavaFX Textfield Overview. ThoughtCo. https://www.thoughtco.com/textfield-overview-2033936 
        questionTextBox.setPromptText("Your question...");

        TextField answerTextBox = new TextField();
        answerTextBox.setPromptText("Your answer...");

        TextField globalSearchTextBox = new TextField();
        globalSearchTextBox.setPromptText("Global search...");

        Button addQuestionButton = new Button("Add");
        Button updateQuestionButton = new Button("Update");
        Button deleteQuestionButton = new Button("Delete");
        Button addAnswerButton = new Button("Add");
        Button updateAnswerButton = new Button("Update");
        Button deleteAnswerButton = new Button("Delete");
        Button addReplyButton = new Button("Add Reply");
        Button questionSolvedButton = new Button("Mark as Solved");
        Button filterUnresolvedButton = new Button("Show Unresolved Questions");
        Button showAllQuestionsButton = new Button("Show All Questions");
        // New Buttons
        Button showTrustedAnswersButton = new Button("Show Trusted Answers");
        Button showAllAnswersButton = new Button("Show All Answers");
        Button upvoteButton = new Button("👍 Upvote");
        CheckBox trustedBox = new CheckBox("Trusted Reviewer?");


        
        filterUnresolvedButton.setOnAction(e -> {
            FilteredList<Question> unresolvedQuestions = viewModel.getQuestionInList().filtered(question -> !question.isSolved());
            questionInListView.setItems(unresolvedQuestions);
        });

        showAllQuestionsButton.setOnAction(e -> {
            questionInListView.setItems(viewModel.getQuestionInList()); // Show all questions
        });


        Label errorLabelQuestion = new Label();
        errorLabelQuestion.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");

        Label errorLabelAnswer = new Label();
        errorLabelAnswer.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");

        questionInListView = new ListView<>(viewModel.getQuestionInList());
        questionInListView.setPrefHeight(160);

        // Release: Javafx 2.2, Using JavaFX UI Controls: List View | JavaFX 2 Tutorials and Documentation. (2013). https://docs.oracle.com/javafx/2/ui_controls/list-view.htm (accessed February 10, 2025).
     
        answerTreeView = new TreeView<>();
        answerTreeView.setPrefHeight(160);

        addQuestionButton.setOnAction(e -> {
            if (questionTextBox.getText().isEmpty()) {
                errorLabelQuestion.setText("Question field is empty!");
            } else {
                viewModel.createOrUpdateQuestion(questionTextBox.getText(), true, null);
                errorLabelQuestion.setText("");
                questionTextBox.clear();
            }
        });

        updateQuestionButton.setOnAction(e -> {
            Question selected = questionInListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                viewModel.createOrUpdateQuestion(questionTextBox.getText(), false, selected);
                errorLabelQuestion.setText("");
            } else {
                errorLabelQuestion.setText("Please select a question!");
            }
        });

        deleteQuestionButton.setOnAction(e -> {
            Question selected = questionInListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                viewModel.deleteQuestion(selected);
                errorLabelQuestion.setText("");
                answerTreeView.setRoot(null);
            } else {
                errorLabelQuestion.setText("Please select a question to delete!");
            }
        });

        addAnswerButton.setOnAction(e -> {
            Question selectedQuestion = questionInListView.getSelectionModel().getSelectedItem();
            if (selectedQuestion == null || answerTextBox.getText().isEmpty()) {
                errorLabelAnswer.setText("Please select a question and type your answer!");
            } else {
            	// add the Trust option
            	int trusted = trustedBox.isSelected() ? 1 : 0;
            	Answer answer = new Answer(answerTextBox.getText(), trusted);
            	viewModel.getQuestionAndAnswer().get(selectedQuestion).add(answer);

                errorLabelAnswer.setText("");
                answerTextBox.clear();
                updateTreeView(selectedQuestion);
            }
        });

        updateAnswerButton.setOnAction(e -> {
            Answer selectedAnswer = getSelectedAnswer();
            Question selectedQuestion = questionInListView.getSelectionModel().getSelectedItem();
            if (selectedAnswer != null && !answerTextBox.getText().isEmpty()) {
            	//updated
            	selectedAnswer.setanswerFromInput(answerTextBox.getText());
            	selectedAnswer.setTrusted(trustedBox.isSelected() ? 1 : 0);
                errorLabelAnswer.setText("");
                updateTreeView(selectedQuestion);
            } else {
                errorLabelAnswer.setText("Please select an answer and type your answer!");
            }
        });

        deleteAnswerButton.setOnAction(e -> {
            Answer selectedAnswer = getSelectedAnswer();
            Question selectedQuestion = questionInListView.getSelectionModel().getSelectedItem();
            if (selectedAnswer != null && selectedQuestion != null) {
                viewModel.deleteAnswer(selectedQuestion, selectedAnswer);
                errorLabelAnswer.setText("");
                updateTreeView(selectedQuestion);
            } else {
                errorLabelAnswer.setText("Please select an answer to delete!");
            }
        });

        addReplyButton.setOnAction(e -> {
            Answer selectedAnswer = getSelectedAnswer();
            if (selectedAnswer == null || answerTextBox.getText().isEmpty()) {
                errorLabelAnswer.setText("Please select an answer and type your reply!");
            } else {
            	//updated
            	int trusted = trustedBox.isSelected() ? 1 : 0;
            	Answer reply = new Answer(answerTextBox.getText(), trusted);
            	selectedAnswer.createReply(reply);

                errorLabelAnswer.setText("");
                answerTextBox.clear();
                updateTreeView(questionInListView.getSelectionModel().getSelectedItem());
            }
        });
        
        //new upvote button
        upvoteButton.setOnAction(e -> {
            Answer selected = getSelectedAnswer();
            if (selected != null) {
                selected.upvote();
                updateTreeView(questionInListView.getSelectionModel().getSelectedItem());
            }
        });


        questionSolvedButton.setOnAction(e -> {
            Question selected = questionInListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                viewModel.solvedQuestion(selected);
                errorLabelQuestion.setText("");
            } else {
                errorLabelQuestion.setText("Please select a question to mark as solved!");
            }
        });
        
        // Trusted Answers filter button
        showTrustedAnswersButton.setOnAction(e -> {
            Question selected = questionInListView.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            TreeItem<Answer> root = new TreeItem<>();

            // Get and sort trusted top-level answers by weight
            List<Answer> sortedTrustedAnswers = new ArrayList<>();
            for (Answer a : viewModel.getQuestionAndAnswer().get(selected)) {
                if (a.getTrusted() == 1) {
                    sortedTrustedAnswers.add(a);
                }
            }
            sortedTrustedAnswers.sort((a1, a2) -> Integer.compare(a2.getWeight(), a1.getWeight())); // descending

            for (Answer a : sortedTrustedAnswers) {
                TreeItem<Answer> answerItem = new TreeItem<>(a);

                // Get and sort trusted replies by weight
                List<Answer> sortedTrustedReplies = new ArrayList<>();
                for (Answer r : a.getReply()) {
                    if (r.getTrusted() == 1) {
                        sortedTrustedReplies.add(r);
                    }
                }
                sortedTrustedReplies.sort((r1, r2) -> Integer.compare(r2.getWeight(), r1.getWeight()));

                for (Answer r : sortedTrustedReplies) {
                    answerItem.getChildren().add(new TreeItem<>(r));
                }

                root.getChildren().add(answerItem);
            }

            answerTreeView.setRoot(root);
            answerTreeView.setShowRoot(false);
        });

        
        // show all answers button
        
        showAllAnswersButton.setOnAction(e -> {
            Question selected = questionInListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                updateTreeView(selected);
            }
        });


        globalSearchTextBox.textProperty().addListener((obs, oldVal, newVal) -> {
            FilteredList<Question> filteredQuestions = viewModel.initiatedGlobalSearch(newVal);
            questionInListView.setItems(filteredQuestions);

            if (!filteredQuestions.isEmpty()) {
                Question firstMatchingQuestion = filteredQuestions.get(0);
                TreeItem<Answer> filteredTree = filterAnswer(firstMatchingQuestion, newVal);
                answerTreeView.setRoot(filteredTree);
                answerTreeView.setShowRoot(false);
            } else {
                answerTreeView.setRoot(null);
            }
        });

        // Update answer view when quesion is selected
        questionInListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                questionTextBox.setText(newVal.getQuestionFromUser());
                updateTreeView(newVal);
            } else {
                questionTextBox.setText("");
                answerTreeView.setRoot(null);
            }
        });

        // Jenkov, J. (n.d.-a). JavaFX Splitpane. Jenkov.com Tech & Media Labs - Resources for Developers, IT Architects and Technopreneurs. https://jenkov.com/tutorials/javafx/splitpane.html 
        SplitPane splitPane = new SplitPane();
        
        VBox questionBox = new VBox(new Label("Questions"), globalSearchTextBox, questionTextBox, errorLabelQuestion, 
        	    addQuestionButton, updateQuestionButton, deleteQuestionButton, questionSolvedButton, filterUnresolvedButton, showAllQuestionsButton, questionInListView);
        VBox answerBox = new VBox(
        	    new Label("Answers"),
        	    answerTextBox,
        	    trustedBox,
        	    errorLabelAnswer,
        	    addAnswerButton,
        	    updateAnswerButton,
        	    deleteAnswerButton,
        	    addReplyButton,
        	    upvoteButton,
        	    showTrustedAnswersButton,
        	    showAllAnswersButton,
        	    answerTreeView
        	);

        splitPane.getItems().addAll(questionBox, answerBox);

        primaryStage.setScene(new Scene(splitPane, 1000, 700));
        primaryStage.setTitle("Ed Discussion Lite");
        primaryStage.show();
    }

    private Answer getSelectedAnswer() {
        TreeItem<Answer> selectedItem = answerTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.getValue();
        } else {
            return null;
        }
    }
    // updated
    private void updateTreeView(Question question) {
        TreeItem<Answer> root = new TreeItem<>();

        List<Answer> sortedAnswers = new ArrayList<>(viewModel.getQuestionAndAnswer().get(question));
        sortedAnswers.sort((a1, a2) -> Integer.compare(a2.getWeight(), a1.getWeight())); // descending

        for (Answer answer : sortedAnswers) {
            TreeItem<Answer> answerItem = new TreeItem<>(answer);

            List<Answer> sortedReplies = new ArrayList<>(answer.getReply());
            sortedReplies.sort((r1, r2) -> Integer.compare(r2.getWeight(), r1.getWeight())); // descending

            for (Answer reply : sortedReplies) {
                answerItem.getChildren().add(new TreeItem<>(reply));
            }

            root.getChildren().add(answerItem);
        }

        answerTreeView.setRoot(root);
        answerTreeView.setShowRoot(false);
    }


    // Sshahine. (n.d.). JFoenix/demo/src/main/java/demos/components/treeviewdemo.java at master · SSHAHINE/JFOENIX. GitHub. https://github.com/sshahine/JFoenix/blob/master/demo/src/main/java/demos/components/TreeViewDemo.java 
    private TreeItem<Answer> filterAnswer(Question question, String searchQuery) {
        TreeItem<Answer> root = new TreeItem<>();
        List<Answer> answers = viewModel.getQuestionAndAnswer().getOrDefault(question, List.of());

        for (Answer answer : answers) {
            if (!answer.getAnswerFromUser().contains(searchQuery)) {
                continue; 
            }
            TreeItem<Answer> answerItem = new TreeItem<>(answer);
            root.getChildren().add(answerItem);

            for (Answer reply : answer.getReply()) {
                if (reply.getAnswerFromUser().contains(searchQuery)) {
                    answerItem.getChildren().add(new TreeItem<>(reply));
                }
            }
        }
        return root;
    }

}