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
	   private Connection dbcon() throws SQLException {
	   Connection con = null;
	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	           con = DriverManager.getConnection(url, user, password);
	           if(con != null) System.out.println("db connected");
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	       return con;
	   }

	   
	   //페이징 전체 리스트 구하기 ArrayList<Review>
	   public ArrayList<Review> listPaging(int currentPage, int pageSize){	//1page 에 10 개  
		   Connection con  = null;
		   PreparedStatement pst = null;
		   ResultSet rs = null;
		   
		   int start =(currentPage - 1) * pageSize + 1;
		   int end =currentPage * pageSize;
		   String sql ="SELECT * FROM ( " +
	                 "  SELECT reviewtbl.*, ROWNUM AS rnum FROM reviewtbl " +
	                 "  ORDER BY review_no DESC " +
	                 ") WHERE rnum BETWEEN ? AND ?";
			  ArrayList<Review> list = new ArrayList<>();
		   
		   try {
			con = dbcon();
			pst = con.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, end);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				String review_no = rs.getString(1);
				String user_id = rs.getString(2);
				String product_no = rs.getString(3);
				String item_no = rs.getString(4);
				String contents = rs.getString(5);
				String rating = rs.getString(6);
				String created_at = rs.getString(7);
				
				Review review = new Review(review_no, user_id, product_no ,item_no,contents,rating, created_at);
				list.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(con, pst, rs);
		}
		   return list;
	   }
	   
	   
	   
	   //전체레코드 수 구하기 
	   public int selectTotalCnt() {
			
			Connection con;
			PreparedStatement pst =null;
			ResultSet rs =null;
			String sql = "select count(*) from reviewtbl";	// 전체레코드 수 구하기
			int rowTotalCnt=0;
			
			try {
				con = dbcon();
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				if(rs.next()) {
					rowTotalCnt = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rowTotalCnt;
		}
		
	   
	   
	   
	   
	 //리뷰 상세 조회
	   public Review selectOne(String review_no) {
		
		   Review review = null;
		   String sql = "select * from reviewtbl where review_no=?";
		   Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
		   
		   try {
			con = dbcon();
			pst =con.prepareStatement(sql);
			pst.setString(1, review_no);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				String review_no1 = rs.getString("review_no");
				String user_id = rs.getString("user_id");
				String product_no = rs.getString("product_no");
				String item_no = rs.getString("item_no");
				String contents = rs.getString("contents");
				String rating = rs.getString("rating");
				String created_at = rs.getString("created_at");
				
				review = new Review(review_no1, user_id,  product_no, item_no, contents, rating, created_at);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
         close(rs, pst, con);
     }
		   return review;
	   }
	   
	   
	   
	// 상세조회 한 뒤 수정 가능 => 수정하면 update 
	   public int update (Review review){
		   int row = 0;
		   Connection con =null;
		   PreparedStatement pst =null;
						//update  reviewtbl set  contents ='하이' where review_no = '1';
		   String sql ="update  reviewtbl set  contents =?, rating=?  where review_no= ? ";
		   try {
			con = dbcon();
			pst = con.prepareStatement(sql);
     
			pst.setString(1, review.getContents());
			pst.setString(2, review.getRating());
			pst.setString(3, review.getReview_no());
			
	        row =pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        close(pst, con);  // 연결 종료는 finally 블록에서 수행
	    }
		   return row;
	   }
 
	 //리뷰 삭제   
	   public int delete(String review_no) {
		   int row = 0;
		   Connection con = null;		    
		   PreparedStatement pst =null;
		   
		   try {
			   con = dbcon();
			// review_no를 숫자로 변환
			  // int reviewNoInt = Integer.parseInt(review_no);
			   
			   //1.리뷰 삭제
			   String sql = "delete from reviewtbl where review_no=?";
			   pst = con.prepareStatement(sql);
			   pst.setString(1, review_no); // int로 설정
			   row = pst.executeUpdate();
			   
			   //2.리뷰 번호 재조정
			   String updateSql = "UPDATE reviewtbl SET review_no = review_no - 1 WHERE review_no > ?";
			   // String updateSql = "UPDATE reviewtbl SET review_no = ROWNUM " +
			   //"WHERE review_no > (SELECT review_no FROM reviewtbl WHERE review_no = ?)";
			   pst= con.prepareStatement(updateSql);
			    pst.setString(1, review_no);
			    row = pst.executeUpdate();
		   		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        close(pst, con);  // 연결 종료는 finally 블록에서 수행
	    }
	    return row;
	}
/*
	   public int delete(String review_no) {
		    int row = 0;
		    Connection con = null;
		    PreparedStatement pstDelete = null;
		    PreparedStatement pstUpdate = null;

		    try {
		        con = dbcon();

		        // 1. 리뷰 삭제
		        String sqlDelete = "DELETE FROM reviewtbl WHERE review_no=?";
		        pstDelete = con.prepareStatement(sqlDelete);
		        pstDelete.setString(1, review_no);
		        row = pstDelete.executeUpdate(); // 삭제된 행 수

		        // 2. 리뷰 번호 재조정 (삭제된 번호보다 큰 모든 리뷰 번호를 1씩 감소)
		        if (row > 0) { // 리뷰가 성공적으로 삭제된 경우
		            String sqlUpdate = "UPDATE reviewtbl SET review_no = review_no - 1 WHERE review_no > ?";
		            pstUpdate = con.prepareStatement(sqlUpdate);
		            pstUpdate.setString(1, review_no);
		            pstUpdate.executeUpdate();
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // 연결 종료는 finally 블록에서 수행
		        close(pstDelete, con);
		        close(pstUpdate, null);
		    }
		    return row; // 삭제된 리뷰 수를 반환
		} 
*/	   
	   //리뷰추가
	   public int addReview(Review review) {
		   int row = 0;
		    String sql = "INSERT INTO reviewtbl (review_no, user_id, product_no, item_no, contents, rating, created_at) VALUES (review_no_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		    Connection con = null;
		    PreparedStatement pst = null;
		    try {
		    	con = dbcon();
		    	pst = con.prepareStatement(sql);
		    
		        pst.setString(1, review.getUser_id());
		        pst.setString(2, review.getProduct_no());
		        pst.setString(3, review.getItem_no());
		        pst.setString(4, review.getContents());
		        pst.setString(5, review.getRating());

		        row = pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(pst, con); 
		    } return row;
	   }
/*	   
	   //검색하기
	   public ArrayList<Review> searchReviews(String searchType, String searchQuery) {
		    // DB 연결 및 쿼리 생성
		    ArrayList<Review> reviews = new ArrayList<>();
		    String query = "SELECT * FROM reviewtbl WHERE " + searchType + " LIKE ?";
		    Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
		    
		    try {
		        con = dbcon();
		        pst = con.prepareStatement(query);
		        pst.setString(1, "%" + searchQuery + "%");
		        rs = pst.executeQuery();
		        
		        while (rs.next()) {
		        	 String review_no = rs.getString("review_no");
		             String user_id = rs.getString("user_id");
		             String product_no = rs.getString("product_no");
		             String item_no = rs.getString("item_no");
		             String contents = rs.getString("contents");
		             String rating = rs.getString("rating");
		             String created_at = rs.getString("created_at"); // Date는 java.util.Date로 변환 가능
		             Review review = new Review(review_no, user_id, product_no, item_no, contents, rating, created_at);
		             reviews.add(review);
		             }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        // 자원 해제
		        close(rs, pst, con); // 기존의 close 메서드 사용
		    }
		    return reviews;
		}
*/
	   
	// 검색하기 (페이지네이션 적용)
	   public ArrayList<Review> searchReviews(String searchType, String searchQuery, int currentPage, int pageSize) {
	       ArrayList<Review> reviews = new ArrayList<>();
	       String query = "SELECT * FROM ( " +
	                      "  SELECT reviewtbl.*, ROWNUM AS rnum FROM reviewtbl " +
	                      "  WHERE " + searchType + " LIKE ? " +
	                      ") WHERE rnum BETWEEN ? AND ?";
	       
	       Connection con = null;
	       PreparedStatement pst = null;
	       ResultSet rs = null;

	       try {
	           con = dbcon();
	           pst = con.prepareStatement(query);
	           pst.setString(1, "%" + searchQuery + "%");
	           int start = (currentPage - 1) * pageSize + 1;
	           int end = currentPage * pageSize;
	           pst.setInt(2, start);
	           pst.setInt(3, end);
	           rs = pst.executeQuery();

	           while (rs.next()) {
	               String review_no = rs.getString("review_no");
	               String user_id = rs.getString("user_id");
	               String product_no = rs.getString("product_no");
	               String item_no = rs.getString("item_no");
	               String contents = rs.getString("contents");
	               String rating = rs.getString("rating");
	               String created_at = rs.getString("created_at");

	               Review review = new Review(review_no, user_id, product_no, item_no, contents, rating, created_at);
	               reviews.add(review);
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }

	       return reviews;
	   }	   
	   
	   //검색된 총 레코드 수를 반환
	   public int getSearchTotalCount(String searchType, String searchQuery) {
		    int count = 0;
		    String query = "SELECT COUNT(*) FROM reviewtbl WHERE " + searchType + " LIKE ?";
		    
		    Connection con = null;
		    PreparedStatement pst = null;
		    ResultSet rs = null;

		    try {
		        con = dbcon();
		        pst = con.prepareStatement(query);
		        pst.setString(1, "%" + searchQuery + "%");
		        rs = pst.executeQuery();
		        
		        if (rs.next()) {
		            count = rs.getInt(1);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(con, pst, rs);
		    }

		    return count;
		}   
	   
	   
	   
	   
	   
	   //자원해제
	   public void close( AutoCloseable ...a) {
			for( AutoCloseable  item : a) {
				if (item != null) {//item 객체가 null인지 확인하고, null인 경우에 대한 예외 처리를 추가하는 방법
			        try {
			            item.close(); // AutoCloseable 객체 닫기
			        } catch (Exception e) {
			            // 예외 처리 로깅 또는 추가 처리
			            e.printStackTrace();
			        }
			    } else {
			        // item이 null일 경우의 처리
			        System.out.println("Warning: item is null, cannot close.");
			    }
			}
	   }
	   
	   
	   public static void main(String[] args) {
		   ReviewDAO dao = new ReviewDAO();
			//ArrayList<Review> r = dao.selectAllReview();
			//for (Review review : r) {
			//	System.out.println(review);
			//}
			ArrayList<Review> list = dao.listPaging(1, 10);
			System.out.println(list);
		   
			//추가
//		   Review r = new Review("1","2","3","4","5");
//		   dao.addReview(r);
//		   System.out.println(r);
	}
}
