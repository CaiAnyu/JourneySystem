<%-- 
    Document   : sightlist
    Created on : 2018-7-1, 21:03:42
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
            div.pic{
                margin-top:10px;
                margin-bottom: 10px;
                margin-left:20px;
                margin-right:20px;
                border:1px solid;
                border-radius:8px;
                
            }
            div.main{
                margin-left:12%;
                margin-right:12%;
                background-color: #f2f2f2;
            }
            div.top{
                    border-radius: 5px;
                    background-color: #f2f2f2;
                     padding: 10px;
            }
            div.container {
                  width:1600px;
                    height: 50px;
                    border-radius: 3px;
                    background-color: #f2f2f2;
                  
              }
              input[type=submit] {
          background-color: #4CAF50;
                  color: white;
                   padding: 4px 4px;
                 border: none;
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
   
        form{
    padding: 2px;
    margin: 2px;
    }
        </style>
          <script type="text/javascript">
            function  tip9() {
                    {
                    alert("预定成功");
                    }
            }
        </script>
        
    <body>
           <div class="top" style="width: 100%;height:25px">
            <div style="width: 85%;float:left;height:25px">
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="s2" value="个人中心">
        </FORM>   
             </div>
            <div style="float:right;height:25px">
                 <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="退出登录">
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
     <div  style="width:100%;height: 90%">
            <div class="main">
                  <p class="biaoqian" align="center"> 产 品 列 表 </p>
 
            <%=session.getAttribute("list")%>
      </div>
       </div>
    </body>
</html>
