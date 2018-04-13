<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: ysy
  Date: 4/12/18
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
%>
<head>
    <title>Title</title>
</head>
<body>
<%!
    String name = "GT Farm";
    String owner = "SY";
    String email = "ysy@gatech.edu";
    int visits = 100;
    String address = "100midtown";
    String city = "ATL";
    String zip = "30309";
    double size = 10.00;
    double avgrate = 2.7;
    String type = "Farm";
    boolean ispublic = true;
    boolean iscommercial = true;
    String id = "123455";
    String crop = "corn";
    String animal = "pig";

    void printpropdetail(JspWriter out) throws IOException {
        out.println("Name: " + name + "<br>");
        out.println("Owner: " + owner + "<br>");
        out.println("Owner Email: " + email + "<br>");
        out.println("Visits: " + visits + "<br>");
        out.println("Address: " + address + "<br>");
        out.println("City: " + city + "<br>");
        out.println("Zip: " + zip + "<br>");
        out.println("Size (acres): " + size + "<br>");
        out.println("Avg.Rating: " + avgrate + "<br>");
        out.println("Type: " + type + "<br>");
        out.println("Public: " + ispublic + "<br>");
        out.println("Commercial: " + iscommercial + "<br>");
        out.println("ID: " + id + "<br>");
        out.println("<br>");
        out.println("<br>");
        out.println("Crops: " + crop + "<br>");
        out.println("Animals: " + iscommercial + "<br>");

    }
%>
  <h1>Property Detail</h1>
  <br>
    <% printpropdetail(out); %>
  <br>
<tr>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><button formaction="">Back</button></td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</body>
</html>
