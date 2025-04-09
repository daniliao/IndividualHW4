# CSE360 Individual Homework 4 | Wednesday 48

DANIEL YI-CHIAN LIAO

### Video explains the code and JUnit test: 

https://youtu.be/EqYPwPa-z20

https://youtu.be/14XF1Q4WgWM


### Video on how each requirement is satisfied: 

[https://youtu.be/link](https://youtu.be/SMYdryD6mMM)

### 1. Staff navigates to different views

```java
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
```

---

### 2. Pages connected via Buttons

| Button                       | Opens Page                         |
|-----------------------------|-------------------------------------|
| `Messaging`                 | ReviewerMessagingPage               |
| `reviewList`               | ReviewerReviewListPage              |
| `studentMessageReviewerPage` | StudentMessageReviewerPage        |
| `discussionPage`           | DiscussionPageView (discussion board)|

---



## New files for HW4

```
application
└──StaffHomePage.java
```

```
.
├── FoundCode copy
│   ├── bin
│   │   ├── application
│   │   │   ├── AdminHomePage.class
│   │   │   ├── AdminSetupPage.class
│   │   │   ├── FirstPage.class
│   │   │   ├── InvitationPage.class
│   │   │   ├── SetupAccountPage.class
│   │   │   ├── SetupLoginSelectionPage.class
│   │   │   ├── StartCSE360.class
│   │   │   ├── User.class
│   │   │   ├── UserHomePage.class
│   │   │   ├── UserLoginPage.class
│   │   │   ├── View
│   │   │   │   └── DiscussionPageView.class
│   │   │   ├── WelcomeLoginPage.class
│   │   │   ├── model
│   │   │   │   ├── Answer.class
│   │   │   │   ├── Answers.class
│   │   │   │   ├── Question.class
│   │   │   │   └── Questions.class
│   │   │   └── viewModel
│   │   │       └── DiscussionPageViewModel.class
│   │   ├── databasePart1
│   │   │   └── DatabaseHelper.class
│   │   └── module-info.class
│   └── src
│       ├── application
│       │   ├── AdminHomePage.java
│       │   ├── AdminSetupPage.java
│       │   ├── FirstPage.java
│       │   ├── InvitationPage.java
│       │   ├── SetupAccountPage.java
│       │   ├── SetupLoginSelectionPage.java
│       │   ├── StartCSE360.java
│       │   ├── User.java
│       │   ├── UserHomePage.java
│       │   ├── UserLoginPage.java
│       │   ├── View
│       │   │   └── DiscussionPageView.java
│       │   ├── WelcomeLoginPage.java
│       │   ├── model
│       │   │   ├── Answer.java
│       │   │   ├── Answers.java
│       │   │   ├── Question.java
│       │   │   └── Questions.java
│       │   └── viewModel
│       │       └── DiscussionPageViewModel.java
│       ├── databasePart1
│       │   └── DatabaseHelper.java
│       └── module-info.java
├── README.md
├── Vids
│   └── (Show All Questions , Show Unresolved Questions).mp4
├── old code
│   ├── AdminHomePage.java
│   ├── AdminSetupPage.java
│   ├── DatabaseHelper.java
│   ├── FirstPage.java
│   ├── InvitationPage.java
│   ├── SetupAccountPage.java
│   ├── SetupLoginSelectionPage.java
│   ├── StartCSE360.java
│   ├── User.java
│   ├── UserHomePage.java
│   ├── UserLoginPage.java
│   └── WelcomeLoginPage.java
└── old vids
    ├── GMT20250208-022512_Recording_1440x900.mp4
    ├── Logout Functionality Test Cases(demo).mp4
    ├── Logout Functionality code explanation.mp4
    └── Matthew Cruz- screencast test for user roles and temp password.mp4
```

