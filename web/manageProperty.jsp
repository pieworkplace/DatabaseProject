<%--
  Created by IntelliJ IDEA.
  User: guowanyang
  Date: 4/10/18
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>manageProperty</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
    <h3><u>Manage East Lake Urban Farm</u></h3>
    <form action="" method="post" id="adminFunctionality">
        <table id="commdity-table">
            <tr>
                <td>Name:<input type="text" value="East Lake Urban Farm"></td>
                <td>Type:Farm</td>
            </tr>
            <tr>
                <td>Address:<input type="text" value="2nd Avenue"></td>
                <td>Public:<select id="WhetherPublic">
                    <option value="False">False</option>
                    <option value="True">True</option>
                </select></td>
            </tr>
            <tr>
                <td>City:<input type="text" value="Atlanta"></td>
                <td>Public:<select id="WhetherCommercial">
                    <option value="True">True</option>
                    <option value="False">False</option>
                </select></td>
            </tr>
            <tr>
                <td>Zip:<input type="text" value="30313"></td>
                <td>ID:00237</td>
            </tr>
            <tr>
                <td>Size(acres):<input type="text" value="20"></td>
            </tr>
            <tr>
                <td class="td_animals">Animals:</td>
                <td class="td_crops">Crops:</td>
            </tr>
            <tr>
                <td>Add new Animal:<select id="AnimalType">
                    <option value="Select approved animal...">Select approved animal...</option>
                    <option value="Chickens">Chickens</option>
                </select></td>
                <td>Crops:<select id="CropType">
                    <option value="Select approved crop...">Select approved crop...</option>
                    <option value="Broccoli">Broccoli</option>
                    <option value="Apples">Apples</option>
                    <option value="Peppers">Peppers</option>
                    <option value="Roses">Roses</option>
                </select></td>
            </tr>
            <tr>
                <td><input type="button" value="Add Animal to Property" id="animals_button" /></td>
                <td><input type="button" value="Add Crop to Property" id="crops_button" /></td>
            </tr>
            <tr>
                <td><button><b>Save Changes</b><br />(confirm property)</button></td>
            </tr>
            <tr>
                <td><button><b>Back</b><br />(Don't Save or Confirm)</button></td>
            </tr>
            <tr>
                <td><button><b><font color="red">Delete Property</font></b></button></td>
            </tr>
        </table>
    </form>
</body>
<script>
    $(function(){
        $("#animals_button").click(function(){
            var i = $('#AnimalType').val();
            $('<input type="button"/>').val(i).click(function(){
                $(this).remove();
            }).appendTo('td.td_animals');
        });
    });
    $(function(){
        $("#crops_button").click(function(){
            var i = $('#CropType').val();
            $('<input type="button"/>').val(i).click(function(){
                $(this).remove();
            }).appendTo('td.td_crops');
        });
    });
</script>
</html>
