package com.swordsoft.telegram;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swordsoft.utils.CnxUtils;


public class WebHookTelegramApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public WebHookTelegramApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		
		Map<String,String> parameters = new HashMap<String,String>(); 
		
		parameters.put("url", "");
		
		System.out.println("Initiating Servlet ... ");
		
		Map<String,String> params = new HashMap<String,String>();
		
		params.put("url","");
		
		String resp =CnxUtils.initiatePost("https://api.telegram.org/bot320997687:AAEFMyZEhUso49cHojFndUnw3qtY3LnFwZA/setWebhook","" , null, true).toString();
		
		System.out.println(resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
