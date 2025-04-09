
package test;

import static org.junit.Assert.*;
import org.junit.Before;

import application.model.Review;
import application.model.ReviewStore;


import java.util.List;


import org.junit.Test;

public class ReviewStoreTest {

    @Before
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
