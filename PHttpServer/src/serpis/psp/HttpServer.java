package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)throws IOException, InterruptedException {
		
		int port=8080;
		
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Esperando...");
		
		while(true){
		Socket socket = serverSocket.accept();//proceso se detiene esperando una conexion
		
		
		HttpServerHilo h = new HttpServerHilo(socket);
		h.run();
		}
	}

}
