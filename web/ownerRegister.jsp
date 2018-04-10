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
    <form action="" method="post" id="ownerRegisterForm">
        <table>
            <tr>
                <td>Email*</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Username*</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password*</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Confirm Password*</td>
                <td><input type="password" name="passwordAgain"></td>
            </tr>
            <tr>
                <td>Property Name*</td>
                <td><input type="text" name="propertyName"></td>
            </tr>
            <tr>
                <td>Street Address*</td>
                <td><input type="text" name="streetAddress"></td>
            </tr>
            <tr>
                <td>City*</td>
                <td><input type="text" name="city"></td>
                <td>Zip*</td>
                <td><input type="text" name="zip"></td>
                <td>Acres*</td>
                <td><input type="text" name="acres"></td>
            </tr>
            <tr>
                <td>Property Type*</td>
                <td><select id="propertyType">
                    <option value="farm">Farm</option>
                    <option value="garden">Garden</option>
                    <option value="orchard">Orchard</option>
                </select></td>
                <td id="animalText">Animal*</td>
                <td><select id="animalSelect">
                </select></td>
                <td>Crop*</td>
                <td><select id="cropSelect">
                    <option value="fruit">Fruit</option>
                    <option value="nut">Nut</option>
                    <option value="flower">Flower</option>
                    <option value="vegetable">Vegetable</option>
                </select></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Register" id="ownerRegisterSubmitButton">
                    <button formaction="login.jsp">Cancel</button>
                </td>
            </tr>
        </table>
    </form>
    <script>
        $("#ownerRegisterSubmitButton").on("click", function(e) {
            e.preventDefault();
        });
        $('#propertyType').on('change', function(){
            $('#cropSelect').html('');
            if($('#propertyType').val()=="farm"){
                $('#animalSelect').css('visibility', 'visible');
                $('#animalText').css('visibility', 'visible');
                $('#cropSelect').append('<option value="fruit">Fruit</option>\n' +
                    '                    <option value="nut">Nut</option>\n' +
                    '                    <option value="flower">Flower</option>\n' +
                    '                    <option value="vegetable">Vegetable</option>');
            };
            if($('#propertyType').val()=="orchard"){
                $('#animalSelect').css('visibility', 'hidden');
                $('#animalText').css('visibility', 'hidden');
                $('#cropSelect').append('<option value="fruit">Fruit</option>\n' +
                    '                    <option value="nut">Nut</option>');
            };
            if($('#propertyType').val()=="garden"){
                $('#animalSelect').css('visibility', 'hidden');
                $('#animalText').css('visibility', 'hidden');
                $('#cropSelect').append('<option value="flower">Flower</option>\n' +
                    '                    <option value="vegetable">Vegetable</option>');
            };
        });
    </script>
</body>
</html>