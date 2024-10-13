package Review;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/addReview")
public class AddReviewServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 세션에서 로그인 상태 확인
        HttpSession session = req.getSession(false); // 새로운 세션을 만들지 않음
        //세션이 존재할 경우 로그인 한 사용자의 id 를 가져오기
        String id = (session != null) ? (String) session.getAttribute("id") : null;

        if (id != null) {
            // 회원인 경우, 새 글 작성 페이지로 이동
            req.getRequestDispatcher("WEB-INF/views/reviewAdd.jsp").forward(req, resp);
        } else {
            // 비회원인 경우, 경고 메시지와 함께 리뷰 메인 페이지로 리다이렉트
            String message = URLEncoder.encode("로그인이 필요합니다.", "utf-8");
            resp.sendRedirect("/web/review?message=" + message);
        }	}

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
	   
	   
	   resp.sendRedirect("/web/review?p=1");
	}
}
