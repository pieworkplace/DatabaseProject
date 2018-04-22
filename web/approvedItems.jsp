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
    List<FarmItem> approvedItems = (List<FarmItem>) request.getSession().getAttribute("approvedItems");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>Approved Animals/Crops</title>
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

<h3>Approved Animals/Crops</h3>

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Type<input type="submit" value="↓"></th>
    </tr>
    <%
        for (FarmItem farmItem : approvedItems) { %>
    <tr class="line">
        <td><% out.print(farmItem.getName()); %></td>
        <td><% out.print(farmItem.getType()); %></td>
    </tr>
    <%}
    %>
</table>
<table>
    <tr>
        <td><select id="SearchSelect" name="SearchSelect">
            <option value="" disabled selected>Search by...</option>
            <option value="Name">Name</option>
            <option value="Type">Type</option>
        </select></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><select id="TypeSelect" name="TypeSelectCol">
            <option value="" disabled selected>Type...</option>
            <option value="ANIMAL">ANIMAL</option>
            <option value="NUT">NUT</option>
            <option value="FRUIT">FRUIT</option>
            <option value="FLOWER">FLOWER</option>
            <option value="VEGETABLE">VEGETABLE</option>
        </select></td>
    </tr>
    <tr>
        <td>
            <input type="text" name="SearchName" placeholder="Search Term" id="SearchName">
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <form name="MyItem">
                <input type="text" name="ItemName" placeholder="Enter Name" id="ItemName">
            </form>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <form action="/AdminApprovedDelServlet" method="post">
                <input type="hidden" id="rowIndexDL" name="rowIndexDL" value="-1"/>
                <input type="submit" value="Delete Selection" id = "DeleteSelection">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/AdminSearchApprovedServlet" method="post">
                <input type="hidden" id="SearchNameText" name="SearchNameText" value=""/>
                <input type="hidden" id="SearchTypeText" name="SearchTypeText" value=""/>
                <input type="submit" name="Search Approved Item" value="Search Approved Item" onclick="get()">
            </form>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <form action="/AdminAddAprovedServlet" method="post">
                <input type="hidden" id="ItemNameText" name="ItemNameText" value=""/>
                <input type="hidden" id="ItemTypeText" name="ItemTypeText" value=""/>
                <input type="submit" name="Add to Approved List" value="Add to Approved List" onclick="get()">
            </form>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="adminFunctionality.jsp" id="Back">Back</button></td>
    </tr>
</table>
<script>
    $(document).ready(function() {
        $('#example .line').click(function(e){
            e.stopPropagation();
            var row_index = $(this).parent().children().index($(this)) - 1;
            $(this).addClass('selected').siblings().removeClass('selected');
            document.getElementById("rowIndexDL").value = row_index;
        });
        $('#DeleteSelection').on('click', function(e){
            e.stopPropagation();
            if (($('.selected').length) === 0){
                alert("Please select an element.");
            }else{
                window.location.href = "/AdminApprovedDelServlet";
            }
        });
        $('#Back').on('click', function () {
            window.location.href = "/adminFunctionality.jsp";
        })
        $(document).click(function() {
            $('#example .line').removeClass('selected');
            document.getElementById("rowIndexDL").value = -1;
        });
    });
    
    function get() {
        var ItemName = document.getElementById("ItemName").value;
        var Select = document.getElementById("TypeSelect");
        var index = Select.selectedIndex;
        var SelectType = Select.options[index].value;
        document.getElementById("ItemNameText").value = ItemName;
        document.getElementById("ItemTypeText").value = SelectType;

        var SearchName = document.getElementById("SearchName").value;
        var SearchSelect = document.getElementById("SearchSelect");
        var Searchindex = SearchSelect.selectedIndex;
        var SearchType = SearchSelect.options[Searchindex].value;
        document.getElementById("SearchNameText").value = SearchName;
        document.getElementById("SearchTypeText").value = SearchType;
    }
</script>
</body>
</html>