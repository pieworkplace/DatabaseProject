<%@ page import="database.classes.User" %>
<%@ page import="database.classes.Visit" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: JunlinLiu
  Date: 2018/4/10
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%
    User user = (User) request.getSession().getAttribute("user");
    List<Visit> allVisitors = (List<Visit>) request.getSession().getAttribute("allVisitors");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>All Visitors in System</title>
    <style>
        #example tr:hover {
            background-color: #ccc;
        }
        #example th {
            background-color: #fff;
        }
        #example td:hover {
            cursor: pointer;
        }
        .selected {
            background-color: #ccc;
        }
    </style>
</head>
<body>

<h3>All Visitors in System</h3>

<table border="1" id="example">
    <tr id="head">
        <th>Username<input type="submit" value="↓"></th>
        <th>Email<input type="submit" value="↓"></th>
        <th>Logged Visits<input type="submit" value="↓"></th>
    </tr>
    <%
        for (Visit visitor : allVisitors) { %>
            <tr class="line">
                <td><% out.print(visitor.getUsername()); %></td>
                <td><% out.print(visitor.getEmail()); %></td>
                <td><% out.print(visitor.getLogged_visit()); %></td>
            </tr>
        <%}
    %>
</table>

<table>
    <tr>
        <td><select>
            <option value="" disabled selected>Search by...</option>
            <option value="Name">Name</option>
        </select></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><input type="button" name="OK" class="ok" value="Delete Visitor Account"/></td>
    </tr>
    <tr>
        <td><input type="text" name="username" placeholder="Search Term"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><input type="button" name="OK" class="ok" value="Delete Log History"/></td>
    </tr>
    <tr>
        <td><button formaction="">Search Visitors</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="/adminFunctionality.jsp">Back</button></td>
    </tr>
</table>
<script>
    $(document).ready(function() {
        $('#example .line').click(function(e){
            e.stopPropagation();
            $(this).addClass('selected').siblings().removeClass('selected');
        });
        $('.ok').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
            }else{
                alert($("#example tr.selected td:first").html());
            }
        });
        $(document).click(function() {
            $('#example .line').removeClass('selected');
        });
    });
</script>
</body>
</html>