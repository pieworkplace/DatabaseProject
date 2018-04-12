<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Owner Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<h3>Manage Garden</h3>
<form action="" method="post" id="ownerRegisterForm">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"></td>
            <td>Type:</td>
            <td>Garden</td> <%--TODO--%>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input type="text" name="address"></td>
            <td>Public:</td>
            <td><select id = "public">
                <option value=true>True</option>
                <option value=false>False</option>
            </select></td>
        </tr>

        <tr>
            <td>City:</td>
            <td><input type="text" name="city"></td>
            <td>Commercial:</td>
            <td><select id = "commercial">
                <option value=true>True</option>
                <option value=false>False</option>
            </select></td>
        </tr>

        <tr>
            <td>Zip:</td>
            <td><input type = "text" name = "zip"></td>
            <td>ID:</td>
            <td>00237</td> <%--TODO--%>
        </tr>

        <tr>
            <td>Size:</td>
            <td><input type = "text" name = "size"></td>
        </tr>

        <tr>
            <td>Add New Crop:<select id = "newcrop">
                <option value="select">Select a Crop to Add</option>
                <option value="rose">Rose</option>
                <option value="sunflower">Sunflower</option>
                <option value="cucumber">Cucumer</option>
                <option value="eggplant">Eggplant</option>
                <option value="tomato">Tomato</option>
            </select></td>
            <td class="crops">Crops:</td>
        </tr>


        <tr>
            <td><input type="button" value="Add a New Crop" id="addNewCrop"></td>
        </tr>

        <tr>
            <td>Request Corp Approval:</td>
            <td>
                <input type="text" name="request" placeholder="Enter new corp name">
            </td>
            <td><select id = "crop_type">
                <option value="New Crop Type">New Crop Type</option>
                <option value="vegetables">Vegetables</option>
                <option value="flowers">Flowers</option>
            </select></td>
            <td>
                <button>Submit Request</button>
            </td>
        </tr>

        <tr>
            <td>
                <button formaction=""><b><font color="red">Delete Property</font></b></button>
            </td>
            <td>
                <button>Save Changes</button>
            </td>
            <td>
                <button>Back (Don't Save)</button>
            </td>
        </tr>

    </table>
</form>

</body>

<script>
    $(function(){
        $("#addNewCrop").click(function(){
            var i = $('#newcrop').val();
            $('<input type="button"/>').val(i).click(function(){
                $(this).remove();
            }).appendTo("td.crops");
        });
    });

</script>

</html>