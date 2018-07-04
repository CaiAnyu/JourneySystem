<%-- 
    Document   : newticket
    Created on : 2018-7-5, 0:19:35
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
        <h1>新建票</h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=newticket>    
                票代号<input type="text" name="key" value="eee"><p>
                始发地<input type="text" name="start" value="宿舍楼"><p>
                目的地<input type="text" name="terminal" value="美食园"><p>
                时间<input type="text" name="year" value="2018"><input type="text" name="month" value="12"><input type="text" name="date" value="31"><p>
                价格<input type="text" name="price" value="3000"><p>
                种类<input type="text" name="type" value="火车"><p>
                车次<input type="text" name="num" value="H99"><p>
                <input type="submit" name="s1" value="确认">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=toadminindex>    
            <input type="submit" name="a" value="返回管理中心">
        </FORM>
    </body>
</html>
