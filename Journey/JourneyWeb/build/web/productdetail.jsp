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
        <div style="width: 100%;height:30px">
            
            <div style="float:left">
            <FORM METHOD="post" ACTION=CtrlServlet?method=journeyaddcart>    
                <b>产品详情</b>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="s" value="加入购物车">&nbsp;&nbsp;
             </FORM>
            </div>
            <div style="float:left">
                <FORM METHOD="post" ACTION=CtrlServlet?method=journeyaddfavourite>    
                <input type="submit" name="s" value="收藏">
                </FORM>
            </div>
        </div>
        <hr>
        <%=session.getAttribute("productdetail")%><p>
        评价表<hr>
        <%=session.getAttribute("productcomment")%><p>
             <hr>
        <FORM METHOD="post" ACTION=CtrlServlet?method=comment>    
            请在此输入评论 <p><input type="text" name="comment" value="差评">&nbsp;&nbsp;
                <input type="submit" name="s" value="提交评论">
        </FORM>
        <hr>
        
     
    
    </body>
</html>
