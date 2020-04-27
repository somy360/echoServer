package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	//static variables of our server
	private static final String host = "www.writethehostnamehere.com";
	private static final int port = 40138;
	
	private ArrayList<ClientHandler> clients = new ArrayList<>();
	private ExecutorService pool = Executors.newFixedThreadPool(10);
	
	/*
	 * Our main constructor
	 */
	Server(){

		initialise();
		try {
			echo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * Initialise our global variables for use throughout the class
	 */
	private void initialise() {

	}
	
	private void echo() throws IOException {
        
      ServerSocket serverSocket = new ServerSocket(port);

      while(true) {
          Socket clientSocket = serverSocket.accept();
          ClientHandler clientThread = new ClientHandler(clientSocket);
          clients.add(clientThread);
          pool.execute(clientThread);
      }
    }
	
	private void echoThread(Socket clientSocket){
		try (		
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
