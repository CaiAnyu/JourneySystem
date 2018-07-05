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
               inf+="<div style=\" width:1500px;height:350px\">";
            while (rs.next() && n > 0) {
                inf+="<div class=\"pic\" style=\" float:left;\">";
                inf+="<div style=\" float:left\">";
                inf+="<img height=\"250px\" width=\"300px\" src=\"pic\\"+rs.getString(7)+"\">";
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
            inf+="<div style=\" width:1500px;height:350px\">";
            while (rs.next() && n > 0) {
                inf+="<div class=\"pic\" style=\" float:left;\">";
                  inf+="<div style=\" float:left\">";
                inf+="<img height=\"250px\" width=\"300px\" src=\"pic\\"+rs.getString(7)+"\">";
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
            while (rs.next()) {
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";
                inf += type + "             " + rs.getString(2) + "                 <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"" + rs.getString(7) + "\"></FORM>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
            }
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
            String inf2 = "";
            while (rs.next()) {
                // inf+="<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";  
                inf2 = type + "  " + rs.getString(2) +" "+ rs.getString(3) +" "+ rs.getString(4) +" "+ rs.getString(5)+" " + rs.getString(6)+" " + rs.getString(7)+" ";//+"                 <input type=\"submit\" name=\"s1\" value=\""+rs.getString(7)+"\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketaddcart>"+inf2+" <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
                inf2 ="";
            }
            return inf;
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
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4).substring(0, 10)+" ";
                inf += rs.getString(5)+" ";
                inf += rs.getString(6)+" ";
                inf += rs.getString(7)+"<p>";

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

            while (rs.next()) {
                inf += rs.getString(1).substring(0, 10)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+"<p>";

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
               String hotelinf2 = "";
            String journeyinf2 = "";
            while (rs.next()) {
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
                rs2.next();
                if (rs2.getString(3).equals("酒店") || rs2.getString(3).equals("已下架酒店")) {
                    hotelinf2 += rs2.getString(2)+" ";
                    hotelinf2+= rs2.getString(4).substring(0, 10)+" ";
                    hotelinf2 += rs2.getString(6)+" ";
                    hotelinf2 += rs2.getString(5)+" ";
                    hotelinf+="<div style=\"width: 100%;height:45px\"><div style=\"float:left\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeypay> "+hotelinf2+"<input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"付款\">&nbsp;&nbsp;</FORM> ";
                    hotelinf+= "</div>";
                    hotelinf+= " <div style=\"float:left\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeydel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"退订\"></FORM>";
                    hotelinf+= "</div>";
                    hotelinf+= "</div>";
                     hotelinf2=" ";
                } else {
                    journeyinf2 += rs2.getString(2)+" ";
                    journeyinf2 += rs2.getString(4).substring(0, 10)+" ";
                    journeyinf2 += rs2.getString(6)+" ";
                    journeyinf2 += rs2.getString(5)+" ";
                    journeyinf+="<div style=\"width: 100%;height:45px\"><div style=\"float:left\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeypay>"+journeyinf2+" <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"付款\">&nbsp;&nbsp;</FORM>";
                    journeyinf+= "</div>";
                    journeyinf+= " <div style=\"float:left\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeydel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"退订\"></FORM>";
                    journeyinf+= "</div>";
                    journeyinf+= "</div>";
                    journeyinf2 =" ";
                }
            }
            return hotelinf + journeyinf;
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
            while (rs.next()) {
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 票 where 票代号='" + key + "'");
                rs2.next();
                if (rs2.getString(2).equals("火车票") || rs2.getString(2).equals("已下架火车票")) {
                    traininf2 += rs2.getString(1)+" ";
                    traininf2 += rs2.getString(3)+" ";
                    traininf2 += rs2.getString(4)+" ";                
                    traininf2 += rs2.getString(5).substring(0, 10)+" ";
                     traininf2 += rs2.getString(6)+" ";
                      traininf2 += rs2.getString(7)+" ";
                    traininf+="<div style=\"width: 100%;height:45px\"><div style=\"float:left\">";
                    traininf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketpay> "+traininf2+"<input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"付款\">&nbsp;&nbsp;</FORM>";
                    traininf += "</div>";
                    traininf += " <div style=\"float:left\">";
                    traininf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"退订\"></FORM>";
                    traininf += "</div>";
                    traininf += "</div>";
                    traininf2 =" ";
                } else {
                    planeinf2 += rs2.getString(1)+" ";
                    planeinf2 += rs2.getString(3)+" ";
                    planeinf2 += rs2.getString(4)+" ";
                    planeinf2 += rs2.getString(5).substring(0, 10)+" ";
                    planeinf2 += rs2.getString(6)+" ";
                    planeinf2 += rs2.getString(7)+" ";
                    planeinf +="<div style=\"width: 100%;height:45px\"><div style=\"float:left\">";
                    planeinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketpay> "+ planeinf2+"<input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"付款\">&nbsp;&nbsp;</FORM>";
                     planeinf += "</div>";
                   planeinf += " <div style=\"float:left\">";
                    planeinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"退订\"></FORM>";
                    planeinf += "</div>";
                    planeinf += "</div>";
                    planeinf2 = " ";
                }
            }
            return traininf + planeinf;
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
            while (rs.next()) {
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
                rs2.next();
                if (rs2.getString(3).equals("酒店")) {
                    hotelinf += rs2.getString(2)+" ";
                    hotelinf += rs2.getString(4).substring(0, 10)+" ";
                    hotelinf += rs2.getString(6)+" ";
                    hotelinf += rs2.getString(5)+" ";
                    
                    hotelinf += "<p>";
                } else {
                    journeyinf += rs2.getString(2)+" ";
                    journeyinf += rs2.getString(4).substring(0, 10)+" ";
                    journeyinf += rs2.getString(6)+" ";
                    journeyinf += rs2.getString(5)+" ";
                    
                    journeyinf += "<p>";
                }
            }
            return hotelinf + journeyinf;
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
            while (rs.next()) {
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 票 where 票代号='" + key + "'");
                rs2.next();
                if (rs2.getString(2).equals("火车票") || rs2.getString(2).equals("已下架火车票")) {
                    traininf += rs2.getString(1)+" ";
                    traininf += rs2.getString(3)+" ";
                    traininf += rs2.getString(4)+" ";                
                    traininf += rs2.getString(5).substring(0, 10)+" ";
                     traininf += rs2.getString(6)+" ";
                      traininf += rs2.getString(7)+" ";
                    
                    traininf += "<p>";
                } else {
                    planeinf += rs2.getString(1)+" ";
                    planeinf += rs2.getString(3)+" ";
                    planeinf += rs2.getString(4)+" ";
                    planeinf += rs2.getString(5).substring(0, 10)+" ";
                    planeinf += rs2.getString(6)+" ";
                    planeinf += rs2.getString(7)+" ";    
                    planeinf += "<p>";
                }
            }
            return traininf + planeinf;
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
            String traininf = "";
            String planeinf = "";
            String hotelinf = "";
            String journeyinf = "";
            String hotelinf2 = "";
            String journeyinf2 = "";
            while (rs.next()) {
                String key = rs.getString(1);
                ResultSet rs2 = st.executeQuery("select * from 旅游产品 where 产品代号='" + key + "'");
                rs2.next();
                if (rs2.getString(3).equals("酒店")) {
                    hotelinf2 += rs2.getString(2)+" ";
                    hotelinf2 += rs2.getString(4).substring(0, 10)+" ";
                    hotelinf2 += rs2.getString(6)+" ";
                    hotelinf2 += rs2.getString(5)+" ";
                    
                    hotelinf +="<div style=\"width: 100%;height:40px\"><div style=\"float:left\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=favtodetail>"+hotelinf2+"<input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"详情\">&nbsp;&nbsp;</FORM>";
                    hotelinf += "</div>";
                    hotelinf += " <div style=\"float:left\">";
                    hotelinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyfavdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"取消收藏\"></FORM>";
                    hotelinf += "</div>";
                    
                     hotelinf += "</div>";
                    hotelinf2=" ";
                } else {
                    journeyinf2 += rs2.getString(2)+" ";
                    journeyinf2 += rs2.getString(4).substring(0, 10)+" ";
                    journeyinf2 += rs2.getString(6)+" ";
                    journeyinf2 += rs2.getString(5)+" ";
                    
                    journeyinf += "<div style=\"width: 100%;height:40px\"><div style=\"float:left\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=favtodetail>"+journeyinf2+"<input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"详情\">&nbsp;&nbsp;</FORM>";
                    journeyinf +=  "</div>";
                    journeyinf +=  " <div style=\"float:left\">";
                    journeyinf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyfavdel> <input type=\"hidden\" name=\"num\" value=\"" + key + "\"> <input type=\"submit\" value=\"取消收藏\"></FORM>";
                    journeyinf += "</div>";
                    journeyinf += "</div>";
                    journeyinf2 = "";
                }
            }
            return hotelinf + journeyinf;
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
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+" ";
                inf += rs.getString(6)+" ";
                inf += rs.getString(7)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeproduct>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=productoff>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=producton>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM><p>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
            }
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
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+" ";
                inf += rs.getString(6)+" ";
                inf += rs.getString(7)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeticket>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketoff>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketon>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
            }
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
            while (rs.next()) {
                rs2.next();
                inf += rs2.getString(1) + "       ";
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=deletecomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs2.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"删除\"></FORM>";
            }
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

            while (rs.next()) {
                rs2.next();
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=deleteusercomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs2.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"删除\"></FORM>";
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
    public String showuserproduct(String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 产品预定 where 操作者名='" + key + "'");
            String inf = "";
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
            }
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
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
            }
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
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(3)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=tousercomment>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"看详细信息\"></FORM>";
            }
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
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+" ";
                inf += rs.getString(6)+" ";
                inf += rs.getString(7)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeproduct>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=productoff2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=producton2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
            }
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
            while (rs.next()) {
                inf += rs.getString(1)+" ";
                inf += rs.getString(2)+" ";
                inf += rs.getString(3)+" ";
                inf += rs.getString(4)+" ";
                inf += rs.getString(5)+" ";
                inf += rs.getString(6)+" ";
                inf += rs.getString(7)+" ";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=tochangeticket>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"修改\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketoff2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"下架\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketon2>";
                inf += "<input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"上架\"></FORM>";
                // inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=journeyaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
            }
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
            inf += rs.getString(1) + "<p>";
            inf += rs.getString(3) + "<p>";
            inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=changeproduct>";
            inf += "<input type=\"hidden\" name=\"key\" value=\"" + rs.getString(1) + "\">";
            inf += "<input type=\"hidden\" name=\"type\" value=\"" + rs.getString(3) + "\">";
            inf += "<input type=\"text\" name=\"name\" value=\"" + rs.getString(2) + "\"><p>";
            inf += "<input type=\"text\" name=\"year\" value=\"" + rs.getDate(4).getYear() + "\"><input type=\"text\" name=\"month\" value=\"" + rs.getDate(4).getMonth() + "\"><input type=\"text\" name=\"date\" value=\"" + rs.getDate(4).getDate() + "\"><p>";
            inf += "<input type=\"text\" name=\"price\" value=\"" + rs.getInt(5) + "\">";
            inf += "<input type=\"text\" name=\"detail\" value=\"" + rs.getString(6) + "\">";
            inf += "<input type=\"text\" name=\"url\" value=\"" + rs.getString(7) + "\">";
            inf += " <input type=\"submit\" name=\"s1\" value=\"确认\"></FORM>";
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
            java.sql.Date date = new java.sql.Date(year, month, datee);
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
            inf += rs.getString(1) + "<p>";
            inf += rs.getString(2) + "<p>";
            inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=changeticket>";
            inf += "<input type=\"hidden\" name=\"key\" value=\"" + rs.getString(1) + "\">";
            inf += "<input type=\"hidden\" name=\"type\" value=\"" + rs.getString(2) + "\">";
            inf += "<input type=\"text\" name=\"start\" value=\"" + rs.getString(3) + "\"><p>";
            inf += "<input type=\"text\" name=\"terminal\" value=\"" + rs.getString(4) + "\"><p>";
            inf += "<input type=\"text\" name=\"year\" value=\"" + rs.getDate(5).getYear() + "\"><input type=\"text\" name=\"month\" value=\"" + rs.getDate(5).getMonth() + "\"><input type=\"text\" name=\"date\" value=\"" + rs.getDate(5).getDate() + "\"><p>";
            inf += "<input type=\"text\" name=\"price\" value=\"" + rs.getInt(6) + "\">";
            inf += "<input type=\"text\" name=\"num\" value=\"" + rs.getString(7) + "\">";
            inf += " <input type=\"submit\" name=\"s1\" value=\"确认\"></FORM>";
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
            java.sql.Date date = new java.sql.Date(year, month, datee);
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
