# CSE360 Team Project | Wednesday 48

Team Member: 
Mohammed Almakki, 
Matthew Cruz, 
Octavian Englund, 
Ricardo Guerrero, 
Cristian Holguin, 
DANIEL YI-CHIAN LIAO

## Team project 

![Demo!](https://github.com/link)

### Video explains the code: 

https://youtu.be/link

### Video on how each requirement is satisfied: 

https://youtu.be/link

### 1.  Students to establish and maintain a list of trusted reviewers.
```java
// User story 1 and 5: Students to ask or update question
    // UI: Add checkbox to mark answer as trusted
    CheckBox trustedBox = new CheckBox("Trusted Reviewer?");

    // In addAnswerButton:
    int trusted = trustedBox.isSelected() ? 1 : 0;
    Answer answer = new Answer(answerTextBox.getText(), trusted);
    viewModel.getQuestionAndAnswer().get(selectedQuestion).add(answer);

```


```java
// 
    // In Answer.java
    private int trusted; // 1 = trusted, 0 = not trusted

    public int getTrusted() { return trusted; }
    public void setTrusted(int trusted) { this.trusted = trusted; }

    @Override
    public String toString() {
    String trustLabel = trusted == 1 ? " (by trusted)" : "";
    return "Answer: " + answerFromInput + trustLabel + " | Weight: " + weight;
}

```



```java
// Filter trusted answers
    showTrustedAnswersButton.setOnAction(e -> {
    TreeItem<Answer> root = new TreeItem<>();
    for (Answer a : viewModel.getQuestionAndAnswer().get(selected)) {
        if (a.getTrusted() == 1) {
            TreeItem<Answer> answerItem = new TreeItem<>(a);
            root.getChildren().add(answerItem);
        }
    }
    answerTreeView.setRoot(root);
    });

```



### 2. Students to add a weightage value to each reviewer 

```java
    // In Answer.java
    private int weight = 0;

    public int getWeight() { return weight; }
    public void upvote() { weight++; }

```



```java

 // In DiscussionPageView.java
    Button upvoteButton = new Button("?? Upvote");

    upvoteButton.setOnAction(e -> {
    Answer selected = getSelectedAnswer();
    if (selected != null) {
        selected.upvote();
        updateTreeView(questionInListView.getSelectionModel().getSelectedItem());
    }
    });

```


```java
// 
    // Sort trusted answers by weight
    List<Answer> sortedTrustedAnswers = new ArrayList<>();
    for (Answer a : viewModel.getQuestionAndAnswer().get(selected)) {
        if (a.getTrusted() == 1) sortedTrustedAnswers.add(a);
    }
    sortedTrustedAnswers.sort((a1, a2) -> Integer.compare(a2.getWeight(), a1.getWeight()));

```

### .
```java

```

## New files from team project 1 to team project 2

```
View
└──DiscussionPageView.class
```

```
model
└──Answer.class
└──Answers.class
└──Question.class
└──Questions.class
```

```
viewModel
└── DiscussionPageViewModel.class
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

