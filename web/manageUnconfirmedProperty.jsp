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
    Property property = (Property) request.getSession().getAttribute("unconfirmedpropertyselected");
    User user = (User) request.getSession().getAttribute("user");
%>
<h3>Manage <%out.print(property.getName());%></h3>
<form action="/AdminUpdateUnconfirmedServlet" method="post">
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
                <input class="removable" type="hidden" name="<%out.print(farmItem.getName());%>" value="<%out.print(farmItem.getName());%>"/>
                <%}%>
            </td>
        </tr>
        <tr>
            <td><input type="button" value="Add to Property" id="crops_button" /></td>
        </tr>
        <tr>
            <td><button type="submit"><b>Save Changes</b><br />(confirm property)</button></td>
            <td><button formaction="unconfirmedProperties.jsp" formnovalidate><b>Back</b><br />(Don't Save or Confirm)</button></td>
        </tr>
        <tr>
            <td><button formaction="/AdminDeleteUnconfirmedServlet" formnovalidate><b><font color="red">Delete Property</font></b></button></td>
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
                $('<input type="hidden" class="removable"/>').attr("name", i).val(i).appendTo('td.td_crops');
                $('<input type="button" class="removable"/>').val(i).click(function(){
                    // $(this).remove();
                    var vlu = $(this).val();
                    $( ".removable" ).each(function() {
                        if (vlu === $(this).val()){
                            $(this).remove();
                        }
                    });
                }).appendTo('td.td_crops');
            }
        });
    });
    $('.removable').on('click', function () {
        var vlu = $(this).val();
        $( ".removable" ).each(function() {
            if (vlu === $(this).val()){
                $(this).remove();
            }
        });

    })

    function get() {
        var SearchName = document.getElementById("request").value;
        var SearchSelect = document.getElementById("crop_type");
        var Searchindex = SearchSelect.selectedIndex;
        var SearchType = SearchSelect.options[Searchindex].value;
        document.getElementById("SearchText").value = SearchName;
        document.getElementById("SearchTypeText").value = SearchType;
    }
</script>
</html>



<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: guowanyang--%>
  <%--Date: 4/10/18--%>
  <%--Time: 10:12 PM--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Manage Property</title>--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
    <%--<h3><u>Manage East Lake Urban Farm</u></h3>--%>
    <%--<form action="" method="post" id="adminFunctionality">--%>
        <%--<table id="commdity-table">--%>
            <%--<tr>--%>
                <%--<td>Name:<input type="text" value="East Lake Urban Farm"></td>--%>
                <%--<td>Type:Farm</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Address:<input type="text" value="2nd Avenue"></td>--%>
                <%--<td>Public:<select id="WhetherPublic">--%>
                    <%--<option value="False">False</option>--%>
                    <%--<option value="True">True</option>--%>
                <%--</select></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>City:<input type="text" value="Atlanta"></td>--%>
                <%--<td>Public:<select id="WhetherCommercial">--%>
                    <%--<option value="True">True</option>--%>
                    <%--<option value="False">False</option>--%>
                <%--</select></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Zip:<input type="text" value="30313"></td>--%>
                <%--<td>ID:00237</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Size(acres):<input type="text" value="20"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td class="td_animals">Animals:</td>--%>
                <%--<td class="td_crops">Crops:</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Add new Animal:<select id="AnimalType">--%>
                    <%--<option value="Select approved animal...">Select approved animal...</option>--%>
                    <%--<option value="Chickens">Chickens</option>--%>
                <%--</select></td>--%>
                <%--<td>Crops:<select id="CropType">--%>
                    <%--<option value="Select approved crop...">Select approved crop...</option>--%>
                    <%--<option value="Broccoli">Broccoli</option>--%>
                    <%--<option value="Apples">Apples</option>--%>
                    <%--<option value="Peppers">Peppers</option>--%>
                    <%--<option value="Roses">Roses</option>--%>
                <%--</select></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="button" value="Add Animal to Property" id="animals_button" /></td>--%>
                <%--<td><input type="button" value="Add Crop to Property" id="crops_button" /></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><button><b>Save Changes</b><br />(confirm property)</button></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><button><b>Back</b><br />(Don't Save or Confirm)</button></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><button><b><font color="red">Delete Property</font></b></button></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form>--%>
<%--</body>--%>
<%--<script>--%>
    <%--$(function(){--%>
        <%--$("#animals_button").click(function(){--%>
            <%--var i = $('#AnimalType').val();--%>
            <%--$('<input type="button"/>').val(i).click(function(){--%>
                <%--$(this).remove();--%>
            <%--}).appendTo('td.td_animals');--%>
        <%--});--%>
    <%--});--%>
    <%--$(function(){--%>
        <%--$("#crops_button").click(function(){--%>
            <%--var i = $('#CropType').val();--%>
            <%--$('<input type="button"/>').val(i).click(function(){--%>
                <%--$(this).remove();--%>
            <%--}).appendTo('td.td_crops');--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
<%--</html>--%>
