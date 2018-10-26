package jdbc_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class JDBCMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = 
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			//localhost 대신 ip주소 써도 됨
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from test2");
			
			while(rs.next()) {
				String id = rs.getString("id");
				Date reg_data = rs.getDate("REG_DATA");
				int grade = rs.getInt("grade");
				
				System.out.println(id+","+reg_data+","+grade);
			}
			
			rs.close();
			stmt.close();
			con.close();
			//oracle.jdbc.driver.OracleDriver.class
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
