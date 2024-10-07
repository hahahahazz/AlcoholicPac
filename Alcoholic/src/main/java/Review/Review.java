package Review;

public class Review {
	// 리뷰아이디 review_id , 사용자아이디 user_id, 제품번호 product_no,
	// 아이템번호 item_no, 리뷰내용 review_contents , 평점 rating
	// 리뷰작성시간 review_date (type date) 타이틀 (리뷰내용 제목) title

	String review_id;
	String user_id;
	String product_no;
	String item_no;
	String review_contents;
	String rating;
	String review_date;
	String title;

	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(String review_id, String user_id, String product_no, String item_no, String review_contents,
			String rating, String review_date, String title) {
		super();
		this.review_id = review_id;
		this.user_id = user_id;
		this.product_no = product_no;
		this.item_no = item_no;
		this.review_contents = review_contents;
		this.rating = rating;
		this.review_date = review_date;
		this.title = title;
	}

	@Override
	public String toString() {
		return "review [review_id=" + review_id + ", user_id=" + user_id + ", product_no=" + product_no + ", item_no="
				+ item_no + ", review_contents=" + review_contents + ", rating=" + rating + ", review_date="
				+ review_date + ", title=" + title + "]";
	}

	public String getReview_id() {
		return review_id;
	}

	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getproduct_no() {
		return product_no;
	}

	public void setproduct_no(String product_no) {
		this.product_no = product_no;
	}

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public String getReview_contents() {
		return review_contents;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

}
