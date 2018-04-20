<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>ATL Gardens, Farms and Orchards</h3>
    <form action="/LoginServlet" method="post">
        <table>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" required></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td><input type="submit" value="Login"></td>
                <% Object bool = request.getSession().getAttribute("fail");
                    if (bool != null && (Boolean) bool){ %>
                    <td style="color: red">Wrong email or password. Please try again.</td>
                <%}
                    request.getSession().setAttribute("fail", false);
                %>
            </tr>
            <tr>
                <td><button formaction="ownerRegister.jsp" formnovalidate>New Owner Registration</button></td>
                <td><button formaction="visitorRegister.jsp" formnovalidate>New Visitor Registration</button></td>
            </tr>
        </table>
    </form>
</body>
</html>