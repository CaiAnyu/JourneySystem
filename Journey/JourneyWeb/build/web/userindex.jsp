<%-- 
    Document   : userindex
    Created on : 2018-7-1, 20:47:04
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
        <h1>你好，<%=session.getAttribute("username")%></h1>
        <%=session.getAttribute("showhotel")%>
        <%=session.getAttribute("showjourney")%>
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            <input type="submit" name="s2" value="个人中心">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=usersearch>    
            <select name="select">
                <option value="酒店">酒店</option>
                <option value="旅游团" selected="selected">旅游团</option>
            </select>
            <input type="text" name="key">
            <input type="submit" name="s3" value="搜索"><p>
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=usersearchticket>    
            <select name="select">
                <option value="飞机票">飞机票</option>
                <option value="火车票" selected="selected">火车票</option>
            </select>
            始发地<input type="text" name="start"><p>
            目的地<input type="text" name="terminal"><p>
            <input type="submit" name="s4" value="搜索">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
    </body>
</html>
