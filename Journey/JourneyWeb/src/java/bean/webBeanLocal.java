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
    
}
