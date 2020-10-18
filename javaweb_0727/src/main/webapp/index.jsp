<%@page pageEncoding="utf-8" %>
<html>
<body>
<form method="post" action="">
    用户名：<input type="text" name="username" id="username" onblur="check()"><span id="flag"></span><br>
    密码：<input type="password" name="password" id="password" ><br>
</form>
</body>
</html>
<script>
    function check() {
        var requset =new XMLHttpRequest();
        requset.onreadystatechange=function () {
            if (requset.readyState==4 && requset.status==200){
                var ret=requset.responseText;
                alert(ret);
                if (ret==2){
                    document.getElementById("flag").innerHTML="√";
                }else {
                    document.getElementById("flag").innerHTML="×";
                }
            }
        }
        var username=document.getElementById("username").value;
        requset.open("get","test.do?username="+username,false);
        requset.send(null);


    }
</script>
