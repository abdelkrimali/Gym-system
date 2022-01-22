package dbase;

import java.sql.*;

public class Go {
		private static String url;
	    private static Connection con;
	    public static void SetUrl(){
	    	url="jdbc:mysql://localhost:3306/gymkass"
	                +"?useUnicode=true&characterEncoding=UTF-8";
	    }
	    public static void Setconnection()
	    {
	    	 try{
	    	        SetUrl();
	    	          con=DriverManager.getConnection(url,"root","");
	    	        }
	    	        catch(SQLException ex){
	    	            ex.printStackTrace();
	    	        }
	    }
	    public static boolean Chekuserandpass(String user,String pass)
	    {
	        try
	        {
	            Setconnection();
	            Statement stmt=con.createStatement();
	            String check="select * from utilisateur where nom_utilisateur='"+user+"' and mot_pass='"+pass+"'";
	            ResultSet rs=stmt.executeQuery(check);
	            while(rs.next())
	            {
	                return true;
	            }
	            
	        }
	        catch(SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	        return false;
	    }
	    public static boolean runNonQuery(String sqlstatement){
	        try{
	            Setconnection();
	            Statement stmt=con.createStatement();
	            stmt.execute(sqlstatement);
	            con.close();
	            return true;
	            
	        }catch(SQLException ex){
	            ex.printStackTrace();
	            
	        }
	        return false;
	    }
	    public static String GetAutoNumber(String tablename, String colomnname){
	        try{
	           Setconnection();
	            Statement stmt=con.createStatement();
	            String select="select max("+colomnname+") +1 as autonumber "
	                    +" from "+tablename;
	            ResultSet rs=stmt.executeQuery(select);
	            String num="";
	            while(rs.next()){
	                num=rs.getString("autonumber");
	            }
	            if(num==null ||"".equals(num))
	            {
	                return "1";
	            }
	            else
	            {
	                return num ;
	            }
	            
	        }
	        catch(SQLException ex){
	           new Tools().msgbox(ex.getMessage());
	            return "0";
	        }
	    }
	    
	    
	    
	    }
