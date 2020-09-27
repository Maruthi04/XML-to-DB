package jdbcfinal;
import java.sql.*; 
import java.util.regex.Pattern;
import java.util.*;
public class finalclass 
{ 
	public static boolean isValid(String email)
	 {
	// Validation checking for email input.
	 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	 "[a-zA-Z0-9_+&*-]+)*@" +
	 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	 "A-Z]{2,7}$";

	 Pattern pat = Pattern.compile(emailRegex);
	 if (email == null)
	 return false;
	 return pat.matcher(email).matches();
	 }

	public static void main(String args[]) 
	{ 
		try
		{ 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Scanner sc=new Scanner(System.in);
			// Establishing Connection 
			Connection con = DriverManager.getConnection( 
			"jdbc:oracle:thin:@localhost:1521/XE", "system", "9848"); 
			if (con != null)			 
				System.out.println("Connected");			 
			else			
				System.out.println("Not Connected"); 
//			System.out.println("Enter the Username!");
//			String username=sc.nextLine();
//			System.out.println("Enter the password!");
//			String password=sc.nextLine();
//			Statement st=con.createStatement();
//			String que="select * from login";
//			ResultSet rs=st.executeQuery(que);
//			while(rs.next()) {
//				System.out.println(rs.getString(1)+" "+rs.getString(2));
//			}
//			String q2 = "select * from login WHERE username = '" + username +  
//                    "' AND password = '" + password + "'"; 
//			ResultSet rs1=st.executeQuery(q2);
//			if(rs1.next()) {
//				System.out.println("welcome "+username);
//			}
//			else {
//				System.out.println("Wrong USername/Password");
//			}
			/*
			 Insert statement code!!
			 */
//			String q1 = "insert into login values('" +username+ "', '" +password+  
//                    "')"; 
//			int x = st.executeUpdate(q1); 
//			if (x > 0)             
//			  System.out.println("Successfully Inserted");             
//			else            
//			  System.out.println("Insert Failed"); 
			String first,last,email,passwordd;
			System.out.println("Enter the firstname");
			first=sc.next();
			System.out.println("enter the lastname");
			last=sc.next();
			System.out.println("Enter the email");
			
			email=sc.next();
			System.out.println("Enter the password");
			passwordd=sc.next();
			finalclass fn=new finalclass();
			if(fn.isValid(email)==true) {
			PreparedStatement stmt=con.prepareStatement("insert into user_db values(?,?,?,?)");  
			stmt.setString(1,first);  
			stmt.setString(2,last);  
			stmt.setString(3,email);  
			stmt.setString(4,passwordd);  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			con.close();
			}
			else {
				System.out.println("invalid email");
			}
			 
		} 
		catch(Exception e) 
		{ 
			System.out.println(e); 
		} 
	} 
} 
