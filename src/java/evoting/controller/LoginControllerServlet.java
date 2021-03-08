
package evoting.controller;

import evoting.dao.UserDao;
import evoting.dto.UserDto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginControllerServlet extends HttpServlet {

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
        RequestDispatcher rd=null;
        UserDto user=new UserDto();
        user.setUserid(request.getParameter("userid"));
        user.setPassword(request.getParameter("password"));
        System.out.println("User id = "+request.getParameter("userid"));
        System.out.println("Password = "+request.getParameter("password"));
        try   
        { 
           
            String  result=UserDao.validateUser(user);
            if(result!=null)
            {
                boolean status=UserDao.isUserActive(request.getParameter("userid"));
                request.setAttribute("status", status);         
            }
            request.setAttribute("result", result);
         //   System.out.println("Userid = "+request.getParameter("userid"));
            request.setAttribute("userid", request.getParameter("userid"));
            rd=request.getRequestDispatcher("loginresponse.jsp");
        }
        catch (Exception ex) 
        {
            request.setAttribute("exception", ex);
            rd=request.getRequestDispatcher("showexception.jsp");
            ex.printStackTrace();
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
