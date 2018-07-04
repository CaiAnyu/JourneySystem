<%-- 
    Document   : sightlist
    Created on : 2018-7-1, 21:03:42
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
        <h1>产品列表</h1>
        <%=session.getAttribute("list")%>
        <FORM METHOD="post" ACTION=CtrlServlet?method=touserindex>    
            <input type="submit" name="a" value="返回个人主页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
    </body>
</html>
