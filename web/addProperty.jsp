<%@ page import="database.classes.FarmItem" %>
<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %><%--
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
    <form method="post" action="/OwnerAddPropertyServlet">
        <table>
            <tr>
                <td>Property Name*</td>
                <td><input type="text" name="propertyName" required></td>
            </tr>
            <tr>
                <td>Street Address*</td>
                <td><input type="text" name="streetAddress" required></td>
            </tr>
            <tr>
                <td>City*</td>
                <td><input type="text" name="city" required></td>
                <td>Zip*</td>
                <td><input type="number" name="zip" min="10000" max="99999" required></td>
                <td>Acres*</td>
                <td><input type="number" name="acres" step="0.01" min="0" required></td>
            </tr>
            <tr>
                <td>Property Type*</td>
                <td><select id="propertyType" name="propertyType">
                    <option value="FARM">Farm</option>
                    <option value="GARDEN">Garden</option>
                    <option value="ORCHARD">Orchard</option>
                </select></td>
                <td id="animalText">Animal*</td>
                <td><select id="animalSelect" name="animalSelect">
                    <%
                        List<FarmItem> farmItemList1 = UserService.getAnimalList();
                        for (FarmItem farmItem : farmItemList1){%>
                    <option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option>
                    <%}
                    %>
                </select></td>
                <td>Crop*</td>
                <td><select id="cropSelect" name="cropSelect">
                    <%List<FarmItem> farmItemList5 = UserService.getCropList();for (FarmItem farmItem : farmItemList5){%>
                    <option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option><%}%>
                </select></td>
            </tr>
            <tr>
                <td>Public?*</td>
                <td><select id="isPublic" name="isPublic">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select></td>
            </tr>
            <tr>
                <td>Commercial?*</td>
                <td><select id="isCommercial" name="isCommercial">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Add Property">
                </td>
                <td><button formaction="/ownerCenter.jsp" formnovalidate>Cancel</button></td>
            </tr>
        </table>
    </form>
    <% Object bool = request.getSession().getAttribute("addNewPropertyFail");
        if (bool != null){
            if ((boolean)bool){%>
    <p style="color: red">Property name already exists.</p>
    <%}}
        request.getSession().setAttribute("addNewPropertyFail", false);
    %>
    <script>
        $("#ownerRegisterSubmitButton").on("click", function(e) {
            // e.preventDefault();
        });
        $('#propertyType').on('change', function(){
            $('#cropSelect').html('');
            if($('#propertyType').val()=="FARM"){
                $('#animalSelect').css('visibility', 'visible');
                $('#animalText').css('visibility', 'visible');
                $('#cropSelect').append('<%List<FarmItem> farmItemList4 = UserService.getCropList();for (FarmItem farmItem : farmItemList4){%>'+
                    '<option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option><%}%>');
            };
            if($('#propertyType').val()=="ORCHARD"){
                $('#animalSelect').css('visibility', 'hidden');
                $('#animalText').css('visibility', 'hidden');
                $('#cropSelect').append('<%List<FarmItem> farmItemList2 = UserService.getOrchardList();for (FarmItem farmItem : farmItemList2){%>'+
                    '<option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option><%}%>');
            };
            if($('#propertyType').val()=="GARDEN"){
                $('#animalSelect').css('visibility', 'hidden');
                $('#animalText').css('visibility', 'hidden');
                $('#cropSelect').append('<%List<FarmItem> farmItemList3 = UserService.getGardenList();for (FarmItem farmItem : farmItemList3){%>'+
                    '<option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option><%}%>');
            };
        });
    </script>
</body>
</html>
