<%-- 
    Document   : newticket
    Created on : 2018-7-5, 0:19:35
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
                
          <p class="biaoqian" align="center">新 建 票 务</p><hr>
        <FORM METHOD="post" ACTION=CtrlServlet?method=newticket>    
                 &nbsp;票代号：<input type="text" name="key" value="eee"><p>
                 &nbsp;始发地: <input type="text" name="start" value="宿舍楼"><p>
                 &nbsp;目的地: <input type="text" name="terminal" value="美食园"><p>
                 &nbsp;时间：<input type="text" name="year" value="2018">&nbsp;年&nbsp;&nbsp;<input type="text" name="month" value="11">&nbsp;月&nbsp;&nbsp;<input type="text" name="date" value="21">&nbsp;日<p>
                 &nbsp;价格：<input type="text" name="price" value="3000"><p>
                 &nbsp;种类：<input type="text" name="type" value="火车"><p>
                 &nbsp;车次：<input type="text" name="num" value="H99"> &nbsp;&nbsp; 
                <input id="new"type="submit" name="s1" value="确认新建">
        </FORM>
          </div>
           </div>
    </body>
</html>
