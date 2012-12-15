package serpis.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.Scanner;
import java.util.*;

public class TCPServer {

	
	public static void main (String[] args) throws IOException{
		//holaMundo();
		pingPong();
		//connectAndRead();
	}
	
	//METODO PING PONG
	private static void pingPong() throws IOException{
		int port = 10001;
		ServerSocket serverSocket = new ServerSocket(port);
		String mensaje;

			Socket socket = serverSocket.accept();

			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader ( socket.getInputStream() ) );
			PrintWriter printWriter = new PrintWriter ( socket.getOutputStream() );
		
			mensaje = bufferedReader.readLine();
			System.out.println("Mensaje recibido: " + mensaje);
			System.out.println("Mensaje en minusculas: "+mensaje.toLowerCase());
			printWriter.println(mensaje.toLowerCase());
	
			//Cierre
			printWriter.close();
			bufferedReader.close();
			socket.close();
			serverSocket.close();
	}
	
	//METODO CONNECT AND READ
	private static void connectAndRead(String[] args) throws IOException{
			int port = 10001;
			
				ServerSocket serverSocket = new ServerSocket(port);
				Socket socket = serverSocket.accept();

				PrintWriter printWriter = new PrintWriter ( socket.getOutputStream(), true );
						
				String mensaje = "Esto es una prueba";//** Falta la fecha **
				System.out.println("Mensaje enviado: " + mensaje);
		
				printWriter.println(mensaje);
				
				//cierre
				printWriter.close();
				socket.close();
				serverSocket.close();
		}
	
	//METODO HOLAMUNDO
	private static void holaMundo() throws IOException{
		int port=10001;
		
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner((Readable) socket.getOutputStream());
		String line = scanner.nextLine();
		System.out.println("Linea "+line);
		
		socket.close();
		serverSocket.close();
	}
}
