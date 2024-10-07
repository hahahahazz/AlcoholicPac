package Review;

import java.util.ArrayList;

public class ReviewService {

	ReviewDAO dao = new ReviewDAO();
	public ArrayList<Review> getReview(){
		return dao.selectReview();
	}
	
	public void addReview(Review review) {
        dao.addReview(review);
    }
}
