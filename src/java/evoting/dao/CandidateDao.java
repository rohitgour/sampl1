
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.AddCandidateDto;
import evoting.dto.CandidateDetails;
import evoting.dto.UpdateCandidateDto;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

public class CandidateDao {
   private static Statement st,st2,st3;
   private static PreparedStatement ps,ps2,ps3,ps4,ps5,ps6;
    static
    {
        try 
        {
            st=DBConnection.getConnection().createStatement();
            st2=DBConnection.getConnection().createStatement();
            st3=DBConnection.getConnection().createStatement();
            ps=DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
            ps2=DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
            ps3=DBConnection.getConnection().prepareStatement("Select * from candidate where candidate_id=?");
            ps5=DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
          //  ps6=DBConnection.getConnection().prepareStatement("select symbol from candidate where user_id=?");
        
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    public static String getNewCandidateId()throws SQLException
    {
        ResultSet rs=st.executeQuery("select count(*) from candidate");
        if(rs.next())
            return "C"+(100+(rs.getInt(1)+1));
        else
            return "C101";
    }
    public static String getUsernameById(String uid)throws SQLException
    {
        ps.setString(1, uid);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }

     public static ArrayList<String> getCity()throws SQLException
    {
        ArrayList<String> city=new ArrayList<>();
        ResultSet rs=st2.executeQuery("select distinct city from user_details");
        while(rs.next())
            city.add(rs.getString(1));
        return city;
    }
     public static boolean addCandidate(AddCandidateDto candidate)throws SQLException, IOException
     {
         System.out.println("Add Canidate = "+candidate.toString());
         ps2.setString(1, candidate.getCandidateId());
         ps2.setString(2, candidate.getParty());
         ps2.setString(3, candidate.getUserId());
         ps2.setBinaryStream(4, candidate.getSymbol(), candidate.getSymbol().available());
         ps2.setString(5, candidate.getCity()); 
         int ans=ps2.executeUpdate();
         return(ans!=0);
     }
     public static ArrayList<String> getCandidateId()throws SQLException
     {
         ResultSet rs=st3.executeQuery("Select candidate_id from candidate");
         ArrayList<String>id=new ArrayList<>(); 
         while(rs.next())
         {
             id.add(rs.getString(1));
         }
         return id;   
     }
     public static CandidateDetails getDetailsById(String cid)throws SQLException
     {
        ps3.setString(1, cid);
        ResultSet rs=ps3.executeQuery();
        CandidateDetails candidate=new CandidateDetails();
        Blob blob;
        byte[] imageBytes;
        String base64Image;
        if(rs.next())
        {
            blob=rs.getBlob("symbol");
            imageBytes=blob.getBytes(1L,(int)blob.length());
            Encoder ec=Base64.getEncoder();
            base64Image=ec.encodeToString(imageBytes);
            candidate.setCandidateId(cid);
            candidate.setParty(rs.getString("party"));
            candidate.setUserId(rs.getString("user_id"));
            candidate.setSymbol(base64Image);
            candidate.setCity(rs.getString("city"));  
        }
        return candidate;
     }
     public static boolean updateCandidate(UpdateCandidateDto candidate)throws SQLException, IOException
     { 
        // System.out.println("In Dao Symbol Chnages = "+candidate.isFlag());
         if(!candidate.isFlag())
         {
             
          //  System.out.println("Candidate object if= "+candidate.toString());
           //System.out.println("Only 3");
//            System.out.println("1 ="+candidate.getCity());
//            System.out.println("2 ="+candidate.getParty());
//            System.out.println("3 ="+candidate.getUserId());
            ps4=DBConnection.getConnection().prepareStatement("update candidate set city=?,party=? where user_id=?"); 
            ps4.setString(1, candidate.getCity());
            ps4.setString(2, candidate.getParty());
            ps4.setString(3, candidate.getUserId());
         }
         else
         {
//             System.out.println("Candidate object if= "+candidate.toString());
//             System.out.println("All 4");
//             System.out.println("1 ="+candidate.getCity());
//             System.out.println("2 ="+candidate.getParty());
//             System.out.println("3 ="+candidate.getSymbol());
//             System.out.println("4 ="+candidate.getUserId());
             ps4=DBConnection.getConnection().prepareStatement("update candidate set city=?,party=?,symbol=? where user_id=?");         
             ps4.setString(1, candidate.getCity());
             ps4.setString(2, candidate.getParty()); 
             ps4.setBinaryStream(3, candidate.getSymbol(), candidate.getSymbol().available());
             ps4.setString(4, candidate.getUserId());
         }
            
         int ans=ps4.executeUpdate();
         System.out.println("ans = "+ans);
         return(ans!=0);
     }
   public static boolean removeCandidate(String cid)throws SQLException
   {
       System.out.println("Candidate id in DAO = "+cid);
        ps5.setString(1, cid);
        int ans=ps5.executeUpdate();
        return(ans!=0);
   }
//   public static String getImage(String uid)throws SQLException
//   {
//       
//        ps6.setString(1, uid);
//        ResultSet rs=ps6.executeQuery();
//        Blob blob;
//        byte[] imageBytes;
//        String base64Image = null;
//        if(rs.next())
//        {
//            blob=rs.getBlob("symbol");
//            imageBytes=blob.getBytes(1L,(int)blob.length());
//            Encoder ec=Base64.getEncoder();
//            base64Image=ec.encodeToString(imageBytes);
//        }
//        return base64Image;
//   }
}



          
           // byte[] bytes = base64Image.getBytes("UTF-8");
            //String encoded = Base64.getEncoder().encodeToString(bytes);
            //byte[] decoded = Base64.getDecoder().decode(encoded);
            //InputStream targetStream = new ByteArrayInputStream(decoded);
           // ps4.setBinaryStream(3, targetStream , targetStream .available());