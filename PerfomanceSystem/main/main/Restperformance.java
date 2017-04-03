package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restperformance {
	@RequestMapping(value="/meanperformance")
    public String getmean() throws ClassNotFoundException, SQLException {
		String final_statement="";
		float meancpu=0,meanmem=0;
		
        Connection con = connectionQuerry();
        Statement st = con.createStatement();
        
		ResultSet r=st.executeQuery("select avg(cpu) FROM performance");
		while (r.next()) {
			meancpu = r.getFloat(1);
		}
		r=st.executeQuery("select avg(cpu) FROM performance");
		while (r.next()) {
			meanmem = r.getFloat(1);
		}
		final_statement = "The average CPU performance of our system is: "+meancpu+"</br>";
		final_statement = final_statement+ "The average memory performance of our system is: "+meanmem;
        return final_statement;
    }
	@RequestMapping(value="/performance")
    public String getperformance() throws ClassNotFoundException, SQLException {
		String final_statement="";
		float mincpu=0,maxcpu=0,minmem=0,maxmem=0;

        Connection con = connectionQuerry();
        Statement st = con.createStatement();
        
		ResultSet r=st.executeQuery("select max(cpu) FROM performance");
		while (r.next()) {
			maxcpu = r.getFloat(1);
		}
		r=st.executeQuery("select min(cpu) FROM performance");
		while (r.next()) {
			mincpu = r.getFloat(1);
		}r=st.executeQuery("select max(memory) FROM performance");
		while (r.next()) {
			maxmem = r.getFloat(1);
		}r=st.executeQuery("select min(memory) FROM performance");
		while (r.next()) {
			minmem = r.getFloat(1);
		}
		final_statement = "The max cpu: "+maxcpu+"</br>"+"The min cpu: "+mincpu+"</br>"
				+"The max memory: "+maxmem+"</br>"+"The min memory: "+minmem+"</br>";
        return final_statement;
    }
    public static java.sql.Connection connectionQuerry() throws ClassNotFoundException
	{
    	String url = "jdbc:mysql://127.0.0.1:3306/client_performance?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    	java.sql.Connection sqlcon = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			sqlcon = DriverManager.getConnection(url, "root", "lk1995sf");
			System.out.println("Remote DB connection established");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Remote server could not be connected");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Remote db connection establishment error");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("False querry");
		}
		return sqlcon;
	}
	
}
