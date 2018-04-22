<%@ page import="database.classes.FarmItem" %>
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
    List<FarmItem> pendingItems = (List<FarmItem>) request.getSession().getAttribute("pendingItems");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>Pending Approval Animals/Crops</title>
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

<h3>Pending Approval Animals/Crops</h3>

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Type<input type="submit" value="↓"></th>
    </tr>
    <%
        for (FarmItem farmItem : pendingItems) { %>
    <tr class="line">
        <td><% out.print(farmItem.getName()); %></td>
        <td><% out.print(farmItem.getType()); %></td>
    </tr>
    <%}
    %>
</table>
<table>
    <tr>
        <td>
            <form action="/AdminpendingServlet" method="post">
                <input type="hidden" id="rowIndexAP" name="rowIndexAP" value="-1"/>
                <input type="submit" value="Approve Selection" id = "ApproveSelection">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/AdminPendingDelServlet" method="post">
                <input type="hidden" id="rowIndexDL" name="rowIndexDL" value="-1"/>
                <input type="submit" value="Delete Selection" id = "DeleteSelection">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <button formaction="adminFunctionality.jsp" id="Back">Back</button>
        </td>
    </tr>
</table>
<script>
    $(document).ready(function() {
        $('#example .line').click(function(e){
            e.stopPropagation();
            var row_index = $(this).parent().children().index($(this)) - 1;
            $(this).addClass('selected').siblings().removeClass('selected');
            document.getElementById("rowIndexAP").value = row_index;
            document.getElementById("rowIndexDL").value = row_index;
        });
        $('#ApproveSelection').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
            }else{
                window.location.href = "/AdminpendingServlet";
            }
        });
        $('#DeleteSelection').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
            }else{
                window.location.href = "/AdminpendingDelServlet";
            }
        });
        $('#Back').on('click', function () {
            window.location.href = "/adminFunctionality.jsp";
        })
        $(document).click(function() {
            $('#example .line').removeClass('selected');
            document.getElementById("rowIndexAP").value = -1;
            document.getElementById("rowIndexDL").value = -1;
        });
    });
</script>
</body>
</html>