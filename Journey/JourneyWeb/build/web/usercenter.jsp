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
         <style>
             p.biaoqian{
             
                font-size: 25px;
                
                background-color: #4BAF62;
            }
             select{
            margin-top: 7px;
            width: 80px;
            height:37px;
        } 
        form{
        padding: 2px;
        margin: 2px;
            }
               div.container {
                    width:1600px;
                    height: 50px;
                    border-radius: 3px;
                    background-color: #f2f2f2;
                  
              }
                div.main{
                margin-left:12%;
                margin-right:12%;
                background-color:#f2f2f2;
            }
          div.top{
                    border-radius: 5px;
                    background-color: #f2f2f2;
                     padding: 10px;
            }
         
          input[type=submit] {
                  background-color: #4CAF50;
                  color: white;
                   padding: 4px 4px;
                 border: none;
                     border-radius: 4px;
                 cursor: pointer;
                 }
         
            input[type=submit]:hover {
            background-color: #45a049;
            }
            input[type=text], select, textarea {
        width: 200px;
        height: 35px;
        padding: 3px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
     margin-top: 3px;
    margin-bottom: 3px;
    resize: vertical;
        }
         </style>
    </head>
    <body>
              <div class="top" style="width: 100%;height:25px">
            <div style="width: 85%;float:left;height:25px">
       <FORM METHOD="post" ACTION=CtrlServlet?method=tologin> 
                你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="a" value="退出登录">
              </FORM>
             </div>
            <div style="float:right;height:25px">
            <FORM METHOD="post" ACTION=CtrlServlet?method=touserindex>    
            <input type="submit" name="a" value="返回主页">
              </FORM>
            </div>
        </div>
        <hr>
        <div class="container">
        <div style="float:left">
              <FORM METHOD="post" ACTION=CtrlServlet?method=usersearch>    
            <select name="select">
                <option value="酒店">酒店</option>
                <option value="旅游团" selected="selected">旅游团</option>
            </select>
            &nbsp;&nbsp;
            <input type="text" name="key">&nbsp;
            <input class="searchinput" type="submit" name="s3" value="搜索"><p>
            </FORM>
        </div>
        <div style="float:left;margin-left:20px">
             <FORM METHOD="post" ACTION=CtrlServlet?method=usersearchticket>    
            <select name="select">
                <option value="飞机票">飞机票</option>
                <option value="火车票" selected="selected">火车票</option>
            </select>
            &nbsp;&nbsp;
            始发地:&nbsp;<input type="text" name="start">&nbsp;
            目的地:&nbsp;<input type="text" name="terminal">&nbsp;
            <input class="searchinput" type="submit" name="s4" value="搜索">
        </FORM>
        </div>      
        </div>  
        <br>
      
        <hr>
        
        <FORM METHOD="post" ACTION=CtrlServlet?method=repassword>    
            重置密码: &nbsp;&nbsp;&nbsp;&nbsp;新密码： <input type="password" name="password">&nbsp;
                再确认： <input type="password" name="repassword">&nbsp;
            <input type="submit" name="s1" value="更改密码">
                  <%if (session.getAttribute("repasswordfalse") != null) {
                if (session.getAttribute("repasswordfalse").equals("false")) {
                    out.println("重置失败");
                    session.setAttribute("registerfalse", "true");
                }
            }%>
        </FORM>
         <hr>
         <div style="width:100%;height: 90%">
           <div class="main" style="height:90%">            
            <p class="biaoqian" align="center"> 收 藏 精 品 </p>
            <%=session.getAttribute("showfavourite")%> 
            <p class="biaoqian" align="center"> 预 定 产 品 </p>
            <%=session.getAttribute("showjourneycart")%>
            <p class="biaoqian" align="center"> 预 定 票 务 </p>
            <%=session.getAttribute("showticketcart")%> 
            <p class="biaoqian" align="center"> 已 付 款 产 品 </p>
            <%=session.getAttribute("showjourneyorder")%>
            <p class="biaoqian" align="center"> 已 付 款 票 务 </p>
            <%=session.getAttribute("showticketorder")%>  <hr>
            </div>
         </div>
      
    </body>
</html>
