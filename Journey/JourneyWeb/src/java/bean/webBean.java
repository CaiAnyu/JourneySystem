/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ResultSet rs = st.executeQuery("select 密码 from 用户 where 用户名='" + adminname + "' AND 身份='管理员'" );
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
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\TEST.accdb");
            String sql = "INSERT INTO 用户 VALUES (\"wwwww\", \"11111111\",'用户');";

            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.executeUpdate();
            con.close();
            return "成功";
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
            return "????????????";
        } catch (SQLException ex) {

            Logger.getLogger(webBean.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }

    }
}
