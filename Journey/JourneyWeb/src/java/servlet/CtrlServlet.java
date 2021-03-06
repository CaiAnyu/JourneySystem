/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.webBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 78288
 */

public class CtrlServlet extends HttpServlet {
    public static boolean isNumeric(String str){
  for(int i=str.length();--i>=0;){
   int chr=str.charAt(i);
   if(chr<48 || chr>57)
     return false;
  }
  return true;
}
    webBeanLocal webBean = lookupwebBeanLocal();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if (new String(request.getParameter("method")).equals("login")) {    //登录
                String type = request.getParameter("r");
                if (type.equals("1")) {                                          //管理员
                    if (this.webBean.adminlogin(request.getParameter("username"), request.getParameter("password")) == true) {
                        session.setAttribute("loginfalse", "true");
                        session.setAttribute("adminindexinf", "");
                        session.setAttribute("username", this.webBean.getusername());
                        RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                        disp.forward(request, response);
                    } else {
                        session.setAttribute("loginfalse", "false");
                        RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
                        disp.forward(request, response);
                    }
                } else {                                                        //用户
                    if (this.webBean.userlogin(request.getParameter("username"), request.getParameter("password")) == true) {
                        session.setAttribute("loginfalse", "true");
                        session.setAttribute("username", this.webBean.getusername());
                        session.setAttribute("showhotel", this.webBean.showhotel(4));
                        session.setAttribute("showjourney", this.webBean.showjourney(4));
                        RequestDispatcher disp = request.getRequestDispatcher("userindex.jsp");
                        disp.forward(request, response);
                    } else {
                        session.setAttribute("loginfalse", "false");
                        RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
                        disp.forward(request, response);
                    }
                }
            }
            if (new String(request.getParameter("method")).equals("logintoregister")) {//去注册
                 session.setAttribute("registerfalse", "true");
                RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("touserindex")) {//去个人主页
                session.setAttribute("repasswordfalse", "true");
                session.setAttribute("username", this.webBean.getusername());
                session.setAttribute("showhotel", this.webBean.showhotel(4));
                session.setAttribute("showjourney", this.webBean.showjourney(4));
                RequestDispatcher disp = request.getRequestDispatcher("userindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("tologin")) {//返回首页
                this.webBean.clear();
                RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("userindextocenter")) {//查看个人中心
                session.setAttribute("showjourneycart", this.webBean.showjourneycart());
                session.setAttribute("showticketcart", this.webBean.showticketcart());
                session.setAttribute("showjourneyorder", this.webBean.showjourneyorder());
                session.setAttribute("showticketorder", this.webBean.showticketorder());
                session.setAttribute("showfavourite", this.webBean.showfavourite());
                 session.setAttribute("repasswordfalse", "true");
                 session.setAttribute("repasswordfalse2", "false");
                RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("journeypay")) {//旅游产品付款
                out.println(this.webBean.journeypay(request.getParameter("num")));
                session.setAttribute("showjourneycart", this.webBean.showjourneycart());
                session.setAttribute("showjourneyorder", this.webBean.showjourneyorder());
                RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("journeydel")) {//旅游产品退订
                out.println(this.webBean.journeydel(request.getParameter("num")));
                session.setAttribute("showjourneycart", this.webBean.showjourneycart());

                RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketpay")) {//票付款
                out.println(this.webBean.ticketpay(request.getParameter("num")));
                session.setAttribute("showticketcart", this.webBean.showticketcart());
                session.setAttribute("showticketorder", this.webBean.showticketorder());
                RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketdel")) {//票退订
                out.println(this.webBean.ticketdel(request.getParameter("num")));
                session.setAttribute("showticketcart", this.webBean.showticketcart());
                RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("journeyfavdel")) {//取消收藏
                out.println(this.webBean.favdel(request.getParameter("num")));
                session.setAttribute("showfavourite", this.webBean.showfavourite());
                RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("usersearch")) {//搜产品
                session.setAttribute("list", this.webBean.searchjourneyforlist(new String(request.getParameter("select").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("key").getBytes("iso-8859-1"), "utf-8")));
                //  session.setAttribute("list", this.webBean.test());
                RequestDispatcher disp = request.getRequestDispatcher("productlist.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("usersearchticket")) {//搜票
                //String start=new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8");
               // String terminal=new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8");
                //if("".equals(start.trim())&&"".equals(terminal.trim()))
              //  {  
                //   session.setAttribute("list",this.webBean.showallticketlist());
             //   }else{    
                session.setAttribute("list", this.webBean.searchticketforlist(new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("select").getBytes("iso-8859-1"), "utf-8")));
               // }
                RequestDispatcher disp = request.getRequestDispatcher("productlist.jsp");
                
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("journeyaddcart")) {//买产品
                //out.println(this.webBean.test());
                String key = (String) session.getAttribute("detailkey");
                out.println(this.webBean.userbuyjourney(key));
                RequestDispatcher disp = request.getRequestDispatcher("productdetail.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("journeyaddfavourite")) {//收藏产品
                //out.println(this.webBean.test());
                String key = (String) session.getAttribute("detailkey");
                out.println(this.webBean.useraddjourneytofavourite(key));
                RequestDispatcher disp = request.getRequestDispatcher("productdetail.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketaddcart")) {//买票
                // out.println(this.webBean.test());
                out.println(this.webBean.userbuyticket(request.getParameter("num")));
                RequestDispatcher disp = request.getRequestDispatcher("productlist.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("listproductdetail") || new String(request.getParameter("method")).equals("favtodetail")) {
                // out.println(this.webBean.test());                   //看详细信息
                String key = request.getParameter("num");
                session.setAttribute("detailkey", key);
                session.setAttribute("productdetail", this.webBean.showdetail(key));
                session.setAttribute("productcomment", this.webBean.showcomment(key));

                RequestDispatcher disp = request.getRequestDispatcher("productdetail.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("comment")) {//评论
                String key = (String) session.getAttribute("detailkey");
                String comment=new String(request.getParameter("comment").getBytes("iso-8859-1"), "utf-8");
                if("".equals(comment.trim())||comment.isEmpty())
                {
                    session.setAttribute("productcomment", this.webBean.showcomment(key));
                //    out.println(this.webBean.userbuyticket(request.getParameter("num")));
                RequestDispatcher disp = request.getRequestDispatcher("productdetail.jsp");
                disp.forward(request, response);
                }else{
                      this.webBean.comment(key, new String(request.getParameter("comment").getBytes("iso-8859-1"), "utf-8"));
                session.setAttribute("productcomment", this.webBean.showcomment(key));
                //    out.println(this.webBean.userbuyticket(request.getParameter("num")));
                RequestDispatcher disp = request.getRequestDispatcher("productdetail.jsp");
                disp.forward(request, response);
                }
                //   out.println(key+new String(request.getParameter("comment").getBytes("iso-8859-1"), "utf-8"));
              
            }

            if (new String(request.getParameter("method")).equals("register")) {//注册
                String name = request.getParameter("username");
                 String Testname = request.getParameter("username");
                  if(Testname.replaceAll("[a-z]*[A-Z]*\\d*", "").length()!=0){
                    session.setAttribute("registerfalse", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                    disp.forward(request, response); 
                  }
                  
                  
                  
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                if (name.isEmpty() || password.isEmpty() || repassword.isEmpty() || name.length() > 20 || password.length() != 8) {
                    session.setAttribute("registerfalse", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                    disp.forward(request, response);    
                }
                if (password.equals(repassword)) {
                    //   out.println(this.webBean.test());
                    if (this.webBean.register(name, password)) {
                        session.setAttribute("registerfalse", "true");
                        RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
                        disp.forward(request, response);
                    } else {
                        session.setAttribute("registerfalse", "false");
                        RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                        disp.forward(request, response);
                    }
                } else {
                    session.setAttribute("registerfalse", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                    disp.forward(request, response);
                }
            }
            if (new String(request.getParameter("method")).equals("repassword")) {//重置密码
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                if (password.isEmpty() || repassword.isEmpty() || password.length() != 8) {
                    session.setAttribute("repasswordfalse", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                    disp.forward(request, response);
                }
                if (password.equals(repassword)) {
                    //   out.println(this.webBean.test());
                    if (this.webBean.repassword(password)) {
                        session.setAttribute("repasswordfalse", "true");
                         session.setAttribute("repasswordfalse2", "true");
                        RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                        disp.forward(request, response);
                    } else {
                        session.setAttribute("repasswordfalse", "false");
                        RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                        disp.forward(request, response);
                    }
                } else {
                    session.setAttribute("repasswordfalse", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("usercenter.jsp");
                    disp.forward(request, response);
                }
            }
//***************************************************************************************************************************************************************
//****************************************************************************************************************************************************************

            if (new String(request.getParameter("method")).equals("manageproduct")) {//产品管理
                    session.setAttribute("adminindexinf", this.webBean.showallproductlist());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }

            if (new String(request.getParameter("method")).equals("productoff")) {//产品下架
                String key = request.getParameter("num");
                out.println(this.webBean.productoff(key));
                session.setAttribute("adminindexinf", this.webBean.showallproductlist());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("producton")) {//产品上架
                String key = request.getParameter("num");
                out.println(this.webBean.producton(key));
                session.setAttribute("adminindexinf", this.webBean.showallproductlist());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("manageticket")) {//票务管理
                session.setAttribute("adminindexinf", this.webBean.showallticketlist());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketoff")) {//票务下架
                String key = request.getParameter("num");
                out.println(this.webBean.ticketoff(key));
                // out.println(this.webBean.test());
                session.setAttribute("adminindexinf", this.webBean.showallticketlist());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketon")) {//票务上架
                String key = request.getParameter("num");
                out.println(this.webBean.ticketon(key));
                session.setAttribute("adminindexinf", this.webBean.showallticketlist());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("seecomment")) {//查看评论
                session.setAttribute("adminindexinf", this.webBean.showallcomment());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("deletecomment")) {//删除评论
                String key = request.getParameter("num");
                out.println(key + this.webBean.deletecomment(key));
                session.setAttribute("adminindexinf", this.webBean.showallcomment());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("adminsearch")) {//管理员搜索
                String select = (String) request.getParameter("select");
                String key = new String(request.getParameter("key").getBytes("iso-8859-1"), "utf-8");
                if (select.equals("user")) {
                    String r = "";
                    r += this.webBean.showusercomment(key) + "<p>";
                    r += this.webBean.showuserproduct(key) + "<p>";
                    r += this.webBean.showuserticket(key) + "<p>";
                    session.setAttribute("adminindexinf", r);
                }
                if (select.equals("ticket")) {
                    session.setAttribute("adminindexinf", this.webBean.searchforticketlist(key));
                }
                if (select.equals("product")) {
                    session.setAttribute("adminindexinf", this.webBean.searchforproductlist(key));
                }
                session.setAttribute("userkey", key);
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("seeuser")) {//看用户 
                session.setAttribute("adminindexinf", this.webBean.showuser());
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("tousercomment")) {//看某位用户
                String key = request.getParameter("num");
                String r = "";
                r += this.webBean.showusercomment(key) + "<p>";
                r += this.webBean.showuserproduct(key) + "<p>";
                r += this.webBean.showuserticket(key) + "<p>";
                session.setAttribute("adminindexinf", r);
                session.setAttribute("userkey", key);
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("deleteusercomment")) {//删除用户评论
                String key = request.getParameter("num");
                out.println(key + this.webBean.deletecomment(key));
                key = (String) session.getAttribute("userkey");
                String r = "";
                r += this.webBean.showusercomment(key) + "<p>";
                r += this.webBean.showuserproduct(key) + "<p>";
                r += this.webBean.showuserticket(key) + "<p>";
                session.setAttribute("adminindexinf", r);

                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketoff2")) {//票务下架2
                String key = request.getParameter("num");
                out.println(this.webBean.ticketoff(key));
                // out.println(this.webBean.test());
                key = (String) session.getAttribute("userkey");
                session.setAttribute("adminindexinf", this.webBean.searchforticketlist(key));
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("ticketon2")) {//票务上架2
                String key = request.getParameter("num");
                out.println(this.webBean.ticketon(key));
                key = (String) session.getAttribute("userkey");
                session.setAttribute("adminindexinf", this.webBean.searchforticketlist(key));
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("productoff2")) {//产品下架2
                String key = request.getParameter("num");
                out.println(this.webBean.productoff(key));
                key = (String) session.getAttribute("userkey");
                session.setAttribute("adminindexinf", this.webBean.searchforproductlist(key));
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("producton2")) {//产品上架2
                String key = request.getParameter("num");
                out.println(this.webBean.producton(key));
                key = (String) session.getAttribute("userkey");
                session.setAttribute("adminindexinf", this.webBean.searchforproductlist(key));
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("tonewproduct")) {//转到建立产品页
                RequestDispatcher disp = request.getRequestDispatcher("newproduct.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("newproduct")) {//建立产品
                
                String URL=request.getParameter("url");
                if(URL.contains(".jpg,")&&URL.substring(URL.length()-4,URL.length()).contains(".jpg"))
                {
                    String name=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
                     String type=new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8");
                    if("".equals(name.trim())||name.isEmpty()||!type.equals("酒店")||!type.equals("旅游团"))
                    {      session.setAttribute("newproducterror", "true");  
                           RequestDispatcher disp = request.getRequestDispatcher("newproduct.jsp");
                      disp.forward(request, response);
                    }else{
                          if (this.webBean.newproduct(new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8"), request.getParameter("key"), new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"), Integer.valueOf(request.getParameter("year")), Integer.valueOf(request.getParameter("month")), Integer.valueOf(request.getParameter("date")), Integer.valueOf(request.getParameter("price")), new String(request.getParameter("detail").getBytes("iso-8859-1"), "utf-8"), request.getParameter("url"))) {
                     session.setAttribute("newproducterror", "false");  
                     session.setAttribute("adminindexinf", this.webBean.showallproductlist());        
                    RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                    disp.forward(request, response);
                     } else {
                              session.setAttribute("newproducterror", "true");  
                    RequestDispatcher disp = request.getRequestDispatcher("newproduct.jsp");
                      disp.forward(request, response);
                         }
                    }
                   
                }
                else {
                    session.setAttribute("newproducterror", "true");  
                    RequestDispatcher disp = request.getRequestDispatcher("newproduct.jsp");
                    disp.forward(request, response);
                }
            }
            if (new String(request.getParameter("method")).equals("tonewticket")) {//转到建票页
                RequestDispatcher disp = request.getRequestDispatcher("newticket.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("newticket")) {//建票
                try{
                     String key=request.getParameter("key");
                String start= new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8");
                String terminal=new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8");
                String type=new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8");
                
                


                 if("".equals(terminal.trim())||terminal.isEmpty()||"".equals(start.trim())||start.isEmpty()||!type.equals("飞机票")||!type.equals("火车票")||!isNumeric(request.getParameter("price")))
                 {    
                      session.setAttribute("newticketerror", "true");
                     RequestDispatcher disp = request.getRequestDispatcher("newticket.jsp");
                    disp.forward(request, response); 
                 }else{
                        if(key.replaceAll("[a-z]*[A-Z]*\\d*", "").length()!=0||(Integer.valueOf(request.getParameter("price"))<=0))
                 {
                       session.setAttribute("newticketerror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("newticket.jsp");
                    disp.forward(request, response);
                 }else{
                      try{
                                 if (this.webBean.newticket(request.getParameter("key"), new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8"), Integer.valueOf(request.getParameter("year")), Integer.valueOf(request.getParameter("month")), Integer.valueOf(request.getParameter("date")), Integer.valueOf(request.getParameter("price")), new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"), request.getParameter("num"))) {
                     session.setAttribute("adminindexinf", this.webBean.showallticketlist());       
                       session.setAttribute("newticketerror", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                    disp.forward(request, response);
                } else {
                            session.setAttribute("newticketerror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("newticket.jsp");
                    disp.forward(request, response);
                }          
                     }catch(Exception e){
                          session.setAttribute("newticketerror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("newticket.jsp");
                    disp.forward(request, response);
                     }
         
                }
                 }
                }catch(Exception e){
                           session.setAttribute("newticketerror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("newticket.jsp");
                    disp.forward(request, response);
                }
           
             
                // out.println(this.webBean.newticket(request.getParameter("key"),  new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8"), Integer.valueOf(request.getParameter("year")), Integer.valueOf(request.getParameter("month")), Integer.valueOf(request.getParameter("date")), Integer.valueOf(request.getParameter("price")),  new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"), request.getParameter("num")));
              
            }
            if (new String(request.getParameter("method")).equals("tochangeproduct")) {//转到改产品页
                String key = request.getParameter("num");
                session.setAttribute("changeproductinf", this.webBean.changeproductpage(key));
                RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                disp.forward(request, response);
            }
            if (new String(request.getParameter("method")).equals("changeproduct")) {//改产品
                //  String key = request.getParameter("num");
                int price=0;
                try{
                     price=Integer.valueOf(request.getParameter("price"));
                }catch(Exception e){
                    session.setAttribute("changeproducterror", "true");
                 RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                    disp.forward(request, response);
                }
                
                 //String name=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
                 //if(name.length()>20)
            if(price<=0||price>9999)
            {   
                  session.setAttribute("changeproducterror", "true");
                 RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                    disp.forward(request, response);
            }else{
             String URL=request.getParameter("url");
              if(URL.contains(".jpg,")&&URL.substring(URL.length()-4,URL.length()).contains(".jpg"))
                {      
                     String name=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
                  if("".equals(name.trim())||name.isEmpty())
                    {
                        session.setAttribute("changeproducterror", "true");
                           RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                      disp.forward(request, response);
                    }else{
                      try{
                            if (this.webBean.changeproduct(new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8"), request.getParameter("key"), new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"), Integer.valueOf(request.getParameter("year")), Integer.valueOf(request.getParameter("month")), Integer.valueOf(request.getParameter("date")), Integer.valueOf(request.getParameter("price")), new String(request.getParameter("detail").getBytes("iso-8859-1"), "utf-8"), request.getParameter("url"))) {
                    session.setAttribute("changeproducterror", "false");
                              session.setAttribute("adminindexinf", this.webBean.showallproductlist());
                    RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                    disp.forward(request, response);
                } else {
                              session.setAttribute("changeproducterror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                    disp.forward(request, response);
                }
                      }catch(Exception e){
                           session.setAttribute("changeproducterror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                    disp.forward(request, response);
                      }
                        
                  }    
        
                }else {
                  session.setAttribute("changeproducterror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("changeproduct.jsp");
                    disp.forward(request, response);
                }   
            }
             
            }
           
        if (new String(request.getParameter("method")).equals("tochangeticket")) {//转到改票页
             String key = request.getParameter("num");
                session.setAttribute("changeticketinf", this.webBean.changeticketpage(key));
            RequestDispatcher disp = request.getRequestDispatcher("changeticket.jsp");
            disp.forward(request, response);
        }
        if (new String(request.getParameter("method")).equals("changeticket")) {//改票
                //  String key = request.getParameter("num");
            try{
                   int price=Integer.valueOf(request.getParameter("price"));
                     if(price<=0||price>9999)
            {
                session.setAttribute("changeticketerror", "true");
                 RequestDispatcher disp = request.getRequestDispatcher("changeticket.jsp");
                    disp.forward(request, response);
            }else{
              String start= new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8");
                String terminal=new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8");
                 if("".equals(terminal.trim())||terminal.isEmpty()||"".equals(start.trim())||start.isEmpty())
                 {
                      session.setAttribute("changeticketerror", "true");
                     RequestDispatcher disp = request.getRequestDispatcher("changeticket.jsp");
                    disp.forward(request, response); 
                 }else{
                     if (this.webBean.changeticket(request.getParameter("key"), new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8"), Integer.valueOf(request.getParameter("year")), Integer.valueOf(request.getParameter("month")), Integer.valueOf(request.getParameter("date")), Integer.valueOf(request.getParameter("price")), new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"), request.getParameter("num"))) {
           session.setAttribute("changeticketerror", "false");
                         session.setAttribute("adminindexinf", this.webBean.showallticketlist());           
         RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                    disp.forward(request, response);
                } else {
                          session.setAttribute("changeticketerror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("changeticket.jsp");
                    disp.forward(request, response);
                }
                 }   
            }
            }catch(Exception e){
                  session.setAttribute("changeticketerror", "true");
                    RequestDispatcher disp = request.getRequestDispatcher("changeticket.jsp");
                    disp.forward(request, response);
            }
    
       
             
         //   out.println(this.webBean.changeticket(request.getParameter("key"), new String(request.getParameter("start").getBytes("iso-8859-1"), "utf-8"), new String(request.getParameter("terminal").getBytes("iso-8859-1"), "utf-8"), Integer.valueOf(request.getParameter("year")), Integer.valueOf(request.getParameter("month")), Integer.valueOf(request.getParameter("date")), Integer.valueOf(request.getParameter("price")), new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8"), request.getParameter("num")));
     
            }
        if (new String(request.getParameter("method")).equals("toadminindex")) {//去管理主页
                RequestDispatcher disp = request.getRequestDispatcher("adminindex.jsp");
                disp.forward(request, response);
            }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CtrlServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet CtrlServlet at " + request.getContextPath() + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private webBeanLocal lookupwebBeanLocal() {
        try {
            Context c = new InitialContext();
            return (webBeanLocal) c.lookup("java:global/JourneyWeb/webBean!bean.webBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
