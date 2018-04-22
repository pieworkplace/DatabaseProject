<%@ page import="database.classes.User" %>
<%@ page import="database.classes.Visit" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page import="database.classes.Property" %>
<%@ page import="database.classes.FarmItem" %><%--
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
    List<Visit> allVisitors = UserService.getAllVisitors();
    request.getSession().setAttribute("allVisitors", allVisitors);
    List<Visit> allOwners = UserService.getAllOwners();
    request.getSession().setAttribute("allOwners", allOwners);
    List<Property> confirmedProperties = UserService.getconfirmedProperties();
    request.getSession().setAttribute("confirmedProperties", confirmedProperties);
    List<Property> unconfirmedProperties = UserService.getunconfirmedProperties();
    request.getSession().setAttribute("unconfirmedProperties", unconfirmedProperties);
    List<FarmItem> approvedItems = UserService.getapprovedItems();
    request.getSession().setAttribute("approvedItems", approvedItems);
    List<FarmItem> pendingItems = UserService.getpendingItems();
    request.getSession().setAttribute("pendingItems", pendingItems);
    User user = (User) request.getSession().getAttribute("user");
    String adminName = user.getUsername();
%>
<head>
    <title>Welcome <% out.print(adminName); %></title>
</head>
<body>
<h3>Welcome <% out.print(adminName); %></h3>
<form id="adminFunctionality">
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
