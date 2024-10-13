package Review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/review0")
public class ReviewServlet0 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ReviewDAO dao = new ReviewDAO();
        String action = req.getParameter("action");

        // 아이템 번호로 검색
//        if ("searchByItemNo".equals(action)) {
//            String itemNo = req.getParameter("itemNo");
//            ArrayList<Review> allList = dao.searchByItemNo(itemNo);
//            req.setAttribute("allList", allList);
//            req.setAttribute("paging", null); // 검색 시 페이징 정보는 필요 없음
//            req.getRequestDispatcher("WEB-INF/views/review.jsp").forward(req, resp);
//            return;
//        }

		 int pageSize = 3; // 한 페이지에 보여줄 리뷰 수
	        int grpSize = 5;  // 페이지 그룹의 크기
	        int currentPage = 1; // 현재 페이지 초기값

	        // 쿼리 파라미터에서 페이지 번호 가져오기
	        String p_ = req.getParameter("p");
	        if (p_ != null) {
	            currentPage = Integer.parseInt(p_);
	        }

	        int totRecords = dao.selectTotalCnt(); // 총 레코드 수 가져오기
	        ArrayList<Review> allList = dao.listPaging(currentPage, pageSize); // 현재 페이지에 해당하는 리뷰 목록 가져오기

	        // Paging 객체 생성
	        Paging paging = new Paging(pageSize, grpSize, totRecords, currentPage);

	        // 요청 속성에 리뷰 목록과 페이징 정보 설정
	        req.setAttribute("allList", allList);
	        req.setAttribute("paging", paging);

	        // JSP로 포워딩
	        req.getRequestDispatcher("WEB-INF/views/review.jsp").forward(req, resp);
	    }
	}