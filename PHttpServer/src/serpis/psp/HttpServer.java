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
		
		final int port=8080;
		
		ServerSocket serverSocket = new ServerSocket(port);
		/*String threadName =Thread.currentThread().getName();
		System.out.println("Nombre del Thread "+threadName);
		System.out.println("Esperando...");*/
		
		while(true){
		Socket socket = serverSocket.accept();//proceso se detiene esperando una conexion
		
		Runnable runnable = new ThreadServer(socket);
		Thread thread = new Thread(runnable);
		thread.start();
		//Thread.sleep(1000);
		}
	}

}
