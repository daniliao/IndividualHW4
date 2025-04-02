package application.model;

import java.util.List;
import java.util.ArrayList;

public class Answer {
    private String answerFromInput;
    private List<Answer> reply;
    
 // Marks if this answer is by a trusted reviewer (1 = yes, 0 = no)
    private int trusted;

    // Keeps track of votes (weight)
    private int weight = 0;

    // updated
    public Answer(String answerFromInput, int trusted) {
        this.answerFromInput = answerFromInput;
        this.trusted = trusted;
        this.reply = new ArrayList<>();
    }

    public String getAnswerFromUser() {
        return answerFromInput;
    }

    public void setanswerFromInput(String answerFromInput) {
        this.answerFromInput = answerFromInput;
    }
    // Getters and Setters
    public int getTrusted() {
        return trusted;
    }

    public void setTrusted(int trusted) {
        this.trusted = trusted;
    }
    
    public int getWeight() {
        return weight;
    }

    public void upvote() {
        weight++;
    }
    //

    // updated for trusted and weight
    @Override
    public String toString() {
        String trustLabel = trusted == 1 ? " (by trusted)" : "";
        return "Answer: " + answerFromInput + trustLabel + " | Weight: " + weight;
    }

	public List<Answer> getReply() {
		return reply;
	}

	public void setReply(List<Answer> reply) {
		this.reply = reply;
	}
    
	public void createReply(Answer reply) {
		this.reply.add(reply);
	}
    
}