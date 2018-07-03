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
                        RequestDispatcher disp = request.getRequestDispatcher("userindex.jsp");
                        disp.forward(request, response);
                    } else {
                        session.setAttribute("loginfalse", "false");
                        RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
                        disp.forward(request, response);
                    }
                }
            }
            if (new String(request.getParameter("method")).equals("logintoregister")) {
                RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                disp.forward(request, response);
            }

            if (new String(request.getParameter("method")).equals("register")) {//注册
                String name = request.getParameter("username");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                if (name.isEmpty() || password.isEmpty() || repassword.isEmpty() || name.length() > 20 || password.length() != 8) {
                    session.setAttribute("registerfalse", "false");
                    RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
                    disp.forward(request, response);
                }
                if (password.equals(repassword)) {
                   //    out.println(this.webBean.test());
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
