<%-- 
    Document   : adminindex
    Created on : 2018-7-1, 21:05:43
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员</title>
    </head>
    <body>
        <h1>你好,<%=session.getAttribute("username")%></h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=manageproduct>    
            <input type="submit" name="a" value="产品管理">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=manageticket>    
            <input type="submit" name="a" value="票务管理">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=newjourney>    
            <input type="submit" name="a" value="新建产品">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=newticket>    
            <input type="submit" name="a" value="新建票务">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=seecomment>    
            <input type="submit" name="a" value="查看评论">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=seeuser>    
            <input type="submit" name="a" value="查看用户">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=adminsearch>    
        <select name="select">
                <option value="product">旅游产品</option>
                <option value="ticket" selected="selected">票务</option>
                <option value="user">用户</option>
            </select>
            <input type="text" name="key">
            <input type="submit" name="a" value="确认">
             </FORM>
        <%=session.getAttribute("adminindexinf")%>
    </body>
</html>
