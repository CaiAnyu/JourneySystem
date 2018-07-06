<%-- 
    Document   : adminindex
    Created on : 2018-7-1, 21:05:43
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员</title>
         <style>
            .left {
                 float: left;
                 width: 100px;
                 height: 100%;
                 background-color: #4BAF62; 
                 position:fixed;
               }
            .right {
                 background-color: white;
                 margin-left: 200px;
                 height: 600px;
                
               }
            p.biaoqian{
             
                font-size: 25px;
                
                background-color: #4BAF62;
            }
            div.pic{
                margin-top:10px;
                margin-bottom: 10px;
                margin-left:25px;
                margin-right:20px;
                border:2px solid;
                border-radius:8px;
                
            }
            div.top{
                    border-radius: 5px;
                    background-color: #f2f2f2;
                     padding: 10px;
                   
                     
            }
            div.container {
                    width: 100%;
                    float:left;
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
        select{
            margin-top: 7px;

            width: 80px;
            height:37px;
        }
        form{
    padding: 2px;
    margin: 2px;
    }
        </style>
    </head>
    <body>
         <div class="top" style="width: 100%;height:30px">
         <div style="width: 85%;float:left;height:30px">
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;
        </FORM>
         </div>
        <div style="float:right;height:25px">
         <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
         </FORM>
         </div>    
         
         </div>
        
            <div style="float:left;margin-left:500px">
            
            <FORM METHOD="post" ACTION=CtrlServlet?method=adminsearch>  
            <select name="select">
                <option value="product">旅游产品</option>
                <option value="ticket" selected="selected">票务</option>
                <option value="user">用户</option>
            </select>
            &nbsp;&nbsp;
            <input type="text" name="key">
            <input type="submit" name="a" value="确认">
            </FORM>
            
                
            </div>
        
         <br />
         <br />
         <br />
          
        <div class="left"> 
        
        <FORM METHOD="post" ACTION=CtrlServlet?method=manageproduct>    
            <input type="submit" name="a" value="产品管理">
        </FORM>
         <br />
         
        <FORM METHOD="post" ACTION=CtrlServlet?method=manageticket>    
            <input type="submit" name="a" value="票务管理">
        </FORM>
         <br />
       
        <FORM METHOD="post" ACTION=CtrlServlet?method=tonewproduct>    
            <input type="submit" name="a" value="新建产品">
        </FORM>
         <br />
         
        <FORM METHOD="post" ACTION=CtrlServlet?method=tonewticket>    
            <input type="submit" name="a" value="新建票务">
        </FORM>
         <br />
        
        <FORM METHOD="post" ACTION=CtrlServlet?method=seecomment>    
            <input type="submit" name="a" value="查看评论">
        </FORM>
         <br />
         
        <FORM METHOD="post" ACTION=CtrlServlet?method=seeuser>    
            <input type="submit" name="a" value="查看用户">
        </FORM>
        </div>
     
       <div class="right"> 
        <%=session.getAttribute("adminindexinf")%>
       </div>
    </body>
</html>
