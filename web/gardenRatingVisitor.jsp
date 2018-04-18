<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Owner Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<h3>Georgia Tech Garden Details</h3>
<form action="" method="post" id="gardenDetails">
    <table>
        <tr>
            <td><b>Name: </b></td>
            <td id="name">Georgia Tech Garden</td>
        </tr>

        <tr>
            <td><b>Owner: </b></td>
            <td id="owner">GeorgiaTechGardens</td>
        </tr>

        <tr>
            <td><b>Owner Email: </b></td>
            <td id="ownerEmail">gtgardens@gatech.edu</td>
        </tr>
        <tr>
            <td><b>Visits: </b></td>
            <td id="visits">20</td>
        </tr>

        <tr>
            <td><b>Address: </b></td>
            <td id="address">Spring Street Southwest</td>
        </tr>

        <tr>
            <td><b>City: </b></td>
            <td id="city">Atlanta</td>
        </tr>

        <tr>
            <td><b>Zip: </b></td>
            <td id="zip">30309</td>
        </tr>
        <tr>
            <td><b>Size(acres): </b></td>
            <td id="size">0.5</td>
        </tr>
        <tr>
            <td><b><font color="red">Avg Rating: </font>  </b></td>
            <td id="avgRating"><font color="red">3.5</font></td>
        </tr>
        <tr>
            <td><b>Type: </b></td>
            <td id="type">Garden</td>
        </tr>

        <tr>
            <td><b>Public: </b></td>
            <td id="public">true</td>
        </tr>

        <tr>
            <td><b>Commercial: </b></td>
            <td id="commercial">False</td>
        </tr>

        <tr>
            <td><b>ID: </b></td>
            <td id="id">00320</td>
        </tr>
        <tr>
            <td><b>Crops: </b></td>
            <td id="crops">Roses, Tulips, Sage</td>
        </tr>

        <tr>
            <td>
                <button formaction="gardenRating.jsp">Un-Log Visit</button>
            </td>
            <td>
                <button formaction="visitorCenter.jsp">Back</button>
            </td>
        </tr>


    </table>
</form>

</body>
</html>