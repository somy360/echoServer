package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	private static final int port = 40138;
	private Socket client;
	private PrintWriter out;
	private BufferedReader in;

	ClientHandler(Socket clientSocket) throws IOException{
		this.client = clientSocket;
		out = new PrintWriter(clientSocket.getOutputStream(), true);                   
	    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                out.println(inputLine);
	            }
	    }catch (IOException e) {
	    	System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
	    	System.out.println(e.getMessage());
	    }finally {
	    	out.close();
	    	try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
