package Review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {

	 // DB 연결 정보
   private String url = "jdbc:oracle:thin:@localhost:1521:testdb";
   private String user = "scott";
   private String password = "tiger";

   // DB 연결 메서드 (드라이버 로드 및 DB 연결)
   private Connection dbCon() throws SQLException {
   Connection con = null;
       try {
           // 드라이버 로드
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con = DriverManager.getConnection(url, user, password);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       // DB 연결 객체 반환
       return con;
   }
   
   public ArrayList<Review> selectReview(){
   	ArrayList<Review> list = new ArrayList<>();
   	
   	try {
			Connection con = dbCon();
			
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			String sql = "select * from review_tbl";
			
			pst = con.prepareStatement(sql);
		
			rs = pst.executeQuery();
			

			//String review_id, String user_id, String product_no, String item_no, String review_contents,
			//String rating, String review_date, String title
			while (rs.next()) {
				 String review_id = rs.getString(1);
				 String user_id = rs.getString(2);
				 String product_no = rs.getString(3);
				 String item_no = rs.getString(4);
				 String review_contents = rs.getString(5);
				 String rating = rs.getString(6);
				 String review_date = rs.getString(7);
				 String title = rs.getString(8);
				 
				 Review review = new Review(review_id, user_id, product_no, item_no, review_contents, rating, review_date, title);
				 list.add(review);			
			
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   	return list;
   }
   
   
   public void addReview(Review review) {
	   String sql = "INSERT INTO review_tbl (review_id, user_id, product_no, item_no, review_contents, rating, review_date, title) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
       
       try (Connection con = dbCon();
            PreparedStatement pst = con.prepareStatement(sql)) {
           
           // PreparedStatement에 파라미터 설정
           pst.setString(1, review.getReview_id());
           pst.setString(2, review.getUser_id());
           pst.setString(3, review.getproduct_no());
           pst.setString(4, review.getItem_no());
           pst.setString(5, review.getReview_contents());
           pst.setString(6, review.getRating());
           pst.setString(7, review.getReview_date());
           pst.setString(8, review.getTitle());
           
           // 쿼리 실행
           pst.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   public static void main(String[] args) {
	ReviewDAO dao = new ReviewDAO();
	// 리뷰 추가 예시
    Review newReview = new Review("R001", "U001", "P001", "I001", "Great product!", "5", "2024-10-07", "Fantastic!");
    dao.addReview(newReview);
	
	//조회
	ArrayList<Review> list = dao.selectReview();
	System.out.println(list);
}
}
