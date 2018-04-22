<%@ page import="database.classes.User" %>
<%@ page import="database.classes.Property" %>
<%@ page import="database.classes.FarmItem" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Property</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<%
    Property property = (Property) request.getSession().getAttribute("ownerpropertyselected");
    User user = (User) request.getSession().getAttribute("user");
%>
<h3>Manage <%out.print(property.getName());%></h3>
<form action="/OwnerUpdateServlet" method="post">
    <table>
        <tr>
            <td>Name:<input name="propertyName" type="text" value="<%out.print(property.getName());%>" required/></td>
            <td>Type:
                <%
            if (property.getPropertyType() == Property.PropertyType.FARM){
            out.print("Farm");
            } else if (property.getPropertyType() == Property.PropertyType.ORCHARD){
            out.print("Orchard");
            } else{
            out.print("Garden");
            }%><p></td>
        </tr>
        <tr>
            <td>Address:<input name="streetAddress" type="text" value="<%out.print(property.getStreet());%>" required></td>
            <td>Public:<select name="isPublic">
                <option value="True">True</option>
                <option value="False" <%out.print(property.isPublic()?"":"selected");%>>False</option>
            </select></td>
        </tr>
        <tr>
            <td>City:<input name="city" type="text" value="<%out.print(property.getCity());%>" required></td>
            <td>Commercial:<select name="isCommercial">
                <option value="True">True</option>
                <option value="False" <%out.print(property.isCommercial()?"":"selected");%>>False</option>
            </select></td>
        </tr>
        <tr>
            <td>Zip:<input name="zip" type="number" value="<%out.print(property.getZip());%>" min="10000" max="99999" required></td>
            <td>ID:<%out.print(property.getID());%></td>
        </tr>
        <tr>
            <td>Size(acres):<input name="acres" type="number" value="<%out.print(property.getSize());%>" step="0.01" min="0" required></td>
        </tr>
        <tr>
            <td>Add new Crop/Animal:<select id="CropType">
                <% List<FarmItem> farmItemList4 = new ArrayList<>();
                    if (property.getPropertyType() == Property.PropertyType.FARM){
                        List<FarmItem> farmItemList3 = UserService.getAnimalList();
                        for (FarmItem farmItem : farmItemList3){%>
                            <option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option>
                        <%}
                        farmItemList4 = UserService.getCropList();
                } else if (property.getPropertyType() == Property.PropertyType.ORCHARD){
                        farmItemList4 = UserService.getOrchardList();
                } else{
                        farmItemList4 = UserService.getGardenList();
                }
                    for (FarmItem farmItem : farmItemList4){ %>
                <option value="<% out.print(farmItem.getName());%>"><% out.print(farmItem.getName());%></option><%}%>
            </select></td>
            <td class="td_crops">Crops/Animals:
                <% List<FarmItem> farmItemList = UserService.getItemsInProperty(property.getID());
                    for (FarmItem farmItem : farmItemList){%>
                        <input class="removable" type="button" value="<%out.print(farmItem.getName());%>"/>
                    <%}%>
            </td>
        </tr>
        <tr>
            <td><input type="button" value="Add to Property" id="crops_button" /></td>
        </tr>
        <tr>
            <td>Request Crop/Animal Approval:<input type="text" name="request" placeholder="Enter new crop/animal name"></td><td>
                <select id = "crop_type">
                    <% if (property.getPropertyType() == Property.PropertyType.FARM){%>
                        <option value="ANIMAL">Animal</option>
                        <option value="FLOWER">Flower</option>
                        <option value="FRUIT">Fruit</option>
                        <option value="NUT">Nut</option>
                        <option value="VEGETABLE">Vegetable</option>
                    <%}else if (property.getPropertyType() == Property.PropertyType.GARDEN){%>
                        <option value="FLOWER">Flower</option>
                        <option value="VEGETABLE">Vegetable</option>
                    <%} else{%>
                            <option value="FRUIT">Fruit</option>
                            <option value="NUT">Nut</option>
                    <%}%>
            </select></td>
        <tr><td><button>Submit Request</button></td>></tr>
        </tr>
        <tr>
            <td><button type="submit"><b>Save Changes</b><br />(confirm property)</button></td>
            <td><button formaction="ownerCenter.jsp" formnovalidate><b>Back</b><br />(Don't Save or Confirm)</button></td>
        </tr>
        <tr>
            <td><button formaction="/OwnerDeleteServlet" formnovalidate><b><font color="red">Delete Property</font></b></button></td>
        </tr>
    </table>
</form>

<%
    Object o = request.getSession().getAttribute("updatePropertyFail");
    if (o != null && (boolean)o){%>
        <p style="color: red">Property Name already exists.</p>
    <%
            request.getSession().setAttribute("updatePropertyFail", false);
    }
%>

</body>
<script>
    $(function(){
        $("#crops_button").click(function(){
            var i = $('#CropType').val();
            var flag = 0;
            $( ".removable" ).each(function( index ) {
                if (i === $(this).val()){
                    alert("This item already exists in this property.");
                    flag = 1;
                    return false;
                }
            });
            if (flag === 0){
                $('<input type="button" class="removable"/>').val(i).click(function(){
                    $(this).remove();
                }).appendTo('td.td_crops');
            }
        });
    });
    $('.removable').on('click', function () {
        $(this).remove();
    })
</script>
</html>
