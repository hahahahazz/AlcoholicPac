package Review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/review")
public class ReviewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ReviewDAO dao = new ReviewDAO();
		ReviewService service = new ReviewService();
		
		int pageSize =3;
		int grpSize = 5;
		int currentPage =1;
		// 쿼리 파라미터에서 페이지 번호와 검색 조건 가져오기
		String p_ = req.getParameter("p");
		 // 검색 관련 파라미터 가져오기
        String searchType = req.getParameter("searchType");
        String searchQuery = req.getParameter("searchQuery");
        
		if(p_ != null) {
			currentPage = Integer.parseInt(p_);
		}
		
		ArrayList<Review> allList;
		int totRecords;
        
		if (searchQuery != null && !searchQuery.isEmpty()) {
	        // 검색 기능
	        allList = dao.searchReviews(searchType, searchQuery, currentPage, pageSize);
	        // 총 레코드 수는 별도로 계산
	        totRecords = dao.getSearchTotalCount(searchType, searchQuery);
        } else {
            // 총 레코드 수 가져오기
        	totRecords = dao.selectTotalCnt();
            
            // 현재 페이지에 해당하는 리뷰 목록 가져오기
            allList = dao.listPaging(currentPage, pageSize);
        }
		
		Paging paging = new Paging(pageSize,grpSize, totRecords,currentPage);
		
		req.setAttribute("allList", allList);
		req.setAttribute("paging", paging);
		req.setAttribute("totalCount", totRecords); // 전체 리뷰 수 설정
		req.getRequestDispatcher("WEB-INF/views/review.jsp").forward(req, resp);
	}
	}