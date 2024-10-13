package Review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteReview extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String review_no = req.getParameter("review_no");
		
		ReviewService service = new ReviewService();
		
		int result = service.deleteReview(review_no);
		  if (result > 0) {
	            resp.sendRedirect("/web/review"); // 리뷰 목록으로 리다이렉트
	        } else {
	            req.setAttribute("message", "삭제 실패");
	            req.getRequestDispatcher("/WEB-INF/views/reviewOne.jsp").forward(req, resp); 
	
	}
}
}