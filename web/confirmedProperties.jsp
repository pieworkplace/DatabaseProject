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
    List<Property> confirmedProperties = (List<Property>) request.getSession().getAttribute("confirmedProperties");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>Confirmed Properties:</title>
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
Confirmed Properties:

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Address</th>
        <th>City</th>
        <th>Zip<input type="submit" value="↓"></th>
        <th>Size</th>
        <th>Type<input type="submit" value="↓"></th>
        <th>Public</th>
        <th>Commercial</th>
        <th>ID</th>
        <th>Verified by<input type="submit" value="↓"></th>
        <th>Avg. Rating<input type="submit" value="↓"></th>
    </tr>
    <%
        for (Property property : confirmedProperties) { %>
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
        <td><% out.print(property.getApprovedBy()); %></td>
        <td><% out.print(property.getAvg_rating()); %></td>
    </tr>
    <%}
    %>
</table>
<table>
    <tr>
        <td><select id="SelectSearch">
            <option value="" disabled selected>Search by...</option>
            <option value="Name">Name</option>
            <option value="Zip">Zip</option>
            <option value="PropertyType">Type</option>
            <option value="ApprovedBy">Verified By</option>
            <option value="AVG">Avg. Rating</option>
        </select></td>
    </tr>
    <tr>
        <td><input type="text" name="SearchWriteText" placeholder="Search Term" id="SearchWriteText"></td>
    </tr>
    <tr>
        <td>
            <form action="/AdminSearchConfirmedServlet" method="post">
                <input type="hidden" id="SearchText" name="SearchText" value=""/>
                <input type="hidden" id="SearchTypeText" name="SearchTypeText" value=""/>
                <input type="submit" name="Search Properties" value="Search Properties" onclick="get()">
            </form>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><input type="button" name="OK" class="ok" value="Manage Selected Property" formaction="manageProperty.jsp"/></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="adminFunctionality.jsp" id="Back">Back</button></td>
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
        $('#Back').on('click', function () {
            window.location.href = "/adminFunctionality.jsp";
        })
        $(document).click(function() {
            $('#example .line').removeClass('selected');
        });
    });

    function get() {
        var SearchName = document.getElementById("SearchWriteText").value;
        var SearchSelect = document.getElementById("SelectSearch");
        var Searchindex = SearchSelect.selectedIndex;
        var SearchType = SearchSelect.options[Searchindex].value;
        document.getElementById("SearchText").value = SearchName;
        document.getElementById("SearchTypeText").value = SearchType;
    }
</script>
</body>
</html>