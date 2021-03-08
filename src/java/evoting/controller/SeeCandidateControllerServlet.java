
package evoting.controller;

import evoting.dao.UserDao;
import evoting.dao.VoteDao;
import evoting.dto.CandidateDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SeeCandidateControllerServlet extends HttpServlet {

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
        String vote=request.getParameter("vote");
        System.out.println("Vote = "+vote);
        try
        {
            if(userid==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
            //System.out.println("UserId = "+userid);
            String cid=VoteDao.getCandidateId(userid);
            //System.out.println("cid = "+cid);
            CandidateDto candidate;
            if(cid!=null)
            {
                candidate=VoteDao.getVote(cid);
                request.setAttribute("candidate", candidate);
                rd=request.getRequestDispatcher("votedenied.jsp");
            }
            else
            {
                ArrayList<CandidateDto> candidateList=UserDao.viewCandidate(userid);
                request.setAttribute("candidateList", candidateList);
                if(vote!=null)
                     rd=request.getRequestDispatcher("castvote.jsp");
                 else
                   rd=request.getRequestDispatcher("showcandidate.jsp");
            }
        } 
        catch(SQLException ex) 
        {
             ex.printStackTrace();
             System.out.println(ex.getMessage());
             rd=request.getRequestDispatcher("showexception.jsp");
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
