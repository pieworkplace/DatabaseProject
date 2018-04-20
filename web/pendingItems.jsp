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
        <td><% out.print(farmItem.getFarmItemType()); %></td>
    </tr>
    <%}
    %>
</table>
<table>
    <tr>
        <td><input type="button" name="OK" class="ok" value="Approve Selection"/></td>
    </tr>
    <tr>
        <td><input type="button" name="OK" class="ok" value="Delete Selection"/></td>
    </tr>
    <tr>
        <td><button formaction="">Back</button></td>
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