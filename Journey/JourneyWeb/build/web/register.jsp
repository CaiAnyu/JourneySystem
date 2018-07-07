<%-- 
    Document   : register
    Created on : 2018-7-1, 21:04:46
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
      .left {
        float: left;
        width: 300px;
        height: 300px;
        background-color: white;
      }
      body{
          background-image: url(pic/bg.jpg);
           position:fixed;
  top: 0;
  left: 0;
  width:100%;
  height:100%;
  min-width: 1000px;
  z-index:-10;
  zoom: 1;
  background-color: #fff;
  background-repeat: no-repeat;
  background-size: cover;
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-position: center 0;
      }
     input[type=submit] {
                  background-color: #4CAF50;
                  color: white;
                   padding: 4px 4px;
                 border: none;
                   width: 300px;
            height:35px;
                     border-radius: 4px;
                 cursor: pointer;
                 }
                 input.searchinput{
                     width: 45px;
                    height: 33px;
                 
                 }
            input[type=submit]:hover {
            background-color: #45a049;
            }
            input[type=text], select, textarea{
        width:300px;
        height: 40px;
        padding: 3px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
     margin-top: 3px;
    margin-bottom: 3px;
    resize: vertical;
        } input[type=password]{
        width:300px;
        height: 40px;
        padding: 3px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
     margin-top: 3px;
    margin-bottom: 3px;
    resize: vertical;
        }
        select{
            margin-top: 7px;

            width: 100px;
            height:37px;
        }
        form{
    padding: 3px;
    margin: 3px;
    }
  
      .right {
          text-align: center;
           
         background-color: #f2f2f2;
        margin-left: 30%;
        margin-right: 30%;
         margin-top: 13%;
        height: 420px;
        border: 1px solid #000000;
            border-radius: 8px;  
      }
    
    </style>
    </head>
    <body>
        
         <div class="right">
           
           
                <h1 >注 册 用 户</h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=register>    
           <input type="text" name="username" placeholder="请输入用户名" value=''><p>
            <input type="password" name="password" placeholder="请输入密码" value=''><p>
            <input type="password" name="repassword"placeholder="请再输入密码"  value=''><p>
            <input type="submit" name="s1" value="注册">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <%if (session.getAttribute("registerfalse")!=null) if (session.getAttribute("registerfalse").equals("false")) {out.println("<p>注册失败<p>");session.setAttribute("registerfalse", "true");}%>
        </div>
    </body>
</html>
