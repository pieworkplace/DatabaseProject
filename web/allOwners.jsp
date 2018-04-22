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
    List<Visit> allOwners = (List<Visit>) request.getSession().getAttribute("allOwners");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>All Owners in System</title>
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

<h3>All Owners in System</h3>

<table border="1" id="example">
    <tr id="head">
        <th>Username<input type="submit" value="↓"></th>
        <th>Email<input type="submit" value="↓"></th>
        <th>Number of Properties<input type="submit" value="↓"></th>
    </tr>
    <%
        for (Visit owner : allOwners) { %>
    <tr class="line">
        <td><% out.print(owner.getUsername()); %></td>
        <td><% out.print(owner.getEmail()); %></td>
        <td><% out.print(owner.getLogged_visit()); %></td>
    </tr>
    <%}
    %>
</table>
<table>
    <tr>
        <td><select id="SelectSearch">
            <option value="" disabled selected>Search by...</option>
            <option value="Username">Name</option>
            <option value="Email">Email</option>
            <option value="Num_Of_Properties">Number of Properties</option>
        </select></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <form action="/AdminOwnerServlet" method="post">
                <input type="hidden" id="rowIndex" name="rowIndex" value="-1"/>
                <input type="submit" value="Delete Owner Account" id = "DeleteOwnerAccount">
            </form>
        </td>
    </tr>
    <tr>
        <td><input type="text" name="SearchWriteText" placeholder="Search Term" id="SearchWriteText"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="adminFunctionality.jsp" id="Back">Back</button></td>
    </tr>
    <tr>
        <td>
            <form action="/AdminSearchOwnerServlet" method="post">
                <input type="hidden" id="SearchText" name="SearchText" value=""/>
                <input type="hidden" id="SearchTypeText" name="SearchTypeText" value=""/>
                <input type="submit" name="Search Owners" value="Search Owners" onclick="get()">
            </form>
        </td>
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
        $('#DeleteOwnerAccount').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
            }else{
                window.location.href = "/AdminOwnerServlet.jsp";
            }
        });
        $('#Back').on('click', function () {
            window.location.href = "/adminFunctionality.jsp";
        })
        $(document).click(function() {
            $('#example .line').removeClass('selected');
            document.getElementById("rowIndex").value = -1;
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