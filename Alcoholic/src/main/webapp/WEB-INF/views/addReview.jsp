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
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group textarea {
            resize: vertical;
        }
        .submit-button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .submit-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
 <div class="form-container">
        <h1>새 리뷰 작성</h1>
        <form action="<%= request.getContextPath() %>/reviewList" method="post">
            <div class="form-group">
                <label for="reviewId">리뷰 ID:</label>
                <input type="text" name="reviewId" id="reviewId" required>
            </div>
            <div class="form-group">
                <label for="userId">사용자 ID:</label>
                <input type="text" name="userId" id="userId" required>
            </div>
            <div class="form-group">
                <label for="productNo">제품 번호:</label>
                <input type="text" name="productNo" id="productNo" required>
            </div>
            <div class="form-group">
                <label for="itemNo">항목 번호:</label>
                <input type="text" name="itemNo" id="itemNo" required>
            </div>
            <div class="form-group">
                <label for="reviewContents">리뷰 내용:</label>
                <textarea name="reviewContents" id="reviewContents" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <label for="rating">평점:</label>
                <input type="number" name="rating" id="rating" min="1" max="5" required>
            </div>
            <div class="form-group">
                <label for="reviewDate">리뷰 날짜:</label>
                <input type="date" name="reviewDate" id="reviewDate" required>
            </div>
            <div class="form-group">
                <label for="title">제목:</label>
                <input type="text" name="title" id="title" required>
            </div>
            <button type="submit" class="submit-button">리뷰 추가</button>
        </form>
    </div>
</body>
</html>