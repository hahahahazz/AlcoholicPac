<%@page import="Review.Paging"%>
<%@page import="Review.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

.container {
    max-width: 800px;
    margin: auto;
    background: white;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #333;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

.pagination {
    text-align: center;
    margin: 20px 0;
}

.pagination a {
    margin: 0 5px;
    text-decoration: none;
    color: #007bff;
}

.pagination a:hover {
    text-decoration: underline;
}

.new-post {
    text-align: right;
    margin: 20px 0;
}
    </style>
</head>
<body>
<div class="container">
<h1>게시판</h1>
<table>
<thead>
<tr>
	<th>리뷰번호</th>
	<th>제품번호</th>
	<th>아이템 번호</th>
	<th>리뷰 내용</th>
	<th> 평점 </th>
	<th>작성자</th>
	<th>작성일</th>
	</tr>
 </thead>
<tbody>
 <% ArrayList<Review> list = (ArrayList<Review>) request.getAttribute("allList");
for (Review review : list) {
 %>
<tr> 
<td><a href="/web/reviewOne?review_no=<%=review.getReview_no() %>"><%=review.getReview_no() %></a> </td>
<td><%=review.getProduct_no() %> </td>
<td> <%=review.getItem_no() %> </td>
<td> <%=review.getContents() %> </td>
<td> <%=review.getRating() %> </td>
<td> <%=review.getUser_id() %></td>
<td> <%=review.getCreated_at() %></td>

</tr>
 <%} %>
 </tbody>
</table>

<div class="pagination">
<% 
Paging paging = (Paging) request.getAttribute("paging"); 

// 이전
if (paging.getCurrentGroup() > 1) { 
%>
    [ <a href="/web/review?p=<%=paging.getGrpStartPage() - 1%>"> 이전 </a> ]
<% 
} 

// 페이지 번호 표시 (최대 5개)
int startPage = paging.getGrpStartPage();
int endPage = Math.min(startPage + 4, paging.getTotalPage());

for (int i = startPage; i <= endPage; i++) { 
%>
    [ <a href="/web/review?p=<%=i%>"> <%=i%> </a> ]
<% 
} 

// 다음
if (paging.getCurrentGroup() < paging.getTotalPage()) { 
%>
    [ <a href="/web/review?p=<%=paging.getGrpEndPage() + 1%>"> 다음 </a> ]
<% 
} 
%>
</div>
<div class="new-post">
 <a href="/web/addReview">새 글 작성</a>
</div>
</div>
<div class="search">
    <form action="/web/review?action=searchByItemNo" method="get">
        <input type="text" name="itemNo" placeholder="아이템 번호를 입력하세요" required>
        <input type="submit" value="검색">
    </form>
</div>
</body>
</html>