<%-- 
    Document   : sightdetail
    Created on : 2018-7-1, 21:04:01
    Author     : 78288
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>产品详情</title>
        <style>
             p.biaoqian{
             
                  font-size: 35px;
                color: white;
                background-color: #4BAF62;
            }
             select{
              width: 100px;
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
            #yuding{
                  background-color:#FF7F00;
                  color: white;
                   padding: 4px 4px;
                 border: none;
                     border-radius: 4px;
                 cursor: pointer;
            }
            #zhongxin{
                 background-color:#FF7F00;
                  color: white;
                   padding: 4px 4px;
                 border: none;
                     border-radius: 4px;
                 cursor: pointer;
            }
            #text{
                width: 90%;
        height: 45px;
        padding: 3px;
        border: 1px solid;
        border-radius: 4px;
        box-sizing: border-box;
     margin-top: 5px;
     margin-left: 10px;
      margin-right: 10px;
    margin-bottom: 5px;
    resize: vertical;
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
         </style>
          <script type="text/javascript">
            function  tip3() {
                    {
                    alert("提交成功");
                    }
            }
             function  tip4() {
                    {
                    alert("预定成功");
                    }
            }
             function  tip5() {
                    {
                    alert("收藏成功");
                    }
            }
       </script>
    </head>
    <body>
          <div class="top" style="width: 100%;height:25px">
            <div style="width: 85%;float:left;height:25px">
        <FORM METHOD="post" ACTION=CtrlServlet?method=userindextocenter>    
            你好，<%=session.getAttribute("username")%>&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="zhongxin"type="submit" name="s2" value="个人中心">
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
        <div style="float:left;margin-left:300px">
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
       <body>




      
        <hr>
          <div style="width:100%;height: 90%">
              <div class="main" style="height:90%">
        <%=session.getAttribute("productdetail")%><p>
            <a id='DD' ></a>

        <p class="biaoqian" align="center"> 相  关  热  评 </p><hr>
        <%=session.getAttribute("productcomment")%><p>
         </div>
              <div class="main" style="height:10%">
                  <FORM METHOD="post" ACTION=CtrlServlet?method=comment>    
            
           <input type="text" id="text" name="comment" placeholder="请在此输入评论...." value="">&nbsp;&nbsp;
                <input type="submit" name="s" value="提交评论">
                </FORM>
             </div>
        </div>
     
    
    </body>
</html>
