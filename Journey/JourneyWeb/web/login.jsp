<%-- 
    Document   : login
    Created on : 2018-7-1, 20:45:57
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>登录</h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=login>    
            用户名<input type="text" name="username" value="wwwww"><p>
            密码  <input type="password" name="password" value="11111111"><p>
            <INPUT type="radio" name="r" value="1">管理员<p>
            <INPUT type="radio" name="r" value="2">用户<p>
            <input type="submit" name="s1" value="登录">

        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=logintoregister>    
            <input type="submit" name="s2" value="注册">
        </FORM>
        <%if (session.getAttribute("loginfalse")!=null) if (session.getAttribute("loginfalse").equals("false")) {out.println("<p>登录失败<p>");session.setAttribute("loginfalse", "true");}%>
        
    </body>
</html>
