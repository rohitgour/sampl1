
package evoting.controller;

import evoting.dao.RegistrationDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfileControllerServlet extends HttpServlet {

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
        UserDetails user=new UserDetails();
        HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("userid");
        String data=request.getParameter("data");
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
           
            if(data.equals("getform"))
           {
             
             try
            {
               user=RegistrationDao.getUserDetails(userid);
               request.setAttribute("user", user);
               rd=request.getRequestDispatcher("index.jsp");
            }
             catch(Exception e) 
            {
              request.setAttribute("exception", e);
              rd=request.getRequestDispatcher("showexception.jsp");
              e.printStackTrace();
            }
             finally
            {
              rd.forward(request, response);
            }
           }
            if(data.equals("data"))
           {
                user.setUserid(request.getParameter("userid"));
                user.setPassword(request.getParameter("password"));
                user.setAddress(request.getParameter("address"));
                user.setCity(request.getParameter("city"));
                user.setEmail(request.getParameter("email" ));
                user.setMobile(Long.parseLong(request.getParameter("mobile")));
                user.setUsername(request.getParameter("username"));
             try
             {
                 boolean result=false;
                 result=RegistrationDao.updateUser(user);
                 System.out.println("Result in registerDao "+result);
                 request.setAttribute("result", result);                
                 rd=request.getRequestDispatcher("updateprofile.jsp");
              } 
             catch (SQLException ex)
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
