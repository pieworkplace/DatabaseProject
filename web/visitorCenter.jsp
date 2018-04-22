<%@ page import="database.classes.User" %>
<%@ page import="java.util.List" %>
<%@ page import="database.classes.Property" %><%--
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
    String visitorName = user.getUsername();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>Welcome <% out.print(visitorName); %></title>
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

<h3>Welcome <% out.print(visitorName); %></h3>
All public, validated properties:

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Address</th>
        <th>City<input type="submit" value="↓"></th>
        <th>Zip</th>
        <th>Size</th>
        <th>Type<input type="submit" value="↓"></th>
        <th>Public</th>
        <th>Commercial</th>
        <th>ID</th>
        <th>Visits<input type="submit" value="↓"></th>
        <th>Avg. Rating <input type="submit" value="↓"></th>
    </tr>
    <% List<Property> propertyList = (List<Property>) request.getSession().getAttribute("publicProperties");
    
        for (Property property : propertyList){ %>
            <tr class="line">
                <td><% out.print(property.getName()); %></td>
                <td><% out.print(property.getStreet()); %></td>
                <td><% out.print(property.getCity()); %></td>
                <td><% out.print(property.getZip()); %></td>
                <td><% out.print(property.getSize()); %></td>
                <td><% out.print(property.getPropertyType()); %></td>
                <td><% out.print(property.isPublic()); %></td>
                <td><% out.print(property.isCommercial()); %></td>
                <td><% out.print(property.getID()); %></td>
                <td>X</td>
                <td>X</td>
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
    </tr>
    <tr>
        <td><input type="text" name="username" placeholder="Search Term"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <form action="/VisitorManageServlet" method="post">
                <input type = "hidden" id = "rowIndex" name="rowIndex" value="-1"/>
                <input type="submit" value="View Property" id = "viewProperty">
            </form>
        </td>
    </tr>
    <tr>
        <td><button formaction="">Search Properties</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="visitHistory.jsp">View Visit History</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="login.jsp">Log Out</button></td>
    </tr>
</table>
<script>
    $(document).ready(function() {
        $('#example .line').click(function(e){
            e.stopPropagation();
            var row_index = $(this).parent().children().index($(this)) - 1;
            $(this).addClass('selected').siblings().removeClass('selected');
            document.getElementById("rowIndex").value = row_index;
        });
        // $('#example').click(function (e) {
        //     var row_index = $(this).parent().index('tr');
        //
        // })
        $('#viewProperty').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
            }else{
                window.location.href = "/VisitorManageServlet";
            }
        });
        $(document).click(function() {
            $('#example .line').removeClass('selected');
            document.getElementById("rowIndex").value = -1;
        });
    });
</script>
</body>
</html>