package com.swordsoft.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class CnxUtils {

	final static String authUser = "intranet\\khalfallahs";
	final static String authPassword = "KeepGoing";
	final static String ip_address = "192.168.73.200";
	final static int port = 8080;
	
public static StringBuilder getHttpsContent(String https_url) {
		URL url;
		try {
			url = new URL(https_url);
			
			HttpsURLConnection con = (HttpsURLConnection) url
					.openConnection();
			
			con.connect();

			// dumpl all cert info
			// print_https_cert(con);

			// dump all the content
			return print_content(con);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}


   public static StringBuilder initiatePost(String url , String document, Map<String,String> urlParameters, boolean wproxy )
   {
	   StringBuffer response = new StringBuffer();
	   HttpsURLConnection con ;
	    String strUtlParameters ="" ;	
	    StringBuilder res = new StringBuilder();
	    Proxy proxy = null;
	    if(wproxy)
	    {
			 proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					ip_address, port));
	    }
	   try {
		
		
		   URL obj = new URL(url);
		   if(wproxy)
		   {
			    con = (HttpsURLConnection) obj.openConnection(proxy);
		   }
		   else
		   {
			    con = (HttpsURLConnection) obj.openConnection();
		   }
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			for(String key : urlParameters.keySet())
			{
				if(strUtlParameters.length()>1)
				{
					strUtlParameters = strUtlParameters + "&" + key+"=" + urlParameters.get(key);
				}
				else
				{
					strUtlParameters =  key+"=" + urlParameters.get(key);
				}
			}
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(strUtlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   return res.append(response.toString());
   }


	public static StringBuilder getHttpsContentProxy(String https_url) {

		

		Authenticator.setDefault(new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(authUser, authPassword
						.toCharArray());
			}
		});

		URL url;
		try {

			url = new URL(https_url);

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					ip_address, port));

			HttpsURLConnection con = (HttpsURLConnection) url
					.openConnection(proxy);

			con.connect();

			// dumpl all cert info
			// print_https_cert(con);

			// dump all the content
			return print_content(con);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	
	private static StringBuilder print_content(HttpsURLConnection con) {

		StringBuilder res = new StringBuilder();
		if (con != null) {

			try {

				//System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					//System.out.println(input);
					res.append(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return res;

	}
	
	
	private static void print_https_cert(HttpsURLConnection con) {

		if (con != null) {

			try {

				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("Cipher Suite : " + con.getCipherSuite());
				System.out.println("\n");

				Certificate[] certs = con.getServerCertificates();
				for (Certificate cert : certs) {
					System.out.println("Cert Type : " + cert.getType());
					System.out.println("Cert Hash Code : " + cert.hashCode());
					System.out.println("Cert Public Key Algorithm : "
							+ cert.getPublicKey().getAlgorithm());
					System.out.println("Cert Public Key Format : "
							+ cert.getPublicKey().getFormat());
					System.out.println("\n");
				}

			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
