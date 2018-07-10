/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;

/**
 *
 * @author 78288
 */
@Stateful
public class webBean implements webBeanLocal {

    String name;
    int type;

    @Override
    public boolean userlogin(String username, String password) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 密码 from 用户 where 用户名='" + username + "' AND 身份='用户'");
            con.close();
            if (rs.next()) {
                if (rs.getString(1).equals(password)) {
                    name = username;
                    type = 2;
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean adminlogin(String adminname, String password) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 密码 from 用户 where 用户名='" + adminname + "' AND 身份='管理员'");
            if (rs.next()) {
                if (rs.getString(1).equals(password)) {
                    name = adminname;
                    type = 1;
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean register(String username, String password) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 用户 where 用户名='" + username + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            String sql = "INSERT INTO 用户 VALUES ('" + username + "', '" + password + "', '用户')";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String test() {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");

            Statement st = con.createStatement();

            String key = "wwwww";
            ResultSet rs = st.executeQuery("select * from 评论 WHERE 评论者名='" + key + "'");
            ResultSet rs2 = st.executeQuery("select 评论代号 from 评论 WHERE 评论者名='" + key + "'");

            String inf = "123467";

            while (rs.next()) {
                rs2.next();
                inf += rs.getString(1);
                inf += rs.getString(2);
                inf += rs.getString(3);
                inf += rs.getString(4);
                inf += rs.getString(5);
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=deletecomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs2.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"删除\"></FORM>";
            }
            return inf;
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }

    }

    @Override
   public String showhotel(int n) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 种类='酒店'");
            String inf = "";
            if (n <= 0) {
                return null;
            }
               inf+="<div style=\" width:100%;height:300px\">";
            while (rs.next() && n > 0) {
                String[] splitstr=rs.getString(7).split(",");
                inf+="<div class=\"pic\" style=\" float:left; width:21%;height:240px\">";
                inf+="<div style=\" float:left\">";
                inf+="<img height=\"200px\" width=\"100%\" src=\"pic\\"+splitstr[0]+"\">";
                inf +="</div>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";
                inf +=  "名称: "+rs.getString(2) + "  <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\"  name=\"s1\" value=\"" + "查看详情" + "\"></FORM><p>";
                inf +="</div>";
                n--;
            }
            inf+="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！";
    }

    @Override
    public String getusername() {
        return name;
    }

    @Override
   public String showjourney(int n) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 种类='旅游团'");
            String inf = "";
            if (n <= 0) {
                return null;
            }
            inf+="<div style=\" width:100%;height:300px\">";
            while (rs.next() && n > 0) {
                 String[] splitstr=rs.getString(7).split(",");
                inf+="<div class=\"pic\" style=\" float:left; width:21%;height:240px\">";
                  inf+="<div style=\" float:left\">";
                inf+="<img height=\"200px\" width=\"100%\" src=\"pic\\"+splitstr[0]+"\">";
                inf +="</div>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";
                inf +="名称: "+rs.getString(2) + "  <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"" + "查看详情" + "\"></FORM><p>";
                n--;
                 inf +="</div>";
            }
             inf+="</div><br>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！";
    }


    @Override
    public void clear() {
        name = null;
    }

    @Override
   public String searchjourneyforlist(String type, String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 种类='" + type + "' AND 产品名称 LIKE '%" + key + "%'");
            String inf = "";
             inf+="<div style=\" width:100%;height:700px;overflow:scroll;\">";
            while (rs.next()) {
                String[] splitstr=rs.getString(7).split(",");
                inf += "<div class=\"pic\" style=\"float:left;height:240px;width:21%;\">";
                 inf+="<div style=\" float:left\">";
                inf+="<img height=\"200px\" width=\"100%\" src=\"pic\\"+splitstr[0]+"\">";
                inf +="</div>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";
                inf +=  "名称： " + rs.getString(2) + " <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"" + "查看详情" + "\"></FORM>";
                inf+="</div>";
// inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
            }
            inf+="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }

    @Override
    public boolean userbuyjourney(String n) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 产品预定 where 操作者名='" + name + "' AND 产品代号='" + n + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            String sql = "INSERT INTO 产品预定 VALUES ('" + name + "', '" + n + "','0')";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String searchticketforlist(String start, String terminal, String type) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票 where 种类='" + type + "' AND 始发地='" + start + "' AND 目的地='" + terminal + "'");
            String inf = "";
             int a=0;
                 inf+="<div style=\"width:100%;height:35px\">";
                  inf+="<div style=\"float:left; width:6%;height:30px\"></div>";
                inf+="<div style=\"float:left; width:13%;height:30px\">";
                inf += "票务代号";
                 inf+="</div>";
                inf+="<div style=\"float:left; width:13%;height:30px\">";
                inf += "始发地";
                 inf+="</div>";
                  inf+="<div style=\"float:left; width:13%;height:30px\">";
                inf += "目的地";
                 inf+="</div>";
                inf+="<div style=\"float:left; width:13%;height:30px\">";
               inf += "时间";
                inf+="</div>";
                inf+="<div style=\"float:left; width:13%;height:30px\">";
                inf += "价格（元）";
                inf+="</div>";
                inf+="<div style=\"float:left; width:13%;height:30px\">";
                inf += "车次";
                inf+="</div>";
                  inf+="</div>";
            while (rs.next()) {
                // inf+="<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";  
                 a++;
                if(a==1)
                      inf+="<div style=\"width:100%;overflow-y:scroll;height:400px;\">";
                inf+="<div style=\"width:100%;height:55px\">";//1
                  inf+="<div style=\"float:left; width:6%;height:50px\"></div>";//空白
                  inf+="<div style=\"float:left; width:13%;height:30px\">";
                    inf += rs.getString(1)+" ";
                    inf+="</div>";
                   inf+="<div style=\"float:left; width:13%;height:30px\">";
                 inf += rs.getString(3)+" ";
                   inf+="</div>";
                  inf+="<div style=\"float:left; width:13%;height:30px\">";
                 inf += rs.getString(4)+" ";    
                    inf+="</div>";
                 inf+="<div style=\"float:left; width:13%;height:30px\">";
                   inf += rs.getString(5).substring(0, 10)+" ";
                    inf+="</div>";
                   inf+="<div style=\"float:left; width:13%;height:30px\">";
                     inf += rs.getString(6)+" ";
                      inf+="</div>";
                      inf+="<div style=\"float:left; width:13%;height:30px\">";
                      inf += rs.getString(7)+" ";
                       inf+="</div>";
                    inf +="<div style=\"float:left;width: 7%;height:50px\">";    
               // inf2 = type + "  " + rs.getString(2) +" "+ rs.getString(3) +" "+ rs.getString(4) +" "+ rs.getString(5)+" " + rs.getString(6)+" " + rs.getString(7)+" ";//+"                 <input type=\"submit\" name=\"s1\" value=\""+rs.getString(7)+"\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"立 即 预 定\"  onclick=\"tip9()\"></FORM><p>";
                   inf+="</div>" ;
                   inf+="</div>" ;
//inf2 ="";
            }
            if(a==0)
            return inf;
             else
                 return inf+"</div>";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }


