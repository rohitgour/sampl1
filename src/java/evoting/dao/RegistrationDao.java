
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.*;
import java.sql.*;
import java.util.ArrayList;


public class RegistrationDao {
   private static PreparedStatement ps,ps1,ps2,ps3;
    static
    {
        try 
        {
            ps=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
            ps1=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?,?)");
            ps2=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
            ps3=DBConnection.getConnection().prepareStatement("update user_details set adhar_no=?,password=?,username=?,address=?,city=?,email=?,mobile_no=? where adhar_no=?");
  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static boolean searchUser(String userid)throws SQLException
    {
        ps.setString(1, userid);
        return ps.executeQuery().next();
    }
    public static UserDetails getUserDetails(String userid)throws SQLException
    {
        ps2.setString(1, userid);
        ResultSet rs=ps2.executeQuery();
        UserDetails user=new UserDetails();
        while(rs.next())
        {
            user.setUserid(rs.getString("adhar_no"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setEmail(rs.getString("email"));
            user.setMobile(Long.parseLong(rs.getString("mobile_no"))); 
        }
        return user;
    }
     public static boolean registerUser(UserDetails user)throws SQLException
    {
        ps1.setString(1,user.getUserid());
        ps1.setString(2,user.getPassword());
        ps1.setString(3,user.getUsername());
        ps1.setString(4,user.getAddress());
        ps1.setString(5,user.getCity());
        ps1.setString(6,user.getEmail());
        ps1.setLong(7,user.getMobile());
        ps1.setString(8, "Voter");
        ps1.setString(9, "Y");
        return (ps1.executeUpdate()!=0);
    }      
    public static boolean updateUser(UserDetails user)throws SQLException
    {
        
        ps3.setString(1,user.getUserid());
        ps3.setString(2,user.getPassword());
        ps3.setString(3,user.getUsername());
        ps3.setString(4,user.getAddress());
        ps3.setString(5,user.getCity());
        ps3.setString(6,user.getEmail());
        ps3.setLong(7,user.getMobile());
        ps3.setString(8, user.getUserid());
        return (ps3.executeUpdate()!=0);
    }
}  


