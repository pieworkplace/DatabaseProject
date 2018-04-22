<%@ page import="database.classes.Property" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ysy
  Date: 4/11/18
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title> </title>
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

<h3>All Other Valid Properties:</h3>

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Address</th>
        <th>City<input type="submit" value="↓"></th>
        <th>Zip</th>
        <th>Size</th>
        <th>Type</th>
        <th>Public<input type="submit" value="↓"></th>
        <th>Commercial</th>
        <th>ID</th>
        <th>Visits<input type="submit" value="↓"></th>
        <th>Avg. Rating <input type="submit" value="↓"></th>
    </tr>
    <% List<Property> propertyList = (List<Property>) request.getSession().getAttribute("allotherproperties");
        for (Property property : propertyList){ %>
    <tr class="line">
        <td><% out.print(property.getName()); %></td>
        <td><% out.print(property.getStreet()); %></td>
        <td><% out.print(property.getCity()); %></td>
        <td><% out.print(property.getZip()); %></td>
        <td><% out.print(property.getSize()); %></td>
        <td><% out.print(property.getPropertyType()); %></td>
        <td><% out.print(property.isPublic()?"True":"False"); %></td>
        <td><% out.print(property.isCommercial()?"True":"False"); %></td>
        <td><% out.print(property.getID()); %></td>
        <td>X</td>
        <td>X</td>
    </tr>
    <%}%>
</table>
<table>
    <tr>
        <td><select>
            <option value="" disabled selected>Search by...</option>
            <option value="Address">Address</option>
            <option value="City">City</option>
            <option value="Zip">Zip</option>
            <option value="Size">Size</option>
            <option value="Type">Type</option>
            <option value="Public">Public</option>
            <option value="Commercial">Commercial</option>
            <option value="ID">ID</option>
            <option value="Visits">Visits</option>
            <option value="Avg.Rating">Avg.Rating</option>
        </select></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td><input type="text" name="username" placeholder="Search Term"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><form action="/ViewOtherPropertyDetailServlet" method="post">
            <input type = "hidden" id = "rowIndex" name="rowIndex" value="-1"/>
            <input type="submit" value="View Property Details" id = "viewProperty">
        </form></td>
    </tr>
    <tr>
        <td><button formaction="">Search Properties</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><form action="/ownerCenter.jsp" method="post">
            <input type="submit" value="Back">
        </form></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
        $('#viewProperty').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
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