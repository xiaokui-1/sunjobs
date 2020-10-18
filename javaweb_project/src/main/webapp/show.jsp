<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<style>

    #bigDiv{
        margin: auto ;
        width: 1000px;
        height: 300px;
        border: 1px solid red;

    }

    .smallDiv{
        width: 200px;
        height: 200px;
        border: 1px solid blue;
        float: left;
        margin-left: 35px;
        margin-top: 40px;
        text-align: center;


    }

</style>


<head>
    <title>淘宝商城</title>
</head>
<body>
<c:if test="${empty userinfo}" var="f">
    <a href="login.jsp">登录</a>| <a>注册</a>
</c:if>

<c:if test="${not f}">
    欢迎:${userinfo.username}<a href="showCar.jsp">购物车</a><a href="">收藏夹</a>
</c:if>
<div style="width: 1000px;margin:0px auto">
    商品名：<input type="text" name="ming">
    价格范围：<input type="text" name="di">-<input type="text" name="gao">
    <input type="button" value="查找">
</div>
<div id="bigDiv">
    <c:forEach items="${map.list}" var="goods">
        <div class="smallDiv">
                ${goods.goodsid}
                ${goods.goodsname}<br>
            <a href="goods.do?p=findbyid&goodsid=${goods.goodsid}">
                <img title="查看详情" style="width: 150px;height: 150px" src="image/${goods.goodspic}">
            </a>
        </div>
    </c:forEach>
</div>
<div style="text-align: center">
共${map.count}条，当前${map.page}/${map.pageCount}页
<a href="goods.do?p=fenye&page=1&size=${map.size}">首页</a>
<a href="goods.do?p=fenye&page=${map.page-1}&size=${map.size}">上一页</a>
<a href="goods.do?p=fenye&page=1&size=${map.size}">1</a>
<a href="goods.do?p=fenye&page=${map.pageCount}&size=${map.size}">2</a>
<a href="goods.do?p=fenye&page=${map.page+1}&size=${map.size}">下一页</a>
<a href="goods.do?p=fenye&page=${map.pageCount}&size=${map.size}">尾页</a>
</div>
</body>
</html>
