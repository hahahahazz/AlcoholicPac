package Review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reviewList")
public class ReviewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ReviewService service = new ReviewService();
		ArrayList<Review> reviewList = service.getReview();
		
		req.setAttribute("reviewList", reviewList);
		req.getRequestDispatcher("WEB-INF/views/reviewList.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 파라미터에서 리뷰 데이터 가져오기
        String reviewId = req.getParameter("reviewId");
        String userId = req.getParameter("userId");
        String productNo = req.getParameter("productNo");
        String itemNo = req.getParameter("itemNo");
        String reviewContents = req.getParameter("reviewContents");
        String rating = req.getParameter("rating");
        String reviewDate = req.getParameter("reviewDate");
        String title = req.getParameter("title");

        // Review 객체 생성
        Review newReview = new Review(reviewId, userId, productNo, itemNo, reviewContents, rating, reviewDate, title);
        
        // ReviewService를 사용하여 리뷰 추가
        ReviewService service = new ReviewService();
        service.addReview(newReview);
        
        // 리뷰 목록으로 리다이렉트
        resp.sendRedirect("reviewList");
    
	}
}