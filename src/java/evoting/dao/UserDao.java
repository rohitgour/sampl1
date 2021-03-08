
package evoting.dao;
import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDto;
import evoting.dto.UserDetails;
import evoting.dto.UserDto;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class UserDao {
 
 private static PreparedStatement ps,ps2,ps3,ps4,ps5,ps6;
    static
    {
        try 
        {
            
            ps=DBConnection.getConnection().prepareStatement("Select * from user_details where adhar_no=? and password=?");
            ps2=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from user_details,candidate where candidate.user_id=user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
            ps3=DBConnection.getConnection().prepareStatement("update user_details set active='N' where adhar_no=?");
            ps4=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=? and active='Y'");
            ps5=DBConnection.getConnection().prepareStatement("select * from user_details where active='N'");
            ps6=DBConnection.getConnection().prepareStatement("update user_details set active='Y' where adhar_no=?");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error In DB comm:"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static String validateUser(UserDto user)throws SQLException
    {
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs=ps.executeQuery();
        System.out.println("Akash is good boy");
        if(rs.next())
        {
            return (rs.getString(8));
        }
       else
        {
            return null;
        }
    }
    public static boolean isUserActive(String uid)throws SQLException
    {
        ps4.setString(1, uid);
        ResultSet rs=ps4.executeQuery();
        if(rs.next())
            return true;
        return false;
    }
    public static ArrayList<CandidateDto> viewCandidate(String userId)throws SQLException
    {
        ArrayList<CandidateDto> candidate=new ArrayList<>();
        ps2.setString(1, userId);
        ResultSet rs=ps2.executeQuery();
        Blob blob;
        byte[] imageBytes;
        String base64Image;
        CandidateDto cand=new CandidateDto();
        while(rs.next())
        { 
            blob=rs.getBlob("symbol");
            imageBytes=blob.getBytes(1L,(int)blob.length());
            base64Image=Base64.getEncoder().encodeToString(imageBytes);
            candidate.add(new CandidateDto(rs.getString(1),rs.getString(2),rs.getString(3),base64Image));
        }
        return candidate;
    }
      public static ArrayList<String> getUserId()throws SQLException
     {
         ResultSet rs;
         rs = DBConnection.getConnection().createStatement().executeQuery("select adhar_no from user_details where user_type not in 'ADMIN' and active='Y' ORDER BY adhar_no ASC");
         ArrayList<String> id=new ArrayList<>(); 
         while(rs.next())
         {
             id.add(rs.getString(1));
         }
         return id;   
     }
      public static boolean removeUser(String uid)throws SQLException
      {      
          ps3.setString(1, uid);
          return (ps3.executeUpdate()!=0); 
      }
      public static ArrayList<UserDetails> getAllUserDetails()throws SQLException
    {
        ResultSet rs=DBConnection.getConnection().createStatement().executeQuery("select * from user_details ORDER BY adhar_no ASC");
        ArrayList<UserDetails> userList=new ArrayList<>();
        while(rs.next())
        {
            UserDetails user=new UserDetails();
            user.setUserid(rs.getString("adhar_no"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setEmail(rs.getString("email"));
            user.setMobile(Long.parseLong(rs.getString("mobile_no")));
            user.setIsActive(rs.getString("active"));
            userList.add(user);
        }
        return userList;
    }
       public static ArrayList<UserDetails> activateUser()throws SQLException
    {
        ArrayList<UserDetails> userList=new ArrayList<>();
        ResultSet rs=ps5.executeQuery();
        while(rs.next())
        {
            UserDetails user=new UserDetails();
            user.setUserid(rs.getString("adhar_no"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setEmail(rs.getString("email"));
            user.setMobile(Long.parseLong(rs.getString("mobile_no")));
            user.setIsActive(rs.getString("active"));
            userList.add(user);
        }
        return userList;
    }
       public static boolean getActivateUser(String uid)throws SQLException
       {
          ps6.setString(1, uid);
          return (ps6.executeUpdate()!=0); 
       }
}


