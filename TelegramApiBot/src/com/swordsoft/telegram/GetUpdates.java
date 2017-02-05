package com.swordsoft.telegram;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.swordsoft.utils.CnxUtils;

public class GetUpdates extends TimerTask {

	private static DataSource ds;

	@Override
	public void run() {
		Context ctx;
		try {
			  ctx = new InitialContext();
		
		     ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQLDS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	Map<String,String> parameters = new HashMap<String,String>(); 
	
	parameters.put("url", "");
	
	System.out.println("Initiating Servlet ... ");
	
	Map<String,String> params = new HashMap<String,String>();
	
	params.put("limit","20");
	
	String resp =CnxUtils.initiatePost("https://api.telegram.org/bot320997687:AAEFMyZEhUso49cHojFndUnw3qtY3LnFwZA/getUpdates","" , params, "",false).toString();
	
	System.out.println(resp);
	
	try {
		java.sql.Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into log_table(log_text) values(?)" );
		ps.setString(1, "update "+resp);
		ps.executeUpdate();
		ps.close();
		con.close();
		
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
	}

}
