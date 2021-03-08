
package evoting.controller;

import evoting.dao.CandidateDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shivin John
 */
public class DeleteCandidateControllerServlet extends HttpServlet {

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
        HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("userid");
        if(userid==null)
        {
              session.invalidate();
              response.sendRedirect("accessdenied.html");
              return;
        }
        String remove=request.getParameter("remove");
        try{
               if(remove!=null && remove.equals("remove"))
               {
                   String cid=request.getParameter("cid");
                   System.out.println("Cid to remove = "+cid);
                   boolean status=CandidateDao.removeCandidate(cid);
                   System.out.println("Status = "+status);
                   request.setAttribute("status",status);
                   
               }
               rd=request.getRequestDispatcher("deletecandidate.jsp");
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
