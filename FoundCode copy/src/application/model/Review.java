package application.model;

public class Review{
	private String reviewer;
	private String question;
	private String content;
	
	public Review(String reviewer, String question, String content) {
		this.reviewer = reviewer;
		this.question = question;
		this.content = content;
	}
	
	   public String getReviewer() {
	        return reviewer;
	    }

	    public String getQuestion() {
	        return question;
	    }

	    public String getContent() {
	        return content;
	    }

	    @Override
	    public String toString() {
	        return "Q: " + question + "\nReview: " + content;
	    }
}