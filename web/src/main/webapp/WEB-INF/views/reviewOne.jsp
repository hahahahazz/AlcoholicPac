<%@page import="Review.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/web/css/reviewOne.css">
</head>

<body>
<!-- 헤더 부분 시작 -->
<header>
<jsp:include page="../componants/header.jsp"></jsp:include>
</header>
<!-- 헤더 부분  끝-->
    
<!-- 메인 부분 시작 -->
<main>
 <div class="container">
   <h1 class="title"> 리뷰 페이지 </h1>
<% Review review = (Review) request.getAttribute("reviewOne"); %>
<% if (review != null) { %>
<table>
    <tr><th>리뷰 번호</th><td><%=review.getReview_no() %></td></tr>
    <tr><th>작성자</th><td><%=review.getUser_id() %></td></tr>
    <tr><th>제품 번호</th><td><%=review.getProduct_no() %></td></tr>
    <tr><th>아이템 번호</th><td><%=review.getItem_no() %></td></tr>
    <tr><th>내용</th><td><%=review.getContents() %></td></tr>
    <tr><th>평점</th><td><%=review.getRating() %></td></tr>
    <tr><th>작성 시간</th><td><%=review.getCreated_at() %></td></tr>
</table>

<!-- 수정 버튼 추가 -->
<div class="button-container">
<form action="/web/reviewUpdate" method="get">
    <input type="hidden" name="review_no" value="<%=review.getReview_no() %>">
    <input type="submit" value="수정">
</form>
<form action="/web/delete" method="get">
    <input type="hidden" name="review_no" value="<%=review.getReview_no() %>">
    <input type="submit" value="삭제">
</form>
</div>
<% 
} else {
    out.println("<p>해당 리뷰를 찾을 수 없습니다.</p>");
}
%>
<div class="new_wrap"><a class="new" href="review">목록으로 돌아가기</a></div>
</div>
</main>
</body>
</html>