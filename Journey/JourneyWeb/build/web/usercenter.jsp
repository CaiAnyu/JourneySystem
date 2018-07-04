<%-- 
    Document   : usercenter
    Created on : 2018-7-1, 21:05:04
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
        <h1>个人中心</h1>
        你好，<%=session.getAttribute("username")%><p>
        <FORM METHOD="post" ACTION=CtrlServlet?method=repassword>    
            密码<input type="password" name="password"><p>
                再确认<input type="password" name="repassword"><p>
            <input type="submit" name="s1" value="更改密码"><p>
        </FORM>
        收藏的旅游产品:<p>
            <%=session.getAttribute("showfavourite")%>
            预定的旅游产品:<p>
            <%=session.getAttribute("showjourneycart")%>
            预定的票:<p>
            <%=session.getAttribute("showticketcart")%>
            已付款的旅游产品:<p>
            <%=session.getAttribute("showjourneyorder")%>
            已付款的票:<p>
            <%=session.getAttribute("showticketorder")%>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=touserindex>    
            <input type="submit" name="a" value="返回个人主页">
        </FORM>
        <%if (session.getAttribute("repasswordfalse") != null) {
                if (session.getAttribute("repasswordfalse").equals("false")) {
                    out.println("<p>重置失败<p>");
                    session.setAttribute("registerfalse", "true");
                }
            }%>
    </body>
</html>
