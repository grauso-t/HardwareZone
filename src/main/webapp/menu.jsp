<%@ page import="Model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it-IT">
<head>

    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/nav-menu.css">
    <script src='https://kit.fontawesome.com/c6b30e1924.js' crossorigin='anonymous'></script>

    <script>
        <%
            UserBean myProfile = (UserBean) request.getSession().getAttribute("user");

            String name, url;

            if (myProfile == null) {
                name = "Accesso";
                url = "location.href='login.jsp'";
            } else {
                name = myProfile.getName();
                url = "showSubMenu()";
            }
        %>

        window.addEventListener("resize", browserSize);
        function browserSize() {
            if (window.outerWidth <= 1160)
                $("div.menu").hide();
            else
                $("div.menu").show();
        }

        $(document).ready(function() {
            $("div.sub-menu").hide();
            currentPage();
        });

        function showMenu() {
            $("div.menu").slideToggle();
        }

        function showSubMenu() {
            $("div.sub-menu").slideToggle();
        }

        function currentPage() {

            let currentPage = document.title;

            $(".item-menu").each(function() {

                if (currentPage === (this.getAttribute("data-name"))) {
                    $(this).closest("div").addClass("active");
                }
            });
        }
    </script>

</head>
<body>

    <div class="navigation-bar">
        <div class="show-menu" onclick="showMenu()"><i class="fa-solid fa-bars"></i></div>
        <div class="menu">
            <div class="item-menu item-logo" onclick="location.href='index.jsp'"><img src="img/Logo_NoScritta.png" class="logo" alt="logo"></div>
            <div class="item-menu" onclick="location.href='index.jsp'" data-name="HomePage"><i class="fa-solid fa-house iconMenu"></i>HomePage</div>
            <div class="item-menu" onclick="location.href='#'" data-name="Catalogo"><i class="fa-solid fa-book-open iconMenu"></i>Catalogo</div>
            <div class="item-menu" onclick="location.href='#'" data-name="Offerte"><i class="fa-solid fa-dollar-sign iconMenu"></i>Offerte</div>
            <div class="item-menu" onclick="location.href='#'" data-name="Carrello"><i class="fa-solid fa-cart-shopping iconMenu"></i>Carrello</div>
            <div class="item-menu" onclick="location.href='#'" data-name="Chi siamo"><i class="fa-solid fa-circle-info iconMenu"></i>Chi siamo</div>
            <div class="item-menu" onclick="<%= url %>" style="position: relative" data-name="Login"><i class="fa-solid fa-user iconMenu"></i><%= name %>
                <div class="sub-menu" data-name="Login">
                    <div class="item-submenu" onclick="location.href='myProfile-profile-servlet'" data-name="Login">Profilo</div>
                    <div class="item-submenu" onclick="location.href='#'" data-name="Login">Ordini</div>
                    <div class="item-submenu" onclick="location.href='logout-servlet'">Logout</div>
                </div>
            </div>
        </div>
    </div>

</body>