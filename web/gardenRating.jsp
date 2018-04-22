<%@ page import="database.classes.User" %>
<%@ page import="java.util.List" %>
<%@ page import="database.classes.Property" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    Property property = (Property) request.getSession().getAttribute("Ireallyhatethis");
    String propertyName = property.getName();
    String email = (String) request.getSession().getAttribute("ownerEmail");
    int visits = (int) request.getSession().getAttribute("visits");
    List<Double> ratings = (List<Double>) request.getSession().getAttribute("ratings");
    double total = 0;
    for (int i = 0; i < ratings.size(); i++) {
        total += ratings.get(i);
    }
    double average = total / ratings.size();

    List<String> items= (List<String>) request.getSession().getAttribute("items");
%>
<head>
    <title>Owner Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<h3><% out.print(propertyName);%> Details</h3>
<form action="/VisitServlet" method="post" id="gardenDetails">
    <table>
        <tr>
            <td><b>Name: </b></td>
            <td id="name"><% out.print(property.getName()); %></td>
        </tr>

        <tr>
            <td><b>Owner: </b></td>
            <td><% out.print(property.getOwner()); %></td>
        </tr>

        <tr>
            <td><b>Owner Email: </b></td>
            <td id="ownerEmail"> <% out.print(email); %> </td>
        </tr>
        <tr>
            <td><b>Visits: </b></td>
            <td id="visits"> <% out.print(visits); %> </td>
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
            <td id="zip"><% out.print(property.getZip()); %></td>
        </tr>
        <tr>
            <td><b>Size(acres): </b></td>
            <td id="size"><% out.print(property.getSize()); %></td>
        </tr>
        <tr>
            <td><b><font color="red">Avg Rating: </font> </b></td>
            <td id="avgRating"><% out.print(String.format( "%.2f", average )); %></td>
        </tr>
        <tr>
            <td><b>Type: </b></td>
            <td id="type"><% out.print(property.getPropertyType()); %></td>
        </tr>

        <tr>
            <td><b>Public: </b></td>
            <td id="public"><% out.print((property.isPublic())? "True":"False"); %></td>
        </tr>

        <tr>
            <td><b>Commercial: </b></td>
            <td id="commercial"><% out.print((property.isCommercial())? "True":"False"); %></td>
        </tr>

        <tr>
            <td><b>ID: </b></td>
            <td><input name="id" type="hidden" value="<% out.print(property.getID()); %>"><% out.print(property.getID()); %></td>
        </tr>
        <tr>
            <td><b>Crops: </b></td>
            <td id="crops"><% for (int i = 0; i < items.size(); i++) {
                out.print(items.get(i));
                if (i != items.size() - 1) {
                    out.print(", ");
                }
            }%></td>
        </tr>

        <tr>
            <td><b>Rate Your Visit: </b></td>
            <td><select name = "rate" >
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select></td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Log Visit">
            </td>
            <td>
                <button formaction="visitorCenter.jsp">Back</button>
            </td>
        </tr>


    </table>
</form>

</body>
</html>