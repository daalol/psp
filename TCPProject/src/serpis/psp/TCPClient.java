package serpis.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class TCPClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//holaMundo();
		//pingPong();
		//connectAndRead();
		connectUpvWeb();
	}
	
	//METODO PING-PONG
private static void pingPong() throws IOException{
	
	InetAddress inetAddress = InetAddress.getByName("127.0.0.1"); 		
	int port = 10001;
	Socket socket = new Socket(inetAddress,port);
	String mensaje;
	
		InputStreamReader inputStreamReader = new InputStreamReader(System.in); 
		BufferedReader teclado = new BufferedReader(inputStreamReader);
		
		System.out.print("Mensaje a enviar: ");
		mensaje=teclado.readLine();
	
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		
		printWriter.println( mensaje ); 
		
		
		java.io.InputStream inputStream =  socket.getInputStream();
		BufferedReader	bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		mensaje = bufferedReader.readLine();
		System.out.println("Mensaje que a devuelto el servidor: " + mensaje);

	
		printWriter.close();
		bufferedReader.close();
		socket.close();

	}

//METODO PING-PONG SOBREESCRITO
private static void pingPong(int puerto, InetAddress inetAddress) throws IOException{

	Socket socket = new Socket(inetAddress,puerto);
	String mensaje;
	
		InputStreamReader inputStreamReader = new InputStreamReader(System.in); 
		BufferedReader teclado = new BufferedReader(inputStreamReader);
		
		System.out.print("Mensaje a enviar: ");
		mensaje=teclado.readLine();
	
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		
		printWriter.println( mensaje ); 
		
		
		java.io.InputStream inputStream =  socket.getInputStream();
		BufferedReader	bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		mensaje = bufferedReader.readLine();
		System.out.println("Mensaje que a devuelto el servidor: " + mensaje);

	
		printWriter.close();
		bufferedReader.close();
		socket.close();

}

	//METODO CONNECT AND READ
	//para probarlo desde el terminal $java TCPClient arg1 arg2
private static void connectAndRead(String[] args) throws UnknownHostException, IOException{
	//debe conectarse al host del primer parametro y al puerto que se le indica como segundo parametro
	String inetAddress = args[0]; 		
	int port = Integer.parseInt(args[1]);

		Socket socket = new Socket(inetAddress,port);

		java.io.InputStream inputStream = socket.getInputStream();
		Scanner scanner = new Scanner (inputStream);
		
		while (scanner.hasNextLine())
			System.out.println("Mensaje recibido del servidor: " +scanner.nextLine());
		
		socket.close();
}

	//METODO HOLAMUNDO
	private static void holaMundo() throws IOException{
		InetAddress inetAddres = InetAddress.getByName("127.0.0.1");
		String host=null; //localhost
		int port= 10001;
		Socket socket = new Socket(host,port);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		printWriter.print("Hola desde TCPClient");
		
		printWriter.close();
		socket.close();
	}
	
	//METODO ConnectUpvWeb
	private static void connectUpvWeb() throws IOException{
		String host ="www.upv.es";
		int puerto = 80;
		Socket socket = new Socket(host, puerto);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream, true);//autoflush = true
		
		java.io.InputStream inputStream = socket.getInputStream();
		Scanner scanner = new Scanner(inputStream);
		
		String linea1 = "GET /index.html HTTP/1.0";
		String linea2 = "";
		printWriter.println(linea1);
		printWriter.println(linea2);
		
		while(scanner.hasNextLine())
			System.out.println(scanner.nextLine());
		
		//cierre
		printWriter.close();
		scanner.close();
		socket.close();
		
	}

}
