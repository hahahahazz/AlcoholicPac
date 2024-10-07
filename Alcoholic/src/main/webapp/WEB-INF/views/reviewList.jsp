<%@page import="Review.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Alcoholic/css/style.css">
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .add-review-link {
            display: block;
            width: 150px;
            margin: 20px auto;
            padding: 10px;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .add-review-link:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>게시물 목록</h1>
    <table>
        <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <%
        ArrayList<Review> list = (ArrayList<Review>) request.getAttribute("reviewList");
        if (list != null && !list.isEmpty()) {
            for (Review review : list) {
        %>
        <tr>
            <td><%= review.getTitle() %></td>
            <td><%= review.getUser_id() %></td>
            <td><%= review.getReview_date() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">리뷰가 없습니다.</td>
        </tr>
        <%
        }
        %>
    </table>
	<a href="<%= request.getContextPath() %>/addReview">새 게시물 작성</a>

</body>
</html>