<%-- 
    Document   : login
    Created on : 2018-7-1, 20:45:57
    Author     : 78288
--%>
  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <meta http-equiv="X-UA-Compatible" content="IE=Edge">
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
            height:30px;
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
           
           
                <h1 >旅游自助系统</h1>
        <FORM METHOD="post" ACTION=CtrlServlet?method=login>    
            <input type="text" name="username" placeholder="请输入用户名"value=""><p>
            <input type="password" name="password" placeholder="请输入密码" value=""><p>
   
            <INPUT type="radio" name="r" value="2" checked="checked">用户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <INPUT type="radio" name="r" value="1" >管理员<p>
            <input type="submit" name="s1" value="登 录">

        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=logintoregister>    
            <input type="submit" name="s2" value="注 册">
        </FORM>
        <%if (session.getAttribute("loginfalse")!=null) if (session.getAttribute("loginfalse").equals("false")) {out.println("<p>登录失败<p>");session.setAttribute("loginfalse", "true");}%>
           
            </div>  
    </body>
</html>
