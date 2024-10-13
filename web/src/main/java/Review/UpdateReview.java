package Review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/reviewUpdate")
public class UpdateReview extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String review_No = req.getParameter("review_no");
		
        ReviewService service = new ReviewService();
        Review reviewNO = service.getReview(review_No);

		req.setAttribute("review", reviewNO);
		
		req.getRequestDispatcher("WEB-INF/views/reviewUpdate.jsp").forward(req, resp);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
        String contents = req.getParameter("contents");
        String rating = req.getParameter("rating");
		String reviewNo = req.getParameter("review_no");

        Review review = new Review();
        review.setContents(contents); // 내용 설정
        review.setRating(rating); // 평점 설정
        review.setReview_no(reviewNo); // 리뷰 번호 설정

        ReviewService service = new ReviewService();
        service.updateReview(review);
        
        resp.sendRedirect("/web/review"); // 수정 후 목록 페이지로 리다이렉트
	
        System.out.println("Review No: " + reviewNo);
        System.out.println("Contents: " + contents);
        System.out.println("Rating: " + rating);
	
	}
	
}
