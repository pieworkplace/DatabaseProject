<%--
  Created by IntelliJ IDEA.
  User: ysy
  Date: 4/11/18
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<% String ownerName = "Owner1"; %>
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

<h3> </h3>
All Other Valid Properties:

<table border="1" id="example">
    <tr id="head">
        <th>Name<input type="submit" value="↓"></th>
        <th>Address</th>
        <th>City<input type="submit" value="↓"></th>
        <th>Zip</th>
        <th>Size</th>
        <th>Type<input type="submit" value="↓"></th>
        <th>Public<input type="submit" value="↓"></th>
        <th>Commercial</th>
        <th>ID</th>
        <th>Visits<input type="submit" value="↓"></th>
        <th>Avg. Rating <input type="submit" value="↓"></th>
    </tr>
    <tr class="line">
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
    </tr>
    <tr class="line">
        <td>Y</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
        <td>X</td>
    </tr>
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
        <td><button id="test" formaction="farmDetail.jsp">View Property Details</button></td>
    </tr>
    <tr>
        <td><button formaction="">Search Properties</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><button formaction="ownerCenter.jsp">Back</button></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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