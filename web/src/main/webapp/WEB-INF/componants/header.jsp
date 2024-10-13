<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
 <style>
 /* 헤더 부분 시작 */
        header {
            height: 160px;
            background-color: rgba(252, 245, 223, 0.95);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        .top {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 100%;
            position: relative;
            letter-spacing: 5px;
        }

        .logo {
            width: 320px;
            height: 65px;
            font-size: 50px;
            font-weight: 600;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 55px;
        }
        .logo a:hover{
            text-decoration: none;
        }

        .menu {
            width: 440px;
            height: 45px;
            position: absolute;
            bottom: 0;
        }

        .menu ul {
            display: flex;
            align-items: center;
            justify-content: space-around;
            padding: 0;
            margin: 0;
            height: 100%;
        }

        .menu ul li {
            list-style: none;
            font-size: 20px;
            letter-spacing: 0px;
            padding: 20px;
        }

        .main_menu {
            text-decoration: none;
            color: black;
        }

        .menu ul li ul {
            display: none;
            position: absolute;
            top: 45px;
            left: calc(50% - 500px);
            background-color: rgba(252, 245, 223, 0.95);
            opacity: 99%;
            padding: 0;
            margin: 0;
            list-style-type: none;
            width: 1000px;
            height: 250px;
            z-index: 999;
        }

        .menu ul li ul li {
            font-size: 40px;
            font-weight: 600;
            padding: 0;
            margin: 25px;
        }

        .menu ul li:hover ul {
            display: flex;
            flex-direction: column;
            flex-wrap: wrap;
        }

        .menu ul li:hover {
            text-decoration: underline;
            text-decoration-color: rgb(250, 220, 100);
        }
        .menu ul li a {
            text-decoration: none;
        }

        .menu ul li ul li:hover {
            text-decoration: none;
        }

        .menu ul li ul li a:hover {
            text-decoration: none;
            color: rgb(250, 220, 100);
        }

        .login {
            width: auto;
            position: absolute;
            right: 95px;
            top: 45px;
        }

        a {
            color: black;
            text-decoration-line: none;
        }

        a:hover {
            text-decoration: underline;
        }
</style>
<header>
        <div class="top">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/main">BOTTLE</a>
            </div>
            <div class="menu">
                <ul>
                    <li><a class="main_menu" href="">WHISKEY</a></li>
                    <li><a class="main_menu" href="">WINE</a></li>
                    <li><a class="main_menu" href="${pageContext.request.contextPath}/review">REVIEW</a></li>
                     <li>
                        <a class="main_menu" href="">MYPAGE</a>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/mymain">MY PAGE</a></li>
                            <li><a href="${pageContext.request.contextPath}/cart">CART</a></li>
                            <li><a href="${pageContext.request.contextPath}/order">ORDER</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="login">
            <c:choose>
                <c:when test="${empty sessionScope.currentUser}">
                    <a href="${pageContext.request.contextPath}/login">LOGIN</a> /
                    <a href="${pageContext.request.contextPath}/register">JOIN US</a>
                </c:when>
                <c:otherwise>
                    <p>${sessionScope.currentUser.name}님, 안녕하세요.</p>
                    <a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
                </c:otherwise>
            </c:choose>
        </div>
    </header>
 