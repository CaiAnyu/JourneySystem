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
              
                 background-color: #4BAF62; 
                 position:fixed;
                  width: 10%;
                  float: left;
                  
                  min-height: 100%;
                  margin-bottom: -99999px;
                  padding: 0 0 99999px;
               }
            .right {
            
                  height:50px;
                 
                 margin-left:10%;
                
               }
            p.biaoqian{
             
                font-size: 25px;
                
                background-color: #4BAF62;
            }
             div.pic{
                 background-color:#f2f2f2;
                border:1px solid;
                border-radius:8px;
                
            }
            div.top{
               
                    border-radius: 5px;
                    background-color: #f2f2f2;
                     padding: 10px;
                   
                     
            }
              div.main{
                margin-left:4%;
                margin-right:4%;
                
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
             input[type=submit]:action {
            background-color: #636363;
            }
            input[type=submit]:visited {
            background-color: #45a049;
            }    
           
            input[type=text], select, textarea {
        width:400px;
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

            width: 100px;
            height:37px;
        }
        form{
    padding: 2px;
    margin: 2px;
    }
        </style>
        <script type="text/javascript">
            function  tip2() {
                    {
                    alert("删除成功");
                    }
            }
       </script>
    </head>
    <body>
  
<div class="left"> 
            
           <div style="width: 85%;float:left;height:30px">
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;
        </FORM>
         </div>
         <br />
         <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=manageproduct>    
            <input type="submit" name="a" value="产品管理">
        </FORM>
         <br />
         <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=manageticket>    
            <input type="submit" name="a" value="票务管理">
        </FORM>
         <br />
       <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=tonewproduct>    
            <input type="submit" name="a" value="新建产品">
        </FORM>
         <br />
         <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=tonewticket>    
            <input type="submit" name="a" value="新建票务">
        </FORM>
         <br />
        <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=seecomment>    
            <input type="submit" name="a" value="查看评论">
        </FORM>
         <br />
         <br />
        <FORM METHOD="post" ACTION=CtrlServlet?method=seeuser>    
            <input type="submit" name="a" value="查看用户">
        </FORM>
        </div>
     
       <div class="right"> 
          <div class="top" style="width: 100%;height:40px;float:left;">
             
      
        <div style="float:left;margin-left:300px">
             
            <FORM METHOD="post" ACTION=CtrlServlet?method=adminsearch>  
            <select name="select">
                <option value="product"selected="selected">旅游产品</option>
                <option value="ticket" >票务</option>
                <option value="user">用户</option>
            </select>
              
            &nbsp;&nbsp;
            <input type="text" placeholder="请在此输入查询信息..."  name="key">
            <input type="submit" name="a" value="确认查询">
            </FORM>
            
            
           </div>
        
        
        <div style="float:right;height:25px">
         <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="退出登录">
         </FORM>
         </div> 
         
         </div>  
           <br />
           <br />
           <br />
        <%=session.getAttribute("adminindexinf")%>
        
       </div>
    </body>
</html>
