<%@ page import="database.JDBC.DBConnectionUtil" %>
<%@ page import="database.JDBC.DataProcessor" %>
<%@ page import="java.io.IOException" %>
<%@ page import="database.JDBC.SQLPreparator" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: JunlinLiu
  Date: 2018/4/10
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<%
    final StringBuilder result = new StringBuilder();
    DataProcessor dataProcessor = new DataProcessor() {
        @Override
        public void processData(ResultSet resultSet) throws SQLException {
            if (resultSet != null){
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    result.append(name);
                    result.append(" ");
                    int ID = resultSet.getInt("ID");
                    result.append(ID);
                    result.append(" ");
                }
            }
        }
    };

    DBConnectionUtil.select("select * from example", dataProcessor);
    out.print(result.toString());
%>
<%
    DBConnectionUtil.update("delete from example where ID=1");
%>
<%
    result .setLength(0);
    DBConnectionUtil.preSelect("select * from example limit ?, ?", new SQLPreparator() {
        @Override
        public void prepareSQL(PreparedStatement preparedStatement) {
            try {
                preparedStatement.setInt(1, 0);
                preparedStatement.setInt(2, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }, dataProcessor);
    out.print(result);
%>
</body>
</html>
