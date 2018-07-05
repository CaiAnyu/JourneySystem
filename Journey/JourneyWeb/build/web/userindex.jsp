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
        <div style="width: 100%;height:30px">
            <div style="width: 90%;float:left">
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="s2" value="个人中心">
        </FORM>   
             </div>
            <div style="float:right">
                 <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="退出登录">
                 </FORM>
            </div>
        </div>
        <hr>
     
        <%=session.getAttribute("showhotel")%>
        <%=session.getAttribute("showjourney")%>
        <FORM METHOD="post" ACTION=CtrlServlet?method=usersearch>    
            <select name="select">
                <option value="酒店">酒店</option>
                <option value="旅游团" selected="selected">旅游团</option>
            </select>
            &nbsp;&nbsp;
            <input type="text" name="key">
            <input type="submit" name="s3" value="搜索"><p>
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=usersearchticket>    
            <select name="select">
                <option value="飞机票">飞机票</option>
                <option value="火车票" selected="selected">火车票</option>
            </select>
            &nbsp;&nbsp;
            始发地:&nbsp;<input type="text" name="start">&nbsp;
            目的地:&nbsp;<input type="text" name="terminal">&nbsp;
            <input type="submit" name="s4" value="搜索">
        </FORM>
   
    </body>
</html>
