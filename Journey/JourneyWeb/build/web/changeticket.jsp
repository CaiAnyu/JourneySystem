<%-- 
    Document   : changeticket
    Created on : 2018-7-5, 1:50:41
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
                margin-left:15%;
                margin-right:15%;
                    background-color:#f2f2f2;
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
        width:180px;
        height: 35px;
        padding: 3px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
     margin-top: 3px;
    margin-bottom: 3px;
    resize: vertical;
        }
        #new{
              width:100px;
                     height: 50px;
                  background-color: #4CAF50;
                  color: white;
                   padding: 4px 4px;
                 border: none;
                     border-radius: 4px;
                 cursor: pointer;
        }
         #return{
              width:100px;
                     height: 50px;
                  background-color: #4CAF50;
                  color: white;
                   padding: 4px 4px;
                 border: none;
                     border-radius: 4px;
                 cursor: pointer;
        }
        #jieshao{
             width:500px;
        height:40px;
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
function  tip() {
        {
        alert("修改成功");
        }
}
</script>
    </head>
    <body>
             <div class="top" style="width: 100%;height:30px">
         <div style="width: 85%;float:left;height:30px">
        <FORM METHOD="post" ACTION=CtrlServlet?method=toadminindex>     
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp; <input type="submit" name="a" value="管理中心">
        </FORM>
         </div>
        <div style="float:right;height:25px">
         <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="退出登录">
         </FORM>
         </div> 
         </div>
             
           <hr>
           
          <div  style="width:100%;height: 90%">
            <div class="main">
                
          <p class="biaoqian" align="center"> 票 务 修 改 </p><hr>
         <%=session.getAttribute("changeticketinf")%>
        
         </div>
         </div>
    </body>
</html>
