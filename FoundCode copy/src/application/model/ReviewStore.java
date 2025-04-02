package application.model;

import java.util.ArrayList;
import java.util.List;

public class ReviewStore{
	private static final List<Review> reviews = new ArrayList<>();
	
	public static void addReview(Review r) {
		reviews.add(r);
	}
	
	public static List<Review> getReviewsByReviewer(String reviewer){
		List<Review> result = new ArrayList<>();
		for(Review r : reviews) {
			if(r.getReviewer().equals(reviewer)) {
				result.add(r);
			}
		}
		return result;
	}
	
	public static void clear() {
		reviews.clear();
	}
}