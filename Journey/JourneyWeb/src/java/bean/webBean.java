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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            String n = "xhc123";
           
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 评论(评论时间,评论内容,评论者名,评论产品代号) VALUES (?,?,?,?)");
            ps1.setString(2, "差评");
            ps1.setString(3, name);
            ps1.setString(4, "02");
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            ps1.setDate(1, date);
            ps1.execute();
            con.close();

            return "成功";
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 种类='酒店'");
            String inf = "";
            if (n <= 0) {
                return null;
            }
            while (rs.next() && n > 0) {
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";
                inf += "酒店             " + rs.getString(2) + "                 <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"" + rs.getString(7) + "\"></FORM><p>";
                n--;
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
    public String getusername() {
        return name;
    }

    @Override
    public String showjourney(int n) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 种类='旅游团'");
            String inf = "";
            if (n <= 0) {
                return null;
            }
            while (rs.next() && n > 0) {
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";
                inf += "旅游团             " + rs.getString(2) + "                 <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"><input type=\"submit\" name=\"s1\" value=\"" + rs.getString(7) + "\"></FORM><p>";
                n--;
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
    public void clear() {
        name = null;
    }

    @Override
    public String searchjourneyforlist(String type, String key) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 预定 where 操作者名='" + name + "' AND 产品代号='" + n + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            String sql = "INSERT INTO 预定 VALUES ('" + name + "', '" + n + "')";
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 票 where 种类='" + type + "' AND 始发地='" + start + "' AND 目的地='" + terminal + "'");
            String inf = "";
            while (rs.next()) {
                // inf+="<FORM METHOD=\"post\" ACTION=CtrlServlet?method=listproductdetail>";  
                inf += type + "             " + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5) + rs.getString(6) + rs.getString(7);//+"                 <input type=\"submit\" name=\"s1\" value=\""+rs.getString(7)+"\"></FORM>";
                inf += "<FORM METHOD=\"post\" ACTION=CtrlServlet?method=ticketaddcart> <input type=\"hidden\" name=\"num\" value=\"" + rs.getString(1) + "\"> <input type=\"submit\" value=\"加入购物车\"></FORM><p>";
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 购买 where 购买者名='" + name + "' AND 购买票代号='" + n + "'");
            if (rs.next()) {
                con.close();
                return false;
            }
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 购买 values (?,?,?)");
            ps1.setString(2, name);
            ps1.setString(3, n);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 旅游产品 where 产品代号='"+key+"'");
            String inf = "";
            
            while (rs.next()) {
                inf += rs.getString(1);
                inf += rs.getString(2);
                inf += rs.getString(3);
                inf += rs.getString(4);
                inf += rs.getString(5);
                inf += rs.getString(6);
                inf += rs.getString(7);
               
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from 评论 where 评论产品代号='"+key+"'");
            String inf = "";
            
            while (rs.next()) {
                inf += rs.getString(1);
                inf += rs.getString(2);
                inf += rs.getString(3);
                inf += rs.getString(4);
                inf += rs.getString(5);
    
               
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
    public boolean comment(String key,String comment) {
         try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
            Statement st = con.createStatement();          
            PreparedStatement ps1 = null;
            ps1 = con.prepareStatement("INSERT INTO 评论(评论时间,评论内容,评论者名,评论产品代号) VALUES (?,?,?,?)");
            ps1.setString(2, comment);
            ps1.setString(3, name);
            ps1.setString(4, key);
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\JourneyWeb.accdb");
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

}
