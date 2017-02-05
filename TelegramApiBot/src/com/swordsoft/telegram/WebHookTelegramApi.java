package com.swordsoft.telegram;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.Connection;
import com.swordsoft.telegram.entity.Message;
import com.swordsoft.telegram.entity.ResponseMessage;
import com.swordsoft.telegram.entity.Update;
import com.swordsoft.utils.CnxUtils;

import sun.misc.IOUtils;


public class WebHookTelegramApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource ds;
	
    public WebHookTelegramApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		
	
			Context ctx;
			try {
				  ctx = new InitialContext();
			
			     ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQLDS");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
				java.sql.Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into log_table(log_text) values(?)" );
				ps.setString(1, "initialisation "+(new Date()));
				ps.executeUpdate();
				ps.close();
				con.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> parameters = new HashMap<String,String>(); 
		
		parameters.put("url", "");
		
		System.out.println("Initiating Servlet ... ");
		
		Map<String,String> params = new HashMap<String,String>();
		
		params.put("url","https://telegram-seif12.rhcloud.com/AAEFMyZEhUso49cHojFndUnw3qtY3LnFwZA");
		
		String resp =CnxUtils.initiatePost("https://api.telegram.org/bot320997687:AAEFMyZEhUso49cHojFndUnw3qtY3LnFwZA/setWebhook","" , params,"", false).toString();
		
		System.out.println(resp);
		
		/*Timer t = new Timer();
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.SECOND, 15);
		//Timer task t =;
		t.scheduleAtFixedRate( new GetUpdates(), gc.getTime(), 15000);*/
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				java.sql.Connection con = ds.getConnection();
				String update="";
				String line = "";
				
				PreparedStatement ps = con.prepareStatement("insert into log_table(log_text) values(?)" );
				
				update = org.apache.commons.io.IOUtils.toString(request.getInputStream());
				ps.setString(1, "Post Request " + update );
				ps.executeUpdate();
			
				Gson gson = new Gson();
				Update msg = gson.fromJson(update, Update.class);
				
				
				ResponseMessage respMsg = new ResponseMessage();
				respMsg.setChatId(msg.getMessage().getChat().getId());
				respMsg.setText("Reply to "+msg.getMessage().getText());
				respMsg.setReplyToMessageId(msg.getMessage().getMessageId());
				String reply = gson.toJson(respMsg);
				ps.setString(1, "Post Reply "+reply);
				ps.executeUpdate();
			
				Map<String,String> params = new HashMap<String,String>();
				String resp =CnxUtils.initiatePost("https://api.telegram.org/bot320997687:AAEFMyZEhUso49cHojFndUnw3qtY3LnFwZA/sendMessage", reply, params,"json", false).toString();
				ps.setString(1, "Msg Reply "+resp);
				ps.executeUpdate();
				
				ps.close();
				con.close();
				
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		try{
				java.sql.Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into log_table(log_text) values(?)" );
				ps.setString(1, "Post Request " + e.getMessage() );
				ps.executeUpdate();
				ps.close();
				con.close();
		
			}
		catch(Exception ex)
		{
			
		}
	

}}}
