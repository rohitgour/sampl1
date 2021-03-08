
package evoting.controller;

import evoting.dao.CandidateDao;
import evoting.dao.VoteDao;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ElectionResultControllerServlet extends HttpServlet {

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
        String data=request.getParameter("data");
        String admin=request.getParameter("admin");
        System.out.println("Admin = "+admin);
        try
        {
            
            String cid=VoteDao.getCandidateId(userid);
            System.out.println("Cid of Admin = "+cid);
          if(cid!=null || (admin!=null && admin.equals("admin")))
          {
            if(data!=null && data.equals("result"))
            {
                LinkedHashMap<String,Integer> result=VoteDao.getResult();
                System.out.println(result.isEmpty());
                Set s=result.entrySet();
                LinkedHashMap<CandidateDetails,Integer> resultDetails=new LinkedHashMap<CandidateDetails,Integer>(); 
                Iterator it=s.iterator();
                while(it.hasNext())
                {
                    Map.Entry<String,Integer> e=(Map.Entry)it.next();
                    CandidateDetails c=CandidateDao.getDetailsById(e.getKey());
                    String username=CandidateDao.getUsernameById(c.getUserId());
                    c.setCandidateName(username);
                    resultDetails.put(c,e.getValue());
                }
                request.setAttribute("votecount",VoteDao.getVoteCount());
                request.setAttribute("result", resultDetails);
                rd=request.getRequestDispatcher("electionresult.jsp");
            }
          }
          else
          {
                 request.setAttribute("notvoted", "notvoted");
                 rd=request.getRequestDispatcher("votedenied.jsp");
          }
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