    @Override
    public boolean userbuyticket(String n) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票预定 where 购买者名='" + name + "' AND 购买票代号='" + n + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 票预定 values (?,?,?,?)");
            ps1.setString(2, name);
            ps1.setString(3, n);
            ps1.setString(4, "0");
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            ps1.setDate(1, date);
            ps1.execute();
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
   public String showdetail(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
            String inf = "";
            
            while (rs.next()) {
                String[] splitstr=rs.getString(7).split(",");
                inf +=" <p class=\"biaoqian\" align=\"center\">产 品 简 介</p>";
                inf+="<div style=\"width:1400px;height:380px;\">";
                inf+="<div style=\"float:left; width:440px;height:360px\">";
                inf+="<img height=\"340px\" width=\"400px\" src=\"pic\\"+splitstr[0]+"\">";
                inf+="</div>";  
                inf+="<div style=\"float:left;width:950px;height:360px\">";
                inf +="<div style=\"width:550px;height:100px; \">";
                //inf += rs.getString(1)+" ";
                inf += "名称： "+rs.getString(2)+"<br>";
                //inf += rs.getString(3)+" ";
                inf += "可预定时间: "+rs.getString(4).substring(0, 10)+"<br>";
                inf += "限时优惠价： "+rs.getString(5)+" 元<hr>";
                inf += "</div>";
                inf +="<div style=\"width:550px;height:200px;\">";
                inf += "简介: "+rs.getString(6)+" ";
                inf += "<hr></div>";
                inf +=  "<div style=\"margin-top:10px;width:550px;height:50px; \">  <div style=\"float:left\">\n" +
"            <FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart>    \n" +
"              <input type=\"submit\" name=\"s\" value=\"产 品 预 定\" onclick=\"tip4()\">&nbsp;&nbsp;&nbsp;&nbsp;\n" +
"             </FORM>\n" +
"            </div>\n" +
"            <div style=\"float:left\">\n" +
"                <FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddfavourite>    \n" +
"                <input type=\"submit\" name=\"s\" value=\"立 即 收 藏\" onclick=\"tip5()\">\n" +
"                </FORM>\n" +
"            </div></div>";
                inf +="</div>";
                inf +="</div>";
                inf +=" <p class=\"biaoqian\" align=\"center\">产 品 详 情</p>";
                inf +="<div style=\"text-align: center;\">";
                inf+="<img style=\"display: inline-block;\" src=\"pic\\"+splitstr[1]+"\">";
                inf +="</div>";
               // inf += rs.getString(7)+"<p>";

            }

            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！";
    }


    @Override
     public String showcomment(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 评论 where 评论产品代号='" + key + "'");
            String inf = "";
            int a=0;
              inf+="<div style=\"width:100%;height:35px \">";
                  inf+="<div style=\"float:left; width:10%;height:30px\"></div>";
                inf+="<div style=\"float:left; width:20%;height:30px\">";
                inf += " 评 论 时 间 ";
                inf+="</div>";
                inf+="<div style=\"float:left; width:55%;height:30px\">";
                inf += "内 容 ";
                inf+="</div>";
                 inf+="<div style=\"float:left; width:10%;height:30px\">";
                inf += " 用 户 名 ";
                inf+="</div>";
                 inf+="</div>";
            
           
            while (rs.next()) {
                a++;
                if(a==1)
                    inf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
                 inf+="<div style=\"width:100%;height:55px\">";
                  inf+="<div style=\"float:left; width:10%;height:50px\"></div>";
                inf+="<div style=\"float:left; width:20%;height:50px\">";
                inf += rs.getString(1).substring(0, 10)+" ";
                inf+="</div>";
                inf+="<div style=\"float:left; width:55%;overflow-y:scroll;height:50px\">";
                inf += rs.getString(2)+" ";
                inf+="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(3)+" ";
                inf+="</div>";
                 inf+="</div>";
                 
            }
            if(a!=0)
            inf+="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！";
    }

    @Override
    public boolean comment(String key, String comment) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 评论(评论时间,评论内容,评论者名,评论产品代号) VALUES (?,?,?,?)");
            ps1.setString(2, comment);
            ps1.setString(3, name);
            ps1.setString(4, key);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            ps1.setDate(1, date);
            ps1.execute();
            con.close();

