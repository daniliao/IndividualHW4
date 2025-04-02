package test;

import application.model.Answer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {

    @Test
    public void testTrustedAndWeight() {
        // Create a trusted answer (trusted = 1)
        Answer a = new Answer("Test", 1);

        // Verify it's initially trusted
        assertTrue(a.getTrusted() == 1);

        // Change it to not trusted (trusted = 0) and check
        a.setTrusted(0);
        assertEquals(0, a.getTrusted());

        // Check initial weight is 0
        assertEquals(0, a.getWeight());

        // Upvote once and verify weight becomes 1
        a.upvote();
        assertEquals(1, a.getWeight());
    }
}
