<%-- 
    Document   : newproduct
    Created on : 2018-7-4, 23:47:19
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
        <h1>新建产品</h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=newproduct>    
                名称<input type="text" name="name" value="12345"><p>
                代号<input type="text" name="key" value="25"><p>
                种类<input type="text" name="type" value="酒店"><p>
                时间<input type="text" name="year" value="2018"><input type="text" name="month" value="12"><input type="text" name="date" value="31"><p>
                价格<input type="text" name="price" value="1"><p>
                详细介绍<input type="text" name="detail" value="没有"><p>
                URL<input type="text" name="url" value="???"><p>
                <input type="submit" name="s1" value="确认">

        </FORM>
    </body>
</html>
