<%-- 
    Document   : register
    Created on : 2018-7-1, 21:04:46
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
                #header {
            background-color:orange;
            color:black;
            text-align:center;
            padding:10px 100px 10px 100px;
             }
        </style>
    </head>
    <body>
        
        <div id="header"><h1>注册</h1></div>
         <hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
         <div style="width: 300px; height: 500px;margin: 0 auto;">
        <br />
        <br />
        <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=register>    
            用户名<input type="text" name="username" value=''><p>
            密码<input type="password" name="password" value=''><p>
            再确认<input type="password" name="repassword" value=''><p>
            <input type="submit" name="s1" value="注册">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <%if (session.getAttribute("registerfalse")!=null) if (session.getAttribute("registerfalse").equals("false")) {out.println("<p>注册失败<p>");session.setAttribute("registerfalse", "true");}%>
        </div>
    </body>
</html>
