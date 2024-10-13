<%@page import="Review.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/web/css/review.css">
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
  <h1 class="title">리뷰 수정 페이지</h1>
	<form name="form" action="/web/reviewUpdate" method="post">
    <% Review updateReview = (Review) request.getAttribute("review"); %>
    <input type="hidden" name="review_no" value="<%=updateReview.getReview_no() %>"/> 
  <div class="info_wrap">
    <table>
        <tr>
            <td><label for="contents">내용:</label></td>
            <td><textarea name="contents" id="contents" rows="4" cols="50"><%= updateReview.getContents() %></textarea></td>
        </tr>
        <tr>
            <td><label for="rating">평점:</label></td>
            <td>
                <input type="number" name="rating" id="rating" min="1" max="5" value="<%= updateReview.getRating() %>" />
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="수정 완료" /></td>
        </tr>
    </table>
    </div>
</form>
<div class="new_wrap"><a class="new" href="/web/review">목록으로 돌아가기</a></div>
</div>
</main>
</body>
</html>