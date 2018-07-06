<%-- 
    Document   : changeproduct
    Created on : 2018-7-4, 19:23:14
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
             .header {
            background-color:#4BAF62;
            color:black;
            text-align:center;
            padding:10px 100px 10px 100px;
             }
            p.biaoqian{
             
                font-size: 25px;
                
                background-color: #4BAF62;
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
        <div class="header"><h1>修改产品信息</h1></div>
         <hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
         <div style="width: 300px; height: 500px;margin: 0 auto;">
        <%=session.getAttribute("changeproductinf")%>
          <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=toadminindex>    
            <input type="submit" name="a" value="返回管理中心">
        </FORM>
         </div> 
              
    </body>
</html>
