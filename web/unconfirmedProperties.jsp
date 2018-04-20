<%@ page import="database.classes.Property" %>
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
    List<Property> unconfirmedProperties = (List<Property>) request.getSession().getAttribute("unconfirmedProperties");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>Unconfirmed Properties</title>
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
Unconfirmed Properties:

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Address</th>
        <th>City<input type="submit" value="↓"></th>
        <th>Zip</th>
        <th>Size<input type="submit" value="↓"></th>
        <th>Type</th>
        <th>Public</th>
        <th>Commercial</th>
        <th>ID</th>
        <th>Owner<input type="submit" value="↓"></th>
    </tr>
    <%
        for (Property property : unconfirmedProperties) { %>
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
        <td><% out.print(property.getOwner()); %></td>
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
    </tr>
    <tr>
        <td><button formaction="">Search Properties</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><input type="button" name="OK" class="ok" value="Manage Selected Property" formaction="manageProperty.jsp"/></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="adminFunctionality.jsp">Back</button></td>
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