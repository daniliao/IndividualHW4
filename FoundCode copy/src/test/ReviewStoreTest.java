package application.model;

import application.model.Review;
import application.model.ReviewStore;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewStoreTest {

    @BeforeEach
    public void clearStore() {
        ReviewStore.clear();
    }

    @Test
    public void testAddAndGetReview() {
        Review r = new Review("reviewer1", "What is Java?", "Java is a language.");
        ReviewStore.addReview(r);

        List<Review> reviews = ReviewStore.getReviewsByReviewer("reviewer1");

        assertEquals(1, reviews.size());
        assertEquals("What is Java?", reviews.get(0).getQuestion());
    }

    @Test
    public void testGetReviewsEmpty() {
        List<Review> reviews = ReviewStore.getReviewsByReviewer("unknown");
        assertTrue(reviews.isEmpty());
    }
}
