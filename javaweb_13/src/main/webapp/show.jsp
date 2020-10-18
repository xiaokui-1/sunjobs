<%@ page import="java.util.List" %>
<%@ page import="com.pojo.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: xiaokui
  Date: 2020/7/17
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        List<UserInfo> list = (List<UserInfo>) request.getAttribute("list");
    %>

    <%for (UserInfo userInfo : list) {%>
        <%= userInfo.getUsername()%>
        <%= userInfo.getPassword()%>
    <%} %>

</body>
</html>
