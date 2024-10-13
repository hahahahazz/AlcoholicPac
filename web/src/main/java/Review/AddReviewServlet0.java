package Review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addReview0")
public class AddReviewServlet0 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.getRequestDispatcher("WEB-INF/views/reviewAdd.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String user_id = req.getParameter("user_id");
		String product_no = req.getParameter("product_no");
		String item_no = req.getParameter("item_no");
		String contents = req.getParameter("contents");
		String rating = req.getParameter("rating");

	    
	   Review r = new Review(user_id,product_no,item_no,contents,rating);

	   ReviewService service = new ReviewService();
	   service.addReview(r);
	   resp.sendRedirect("/web/review");
	}
}