            return true;
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
        return false;
    }

    @Override
    public boolean useraddjourneytofavourite(String n) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 收藏 where 操作者名='" + name + "' AND 产品代号='" + n + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            String sql = "INSERT INTO 收藏 VALUES ('" + name + "', '" + n + "')";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean repassword(String newpassword) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            String sql = "UPDATE 用户 SET 密码='" + newpassword + "' WHERE 用户名='" + name + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
  public String showjourneycart() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 产品代号 from 产品预定 where 操作者名='" + name + "' AND 是否付款='0'");
            String hotelinf = "";
            String journeyinf = "";
            int a=0;
               String hotelinf2 = "";
            String journeyinf2 = "";
              hotelinf+="<div style=\"width:100%;height:35px\">";
                   hotelinf+="<div style=\"float:left; width:10%;height:30px\"></div>";
                 hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                 hotelinf += "名  称";
                 hotelinf+="</div>";
                hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                 hotelinf += "预 约 时 间";
                 hotelinf+="</div>";
                  hotelinf+="<div style=\"float:left; width:10%;height:30px\">";
                hotelinf += "价  格（元）";
                 hotelinf+="</div>";
                  hotelinf+="</div>";
            while (rs.next()) {
                  a++;
                if(a==1)
                      hotelinf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
                rs2.next();
                if (rs2.getString(3).equals("酒店") || rs2.getString(3).equals("已下架酒店")) {
                     hotelinf+="<div style=\"width:100%;height:55px\">";//1
                    hotelinf+="<div style=\"float:left; width:10%;height:50px\"></div>";//空白
                     hotelinf+="<div style=\"float:left; width:30%;height:50px\">"; // 名称
                    hotelinf += rs2.getString(2)+" ";
                    hotelinf+="</div>";
                        hotelinf+="<div style=\"float:left; width:30%;height:50px\">"; //时间
                    hotelinf += rs2.getString(4).substring(0, 10)+" ";
                      hotelinf+="</div>";
                hotelinf+="<div style=\"float:left; width:10%;height:50px\">";//价格
                    //hotelinf2 += rs2.getString(6)+" ";
                    hotelinf += rs2.getString(5)+" ";
                     hotelinf+="</div>";
                 //hotelinf+="</div>";
                    hotelinf +="<div style=\"float:left;width: 8%;height:50px\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeypay> "+hotelinf2+"<input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"立即付款\" onclick=\"tip7()\" ></FORM> ";
                    hotelinf += "</div>";
                    hotelinf += " <div style=\"float:left;width: 8%;height:50px\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeydel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"一键退订\" onclick=\"tip8()\"></FORM>";
                    hotelinf+= "</div>";
                    hotelinf+= "</div>";
               
                } else {
                    journeyinf+="<div style=\"width:100%;height:55px\">";//1
                    journeyinf+="<div style=\"float:left; width:10%;height:50px\"></div>";//空白
                    journeyinf+="<div style=\"float:left; width:30%;height:50px\">"; // 名称
                    journeyinf += rs2.getString(2)+" ";
                   journeyinf+="</div>";
                       journeyinf+="<div style=\"float:left; width:30%;height:50px\">"; //时间
                    journeyinf += rs2.getString(4).substring(0, 10)+" ";
                      journeyinf+="</div>";
               journeyinf+="<div style=\"float:left; width:10%;height:50px\">";//价格
                    //hotelinf2 += rs2.getString(6)+" ";
                    journeyinf += rs2.getString(5)+" ";
                     journeyinf+="</div>";
                 //hotelinf+="</div>";
                    journeyinf +="<div style=\"float:left;width: 8%;height:50px\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeypay> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"立即付款\" onclick=\"tip7()\"></FORM>";
                  journeyinf +=  "</div>";
                   journeyinf += " <div style=\"float:left;width: 8%;height:50px\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeydel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"一键退订\" onclick=\"tip8()\"></FORM>";
                    journeyinf+= "</div>";
                    journeyinf+= "</div>";
            
                }
            }
            if(a==0)
            return hotelinf + journeyinf;
            else
                return hotelinf + journeyinf+"</div>";
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public boolean journeypay(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 种类 from 旅游产品 where 产品代号='" + key + "'");
            rs.next();
            if (rs.getString(1).equals("已下架酒店") || rs.getString(1).equals("已下架旅游团")) {
                return false;
            }
            String sql = "UPDATE 产品预定 SET 是否付款='1' WHERE 操作者名='" + name + "' AND 产品代号='" + key + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean journeydel(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();

            String sql = "DELETE FROM 产品预定 WHERE 操作者名='" + name + "' AND 产品代号='" + key + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
   public String showticketcart() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 购买票代号 from 票预定 where 购买者名='" + name + "' AND 是否付款='0'");
            String traininf = "";
            String planeinf = "";
            String traininf2 = "";
            String planeinf2 = "";
            int a=0;
              traininf+="<div style=\"width:100%;height:35px\">";
                  traininf+="<div style=\"float:left; width:6%;height:30px\"></div>";
                traininf+="<div style=\"float:left; width:13%;height:30px\">";
                traininf += "票务代号";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:13%;height:30px\">";
                traininf += "始发地";
                 traininf+="</div>";
                  traininf+="<div style=\"float:left; width:13%;height:30px\">";
                traininf += "目的地";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:13%;height:30px\">";
                traininf += "时间";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:13%;height:30px\">";
                traininf += "价格（元）";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:13%;height:30px\">";
                traininf += "车次";
                 traininf+="</div>";
                  traininf+="</div>";
            while (rs.next()) {
                a++;
                if(a==1)
                      traininf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 票 where 票代号='" + key + "'");
                rs2.next();
                if (rs2.getString(2).equals("火车票") || rs2.getString(2).equals("已下架火车票")) {
                     traininf+="<div style=\"width:100%;height:55px\">";//1
                   traininf+="<div style=\"float:left; width:6%;height:50px\"></div>";//空白
                    traininf+="<div style=\"float:left; width:13%;height:30px\">";
                    traininf += rs2.getString(1)+" ";
                    traininf+="</div>";
                     traininf+="<div style=\"float:left; width:13%;height:30px\">";
                    traininf += rs2.getString(3)+" ";
                     traininf+="</div>";
                     traininf+="<div style=\"float:left; width:13%;height:30px\">";
                    traininf += rs2.getString(4)+" ";    
                     traininf+="</div>";
                     traininf+="<div style=\"float:left; width:13%;height:30px\">";
                    traininf += rs2.getString(5).substring(0, 10)+" ";
                     traininf+="</div>";
                     traininf+="<div style=\"float:left; width:13%;height:30px\">";
                     traininf += rs2.getString(6)+" ";
                      traininf+="</div>";
                      traininf+="<div style=\"float:left; width:13%;height:30px\">";
                      traininf += rs2.getString(7)+" ";
                       traininf+="</div>";
                     traininf +="<div style=\"float:left;width: 7%;height:50px\">";
                    traininf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketpay><input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"立即付款\" onclick=\"tip7()\"></FORM>";
                    traininf += "</div>";
                     traininf +="<div style=\"float:left;width: 7%;height:50px\">";
                    traininf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"一键退订\" onclick=\"tip8()\"></FORM>";
                    traininf += "</div>";
                    traininf += "</div>";
        
                } else {
                      planeinf+="<div style=\"width:100%;height:55px\">";//1
                   planeinf+="<div style=\"float:left; width:6%;height:50px\"></div>";//空白
                    planeinf+="<div style=\"float:left; width:13%;height:30px\">";
                    planeinf += rs2.getString(1)+" ";
                    planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:13%;height:30px\">";
                   planeinf += rs2.getString(3)+" ";
                     planeinf+="</div>";
                    planeinf+="<div style=\"float:left; width:13%;height:30px\">";
                   planeinf += rs2.getString(4)+" ";    
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:13%;height:30px\">";
                    planeinf += rs2.getString(5).substring(0, 10)+" ";
                     planeinf+="</div>";
                    planeinf+="<div style=\"float:left; width:13%;height:30px\">";
                    planeinf += rs2.getString(6)+" ";
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:13%;height:30px\">";
                     planeinf += rs2.getString(7)+" ";
                      planeinf+="</div>";
                    planeinf+="<div style=\"float:left;width: 7%;height:50px\">";
                    planeinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketpay> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"立即付款\" onclick=\"tip7()\"></FORM>";
                     planeinf += "</div>";
                     planeinf+="<div style=\"float:left;width: 7%;height:50px\">";
                    planeinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"一键退订\" onclick=\"tip8()\"></FORM>";
                    planeinf += "</div>";
                    planeinf += "</div>";
                }
            }
              if(a==0)
            return traininf + planeinf;
            else
                return traininf + planeinf+"</div>";
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public boolean ticketpay(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 种类 from 票 where 票代号='" + key + "'");
            rs.next();
            if (rs.getString(1).equals("已下架火车票") || rs.getString(1).equals("已下架飞机票")) {
                return false;
            }
            String sql = "UPDATE 票预定 SET 是否付款='1' WHERE 购买者名='" + name + "' AND 购买票代号='" + key + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean ticketdel(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();

            String sql = "DELETE FROM 票预定 WHERE 购买者名='" + name + "' AND 购买票代号='" + key + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
     public String showjourneyorder() {
          try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 产品代号 from 产品预定 where 操作者名='" + name + "' AND 是否付款='1'");
            String hotelinf = "";
            String journeyinf = "";
            int a=0;
                    hotelinf+="<div style=\"width:100%;height:35px\">";
                   hotelinf+="<div style=\"float:left; width:10%;height:30px\"></div>";
                 hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                 hotelinf += "名  称";
                 hotelinf+="</div>";
                hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                 hotelinf += "预 约 时 间";
                 hotelinf+="</div>";
                  hotelinf+="<div style=\"float:left; width:10%;height:30px\">";
                hotelinf += "价  格（元）";
                 hotelinf+="</div>";
                  hotelinf+="</div>";
            while (rs.next()) {
                a++;
                  if(a==1)
                      hotelinf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
                rs2.next();
                if (rs2.getString(3).equals("酒店")) {
                       hotelinf+="<div style=\"width:100%;height:55px\">";//1
                    hotelinf+="<div style=\"float:left; width:10%;height:50px\"></div>";//空白
                      hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                    hotelinf += rs2.getString(2)+" ";
                       hotelinf+="</div>";
                hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                    hotelinf += rs2.getString(4).substring(0, 10)+" ";
                      hotelinf+="</div>";
                  hotelinf+="<div style=\"float:left; width:10%;height:30px\">";
                    //hotelinf += rs2.getString(6)+" ";
                    hotelinf += rs2.getString(5)+" ";
                      hotelinf+="</div>";
                  hotelinf+="</div>";
                } else {
                       journeyinf+="<div style=\"width:100%;height:55px\">";//1
                    journeyinf+="<div style=\"float:left; width:10%;height:50px\"></div>";//空白
                      journeyinf+="<div style=\"float:left; width:30%;height:30px\">";
                    journeyinf += rs2.getString(2)+" ";
                      journeyinf+="</div>";
                         journeyinf+="<div style=\"float:left; width:30%;height:30px\">";
                    journeyinf += rs2.getString(4).substring(0, 10)+" ";
                         journeyinf+="</div>";
                   journeyinf+="<div style=\"float:left; width:10%;height:30px\">";
                    //journeyinf += rs2.getString(6)+" ";
                    journeyinf += rs2.getString(5)+" ";
                          journeyinf+="</div>";
                   journeyinf+="</div>";
                }
            }
            if(a==0)
            return hotelinf + journeyinf;
            else
            return hotelinf + journeyinf+"</div>";
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
     public String showticketorder() {
         try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 购买票代号 from 票预定 where 购买者名='" + name + "' AND 是否付款='1'");
            String traininf = "";
            String planeinf = "";
             int a=0;
              traininf+="<div style=\"width:100%;height:35px\">";
                  traininf+="<div style=\"float:left; width:8%;height:30px\"></div>";
                traininf+="<div style=\"float:left; width:15%;height:30px\">";
                traininf += "票务代号";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:15%;height:30px\">";
                traininf += "始发地";
                 traininf+="</div>";
                  traininf+="<div style=\"float:left; width:15%;height:30px\">";
                traininf += "目的地";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:15%;height:30px\">";
                traininf += "时间";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:15%;height:30px\">";
                traininf += "价格（元）";
                 traininf+="</div>";
                traininf+="<div style=\"float:left; width:15%;height:30px\">";
                traininf += "车次";
                 traininf+="</div>";
                  traininf+="</div>";
            while (rs.next()) {
                  a++;
                if(a==1)
                      traininf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 票 where 票代号='" + key + "'");
                rs2.next();
                if (rs2.getString(2).equals("火车票") || rs2.getString(2).equals("已下架火车票")) {
                        traininf+="<div style=\"width:100%;height:55px\">";//1
                   traininf+="<div style=\"float:left; width:8%;height:50px\"></div>";//空白
                    traininf+="<div style=\"float:left; width:15%;height:30px\">";
                    traininf += rs2.getString(1)+" ";
                     traininf+="</div>";
                    traininf+="<div style=\"float:left; width:15%;height:30px\">";
                    traininf += rs2.getString(3)+" ";
                     traininf+="</div>";
                    traininf+="<div style=\"float:left; width:15%;height:30px\">";
                    traininf += rs2.getString(4)+" ";
                     traininf+="</div>";
                    traininf+="<div style=\"float:left; width:15%;height:30px\">";
                    traininf += rs2.getString(5).substring(0, 10)+" ";
                     traininf+="</div>";
                    traininf+="<div style=\"float:left; width:15%;height:30px\">";
                     traininf += rs2.getString(6)+" ";
                      traininf+="</div>";
                     traininf+="<div style=\"float:left; width:15%;height:30px\">";
                      traininf += rs2.getString(7)+" ";
                     traininf+="</div>";
                    traininf+="</div>";
                } else {
                     planeinf+="<div style=\"width:100%;height:55px\">";//1
                   planeinf+="<div style=\"float:left; width:8%;height:50px\"></div>";//空白
                    planeinf+="<div style=\"float:left; width:15%;height:30px\">";
                    planeinf += rs2.getString(1)+" ";
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:15%;height:30px\">";
                    planeinf += rs2.getString(3)+" ";
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:15%;height:30px\">";
                    planeinf += rs2.getString(4)+" ";
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:15%;height:30px\">";
                    planeinf += rs2.getString(5).substring(0, 10)+" ";
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:15%;height:30px\">";
                    planeinf += rs2.getString(6)+" ";
                     planeinf+="</div>";
                     planeinf+="<div style=\"float:left; width:15%;height:30px\">";
                    planeinf += rs2.getString(7)+" ";    
                      planeinf+="</div>";
                    planeinf+="</div>";
                }
            }
            if(a==0)
            return traininf + planeinf;
            else
                return traininf + planeinf+"</div>";
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }
    @Override
    public String showfavourite() {
           try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 产品代号 from 收藏 where 操作者名='" + name + "'");
            String hotelinf = "";
            String journeyinf = "";
            int a=0;
                  hotelinf+="<div style=\"width:100%;height:35px\">";
                   hotelinf+="<div style=\"float:left; width:10%;height:30px\"></div>";
                 hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                 hotelinf += "名  称";
                 hotelinf+="</div>";
                hotelinf+="<div style=\"float:left; width:30%;height:30px\">";
                 hotelinf += "预 约 时 间";
                 hotelinf+="</div>";
                  hotelinf+="<div style=\"float:left; width:10%;height:30px\">";
                hotelinf += "价  格（元）";
                 hotelinf+="</div>";
                  hotelinf+="</div>";
                   //hotelinf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
            while (rs.next()) {
                
                a++;
                if(a==1)
                      hotelinf+="<div style=\"width:100%;overflow:scroll;height:180px;\">";
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
                rs2.next();
                if (rs2.getString(3).equals("酒店")) {
                      hotelinf+="<div style=\"width:100%;height:55px\">";//1
                    hotelinf+="<div style=\"float:left; width:10%;height:50px\"></div>";//空白
                     hotelinf+="<div style=\"float:left; width:30%;height:50px\">"; // 名称
                    hotelinf += rs2.getString(2)+" ";
                    hotelinf+="</div>";
                        hotelinf+="<div style=\"float:left; width:30%;height:50px\">"; //时间
                    hotelinf += rs2.getString(4).substring(0, 10)+" ";
                      hotelinf+="</div>";
                hotelinf+="<div style=\"float:left; width:10%;height:50px\">";//价格
                    //hotelinf2 += rs2.getString(6)+" ";
                    hotelinf += rs2.getString(5)+" ";
                     hotelinf+="</div>";
                 //hotelinf+="</div>";
                    hotelinf +="<div style=\"float:left;width: 8%;height:50px\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=favtodetail><input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"查看详情\"></FORM>";
                    hotelinf += "</div>";
                    hotelinf += " <div style=\"float:left;width: 8%;height:50px\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyfavdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"取消收藏\" onclick=\"tip6()\"></FORM>";
                    hotelinf += "</div>";
                     hotelinf += "</div>";//1
                    
                } else {
                             journeyinf+="<div style=\"width:100%;height:55px\">";//1
                    journeyinf+="<div style=\"float:left; width:10%;height:50px\"></div>";//空白
                    journeyinf+="<div style=\"float:left; width:30%;height:50px\">"; // 名称
                    journeyinf += rs2.getString(2)+" ";
                   journeyinf+="</div>";
                       journeyinf+="<div style=\"float:left; width:30%;height:50px\">"; //时间
                    journeyinf += rs2.getString(4).substring(0, 10)+" ";
                      journeyinf+="</div>";
               journeyinf+="<div style=\"float:left; width:10%;height:50px\">";//价格
                    //hotelinf2 += rs2.getString(6)+" ";
                    journeyinf += rs2.getString(5)+" ";
                     journeyinf+="</div>";
                 //hotelinf+="</div>";
                    journeyinf +="<div style=\"float:left;width: 8%;height:50px\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=favtodetail><input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"查看详情\"></FORM>";
                    journeyinf +=  "</div>";
                   journeyinf += " <div style=\"float:left;width: 8%;height:50px\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyfavdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"取消收藏\" onclick=\"tip6()\"></FORM>";
                    journeyinf += "</div>";
                    journeyinf += "</div>";
                }
            }
            if(a==0)
            return hotelinf + journeyinf;
            else
                return hotelinf + journeyinf+"</div>";
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }
    @Override
    public boolean favdel(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            String sql = "DELETE FROM 收藏 WHERE 操作者名='" + name + "' AND 产品代号='" + key + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 @Override
    public String showallproductlist() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品");
            String inf = "";
            inf+="<div>";
            inf+="<div class=\"main\">";
           
                inf+="<div class=\"pic\" style=\"width: 90%;height:35px\">";
                inf+="<div style=\"float:left; width:20%;height:35px\">";
                //inf += rs.getString(6)+" ";
                 inf+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相关图片";
                //inf += rs.getString(7)+" ";
                 inf +="</div>";
                  inf+="<div style=\"float:left;width:2%;height:35px\"></div>";
                inf+="<div style=\"float:left;text-align: center;width:10%;height:35px\">";
                inf += "产品代号";
                inf +="</div>";
                 inf+="<div style=\"float:left;ext-align: center; width:10%;height:35px\">";
                inf += "产品名称";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center;width:12%;height:35px\">";
                inf += "产品种类";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:14%;height:35px\">";
                inf += "预约时间";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:14%;height:35px\">";
                inf += "价格（元）";
                 inf +="</div>";
                     inf+="<div style=\"float:left;text-align: center; width:10%;height:35px\">";
                inf += "相关操作";
                 inf +="</div>";
                  inf +="</div>";
            while (rs.next()) {
                String[] splitstr=rs.getString(7).split(",");
                inf+="<div style=\"width: 90%;height:10px\">";
                inf +="</div>";
                inf+="<div class=\"pic\" style=\"width: 90%;height:160px\">";
                inf+="<div style=\"float:left; width:20%;height:160px\">";
                //inf += rs.getString(6)+" ";
                 inf+="<img height=\"160px\" width=\"100%\" src=\"pic\\"+splitstr[0]+"\">";
                //inf += rs.getString(7)+" ";
                 inf +="</div>";
                  inf+="<div style=\"float:left;width:2%;height:160px\"></div>";
                inf+="<div style=\"float:left;margin-top: 25px; text-align: center;width:10%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(1)+"</p>";
                inf +="</div>";
                 inf+="<div style=\"float:left;margin-top: 25px;text-align: center; width:10%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(2)+"</p>";
                 inf +="</div>";
                 inf+="<div style=\"float:left; margin-top: 25px;text-align: center;width:12%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(3)+"</p>";
                 inf +="</div>";
                 inf+="<div style=\"float:left;margin-top: 25px;text-align: center; width:14%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(4).substring(0, 10)+"</p>";
                 inf +="</div>";
                 inf+="<div style=\"float:left;margin-top: 25px;text-align: center; width:14%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(5)+"</p> ";
                 inf +="</div>";
              
                 
                inf += "<div style=\"float:left; width: 10%;margin-top:10px;height:130px\">";
                inf += "<div style=\"margin-top:4px\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeproduct>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修 改 信 息\"></FORM></div>";
                inf += "<div style=\"margin-top:4px\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=productoff>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下 架 产 品\"></FORM></div>";
                inf += "<div style=\"margin-top:4px\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=producton>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上 架 产 品\"></FORM></div>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
                inf +="</div>";
                inf +="</div>";
                inf+="<div style=\"width: 90%;height:10px\">";
                inf +="</div>";
            }
            inf+="</div></div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }

    @Override
    public boolean productoff(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 种类 from 旅游产品 where 产品代号='" + key + "'");
            rs.next();
            if (rs.getString(1).equals("酒店")) {
                String sql = "UPDATE 旅游产品 SET 种类='已下架酒店' WHERE 产品代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            if (rs.getString(1).equals("旅游团")) {
                String sql = "UPDATE 旅游产品 SET 种类='已下架旅游团' WHERE 产品代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean producton(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 种类 from 旅游产品 where 产品代号='" + key + "'");
            rs.next();
            if (rs.getString(1).equals("已下架酒店")) {
                String sql = "UPDATE 旅游产品 SET 种类='酒店' WHERE 产品代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            if (rs.getString(1).equals("已下架旅游团")) {
                String sql = "UPDATE 旅游产品 SET 种类='旅游团' WHERE 产品代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String showallticketlist() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票");
            String inf = "";
             inf+="<div>";
            inf+="<div class=\"main\">";
           
                inf+="<div class=\"pic\" style=\"width: 90%;height:35px\">";
                inf+="<div style=\"float:left; width:10%;height:35px\">";
                //inf += rs.getString(6)+" ";
                 inf+="&nbsp;&nbsp;&nbsp;&nbsp;票务代号";
                //inf += rs.getString(7)+" ";
                 inf +="</div>";
                  inf+="<div style=\"float:left;width:2%;height:35px\"></div>";
                inf+="<div style=\"float:left;text-align: center;width:12%;height:35px\">";
                inf += "票务种类";
                inf +="</div>";
                 inf+="<div style=\"float:left;ext-align: center; width:12%;height:35px\">";
                inf += "始发地";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center;width:12%;height:35px\">";
                inf += "目的地";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:12%;height:35px\">";
                inf += "预约时间";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:10%;height:35px\">";
                inf += "价格（元）";
                 inf +="</div>";
                     inf+="<div style=\"float:left;text-align: center; width:10%;height:35px\">";
                inf += "车次";
                 inf +="</div>";
                     inf+="<div style=\"float:left;text-align: center; width:16%;height:35px\">";
                inf += "相关操作";
                 inf +="</div>";
                  inf +="</div>";
            while (rs.next()) {
                inf+="<div style=\"width: 90%;height:10px\">";
                inf +="</div>";
                 inf+="<div class=\"pic\" style=\"width: 90%;height:50px\">";
                 inf+="<div style=\"float:left; width:2%;height:5px\"></div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(1)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(2)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(3)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(4)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(5).substring(0,10)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(6)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(7)+" ";
                  inf +="</div>";
                inf+="<div style=\"float:left; width:16%;height:50px\">";
                inf += "<div style=\"float:left\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeticket>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM></div>";
                inf += "<div style=\"float:left\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketoff>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM></div>";
                inf += "<div style=\"float:left\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketon>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM></div>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
                inf +="</div>";
                inf +="</div>";
            }
            inf +="</div>";
            inf +="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }

    @Override
    public boolean ticketoff(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 种类 from 票 where 票代号='" + key + "'");
            rs.next();
            if (rs.getString(1).equals("飞机票")) {
                String sql = "UPDATE 票 SET 种类='已下架飞机票' WHERE 票代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            if (rs.getString(1).equals("火车票")) {
                String sql = "UPDATE 票 SET 种类='已下架火车票' WHERE 票代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean ticketon(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select 种类 from 票 where 票代号='" + key + "'");
            rs.next();
            if (rs.getString(1).equals("已下架飞机票")) {
                String sql = "UPDATE 票 SET 种类='飞机票' WHERE 票代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            if (rs.getString(1).equals("已下架火车票")) {
                String sql = "UPDATE 票 SET 种类='火车票' WHERE 票代号='" + key + "'";
                st.executeUpdate(sql);
                con.close();
                return true;
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String showallcomment() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 评论");
            ResultSet rs2 = st.executeQuery("select 评论代号 from 评论");
            String inf = "";
              inf+="<div>";
            inf+="<div class=\"main\">";
             inf+="<div style=\"width:90%;height:30px;background-color:#4BAF62;margin-bottom:5px;text-align: center;\"> 评 价 情 况 </div>";
             inf+="<div class=\"pic\"style=\"width:90%;height:35px \">";
        
             inf+="<div style=\"float:left; width:6%;height:35px\"></div>";
                inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "评论代号";
                inf+="</div>";
                inf+="<div style=\"float:left; width:15%;height:35px\">";
                inf += "评论时间";
                inf+="</div>";
                 inf+="<div style=\"float:left; width:30%;height:35px\">";
                inf += "评论内容";
                inf+="</div>";
                 inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "用户名";
                inf+="</div>";
                  inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "产品代号";
                inf+="</div>";
                  inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "相关操作";
                inf+="</div>";
                 inf+="</div>";
                  inf+="<div  style=\"width:100%;height:10px\"></div>";
                   // inf+="</div>";
            while (rs.next()) {
                rs2.next();
                //inf += rs2.getString(1) + "       ";
                inf+="<div class=\"pic\" style=\"width:90%;height:55px\">";
                  //inf+="<div style=\"float:left; width:10%;height:50px\"></div>";
                  inf+="<div style=\"float:left; width:6%;height:50px\"></div>";
                  inf+="<div style=\"float:left; width:10%;height:50px\">";
                  inf += rs.getString(5)+" ";
                   inf+="</div>";
                inf+="<div style=\"float:left; width:15%;height:50px\">";
                inf += rs.getString(1).substring(0, 10)+" ";
                   inf+="</div>";
                inf+="<div style=\"float:left; width:30%;overflow-y:scroll;height:50px\">";
                inf += rs.getString(2)+" ";
                  inf+="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(3)+" ";
                inf+="</div>";
                inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(4)+" ";
                inf+="</div>";
                inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=deletecomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs2.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"删除评论\" onclick=\"tip2()\"></FORM>";
                inf += "</div>";
                inf += "</div>";
                 inf+="<div  style=\"width:100%;height:10px\">";
                 inf += "</div>";
            }
                inf+="<div>";
                    inf+="<div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }

    @Override
    public boolean deletecomment(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();

            String sql = "DELETE FROM 评论 WHERE 评论代号='" + key + "'";
            st.executeUpdate(sql);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String showusercomment(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 评论 WHERE 评论者名='" + key + "'");
            ResultSet rs2 = st.executeQuery("select 评论代号 from 评论 WHERE 评论者名='" + key + "'");
            String inf = "";
  inf+="<div>";
            inf+="<div class=\"main\">";
            inf+="<div style=\"width:90%;height:30px;background-color:#4BAF62;margin-bottom:5px;text-align: center;\"> 评 价 情 况 </div>";
             inf+="<div class=\"pic\"style=\"width:90%;height:35px \">";
        
             inf+="<div style=\"float:left; width:6%;height:35px\"></div>";
                inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "评论代号";
                inf+="</div>";
                inf+="<div style=\"float:left; width:15%;height:35px\">";
                inf += "评论时间";
                inf+="</div>";
                 inf+="<div style=\"float:left; width:30%;height:35px\">";
                inf += "评论内容";
                inf+="</div>";
                 inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "用户名";
                inf+="</div>";
                  inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "产品代号";
                inf+="</div>";
                  inf+="<div style=\"float:left; width:10%;height:35px\">";
                inf += "相关操作";
                inf+="</div>";
                 inf+="</div>";
                  inf+="<div  style=\"width:100%;height:10px\"></div>";
            while (rs.next()) {
                rs2.next();
                 inf+="<div class=\"pic\" style=\"width:90%;height:55px\">";
                  //inf+="<div style=\"float:left; width:10%;height:50px\"></div>";
                  inf+="<div style=\"float:left; width:6%;height:50px\"></div>";
                  inf+="<div style=\"float:left; width:10%;height:50px\">";
                  inf += rs.getString(5)+" ";
                   inf+="</div>";
                inf+="<div style=\"float:left; width:15%;height:50px\">";
                inf += rs.getString(1).substring(0, 10)+" ";
                   inf+="</div>";
                inf+="<div style=\"float:left; width:30%;overflow-y:scroll;height:50px\">";
                inf += rs.getString(2)+" ";
                  inf+="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(3)+" ";
                inf+="</div>";
                inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(4)+" ";
                inf+="</div>";
                inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=deleteusercomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs2.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"删除评价\" onclick=\"tip2()\"></FORM>";
           inf += "</div>";
                inf += "</div>";
                 inf+="<div  style=\"width:100%;height:10px\">";
                 inf += "</div>";
            }
                inf+="<div>";
                    inf+="<div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！";
    }

    @Override
    public String showuserproduct(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 产品预定 where 操作者名='" + key + "'");
            String inf = "";
             inf+="<div>";
        
             inf+="<div style=\"width:90%;height:30px;background-color:#4BAF62;margin:5px;text-align: center;\">预 约 产 品 情 况</div>";
            while (rs.next()) {
                 inf+="<div class=\"pic\" style=\"width:90%;height:40px;margin:5px;\">";
                inf+="<div  style=\"float:left;height:35px;width:15%;\"></div>";
                 inf+="<div  style=\"float:left;height:35px;width:20%;\">";
                inf += "用户名:&nbsp;"+rs.getString(1)+"&nbsp;&nbsp;&nbsp;&nbsp;";
                 inf+="</div>";
                   inf+="<div  style=\"float:left;height:35px;width:35%;\">";
                inf += "预约产品代号:&nbsp;"+rs.getString(2)+"&nbsp;&nbsp;&nbsp;&nbsp;";
                 inf+="</div>";
                   inf+="<div  style=\"float:left;height:35px;width:10%;\">";
                inf += "是否付款:&nbsp;"+rs.getString(3)+" ";
                inf+="</div>";
                 inf+="<div  style=\"float:left;height:35px;width:10%;\"></div>";
                 inf+="</div>";
            }
         
            inf+="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public String showuserticket(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票预定 where 购买者名='" + key + "'");
            String inf = "";
              inf+="<div>";
          
             
             inf+="<div style=\"width:90%;height:30px;background-color:#4BAF62;margin:5px;text-align: center;\">预 约 票 务 情 况</div>";
            while (rs.next()) {
                   inf+="<div class=\"pic\" style=\"width:90%;height:40px;margin:5px;\">";
                   inf+="<div  style=\"float:left;height:35px;width:8%;\"></div>";
                   inf+="<div  style=\"float:left;height:35px;width:20%;\">";
                inf += "预约时间:"+rs.getString(1).substring(0,10)+" ";
                  inf+="</div>";
                inf+="<div  style=\"float:left;height:35px;width:20%;\">";
                inf += "用户名:"+rs.getString(2)+" ";
                  inf+="</div>";
                inf+="<div  style=\"float:left;height:35px;width:20%;\">";
                inf += "票务代码:"+rs.getString(3)+" ";
                  inf+="</div>";
                inf+="<div  style=\"float:left;height:35px;width:10%;\">";
                inf += "是否付款:"+rs.getString(4)+" ";
                  inf+="</div>";
                  inf+="<div  style=\"float:left;height:35px;width:8%;\"></div>";
                    inf+="</div>";
            }
           
            inf+="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public String showuser() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 用户 where 身份='用户'");
            String inf = "";
            inf+="<div>";
            inf+="<div class=\"main\">";
            inf+="<div style=\"width:90%;height:30px;background-color:#4BAF62;margin-bottom:5px;text-align: center;\"> 用 户 列 表 </div>";
       
            while (rs.next()) {
                inf+="<div class=\"pic\"style=\"float:left;height:40px;width:300px;margin:10px\">";
                inf+="<div style=\"float:left;height:40px;width:200px\">";
                inf += "&nbsp;&nbsp;用户名： "+rs.getString(1)+" ";
                 inf += "</div>";
                inf+="<div style=\"float:left;height:40px;width:100px\">";
               // inf += rs.getString(3)+" ";
                //inf += "<div class=\"top\" style=\"width: 100%;height:25px\">";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=tousercomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"详细信息\"></FORM>&nbsp;&nbsp;&nbsp;&nbsp;";
                inf += "</div>";
                 inf += "</div>";
                  inf+="<div style=\"float:left;height:40px;width:30px\"></div>";
            }
             inf+="</div>";
             inf+="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public String searchforproductlist(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 WHERE 产品名称 LIKE '%" + key + "%'");
            String inf = "";
                inf+="<div>";
            inf+="<div class=\"main\">";
           
                inf+="<div class=\"pic\" style=\"width: 90%;height:35px\">";
                inf+="<div style=\"float:left; width:20%;height:35px\">";
                //inf += rs.getString(6)+" ";
                 inf+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相关图片";
                //inf += rs.getString(7)+" ";
                 inf +="</div>";
                  inf+="<div style=\"float:left;width:2%;height:35px\"></div>";
                inf+="<div style=\"float:left;text-align: center;width:10%;height:35px\">";
                inf += "产品代号";
                inf +="</div>";
                 inf+="<div style=\"float:left;ext-align: center; width:10%;height:35px\">";
                inf += "产品名称";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center;width:12%;height:35px\">";
                inf += "产品种类";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:14%;height:35px\">";
                inf += "预约时间";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:14%;height:35px\">";
                inf += "价格（元）";
                 inf +="</div>";
                     inf+="<div style=\"float:left;text-align: center; width:10%;height:35px\">";
                inf += "相关操作";
                 inf +="</div>";
                  inf +="</div>";
            while (rs.next()) {
                String[] splitstr=rs.getString(7).split(",");
                inf+="<div style=\"width: 90%;height:10px\">";
                inf +="</div>";
                inf+="<div class=\"pic\" style=\"width: 90%;height:160px\">";
                inf+="<div style=\"float:left; width:20%;height:160px\">";
                //inf += rs.getString(6)+" ";
                 inf+="<img height=\"160px\" width=\"100%\" src=\"pic\\"+splitstr[0]+"\">";
                //inf += rs.getString(7)+" ";
                 inf +="</div>";
                  inf+="<div style=\"float:left;width:2%;height:160px\"></div>";
                inf+="<div style=\"float:left;margin-top: 25px; text-align: center;width:10%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(1)+"</p>";
                inf +="</div>";
                 inf+="<div style=\"float:left;margin-top: 25px;text-align: center; width:10%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(2)+"</p>";
                 inf +="</div>";
                 inf+="<div style=\"float:left; margin-top: 25px;text-align: center;width:12%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(3)+"</p>";
                 inf +="</div>";
                 inf+="<div style=\"float:left;margin-top: 25px;text-align: center; width:14%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(4).substring(0, 10)+"</p>";
                 inf +="</div>";
                 inf+="<div style=\"float:left;margin-top: 25px;text-align: center; width:14%;height:130px\">";
                inf += "<p style=\"width:100%;height:45px:\">"+rs.getString(5)+"</p> ";
                 inf +="</div>";
              
                 
                  inf += "<div style=\"float:left; width: 10%;margin-top:10px;height:130px\">";
                inf += "<div style=\"margin-top:4px\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeproduct>";
                
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM></div>";
                inf += "<div style=\"margin-top:4px\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=productoff2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM></div>";
                inf += "<div style=\"margin-top:4px\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=producton2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM></div>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
                inf +="</div>";
                inf +="</div>";
                inf+="<div style=\"width: 90%;height:10px\">";
                inf +="</div>";
            }
            inf+="</div></div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }
    @Override
    public String searchforticketlist(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票 where 票代号 LIKE '%" + key + "%' OR 始发地 LIKE '%" + key + "%' OR 目的地 LIKE '%" + key + "%'");
            String inf = "";
              inf+="<div>";
            inf+="<div class=\"main\">";
           
                inf+="<div class=\"pic\" style=\"width: 90%;height:35px\">";
                inf+="<div style=\"float:left; width:10%;height:35px\">";
                //inf += rs.getString(6)+" ";
                 inf+="&nbsp;&nbsp;&nbsp;&nbsp;票务代号";
                //inf += rs.getString(7)+" ";
                 inf +="</div>";
                  inf+="<div style=\"float:left;width:2%;height:35px\"></div>";
                inf+="<div style=\"float:left;text-align: center;width:12%;height:35px\">";
                inf += "票务种类";
                inf +="</div>";
                 inf+="<div style=\"float:left;ext-align: center; width:12%;height:35px\">";
                inf += "始发地";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center;width:12%;height:35px\">";
                inf += "目的地";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:12%;height:35px\">";
                inf += "预约时间";
                 inf +="</div>";
                 inf+="<div style=\"float:left;text-align: center; width:10%;height:35px\">";
                inf += "价格（元）";
                 inf +="</div>";
                     inf+="<div style=\"float:left;text-align: center; width:10%;height:35px\">";
                inf += "车次";
                 inf +="</div>";
                     inf+="<div style=\"float:left;text-align: center; width:16%;height:35px\">";
                inf += "相关操作";
                 inf +="</div>";
                  inf +="</div>";
            while (rs.next()) {
              inf+="<div style=\"width: 90%;height:10px\">";
                inf +="</div>";
                 inf+="<div class=\"pic\" style=\"width: 90%;height:50px\">";
                 inf+="<div style=\"float:left; width:2%;height:5px\"></div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(1)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(2)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(3)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(4)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:12%;height:50px\">";
                inf += rs.getString(5).substring(0,10)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(6)+" ";
                  inf +="</div>";
                 inf+="<div style=\"float:left; width:10%;height:50px\">";
                inf += rs.getString(7)+" ";
                  inf +="</div>";
                inf+="<div style=\"float:left; width:16%;height:50px\">";
                inf += "<div style=\"float:left\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeticket>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM></div>";
                inf += "<div style=\"float:left\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketoff2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM></div>";
                inf += "<div style=\"float:left\"><FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketon2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM></div>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
               inf +="</div>";
                inf +="</div>";
            }
            inf +="</div>";
            inf +="</div>";
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！！";
    }
    @Override
    public boolean newproduct(String name, String key, String type, int year, int month, int datee, int price, String detail, String url) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "' OR 产品名称='" + name + "'");

            if (rs.next()) {
                con.close();
                return false;
            }
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 旅游产品 values (?,?,?,?,?,?,?)");
            ps1.setString(2, name);
            ps1.setString(1, key);
            ps1.setString(3, type);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(year - 1900, month, datee);
            ps1.setDate(4, date);
            ps1.setInt(5, price);
            ps1.setString(6, detail);
            ps1.setString(7, url);
            ps1.execute();
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    @Override
    public boolean newticket(String key, String start, String terminal, int year, int month, int datee, int price, String type, String num) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票 where 票代号='" + key + "' OR 车次='" + num + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 票 values (?,?,?,?,?,?,?)");
            ps1.setString(2, type);
            ps1.setString(1, key);
            ps1.setString(3, start);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(year - 1900, month, datee);
            ps1.setDate(5, date);
            ps1.setInt(6, price);
            ps1.setString(4, terminal);
            ps1.setString(7, num);
            ps1.execute();
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    @Override
    public String changeproductpage(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
            String inf = "";
            rs.next();
            inf += "&nbsp;&nbsp;代号："+rs.getString(1) + "<p>";
            inf +="&nbsp;&nbsp;类别："+ rs.getString(3) + "<p>";
            
            inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=changeproduct>";
            inf += "&nbsp;名称：<input type=\"hidden\" name=\"key\" value=\"" + rs.getString(1) + "\">";
            inf += "<input type=\"hidden\" name=\"type\" value=\"" + rs.getString(3) + "\">";
            inf += "<input type=\"text\" name=\"name\" value=\"" + rs.getString(2) + "\"><p>";
          
            inf += "&nbsp;日期: <input type=\"text\" name=\"year\" value=\"" + (rs.getDate(4).getYear()+1900)+ "\">&nbsp;&nbsp;年&nbsp;<input type=\"text\" name=\"month\" value=\"" + rs.getDate(4).getMonth() + "\">&nbsp;&nbsp;月&nbsp;<input type=\"text\" name=\"date\" value=\"" + rs.getDate(4).getDate() + "\">&nbsp;&nbsp;日<p>";
            inf += "&nbsp;价格：<input type=\"text\" name=\"price\" value=\"" + rs.getInt(5) + "\"><p>";
            inf += "&nbsp;图片URL: <input type=\"text\" name=\"url\" value=\"" + rs.getString(7) + "\"><p>";
            inf += "&nbsp;详细信息：<input id=\"jieshao\"type=\"text\" name=\"detail\" value=\"" + rs.getString(6) + "\">&nbsp;&nbsp;&nbsp;&nbsp;";
            inf += "<input id=\"new\"type=\"submit\" name=\"s1\" value=\"确认修改\" onclick=\"tip()\"></FORM>";
            
            return inf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "出错啦！！";
    }

    public boolean changeproduct(String name, String key, String type, int year, int month, int datee, int price, String detail, String url) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("UPDATE 旅游产品 SET 产品名称=?,时间=?,价格=?,详细信息=?,图片URL=? WHERE 产品代号='" + key + "'");
            ps1.setString(1, name);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(year-1900, month, datee);
            ps1.setDate(2, date);
            ps1.setInt(3, price);
            ps1.setString(4, detail);
            ps1.setString(5, url);
            ps1.execute();
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    public String changeticketpage(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票 where 票代号='" + key + "'");
            String inf = "";

            rs.next();
            inf += "&nbsp;&nbsp;代号："+rs.getString(1) + "<p>";
            inf += "&nbsp;&nbsp;类型："+rs.getString(2) + "<p>";
            inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=changeticket>";
            inf += "&nbsp;始发地：<input type=\"hidden\" name=\"key\" value=\"" + rs.getString(1) + "\">";
            inf += "<input type=\"text\" name=\"start\" value=\"" + rs.getString(3) + "\"><p>";
            inf += "&nbsp;目的地：<input type=\"hidden\" name=\"type\" value=\"" + rs.getString(2) + "\">";
            inf += "<input type=\"text\" name=\"terminal\" value=\"" + rs.getString(4) + "\"><p>";
           
            inf += "&nbsp;日期: <input type=\"text\" name=\"year\" value=\"" + (rs.getDate(5).getYear()+1900)+ "\">&nbsp;&nbsp;年&nbsp;<input type=\"text\" name=\"month\" value=\"" + rs.getDate(5).getMonth() + "\">&nbsp;&nbsp;月&nbsp;<input type=\"text\" name=\"date\" value=\"" + rs.getDate(5).getDate() + "\">&nbsp;&nbsp;日<p>";
            inf += "&nbsp;价格: <input type=\"text\" name=\"price\" value=\"" + rs.getInt(6) + "\"><p>";
            inf += "&nbsp;车次：<input type=\"text\" name=\"num\" value=\"" + rs.getString(7) + "\">&nbsp;&nbsp;&nbsp;&nbsp;";
            inf += " <input id=\"new\" type=\"submit\" name=\"s1\" value=\"确认修改\" onclick=\"tip()\"></FORM>";
            return inf;
        } catch (ClassNotFoundException ex) {
            return ex.toString();
        } catch (SQLException ex) {
            return ex.toString();
        }

    }

    public boolean changeticket(String key, String start, String terminal, int year, int month, int datee, int price, String type, String num) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("UPDATE 票 SET 车次=?,始发地=?,目的地=?,出发时间=?,价格=? WHERE 票代号='" + key + "'");
            ps1.setString(1, num);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(year-1900, month, datee);
            ps1.setDate(4, date);
            ps1.setInt(5, price);
            ps1.setString(2, start);
            ps1.setString(3, terminal);
            ps1.execute();
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }

    }
}
