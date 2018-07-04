<%-- 
    Document   : login
    Created on : 2018-7-1, 20:45:57
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <title>JSP Page</title>
         <style>
      .left {
        float: left;
        width: 300px;
        height: 300px;
        background-color: white;
      }
      .right {
        background-color: orange;
        margin-left: 810px;
        height: 500px;
        border: 1px solid #000000
      }
    </style>
    </head>
    <body>
        <h1 style="color:black">自助旅游网站</h1>
            <hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
            <div class="right">
            <div style="margin:0 auto;width:100px">
                <br />
                <br />
                <br />
        <h1>登录</h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=login>    
            用户名<input type="text" name="username" value="user3"><p>
            密码  <input type="password" name="password" value="33333333"><p>
            <INPUT type="radio" name="r" value="1" checked="checked">管理员<p>
            <INPUT type="radio" name="r" value="2" >用户<p>
            <input type="submit" name="s1" value="登录">

        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=logintoregister>    
            <input type="submit" name="s2" value="注册">
        </FORM>
        <%if (session.getAttribute("loginfalse")!=null) if (session.getAttribute("loginfalse").equals("false")) {out.println("<p>登录失败<p>");session.setAttribute("loginfalse", "true");}%>
            </div>
            </div>  
    </body>
</html>
