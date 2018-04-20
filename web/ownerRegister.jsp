<%@ page import="database.classes.FarmItem" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Owner Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
    <h3>New Owner Registration</h3>
    <form action="/OwnerRegisterServlet" method="post" id="ownerRegisterForm">
        <table>
            <tr>
                <td>Email*</td>
                <td><input type="email" name="email" pattern="[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,4}$" required></td>
            </tr>
            <tr>
                <td>Username*</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>Password*</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>Confirm Password*</td>
                <td><input type="password" name="passwordAgain" required></td>
            </tr>
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
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                </select></td>
            </tr>
            <tr>
                <td>Commercial?*</td>
                <td><select id="isCommercial" name="isCommercial">
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                </select></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Register" id="ownerRegisterSubmitButton">
                    <button formaction="login.jsp" formnovalidate>Cancel</button>
                </td>
            </tr>
        </table>
    </form>
        <% Object bool = request.getSession().getAttribute("OwnerRegfail");
            if (bool != null){
                if (((int)bool) == 1){%>
        <p style="color: red">Email or password has been used. Please try another one.</p>
        <%} else if (((int)bool) == 3){%>
        <p style="color: red">Password and confirm password must match.</p>
        <%} else if (((int)bool) == 4){%>
        <p style="color: red">Password must have at least 8 characters.</p>
        <%}}
            request.getSession().setAttribute("OwnerRegfail", 0);
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