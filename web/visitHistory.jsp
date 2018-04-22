<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: JunlinLiu
  Date: 2018/4/10
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<%
    List<Integer> ratings = (List<Integer>) request.getSession().getAttribute("rating");
    List<String> propertyName = (List<String>) request.getSession().getAttribute("propertyName");
    List<String> date = (List<String>) request.getSession().getAttribute("date");
%>

<head>
    <title>Visit History</title>
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

<h3>Your Visit History</h3>

<table border="1" id="example">
    <thead>
        <tr id="head">
            <th>Name<input type="submit" value="↓"></th>
            <th>Date Logged<input type="submit" value="↓"></th>
            <th>Rating<input type="submit" value="↓"></th>
        </tr>
    </thead>
    <tbody>
        <%
            for (int i = 0; i < ratings.size(); i++){ %>
        <tr class="line">
            <td><% out.print(propertyName.get(i)); %></td>
            <td><% out.print(date.get(i)); %></td>
            <td><% out.print(ratings.get(i)); %></td>
        </tr>
        <%}
        %>
    </tbody>

</table>
<table>
    <tr>
        <td><input type="submit" name="OK" class="ok" value="View Property Details"/></td>
    </tr>
    <tr>
        <td><form action="/visitorCenter.jsp" method="post"><input type="submit" value="Back"></form></td>
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

    $(document).on('click','th',function(){
        var table = $(this).parents('table').eq(0);
        var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()));
        this.asc = !this.asc;
        if (!this.asc){rows = rows.reverse();}
        table.children('tbody').empty().html(rows);
    });
    function comparer(index) {
        return function(a, b) {
            var valA = getCellValue(a, index), valB = getCellValue(b, index);
            return $.isNumeric(valA) && $.isNumeric(valB) ?
                valA - valB : valA.localeCompare(valB);
        };
    }
    function getCellValue(row, index){
        return $(row).children('td').eq(index).text();
    }
</script>
</body>
</html>