<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>ATL Gardens, Farms and Orchards</h3>
    <form action="" method="post">
        <table>
            <tr>
                <td>Email</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <%--<td><input type="submit" value="Login"></td>--%>
                <td><button formaction="unconfirmedProperties.jsp">Login</button></td>
            </tr>
            <tr>
                <td><button formaction="ownerRegister.jsp">New Owner Registration</button></td>
                <td><button formaction="visitorRegister.jsp">New Visitor Registration</button></td>
            </tr>
        </table>
    </form>
</body>
</html>