package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	//static variables of our server
	private static final String host = "www.writethehostnamehere.com";
	private static final int port = 1238;
	
	/*
	 * Our main constructor
	 */
	Server(){

		initialise();
		echo();
		
	}
	
	
	/*
	 * Initialise our global variables for use throughout the class
	 */
	private void initialise() {

	}
	
	private void echo() {
        
       // if (args.length != 1) {
        //    System.err.println("Usage: java EchoServer <port number>");
        //    System.exit(1);
       // }
        
        
        try (
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
	
}
