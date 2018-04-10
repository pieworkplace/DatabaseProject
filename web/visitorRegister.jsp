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
    <form action="" method="post" id="visitorRegisterForm">
        <table>
            <tr>
                <td>Email*</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Username*</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password*</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Confirm Password*</td>
                <td><input type="password" name="passwordAgain"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Register" id="visitorRegisterSubmitButton">
                    <button formaction="login.jsp">Cancel</button>
                </td>
            </tr>
        </table>
    </form>
    <script>
        $("#visitorRegisterSubmitButton").on("click", function(e) {
            e.preventDefault();
        });
    </script>
</body>
</html>