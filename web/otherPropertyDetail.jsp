<%@ page import="java.io.IOException" %>
<%@ page import="database.classes.Property" %>
<%@ page import="database.classes.User" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: ysy
  Date: 4/12/18
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Property property = (Property) request.getSession().getAttribute("otherpropertyselected");
//    User user = (User) request.getSession().getAttribute("user");
%>
<head>
    <title>Property Detail</title>
</head>
<body>
  <h1><% out.print(property.getName()); %> Details</h1>
  <table>
      <tr>
          <td><b>Name: </b></td>
          <td id="name"><% out.print(property.getName()); %></td>
      </tr>

      <tr>
          <td><b>Owner: </b></td>
          <td id="owner"><% out.print(property.getOwner()); %></td>
      </tr>

      <tr>
          <td><b>Owner Email: </b></td>
          <td id="ownerEmail"> <%out.print(UserService.getOwnerEmail(property.getOwner()));%> </td>
      </tr>
      <tr>
          <td><b>Visits: </b></td>
          <td id="visits"> <%out.print(property.getNumberOfVisits());%> </td>
      </tr>

      <tr>
          <td><b>Address: </b></td>
          <td id="address"><% out.print(property.getStreet()); %></td>
      </tr>

      <tr>
          <td><b>City: </b></td>
          <td id="city"><% out.print(property.getCity()); %></td>
      </tr>

      <tr>
          <td><b>Zip: </b></td>
          <td id="zip"><%out.print(property.getZip());%></td>
      </tr>
      <tr>
          <td><b>Size(acres): </b></td>
          <td id="size"><%out.print(property.getSize());%></td>
      </tr>
      <tr>
          <td><b><font color="red">Avg Rating: </font> </b></td>
          <td id="avgRating"> <%out.print(property.getAvg_rating());%> </td>
      </tr>
      <tr>
          <td><b>Type: </b></td>
          <td id="type"><%out.print(property.getPropertyType());%></td>
      </tr>
      <tr>
          <td><b>Public: </b></td>
          <td id="public"><%out.print(property.isPublic()?"True":"False");%></td>
      </tr>

      <tr>
          <td><b>Commercial: </b></td>
          <td id="commercial"><%out.print(property.isCommercial()?"True":"False");%></td>
      </tr>

      <tr>
          <td><b>ID: </b></td>
          <td id="id"><%out.print(property.getID());%></td>
      </tr>
      <tr>
          <td><b>Crops: </b></td>
          <td id="crops"><%out.print(UserService.getCropsInProperty(property.getID()));%></td>
      </tr>
      <%if (property.getPropertyType() == Property.PropertyType.FARM){%>
            <tr><td><b>Animals: </b></td><td id="animals"><%out.print(UserService.getAnimalsInProperty(property.getID()));%></td></tr>
      <%}%>
      <tr>
          <td><form action="/propertyList.jsp" method="post">
              <input type="submit" value="Back">
          </form></td>
      </tr>
  </table>

</body>
</html>
