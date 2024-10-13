package Review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/reviewOne")
public class ReviewOneServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String review_no = req.getParameter("review_no");
		
		ReviewService service = new ReviewService();
		Review reviewOne = service.getReview(review_no);
		req.setAttribute("reviewOne", reviewOne);
		req.getRequestDispatcher("WEB-INF/views/reviewOne.jsp").forward(req, resp);
	}
}
