<%@ page import="database.classes.User" %><%--
  Created by IntelliJ IDEA.
  User: guowanyang
  Date: 4/10/18
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    User user = (User) request.getSession().getAttribute("user");
    String adminName = user.getUsername();
%>
<head>
    <title>Welcome <% out.print(adminName); %></title>
</head>
<body>
<h3>Welcome <% out.print(adminName); %></h3>
<form action="" method="post" id="adminFunctionality">
    <table>
        <tr>
            <td><button formaction="allVisitors.jsp">View Visitors List</button></td>
        </tr>
        <tr>
            <td><button formaction="allOwners.jsp">View Owners List</button></td>
        </tr>
        <tr>
            <td><button formaction="confirmedProperties.jsp">View Confirmed Properties</button></td>
        </tr>
        <tr>
            <td><button formaction="unconfirmedProperties.jsp">View Unconfirmed Properties</button></td>
        </tr>
        <tr>
            <td><button formaction="approvedItems.jsp">View Approved Animals and Crops</button></td>
        </tr>
        <tr>
            <td><button formaction="pendingItems.jsp">View Pending Animals and Crops</button></td>
        </tr>
        <tr>
            <td><button formaction="login.jsp">Log out</button></td>
        </tr>
    </table>
</form>
</body>
</html>
