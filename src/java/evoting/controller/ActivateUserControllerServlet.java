
package evoting.controller;

import evoting.dao.UserDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActivateUserControllerServlet extends HttpServlet {

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
        HttpSession session=request.getSession();
        RequestDispatcher rd=null;
        String userid=(String)session.getAttribute("userid");
        String data=request.getParameter("data");
        System.out.println("Data = "+data);
          if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
          try
          {
               if(data!=null && data.equals("activate"))
               {
                  ArrayList<UserDetails> user=UserDao.activateUser();
                  System.out.println("user is empty = "+user);
                  request.setAttribute("result","details");
                  request.setAttribute("user",user); 
                  rd=request.getRequestDispatcher("activateuser.jsp");
               }
               else
               {
                  boolean status=UserDao.getActivateUser(data);
                  request.setAttribute("status",status);
                  rd=request.getRequestDispatcher("useractivated.jsp");
               }       }
           catch(Exception e)
          {
              rd=request.getRequestDispatcher("showexception.jsp");
              e.printStackTrace();
          }
          finally
          {
              rd.forward(request, response);
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

}
