<%-- 
    Document   : sightdetail
    Created on : 2018-7-1, 21:04:01
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>产品详情</title>
    </head>
    <body>
        <h1>产品详情</h1>
        <%=session.getAttribute("productdetail")%><p>
        <%=session.getAttribute("productcomment")%><p>     
        <FORM METHOD="post" ACTION=CtrlServlet?method=comment>    
            请在此输入评论<input type="text" name="comment" value="差评"><p>
                <input type="submit" name="s" value="提交评论">
        </FORM>
         <FORM METHOD="post" ACTION=CtrlServlet?method=journeyaddcart>    
                <input type="submit" name="s" value="加入购物车">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=journeyaddfavourite>    
                <input type="submit" name="s" value="收藏">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=touserindex>    
            <input type="submit" name="a" value="返回个人主页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
    </body>
</html>
