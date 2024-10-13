package Review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/review2")
public class ReviewServlet2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		int pageSize =3;
		int grpSize = 5;
		int totRecords =0;
		int currentPage =1;
		
		ReviewService service = new ReviewService();
		//ArrayList<Review> allList = service.getReviewAll();
		ReviewDAO dao = new ReviewDAO();
		ArrayList<Review> allList = dao.listPaging(currentPage, pageSize);
		
		Paging paging = new Paging(pageSize,grpSize,totRecords,currentPage);

		String p_ = req.getParameter("p");
		if(p_ != null) {
			currentPage = Integer.parseInt(p_);
		}
		
		totRecords = dao.selectTotalCnt();
		
		req.setAttribute("allList", allList);
		req.setAttribute("paging", new Paging(pageSize, grpSize, totRecords, currentPage));
		req.getRequestDispatcher("WEB-INF/views/review.jsp").forward(req, resp);
		
		
	}
}
