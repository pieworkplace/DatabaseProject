<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: JunlinLiu
  Date: 2018/4/10
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>var nums = [1,2,3];</script>
<head>
    <title>Title</title>
</head>
<body>
<select id="test">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
</select>
<input type="button" class="addbtn" value="add"/>
<div id="lalala">

</div>
</body>
<script>
    $('.addbtn').on('click', function(){
        var i = $('#test').val();
        $('#lalala').append($('<input type="button"/>').val(i).addClass('btn').on('click', function () {
            $(this).remove();
        }));
    });

    </script>
</html>
