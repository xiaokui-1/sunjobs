<%@ page import="java.util.Map" %>
<%@ page import="com.pojo.UserInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xiaokui
  Date: 2020/7/20
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Map map = (Map)request.getAttribute("map");
    List<UserInfo> list = (List<UserInfo>) map.get("list");
%>

<%for(UserInfo userinfo : list){%>
<%=userinfo.getUsername()%>
<%=userinfo.getPassword()%><a href="user.do?p=deletebyusername&username=<%=userinfo.getUsername()%>">删除</a><br>
<%}%>


<a href="user.do?p=fenye&page=1&size=<%=map.get("size")%>">首页</a>
<a href="user.do?p=fenye&page=<%= (Integer)map.get("page")-1 %>&size=<%=map.get("size")%>">上一页</a>
<a href="user.do?p=fenye&page=<%= (Integer)map.get("page")+1 %>&size=<%=map.get("size")%>">下一页</a>
<a href="user.do?p=fenye&page=<%=map.get("pagemax")%>&size=<%=map.get("size")%>">尾页</a>
</body>
</html>
