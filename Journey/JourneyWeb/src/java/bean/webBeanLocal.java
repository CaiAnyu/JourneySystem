/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.ejb.Local;

/**
 *
 * @author 78288
 */
@Local
public interface webBeanLocal {

    boolean userlogin(String username, String password);

    boolean adminlogin(String username, String password);

    boolean register(String username, String password);

    String test();

    String showhotel(int n);

    String getusername();

    String showjourney(int n);

    void clear();

    String searchjourneyforlist(String type, String key);

    boolean userbuyjourney(String n);

    String searchticketforlist(String start, String terminal, String type);

    boolean userbuyticket(String n);

    String showdetail(String key);

    String showcomment(String key);

    boolean comment(String key,String comment);

    boolean useraddjourneytofavourite(String n);

    boolean repassword(String newpassword);

    String showjourneycart();

    boolean journeypay(String key);

    boolean journeydel(String key);

    String showticketcart();

    boolean ticketpay(String key);

    boolean ticketdel(String key);

    String showjourneyorder();

    String showticketorder();

    String showfavourite();

    boolean favdel(String key);

    String showallproductlist();

    boolean productoff(String key);

    boolean producton(String key);

    String showallticketlist();

    boolean ticketon(String key);

    boolean ticketoff(String key);

    String showallcomment();

    boolean deletecomment(String key);

    String showusercomment(String key);

    String showuserproduct(String key);

    String showuserticket(String key);

    String showuser();

    String searchforproductlist(String key);

    String searchforticketlist(String key);

    boolean newproduct(String name, String key, String type, int year, int month, int date, int price, String detail, String url);

    boolean newticket(String key, String start, String terminal, int year, int month, int datee, int price, String type, String num);

    String changeproductpage(String key);
    boolean changeproduct(String name, String key, String type, int year, int month, int datee, int price, String detail, String url);
    String changeticketpage(String key);
     boolean changeticket(String key, String start, String terminal, int year, int month, int datee, int price, String type, String num) ;
    
}
