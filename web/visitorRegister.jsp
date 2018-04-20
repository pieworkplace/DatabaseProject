<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Visitor Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
    <h3>New Visitor Registration</h3>
    <form action="/VisitorRegisterServlet" method="post" id="visitorRegisterForm">
        <table>
            <tr>
                <td>Email*</td>
                <td><input type="email" name="email" pattern="[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,4}$" required></td>
            </tr>
            <tr>
                <td>Username*</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>Password*</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>Confirm Password*</td>
                <td><input type="password" name="passwordAgain" required></td>
            </tr>
            <tr><td></td>
                <% Object bool = request.getSession().getAttribute("VisitorRegfail");
                    if (bool != null){
                        if (((int)bool) == 1){%>
                <td style="color: red">Email or password has been used. Please try another one.</td>
                <%} else if (((int)bool) == 3){%>
                <td style="color: red">Password and confirm password must match.</td>
                <%} else if (((int)bool) == 4){%>
                <td style="color: red">Password must have at least 8 characters.</td>
                <%}}
                    request.getSession().setAttribute("VisitorRegfail", 0);
                %>
            </tr>
            <tr>
                <td><input type="submit" value="Register" id="visitorRegisterSubmitButton"></td>
                <td><button formaction="login.jsp" formnovalidate>Cancel</button></td>
            </tr>
        </table>
    </form>
    <script>
        // $("#visitorRegisterSubmitButton").on("click", function(e) {
        //     e.preventDefault();
        // });
    </script>
</body>
</html>