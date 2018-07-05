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
         <div style="width: 100%;height:30px">
            <div style="width: 90%;float:left">
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="s2" value="个人中心">
        </FORM>   
             </div>
            <div style="float:right">
            <FORM METHOD="post" ACTION=CtrlServlet?method=touserindex>    
            <input type="submit" name="a" value="返回主页">
              </FORM>
            </div>
        </div>
        <hr>
        
        <h1>产品列表</h1>
        <%=session.getAttribute("list")%>
     
    </body>
</html>
