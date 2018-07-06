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
                        #header {
            background-color:#4BAF62;
            color:black;
            text-align:center;
            padding:10px 100px 10px 100px;
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
        </style>
    </head>
    <body>
         <div id="header"><h1>新建票</h1></div>
         <hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
          <div style="width: 300px; height: 500px;margin: 0 auto;">
        <FORM METHOD="post" ACTION=CtrlServlet?method=newticket>    
                票代号<input type="text" name="key" value="eee"><p>
                始发地<input type="text" name="start" value="宿舍楼"><p>
                目的地<input type="text" name="terminal" value="美食园"><p>
                时间<input type="text" name="year" value="2018"><input type="text" name="month" value="12"><input type="text" name="date" value="31"><p>
                价格<input type="text" name="price" value="3000"><p>
                种类<input type="text" name="type" value="火车"><p>
                车次<input type="text" name="num" value="H99"><p>
                <input type="submit" name="s1" value="确认">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=tologin>    
            <input type="submit" name="a" value="返回首页">
        </FORM>
        <FORM METHOD="post" ACTION=CtrlServlet?method=toadminindex>    
            <input type="submit" name="a" value="返回管理中心">
        </FORM>
          </div>
    </body>
</html>
