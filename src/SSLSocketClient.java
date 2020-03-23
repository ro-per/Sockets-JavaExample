import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 * This example demostrates how to use a SSLSocket as client to
 * send a HTTP request and get response from an HTTPS server.
 * It assumes that the client is not behind a firewall
 */

public class SSLSocketClient {

    public static void main(String[] args) throws Exception {
		try {
		    SSLSocketFactory factory =(SSLSocketFactory)SSLSocketFactory.getDefault();
		    SSLSocket socket =(SSLSocket)factory.createSocket("www.tweakers.net", 443);
	
		    socket.startHandshake();
	
		    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
	
		    out.println("GET / HTTP/1.1\\\nHost: www.tweakers.net");
		    out.println();
		    out.flush();
	
		    /* Make sure there were no surprises */
		    if (out.checkError())
		    	System.out.println("SSLSocketClient:  java.io.PrintWriter error");
	
		    /* read response */
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
		    String inputLine;
		    while ((inputLine = in.readLine()) != null)
		    	System.out.println(inputLine);
	
		    in.close();
		    out.close();
		    socket.close();
	
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
}