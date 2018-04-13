<%--
  Created by IntelliJ IDEA.
  User: guowanyang
  Date: 4/10/18
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Property</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
    <h3>Add New Property</h3>
    <form action="" method="post" id="addnewPropertyForm">
        <table>
            <tr>
                <td>Property Name*</td>
                <td><input type="text" name="Property Name"></td>
            </tr>
            <tr>
                <td>Street Address*</td>
                <td><input type="password" name="Street Address"></td>
            </tr>
            <tr>
                <td>City*</td>
                <td><input type="text" name="City"></td>
                <td>Zip*</td>
                <td><input type="text" name="Zip"></td>
                <td>Acres*</td>
                <td><input type="text" name="Acres"></td>
            </tr>
            <tr>
                <td>Property Type*</td>
                <td><select id="AddpropertyType">
                    <option value="farm">Farm</option>
                    <option value="garden">Garden</option>
                    <option value="orchard">Orchard</option>
                </select></td>
                <td id="AddanimalText">Animal*</td>
                <td><select id="AddanimalSelect">
                    <option value="我好帅">我好帅</option>
                    <option value="看啥呢">看啥呢</option>
                    <%--<option value="你">你</option>--%>
                    <%--<option value="帅">帅</option>--%>
                </select></td>
                <td>Crop*</td>
                <td><select id="AddcropSelect">
                    <option value="fruit">Fruit</option>
                    <option value="nut">Nut</option>
                    <option value="flower">Flower</option>
                    <option value="vegetable">Vegetable</option>
                </select></td>
            </tr>
            <tr>
                <td>Public?*</td>
                <td><select id="PublicSelect">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select></td>
            </tr>
            <tr>
                <td>Commercial?*</td>
                <td><select id="CommercialSelect">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Add Property" id="addnewPropertySubmitButton">
                    <button formaction="login.jsp">Cancel</button>
                </td>
            </tr>
        </table>
    </form>
    <script>
        $("#addnewPropertySubmitButton").on("click", function(e) {
            e.preventDefault();
        });
        $('#AddpropertyType').on('change', function(){
            $('#AddcropSelect').html('');
            if($('#AddpropertyType').val()=="farm"){
                $('#AddanimalSelect').css('visibility', 'visible');
                $('#AddanimalText').css('visibility', 'visible');
                $('#AddcropSelect').append('<option value="fruit">Fruit</option>\n' +
                    '                    <option value="nut">Nut</option>\n' +
                    '                    <option value="flower">Flower</option>\n' +
                    '                    <option value="vegetable">Vegetable</option>');
            };
            if($('#AddpropertyType').val()=="orchard"){
                $('#AddanimalSelect').css('visibility', 'hidden');
                $('#AddanimalText').css('visibility', 'hidden');
                $('#AddcropSelect').append('<option value="fruit">Fruit</option>\n' +
                    '                    <option value="nut">Nut</option>');
            };
            if($('#AddpropertyType').val()=="garden"){
                $('#AddanimalSelect').css('visibility', 'hidden');
                $('#AddanimalText').css('visibility', 'hidden');
                $('#AddcropSelect').append('<option value="flower">Flower</option>\n' +
                    '                    <option value="vegetable">Vegetable</option>');
            };
        });
    </script>
</body>
</html>
