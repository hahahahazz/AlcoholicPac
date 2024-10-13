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
            <h1 class="title">새 리뷰 작성</h1>
<form name="form" action="/web/addReview" method="post" onsubmit="return signUpCheck()">
    <table>
        <tr >
            <td><label for="user_id">작성자:</label></td>
            <td><input type="text" name="user_id" required></td>
        </tr>
        <tr>
            <td><label for="product_no">제품 번호:</label></td>
            <td><input type="text" name="product_no" required></td>
        </tr>
        <tr>
            <td><label for="item_no">아이템 번호:</label></td>
            <td><input type="text" name="item_no" required></td>
        </tr>
        <tr>
            <td><label for="contents">내용:</label></td>
            <td><textarea name="contents" required></textarea></td>
        </tr>
        <tr>
            <td><label for="rating">평점:</label></td>
            <td><input type="text" name="rating" placeholder="1~3" required></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">리뷰 추가</button></td>
        </tr>
    </table>
</form>
 <div class="new_wrap"><a class="new" href="review">목록으로 돌아가기</a></div>

</div>
</main>
</body>
</html>