<%-- 
    Document   : changeproduct
    Created on : 2018-7-4, 19:23:14
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
        <h1>Hello World!</h1>
        <%=session.getAttribute("changeproductinf")%>
          <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=toadminindex>    
            <input type="submit" name="a" value="返回管理中心">
        </FORM>
    </body>
</html>
